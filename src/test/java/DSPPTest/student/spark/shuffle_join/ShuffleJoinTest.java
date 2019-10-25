package DSPPTest.student.spark.shuffle_join;

import DSPPCode.spark.shuffle_join.ShuffleJoinImpl;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.Parser.LineParser;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;
import scala.Tuple2;

import static DSPPTest.util.FileOperator.*;
import static DSPPTest.util.Verifier.verifyKV;

public class ShuffleJoinTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFolder = root + "/spark/shuffle_join/input";
        String personFile = inputFolder + "/persons";
        String orderFile = inputFolder + "/orders";
        String outputFolder = outputRoot + "/spark/shuffle_join";
        String outputFile = outputFolder + "/part-00000";
        String answerFile = root + "/spark/shuffle_join/answer";

        // 删除旧输出
        deleteFolder(outputFolder);

        // 执行
        JavaSparkContext sc = new JavaSparkContext("local", "Shuffle Join");

        JavaPairRDD<Long, String> personRdd = sc.textFile(personFile).mapToPair((String s) -> {
            String[] fields = s.split(",");
            return new Tuple2<>(Long.parseLong(fields[0]), fields[1] + "," + fields[2]);
        });

        JavaPairRDD<Long, String> orderRdd = sc.textFile(orderFile).mapToPair((String s) -> {
            String[] fields = s.split(",");
            return new Tuple2<>(Long.parseLong(fields[2]), fields[1]);
        });

        new ShuffleJoinImpl().join(personRdd, orderRdd).saveAsTextFile(outputFolder);

        sc.close();

        // 检验结果
        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new LineParser());

        System.out.println("恭喜通过~");
    }

}

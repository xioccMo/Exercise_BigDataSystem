package DSPPTest.student.spark.warm_up;

import DSPPCode.spark.warm_up.WordCountImpl;
import DSPPTest.student.TestTemplate;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class WordCountTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFile = root + "/spark/warm_up/input";
        String outputFolder = outputRoot + "/spark/warm_up";
        String outputFile = outputFolder + "/part-00000";
        String answerFile = root + "/spark/warm_up/answer";

        // 删除旧输出
        deleteFolder(outputFolder);

        // 执行
        JavaSparkContext sc = new JavaSparkContext("local", "Word Count");
        JavaRDD<String> lines = sc.textFile(inputFile).cache();
        JavaPairRDD<String, Integer> counter = new WordCountImpl().wordcount(lines);
        counter.saveAsTextFile(outputFolder);
        System.out.println(counter.collect());
        sc.close();

        // 检验结果
        verifyKV(readFile2String(outputFile), readFile2String(answerFile));

        System.out.println("恭喜通过~");
    }

}


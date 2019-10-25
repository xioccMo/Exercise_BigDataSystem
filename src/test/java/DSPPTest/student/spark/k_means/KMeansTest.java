package DSPPTest.student.spark.k_means;

import DSPPCode.spark.k_means.KMeansImpl;
import DSPPTest.student.TestTemplate;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class KMeansTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFile = root + "/spark/k_means/input";
        String answerFile = root + "/spark/k_means/answer";

        // 执行
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("KMeansOneStep");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> data = sc.textFile(inputFile);
        String output = new KMeansImpl().compute(data, 3, 0.05);
        sc.close();

        // 检验结果
        verifyKV(output, readFile2String(answerFile));

        System.out.println("恭喜通过~");
    }

}

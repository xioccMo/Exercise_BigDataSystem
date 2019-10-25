package DSPPTest.student.spark.pagerank;

import DSPPCode.spark.pagerank.CalculateRankImpl;
import DSPPCode.spark.pagerank.FlatMapToPairImpl;
import DSPPTest.student.TestTemplate;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;
import scala.Tuple2;

import java.util.regex.Pattern;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class PageRankTest extends TestTemplate {

    private static final Pattern SPACES = Pattern.compile("\\s+");
    private static final int LOOP = 10;

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFile = root + "/spark/pagerank/input";
        String answerFile = root + "/spark/pagerank/answer";
        String outputFolder = outputRoot + "/spark/pagerank";
        String outputFile = outputFolder + "/part-00000";

        // 删除旧输出
        deleteFolder(outputFolder);

        // 执行
        SparkSession spark = SparkSession.builder().master("local").appName("PageRank").getOrCreate();
        JavaRDD<String> lines = spark.read().textFile(inputFile).javaRDD();
        JavaPairRDD<String, Iterable<String>> links = lines.mapToPair(s -> {
            String[] parts = SPACES.split(s);
            return new Tuple2<>(parts[0], parts[1]);
        }).distinct().groupByKey().cache();

        JavaPairRDD<String, Double> ranks = links.mapValues(rs -> 1.0);

        for (int current = 0; current < LOOP; current++) {
            JavaPairRDD<String, Double> contribs = links.join(ranks).values().flatMapToPair(new FlatMapToPairImpl());
            ranks = contribs.groupByKey().map(new CalculateRankImpl()).mapToPair(s -> s);
        }
        ranks.saveAsTextFile(outputFolder);

        // 检验结果
        verifyKV(readFile2String(outputFile), readFile2String(answerFile), 0.01);

        System.out.println("恭喜通过~");
    }

}

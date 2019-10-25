package DSPPCode.spark.warm_up;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Java 答案示例
 */
public class WordCountImpl extends WordCount {

    public JavaPairRDD<String, Integer> wordcount(JavaRDD<String> lines) {
        return lines
                .flatMap((String line) -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair((String word) -> new Tuple2<>(word, 1))
                .reduceByKey((Integer integer, Integer integer2) -> integer + integer2);
    }

}

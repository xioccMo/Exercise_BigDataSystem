package DSPPCode.spark.warm_up;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

abstract public class WordCount {

    //TODO 请完成该函数
    abstract public JavaPairRDD<String, Integer> wordcount(JavaRDD<String> lines);

}

package DSPPCode.spark.warm_up

import org.apache.spark.api.java.{JavaPairRDD, JavaRDD}

/**
  * Scala 答案示例
  * 注意: 此处为避免与 Java 示例冲突, 临时将类名改为 XXXScalaImpl. 学生使用 Scala 做题时切勿如此命名
  */
class WordCountScalaImpl extends WordCount {

  override def wordcount(lines: JavaRDD[String]): JavaPairRDD[String, Integer] = {
    val rdd = lines.rdd
        .flatMap(_.split(" "))
        .map((_, new Integer(1)))
        .reduceByKey(_ + _)
    JavaPairRDD.fromRDD(rdd)
  }

}

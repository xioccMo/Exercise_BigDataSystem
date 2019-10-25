package DSPPCode.spark.shuffle_join;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

abstract public class ShuffleJoin {

    /**
     * TODO 请完成该函数
     *
     * 连接 Persons 表和 Orders 表
     *
     * @param personRdd Person 数据, 键为 Id_P, 值为 LastName 和 FirstName, 由 "," 分隔 (如 键: 1, 值: "Adams,John")
     * @param orderRdd Order 数据, 键为 Id_P, 值为 OrderNo (如 键: 1, 值: "22456")
     * @return 返回代表连接结果的 RDD, 字段间由 "," 分隔 (如 "Adams,John,24562")
     */
    abstract public JavaRDD<String> join(JavaPairRDD<Long, String> personRdd, JavaPairRDD<Long, String> orderRdd);

}

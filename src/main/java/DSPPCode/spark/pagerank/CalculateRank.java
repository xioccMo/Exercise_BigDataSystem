package DSPPCode.spark.pagerank;

import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

abstract public class CalculateRank implements Function<Tuple2<String, Iterable<Double>>, Tuple2<String, Double>> {

    /**
     * 公式中的 q
     */
    final static Double FACTOR = 0.85;

    /**
     * TODO 请完成该函数
     *
     * 计算新的 rank 值
     *
     * @param weight (节点 ID, 该节点所有入边传递来的权值) 键值对
     * @return (节点 ID, 该节点新的 rank 值) 键值对
     */
    @Override
    abstract public Tuple2<String, Double> call(Tuple2<String, Iterable<Double>> weight) throws Exception;

}

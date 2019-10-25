package DSPPCode.spark.pagerank;

import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

import java.util.Iterator;

public abstract class FlatMapToPair implements PairFlatMapFunction<Tuple2<Iterable<String>, Double>, String, Double> {

    /**
     * TODO 请完成该函数
     *
     * 生成 (节点 ID, 某一出边对其影响) 键值对
     *
     * @param outsideWeight (一个节点所有出边指向的节点 ID, 该节点当前的 rank 值) 键值对
     * @return (出边指向的节点 ID, 出边传递出去的 rank 值) 键值对
     */
    @Override
    abstract public Iterator<Tuple2<String, Double>> call(Tuple2<Iterable<String>, Double> outsideWeight) throws Exception;

}

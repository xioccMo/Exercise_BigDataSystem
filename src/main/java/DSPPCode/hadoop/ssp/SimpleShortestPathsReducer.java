package DSPPCode.hadoop.ssp;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

abstract public class SimpleShortestPathsReducer extends Reducer<Text, Text, Text, Text> {

    /**
     * TODO 请完成该函数
     * -
     * 修改每个节点的最短路径距离
     * 每次迭代都要修改，直到所有节点的最短路径距离不再发生改变
     * {B, {10 (C,1) (D,2)}, {8}, {12}}   =>  B, 8 (C,1) (D,2)
     */
    @Override
    abstract public void reduce(Text nodeKey, Iterable<Text> values, Context context)
            throws IOException, InterruptedException;
    /**
     * 通过自定义计数器判断迭代是否终止
     * 有新的最小值,说明还在进行优化计算，需要继续循环计算
     * 如果新的最小值小于原来的距离，说明更新还在继续
     */
    static String INF = "inf";
    static void isChange(Node node, String min, Context context) {
        if (!min.equals(INF)) {
            if (node.getDistance().equals(INF)) {
                context.getCounter(Node.eInf.COUNTER).increment(1L);
            } else {
                if (Integer.parseInt(node.getDistance()) > Integer.parseInt(min)) {
                    context.getCounter(Node.eInf.COUNTER).increment(1L);
                }
            }
        }
    }
}

package DSPPCode.hadoop.ssp;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

abstract public class SimpleShortestPathsMapper extends Mapper<Text, Text, Text, Text> {

    /**
     * TODO 请完成该函数
     * -
     * 1. 填写默认最短路径距离
     * 2. 计算当前节点经过 所有已有临时最短路径的节点 到A节点的 所有路径距离
     */
    @Override
    abstract public void map(Text key, Text value, Context context) throws IOException, InterruptedException;

}

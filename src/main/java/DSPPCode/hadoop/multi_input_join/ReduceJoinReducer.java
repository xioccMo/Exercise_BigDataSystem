package DSPPCode.hadoop.multi_input_join;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 根据 OrderMapper 和 PersonMapper 传递过来的数据进行 Join 操作
 */
public abstract class ReduceJoinReducer extends Reducer<Text, TextPair, Text, NullWritable> {
    @Override
    protected abstract void reduce(Text key, Iterable<TextPair> values, Context context) throws IOException, InterruptedException;
}

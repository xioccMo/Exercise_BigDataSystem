package DSPPCode.hadoop.multi_join;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

abstract public class MultiJoinReducer extends Reducer<Text, Text, Text, Text> {
    protected int row = 0;
    abstract public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException;
}

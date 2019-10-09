package DSPPCode.hadoop.select;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

abstract public class SelectReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
    abstract public void reduce(Text key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException;
}

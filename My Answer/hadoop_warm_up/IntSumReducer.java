package DSPPCode.hadoop.warm_up;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

abstract public class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    IntWritable result = new IntWritable();

    //TODO 请完成该函数
    abstract public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException;

}

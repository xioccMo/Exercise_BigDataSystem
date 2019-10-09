package DSPPCode.hadoop.warm_up;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

abstract public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

    final static IntWritable one = new IntWritable(1);

    Text word = new Text();

    //TODO 请完成该函数
    abstract public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException;

}

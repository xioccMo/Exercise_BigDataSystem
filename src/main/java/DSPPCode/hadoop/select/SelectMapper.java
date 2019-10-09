package DSPPCode.hadoop.select;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

abstract public class SelectMapper extends Mapper<Object, Text, Text, NullWritable> {
    abstract public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException;
}




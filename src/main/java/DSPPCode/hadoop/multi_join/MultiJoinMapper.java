package DSPPCode.hadoop.multi_join;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

abstract public class MultiJoinMapper extends Mapper<Object, Text, Text, Text> {
    abstract public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException;
}




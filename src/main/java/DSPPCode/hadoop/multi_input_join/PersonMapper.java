package DSPPCode.hadoop.multi_input_join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 处理 Person 表中的数据，根据结果集保留对应的数据，传递到 Reducer 端
 */
public abstract class PersonMapper extends Mapper<LongWritable, Text, Text, TextPair> {
    /**
     * 表数据分割符
     */
    protected static final String DELIMTER = "\t";

    @Override
    protected abstract void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException;
}

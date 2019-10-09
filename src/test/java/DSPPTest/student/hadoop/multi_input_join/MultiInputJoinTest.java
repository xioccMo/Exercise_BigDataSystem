package DSPPTest.student.hadoop.multi_input_join;


import DSPPCode.hadoop.multi_input_join.*;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.LineParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class MultiInputJoinTest extends TestTemplate {

    private static final String BASE_PATH = "/hadoop/multi_input_join/";

    @Test
    public void test() throws Exception {

        String personsInput = root + BASE_PATH + "input/persons.txt";
        String ordersInput = root + BASE_PATH + "input/orders.txt";
        String outputFolder = outputRoot + BASE_PATH;
        String outputFile = outputFolder + "part-r-00000";
        String answerFile = root + BASE_PATH + "answer";

        deleteFolder(outputFolder);

        Job job = Job.getInstance(new Configuration(), getClass().getSimpleName());
        job.setJarByClass(getClass());

        MultipleInputs.addInputPath(job, new Path(personsInput), TextInputFormat.class, PersonMapperImpl.class);
        MultipleInputs.addInputPath(job, new Path(ordersInput), TextInputFormat.class, OrderMapperImpl.class);
        FileOutputFormat.setOutputPath(job, new Path(outputFolder));

        job.setReducerClass(ReduceJoinReducerImpl.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TextPair.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.waitForCompletion(false);

        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new LineParser());
    }
}

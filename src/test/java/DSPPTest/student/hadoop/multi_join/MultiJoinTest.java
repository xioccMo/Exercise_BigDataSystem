package DSPPTest.student.hadoop.multi_join;

import DSPPCode.hadoop.multi_join.MultiJoinMapperImpl;
import DSPPCode.hadoop.multi_join.MultiJoinReducerImpl;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.KVParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class MultiJoinTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        //set dir
        String inputFolder = root + "/hadoop/multi_join/input";
        String outputFolder = outputRoot + "/hadoop/multi_join";
        String outputFile = outputFolder + "/part-r-00000";
        String answerFile = root + "/hadoop/multi_join/answer";

        //delete old dirl
        deleteFolder(outputFolder);

        //do
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(MultiJoinTest.class);

        job.setMapperClass(MultiJoinMapperImpl.class);
        job.setReducerClass(MultiJoinReducerImpl.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(inputFolder));
        FileOutputFormat.setOutputPath(job, new Path(outputFolder));
        job.waitForCompletion(false);

        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new KVParser("\t"));
    }
}

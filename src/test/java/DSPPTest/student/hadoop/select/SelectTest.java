package DSPPTest.student.hadoop.select;

import DSPPCode.hadoop.select.SelectMapperImpl;
import DSPPCode.hadoop.select.SelectReducerImpl;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.KVParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class SelectTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        //set dir
        String inputFolder = root + "/hadoop/select/input";
        String outputFolder = outputRoot + "/hadoop/select";
        String outputFile = outputFolder + "/part-r-00000";
        String answerFile = root + "/hadoop/select/answer";

        //delete old dirl
        deleteFolder(outputFolder);

        //do
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(SelectTest.class);
        job.setMapperClass(SelectMapperImpl.class);
        job.setReducerClass(SelectReducerImpl.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job, new Path(inputFolder));
        FileOutputFormat.setOutputPath(job, new Path(outputFolder));
        job.waitForCompletion(false);

        //check result
        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new KVParser("\t"));
    }
}

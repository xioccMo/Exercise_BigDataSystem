package DSPPTest.student.hadoop.warm_up;

import DSPPCode.hadoop.warm_up.IntSumReducerImpl;
import DSPPCode.hadoop.warm_up.TokenizerMapperImpl;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.KVParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class WordCountTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFile = root + "/hadoop/warm_up/input";
        String outputFolder = outputRoot + "/hadoop/warm_up";
        String outputFile = outputFolder + "/part-r-00000";
        String answerFile = root + "/hadoop/warm_up/answer";

        // 删除旧输出
        deleteFolder(outputFolder);

        // 执行
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(WordCountTest.class);
        job.setMapperClass(TokenizerMapperImpl.class);
        job.setReducerClass(IntSumReducerImpl.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(inputFile));
        FileOutputFormat.setOutputPath(job, new Path(outputFolder));
        job.waitForCompletion(false);

        // 检验结果
        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new KVParser("\t"));

        System.out.println("恭喜通过~");
    }

}

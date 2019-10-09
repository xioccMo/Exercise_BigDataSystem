package DSPPTest.student.hadoop.ssp;

import DSPPCode.hadoop.ssp.Node;
import DSPPCode.hadoop.ssp.SimpleShortestPathsMapperImpl;
import DSPPCode.hadoop.ssp.SimpleShortestPathsReducerImpl;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.KVParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class SimpleShortestPathsTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFile = root + "/hadoop/ssp/input";
        String outputFolder = outputRoot + "/hadoop/ssp";
        String answerFile = root + "/hadoop/ssp/answer";

        // 删除旧输出
        deleteFolder(outputFolder);

        Configuration conf = new Configuration();
        int iter = 0; // 记录迭代次数
        long num = 1; // 标识所有节点distance是否发生改变

        // 执行
        while (num > 0) {
            iter++;
            conf.setInt("run.counter", iter);
            Job job = Job.getInstance(conf);
            job.setJarByClass(SimpleShortestPathsTest.class);
            job.setMapperClass(SimpleShortestPathsMapperImpl.class);
            job.setReducerClass(SimpleShortestPathsReducerImpl.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            job.setInputFormatClass(KeyValueTextInputFormat.class);

            // 设置迭代输入与输出文件路径
            if (iter == 1) {
                // 第一次计算从给定文件夹读数据
                FileInputFormat.addInputPath(job, new Path(inputFile));
            } else {
                // 从上次输出的文件读数据
                FileInputFormat.addInputPath(job, new Path(outputFolder + "/sp" + (iter - 1)));
            }
            // 输出文件地址
            Path outPath = new Path(outputFolder + "/sp" + iter);
            // 输出地址绑定job
            FileOutputFormat.setOutputPath(job, outPath);
            // 任务是否完成
            boolean completion = job.waitForCompletion(true);
            // 完成
            if (completion) {
                // 获取num值
                Counter counter = job.getCounters().findCounter(Node.eInf.COUNTER);
                num = counter.getValue();
                // 如果num为0表示所有节点都没有更新,说明迭代任务已经结束
                if (num == 0) {
                    System.out.println("执行了" + iter + "次, 完成最短路径的计算");
                }
            }
        }

        // 检验结果
        verifyKV(readFile2String(outputFolder + "/sp" + iter + "/part-r-00000"), readFile2String(answerFile),
                new KVParser("\t"));

        System.out.println("恭喜通过~");
    }

}

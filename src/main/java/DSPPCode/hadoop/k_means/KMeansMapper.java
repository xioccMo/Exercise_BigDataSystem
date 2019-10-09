package DSPPCode.hadoop.k_means;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static DSPPCode.hadoop.k_means.Util.CENTERS_PATH;

/**
 * 完成数据的分类
 */
public class KMeansMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

    private List<List<Double>> centers = null;

    private int centerCount;

    @Override
    protected void setup(Context context) {
        // 读取中心数据
        String centersPath = context.getConfiguration().get(CENTERS_PATH);
        centers = Util.getCenters(centersPath);
        if (centers == null) {
            throw new RuntimeException("Cannot parse centers");
        }
        centerCount = centers.size();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] values = value.toString().split(",");
        List<Double> data = new ArrayList<>();
        int centerIndex = 1;
        double minDistance = Double.MAX_VALUE;

        for (String s : values) {
            data.add(Double.parseDouble(s));
        }

        // 计算数据与给定中心之间的距离, 将数据分配给距离最短的中心
        for (int i = 0; i < centerCount; i++) {
            double distance = 0;
            List<Double> center = centers.get(i);
            for (int j = 0; j < center.size(); j++) {
                distance += Math.pow((data.get(j) - center.get(j)), 2);
            }
            distance = Math.sqrt(distance);
            if (distance < minDistance) {
                minDistance = distance;
                centerIndex = i;
            }
        }

        context.write(new IntWritable(centerIndex), value);
    }

}

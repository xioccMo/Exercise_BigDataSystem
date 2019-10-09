package DSPPCode.hadoop.k_means;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static DSPPCode.hadoop.k_means.Util.DEFAULT_SPLITTER;

/**
 * 根据 Mapper 的聚类结果计算出新的中心
 */
public class KMeansReducer extends Reducer<IntWritable, Text, Text, Text> {

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<List<Double>> dataList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        // 解析当前聚类数据
        for (Text text : values) {
            String value = text.toString();
            List<Double> data = new ArrayList<>();
            for (String s : value.split(DEFAULT_SPLITTER)) {
                data.add(Double.parseDouble(s));
            }
            dataList.add(data);
        }

        // 计算当前聚类的中心
        for (int i = 0; i < dataList.get(0).size(); i++) {
            double sum = 0;
            for (List<Double> data : dataList) {
                sum += data.get(i);
            }
            result.append(sum / dataList.size());
            if (i != dataList.get(0).size() - 1) {
                result.append(DEFAULT_SPLITTER);
            }
        }

        context.write(null, new Text(result.toString()));
    }
}

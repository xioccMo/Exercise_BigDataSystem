package DSPPCode.hadoop.warm_up;

import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Java答案示例
 */
public class TokenizerMapperImpl extends TokenizerMapper {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            String str = itr.nextToken();
            str = Pattern.compile("\\W+").matcher(str).replaceAll("");
            word.set(str);
            context.write(word, one);
        }
    }

}

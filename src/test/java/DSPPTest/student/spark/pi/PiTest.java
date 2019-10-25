package DSPPTest.student.spark.pi;

import DSPPCode.spark.pi.PiSimulatorImpl;
import DSPPTest.student.TestTemplate;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PiTest extends TestTemplate {

    @Test
    public void test() {
        int n = 100000;
        List<Object> l = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            l.add(i);
        }

        // 执行
        JavaSparkContext sc = new JavaSparkContext("local", "Pi");

        JavaRDD<Object> parallelInput = sc.parallelize(l);

        int count = parallelInput
                .map(new PiSimulatorImpl())
                .reduce((Integer i1, Integer i2) -> (i1 + i2));

        double pi = 4.0 * count / n;

        sc.close();

        // 检验结果
        assertEquals(pi, Math.PI, 0.03);

        System.out.println("恭喜通过~");
    }

}

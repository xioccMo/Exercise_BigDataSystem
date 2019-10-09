package DSPPTest.student.hadoop.k_means;

import DSPPCode.hadoop.k_means.KMeansImpl;
import DSPPTest.student.TestTemplate;
import org.junit.Test;

import static DSPPTest.util.FileOperator.*;
import static DSPPTest.util.Verifier.verifyList;

public class KMeansTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFile = root + "/hadoop/k_means/input";
        String initCenterFile = root + "/hadoop/k_means/center";
        String oldCenterFile = outputRoot + "/hadoop/k_means/old_center";
        String outputFolder = outputRoot + "/hadoop/k_means/new_center";
        String answerFile = root + "/hadoop/k_means/answer";

        // 删除旧输出
        deleteFolder(oldCenterFile);
        deleteFolder(outputFolder);

        // 拷贝初始中心点
        copyFile(initCenterFile, oldCenterFile);

        // 运行 K-Means
        new KMeansImpl().kMeans(inputFile, oldCenterFile, outputFolder);

        // 检验结果
        verifyList(readFile2String(oldCenterFile), readFile2String(answerFile));

        System.out.println("恭喜通过~");
    }

}

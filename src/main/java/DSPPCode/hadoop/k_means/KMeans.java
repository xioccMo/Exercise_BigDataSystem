package DSPPCode.hadoop.k_means;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.util.ToolRunner;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

import static DSPPCode.hadoop.k_means.Util.*;

abstract public class KMeans {

    /**
     * TODO 请完成该函数
     * -
     * 程序执行过程:
     * 1. 从 inputPath 读取数据, 从 oldCenterPath 读取初始中心点, 经过一次迭代将新中心点写入 newCenterPath
     * 2. 比较新旧中心点: 若不相同则用新中心点覆盖旧中心点, 然后重复 1; 否则结束
     *
     * 请使用 runOneStep() 和 compareAndUpdateCenters() 实现 K-Means
     *
     * @param inputPath     输入数据路径
     * @param oldCenterPath 旧中心点路径, 即初始中心点路径
     * @param newCenterPath 新中心点路径, 即 MapReduce 输出路径
     */
    abstract public void kMeans(String inputPath, String oldCenterPath, String newCenterPath) throws Exception;

    /**
     * 运行 K-Means 一次迭代
     *
     * @param oldCenterPath 旧的中心数据路径
     * @param newCenterPath MapReduce 生成的新的中心数据
     */
    static void runOneStep(String inputPath, String oldCenterPath, String newCenterPath) throws Exception {
        System.out.println("running 1 step of k-means...");
        ToolRunner.run(new KMeansOneStep(), new String[]{inputPath, oldCenterPath, newCenterPath});
    }

    /**
     * 比较新旧中心数据, 用新中心文件覆盖旧中心文件
     *
     * @param oldCenterPath 旧的中心数据路径
     * @param newCenterPath MapReduce 生成的新的中心数据
     * @return 若路径下数据不存在或不相同, 返回 false; 否则返回 true
     */
    static boolean compareAndUpdateCenters(String oldCenterPath, String newCenterPath) {
        List<String> centers = getCentersStr(oldCenterPath);
        List<String> newCenters = getCentersStr(newCenterPath);

        if (centers == null || newCenters == null) {
            return false;
        }

        boolean isSame = true;
        boolean[] rmAnswer = new boolean[newCenters.size()];
        for (String center : centers) {
            boolean found = false;
            for (int i = 0; i < newCenters.size(); i++) {
                if (!rmAnswer[i] && center.equals(newCenters.get(i))) {
                    rmAnswer[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                isSame = false;
                break;
            }
        }

        if (isSame) {
            // 中心相同
            FileUtils.deleteQuietly(new File(newCenterPath));
            return true;

        } else {
            // 中心不同, 则要将新的中心数据移动到初始中心数据路径处
            try {
                List<String> paths = listDataFile(newCenterPath);
                if (paths != null) {
                    FileUtils.deleteQuietly(new File(oldCenterPath));
                    FileChannel outChannel = new FileOutputStream(new File(oldCenterPath), true).getChannel();
                    for (String path : paths) {
                        FileChannel inChannel = new FileInputStream(new File(path)).getChannel();
                        inChannel.transferTo(0, inChannel.size(), outChannel);
                        inChannel.close();
                    }
                    outChannel.close();
                }
                FileUtils.deleteQuietly(new File(newCenterPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}

package DSPPCode.spark.k_means;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import scala.Tuple2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

abstract public class KMeans implements Serializable {

    /**
     * 输入:
     * data 表示已经读取了文件input的RDD, 文件中的每条记录为一个二维坐标点, 以逗号为分隔符.
     * k 表示所求的聚类点数量.
     * convergeDist 表示停止迭代的阈值, 即当两次之间的距离小于该阈值时停止迭代.
     * seed 初始聚类点的采样种子.
     * -
     * 输出:
     * k个聚类点的坐标, 每个坐标以逗号(英文)为分隔符, 每个点的记录以换行符为分隔符, 保存为字符串的形式输出.
     */
    public String compute(JavaRDD<String> lines, int k, double convergeDist) {
        JavaRDD<Vector> points = lines.map((String line) -> {
            double[] point = Arrays.stream(line.split(","))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            return Vectors.dense(point);
        });

        double tmpDist = Double.MAX_VALUE;

        List<Vector> tmpList = points.takeSample(false, k, 50);
        List<Vector> kPoints = new ArrayList<>(tmpList);

        while (tmpDist > convergeDist) {
            JavaPairRDD<Integer, Tuple2<Vector, Integer>> cloest = points.mapToPair((Vector point) -> {
                Tuple2<Vector, Integer> tmp = new Tuple2<>(point, 1);

                return new Tuple2<>(closestPoint(point, kPoints), tmp);
            });

            JavaPairRDD<Integer, Tuple2<Vector, Integer>> pointStatus =
                    cloest.reduceByKey((Tuple2<Vector, Integer> t1, Tuple2<Vector, Integer> t2) -> {
                        double[] tmp = new double[2];
                        tmp[0] = t1._1.toArray()[0] + t2._1.toArray()[0];
                        tmp[1] = t1._1.toArray()[1] + t2._1.toArray()[1];
                        Vector new_vec = Vectors.dense(tmp);
                        return new Tuple2<>(new_vec, t1._2 + t2._2);
                    });

            Map<Integer, Vector> newPoints = pointStatus.mapToPair((Tuple2<Integer, Tuple2<Vector, Integer>> pair) -> {
                int index = pair._1;
                double[] tmp = new double[2];
                tmp[0] = pair._2._1.toArray()[0] * (1.0 / pair._2._2);
                tmp[1] = pair._2._1.toArray()[1] * (1.0 / pair._2._2);
                Vector new_vec = Vectors.dense(tmp);
                return new Tuple2<>(index, new_vec);
            }).collectAsMap();

            tmpDist = 0.0;
            for (int i = 0; i < k; i++) {
                tmpDist += squaredDistance(kPoints.get(i), newPoints.get(i));
            }

            for (Map.Entry<Integer, Vector> newP : newPoints.entrySet()) {
                kPoints.set(newP.getKey(), newP.getValue());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Vector vector : kPoints) {
            String s = String.format("%f,%f\n", vector.toArray()[0], vector.toArray()[1]);
            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * TODO 聚类点集合centers中找出距离目标点centers最近的一个聚类点, 返回该聚类点在集合中的索引位置.
     * 输入: 目标点point与聚类点集合centers.
     * 输出: 所求聚类点在集合中的索引位置, 类型为int, 位置从0开始计算.
     */
    abstract int closestPoint(Vector point, List<Vector> centers);

    /**
     * TODO 计算点与点的距离使用平方距离, 譬如(a,b)与(c,d)的距离为(a-c)^2+(b-d)^2, 即squaredDistance.
     * 输入: 两个点, 类型为Vector.
     * 输出: 两点的距离.
     */
    abstract double squaredDistance(Vector point, Vector index);

}

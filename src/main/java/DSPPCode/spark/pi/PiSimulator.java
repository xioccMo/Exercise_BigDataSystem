package DSPPCode.spark.pi;

import org.apache.spark.api.java.function.Function;

abstract public class PiSimulator implements Function<Object, Integer> {

    /**
     * TODO 请完成该函数
     *
     * 在一正方形中随机生成一点, 判断该点是否在圆内
     *
     * @param unused 无实际意义, 只是用来提供算子并行度
     * @return 随机生成的点在圆内时返回 1, 否则返回 0
     */
    @Override
    abstract public Integer call(Object unused);

}

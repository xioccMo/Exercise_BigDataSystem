package DSPPCode.spark.broadcast_join;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Iterator;
import java.util.Map;

abstract public class BroadcastJoinMapper implements FlatMapFunction<String, String> {

    /**
     * 用于存储广播变量. Map 中的键是 Person 的 Id_P, 值是对应的 LastName 和 FirstName, 由 "," 分隔
     * (如 键: 1, 值: "Adams,John")
     */
    Broadcast<Map<Long, String>> persons;

    public void setPersons(Broadcast<Map<Long, String>> persons) {
        this.persons = persons;
    }

    /**
     * TODO 请完成该函数
     *
     * 根据输入变量 order 和广播变量 persons, 计算有关该 order 的所有连接结果
     *
     * @param order 一个 Order 记录, 各字段由 "," 分隔 (如 "1,77895,3")
     * @return 返回该条 Order 记录的所有连接结果, 其中每条字符串代表一个连接记录, 各字段由 "," 分隔 (如 "Adams,John,24562")
     */
    @Override
    abstract public Iterator<String> call(String order);

}

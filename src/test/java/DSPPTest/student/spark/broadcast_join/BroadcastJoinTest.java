package DSPPTest.student.spark.broadcast_join;

import DSPPCode.spark.broadcast_join.BroadcastJoinMapperImpl;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.Parser.LineParser;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import static DSPPTest.util.FileOperator.*;
import static DSPPTest.util.Verifier.verifyKV;

public class BroadcastJoinTest extends TestTemplate {

    @Test
    public void test() throws Exception {
        // 设置路径
        String inputFolder = root + "/spark/broadcast_join/input";
        String personFile = inputFolder + "/persons";
        String orderFile = inputFolder + "/orders";
        String outputFolder = outputRoot + "/spark/broadcast_join";
        String outputFile = outputFolder + "/part-00000";
        String answerFile = root + "/spark/broadcast_join/answer";

        // 删除旧输出
        deleteFolder(outputFolder);

        // 执行
        JavaSparkContext sc = new JavaSparkContext("local", "Broadcast Join");

        Map<Long, String> persons = parsePersonData(personFile);
        Broadcast<Map<Long, String>> bcPersons = sc.broadcast(persons);

        BroadcastJoinMapperImpl joinMapper = new BroadcastJoinMapperImpl();
        joinMapper.setPersons(bcPersons);
        sc.textFile(orderFile).flatMap(joinMapper).saveAsTextFile(outputFolder);

        bcPersons.destroy();
        sc.close();

        // 检验结果
        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new LineParser());

        System.out.println("恭喜通过~");
    }

    private HashMap<Long, String> parsePersonData(String filePath) throws IOException {
        HashMap<Long, String> personMap = new HashMap<>();
        BufferedReader br = getBufferedReader(filePath);
        String line;

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            personMap.put(Long.parseLong(fields[0]), fields[1] + "," + fields[2]);
        }
        br.close();

        return personMap;
    }

}

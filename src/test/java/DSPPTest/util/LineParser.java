package DSPPTest.util;

import java.util.HashMap;
import java.util.Map;

public class LineParser extends KVParser {

    public LineParser() {
    }

    public LineParser(String delimiter) {
        this.delimiter = delimiter;
    }

    public LineParser(String delimiter, String recordDelimiter) {
        this.delimiter = delimiter;
        this.recordDelimiter = recordDelimiter;
    }

    @Override
    public Map<String, String> parse(String str) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        String[] records = str.split(recordDelimiter);
        for (String record : records) {
            if (ret.containsKey(record)) {
                throw new Exception("Duplicated recored: " + record);
            }
            ret.put(record,"");
        }
        return ret;
    }

}

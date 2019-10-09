package DSPPTest.util;

import java.util.*;

/**
 * Parser of lists
 */
public class ListParser {

    // delimiter between records
    private String recordDelimiter = "\n|\r\n";

    ListParser() {
    }

    public ListParser(String recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
    }

    /**
     * Parse a string to a list of values
     *
     * @param str the string need to be parsed
     * @return a list of values
     */
    List<String> parse(String str) {
        String[] records = str.split(recordDelimiter);
        List<String> ret = new ArrayList<>(records.length);
        Collections.addAll(ret, records);
        return ret;
    }

}

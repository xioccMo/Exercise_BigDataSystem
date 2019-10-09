package DSPPCode.hadoop.multi_input_join;

/**
 * 用于标识数据属于什么表
 */
public class DataSourceFlag {

    /**
     * 标识数据来自 Person 表，{@link OrderMapper}
     */
    public static final String PERSON = "person";

    /**
     * 标识数据来自 Order 表，{@link PersonMapper}
     */
    public static final String ORDER = "order";


    private DataSourceFlag() {
    }
}

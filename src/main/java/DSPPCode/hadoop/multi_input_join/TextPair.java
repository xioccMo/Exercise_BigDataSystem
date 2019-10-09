package DSPPCode.hadoop.multi_input_join;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 用于携带信息和标识信息来源及优先级
 */
public class TextPair implements Writable {

    /**
     * 携带的数据
     */
    private Text mData;

    /**
     * 标识数据来源
     */
    private Text mFlag;

    public TextPair() {
        mData = new Text();
        mFlag = new Text();
    }

    public TextPair(Text id, Text flag) {
        mData = id;
        mFlag = flag;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        mData.write(out);
        mFlag.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        mData.readFields(in);
        mFlag.readFields(in);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextPair) {
            return ((TextPair) obj).mData.equals(mData) &&
                    ((TextPair) obj).mFlag.equals(mFlag);
        }
        return false;
    }

    @Override
    public String toString() {
        return mData + "\t" + mFlag;
    }

    public void setData(Text data) {
        mData = data;
    }

    public void setFlag(Text flag) {
        mFlag = flag;
    }

    public Text getData() {
        return mData;
    }

    public Text getFlag() {
        return mFlag;
    }
}


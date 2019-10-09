package DSPPCode.hadoop.ssp;

public class Node {

    public enum eInf {
        COUNTER
    }

    /** 距离表示距离初始节点的距离*/
    private String distance;

    /** 临接节点列表(节点名，到此节点距离)*/
    private String[] adjs;

    /** 获取距离 */
    public String getDistance() {
        return distance;
    }

    /** 设置距离 */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /** 获取传入字符串的KEY 从第一个字符 到逗号之前 */
    public String getKey(String str) {
        return str.substring(1, str.indexOf(","));
    }

    /** 获取传入字符串的值 从逗号开始到有括号结束 */
    public String getValue(String str) {
        return str.substring(str.indexOf(",") + 1, str.indexOf(")"));
    }

    /** 获取第num个临接点的Key*/
    public String getNodeKey(int num) {
        return getKey(adjs[num]);
    }

    /** 获取第num个临接点的Value */
    public String getNodeValue(int num) {
        return getValue(adjs[num]);
    }

    /** 查询此节点有多少临接节点 */
    public int getNodeNum() {
        return adjs.length;
    }

    /** 构造一个节点 输入为 distance \t (nodeName,adj_distance) \t (nodeName,adj_distance) => distance adj[]*/
    public void FormatNode(String str) {
        if (str.length() == 0) {
            return;
        }

        String[] strs = str.split("\t");
        // 临接节点的个数为 strs长度－1
        adjs = new String[strs.length - 1];

        for (int i = 0; i < strs.length; i++) {

            // 第一个string作为此节点的distance
            if (i == 0) {
                setDistance(strs[i]);
                continue;
            }
            this.adjs[i - 1] = strs[i];
        }

    }

    @Override
    public String toString() {
        String str = this.distance + "";

        if (this.adjs == null) {
            return str;
        }

        for (String s : this.adjs) {
            str = str + "\t" + s;
        }
        return str;
    }

}

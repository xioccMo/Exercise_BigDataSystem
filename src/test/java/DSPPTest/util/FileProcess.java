package DSPPTest.util;

import java.io.*;

public class FileProcess implements Serializable {

    public static BufferedWriter getWriter(String toWrite) {
        BufferedWriter bw = null;
        File f;
        FileWriter fw;
        try {
            f = new File(toWrite);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bw;
    }

    public static void write(String str, BufferedWriter bw) {
        if (bw != null) {
            try {
                bw.write(str);
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(BufferedWriter bw) {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

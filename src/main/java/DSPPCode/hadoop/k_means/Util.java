package DSPPCode.hadoop.k_means;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

    static final String DEFAULT_SPLITTER = ",";

    static final String CENTERS_PATH = "centersPath";

    static List<List<Double>> getCenters(String centerPath) {
        List<List<Double>> centers = new ArrayList<>();
        List<String> paths = listDataFile(centerPath);

        if (paths != null) {
            for (String path : paths) {
                List<List<Double>> subCenters = getCenters(path);
                if (subCenters != null) {
                    centers.addAll(subCenters);
                }
            }
            return centers;
        }

        try {
            InputStream inputStream = new FileInputStream(new File(centerPath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dataArray = line.split(DEFAULT_SPLITTER);
                List<Double> center = new ArrayList<>();
                for (String data : dataArray) {
                    center.add(Double.parseDouble(data));
                }
                centers.add(center);
            }
        } catch (Exception e) {
            return null;
        }

        return centers;
    }

    static List<String> getCentersStr(String centerPath) {
        List<String> centers = new ArrayList<>();
        List<String> paths = listDataFile(centerPath);

        if (paths != null) {
            for (String path : paths) {
                List<String> subCenters = getCentersStr(path);
                if (subCenters != null) {
                    centers.addAll(subCenters);
                }
            }
            return centers;
        }

        try {
            InputStream inputStream = new FileInputStream(new File(centerPath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String center;
            while ((center = reader.readLine()) != null) {
                centers.add(center);
            }
        } catch (Exception e) {
            return null;
        }

        return centers;
    }

    static List<String> listDataFile(String directory) {
        List<String> paths = new ArrayList<>();
        File root = new File(directory);
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                String path = file.getPath();
                if (path.matches(".*[0~9]+")) {
                    paths.add(file.getPath());
                }
            }
        }
        return paths.size() > 0 ? paths : null;
    }

}

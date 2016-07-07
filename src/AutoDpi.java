import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huli on 16/6/26.
 */
public class AutoDpi {
    public static final String path = "output/values-%dx%d";
    private static List<Integer> integerList = new ArrayList<>();
    private static String dimens;


    public static void main(String[] args) {
        readFile();
        makeDirs();
    }


    private static void readFile() {
        File src_file = new File("dimens.xml");
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(src_file));
            String s = reader.readLine();
            while (s != null) {
                builder.append(s).append("\n");
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dimens = builder.toString();
    }

    private static void makeDirs() {
        List<ScreenSize> sizeList = new ArrayList<>();
        File config_file = new File("config.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config_file));
            String s = reader.readLine();
            while (s != null) {
                String[] screen = s.split(",");
                if (screen.length == 2) {
                    int h = Integer.valueOf(screen[0]);
                    int w = Integer.valueOf(screen[1]);
                    ScreenSize screenSize = new ScreenSize(w, h);
                    sizeList.add(screenSize);
                }
                s = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ScreenSize screenSize : sizeList) {
            File path = new File(String.format(AutoDpi.path, screenSize.getH(), screenSize.getW()));
            path.delete();
            path.mkdirs();
            File xmlFile = new File(path, "short_dimens.xml");
            float scale = screenSize.getH() / 640F;
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(xmlFile));
                Pattern pattern = Pattern.compile("\\d+dp");
                Matcher matcher = pattern.matcher(dimens);
                String wr = dimens;
                while (matcher.find()) {
                    String dimen = matcher.group();
                    int px = Math.round(Float.valueOf(dimen.replace("dp", "")) * scale);
                    String result = String.valueOf(px) + "px";
                    wr = wr.replace(dimen, result);
                }
                System.out.println(wr);
                writer.write(wr);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}

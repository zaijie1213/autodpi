import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huli on 16/6/26.
 */
public class Main {
    public static void main(String[] args) {
        List<ScreenSize> sizeList = new ArrayList<>();
        File source_file = new File("/Users/huli/IdeaProjects/learn/auto_dpi/src/dimens.xml");
        File config_file = new File("/Users/huli/IdeaProjects/learn/auto_dpi/src/config.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config_file));
            String s = reader.readLine();
            while (s != null) {
                String[] screen = s.split(",");
                if (screen.length ==2){
                    int h = Integer.valueOf(screen[0]);
                    int w = Integer.valueOf(screen[1]);
                    ScreenSize screenSize = new ScreenSize(w,h);
                    sizeList.add(screenSize);
                }
                s = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ScreenSize screenSize : sizeList) {
            System.out.println(screenSize.getH());
        }
    }
}

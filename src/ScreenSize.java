/**
 * Created by huli on 16/6/26.
 */
public class ScreenSize {
    private int w;
    private int h;

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ScreenSize(int w, int h) {

        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return "ScreenSize{" +
                "w=" + w +
                ", h=" + h +
                '}';
    }
}

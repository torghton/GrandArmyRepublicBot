import java.awt.*;
import java.util.ArrayList;

public class Pixel {

    private ArrayList<Integer> RGBColors;
    private ArrayList<Point> Points;

    public Pixel(ArrayList<Integer> RGBColors, ArrayList<Point> Points) {
        this.RGBColors = RGBColors;
        this.Points = Points;
    }

    public ArrayList<Integer> getRGBColors() {
        return RGBColors;
    }

    public ArrayList<Point> getPoints() {
        return Points;
    }
}

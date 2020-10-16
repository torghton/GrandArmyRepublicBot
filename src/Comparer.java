import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Comparer extends Runner {

    private Point boundingBoxSize;

    public Comparer() {
        boundingBoxSize = new Point((int)(rectangleScreenSize.width/12.8), (int)(rectangleScreenSize.height/24));
    }

    public Point testForCorrectColor(int colorToTestFor, Pixel pixel) {
        for(int i = 0;i < pixel.getRGBColors().size();i++) {
            if(colorToTestFor == pixel.getRGBColors().get(i)) {
                return pixel.getPoints().get(i);
            }
        }
        return null;
    }

    public Stack<Integer> getPixels(Point edge, BufferedImage image) {
        Stack<Integer> colors = new Stack<>();
        for(int x = edge.x;x < boundingBoxSize.x + edge.x;x++) {
            for(int y = edge.y;y > edge.y - boundingBoxSize.y;y--) {
                try {
                    colors.push(image.getRGB(x,y));
                } catch(Exception e) {
                    System.err.println("ERROR IN GETPIXELS METHOD");
                }

            }
        }
        return colors;
    }

    public int checkForAmountOfColor(int colorToCheckFor, Stack<Integer> colorData) {
        int timesShown = 0;
        for(int color: colorData) {
            if(color == colorToCheckFor) {
                timesShown += 1;
            }
        }
        return timesShown;
    }

}

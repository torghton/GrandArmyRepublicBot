/**
 * @author Alexander Zlatev
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GetPixels {


    /**
     *
     * @param image The Image you want to process
     * @param iterator How many pixels you want to skip in the array
     * @param maxSizeX The maximum size that the iterator will check in the x direction
     * @param maxSizeY The maximum size that the iterator will check in the y direction
     * @return Returns a ArrayList with all the color values
     */
    public Pixel pixelColors(BufferedImage image, int iterator, int startX, int startY, int maxSizeX, int maxSizeY) {
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        for(int x = startX;x < maxSizeX; x += iterator) {
            for(int y = startY;y < maxSizeY;y += iterator) {
                colors.add(image.getRGB(x, y));
                points.add(new Point(x, y));
            }
        }
        Pixel pixel = new Pixel(colors, points);
        return pixel;

    }

    public Point findEdge(Point point, BufferedImage image) {
        int color;
        point.x += 50;
        for(int i = 0;i < 100;i++) {
            color = image.getRGB(point.x, point.y + i);
            //Should find color -41121
            if(color != -41378) {
                System.err.println(color);
                point.y += i;
                break;

            }

        }

        for(int i = 0;i < 100;i++) {
            color = image.getRGB(point.x - i, point.y);
            //Should find color -41121
            if(color != -41121) {
                System.err.println(color);
                point.x -= i;
                return point;

            }

        }
        return null;
    }
}

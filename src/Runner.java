import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

public class Runner {
    Dimension dimensionScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle rectangleScreenSize = new Rectangle(0,0,dimensionScreenSize.width,dimensionScreenSize.height);


    public static void main(String[]args) throws AWTException, InterruptedException {
        Runner runner = new Runner();
        GetPixels getPixels = new GetPixels();
        ScreenShoter screenShoter = new ScreenShoter(runner.rectangleScreenSize);
        Comparer comparer = new Comparer();
        MouseMacro mouseMacro = new MouseMacro();

        Thread.sleep(2000);
        while(true) {
            Thread.sleep(20000);

            // Take a screenshot and save it as a image
            BufferedImage image = screenShoter.takeScreenShot();

            // Get each pixel in the image and Save it to Memory
            Pixel imagePixelColorsArrayList = getPixels.pixelColors(image, 10, 0, 100, 1500, image.getHeight());


            // Find a edge of the button
            Point edge;
            Point edge2;
            try {
                edge = new Point(getPixels.findEdge(comparer.testForCorrectColor(-41378, imagePixelColorsArrayList), image));
                edge2 = new Point((int)(edge.x + runner.rectangleScreenSize.width/5.48), edge.y);
            } catch (Exception e) {
                continue;
            }


            // Determine which button is yes or no


            Stack<Integer> colorsInLeftBox = comparer.getPixels(edge, image);
            Stack<Integer> colorsInRightBox = comparer.getPixels(edge2, image);

            int amountInLeftBox = comparer.checkForAmountOfColor(-1,colorsInLeftBox);
            int amountInRightBox = comparer.checkForAmountOfColor(-1,colorsInRightBox);
            System.out.println(amountInLeftBox + ", " + amountInRightBox);

            // Click the yes or no button

            if(amountInLeftBox < amountInRightBox) {
                mouseMacro.mouseSlowMove(new Point(edge.x + 100, edge.y - 50), 1);
            } else {
                mouseMacro.mouseSlowMove(new Point(edge2.x + 100, edge2.y - 50), 1);
            }
            mouseMacro.mouseClick();
        }



    }
}

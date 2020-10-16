import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenShoter {

    private Robot robot;
    private Rectangle screenSize;


    /**
     *
     * @param screenSize Takes in the size of the screen that you want to screen shot
     * @throws AWTException Throws a AWT Exception
     */
    public ScreenShoter(Rectangle screenSize) throws AWTException {
        robot = new Robot();
        this.screenSize = screenSize;
    }

    /**
     *
     * @return Returns a screen Shot of the area you requested
     */
    public BufferedImage takeScreenShot() {
        return robot.createScreenCapture(screenSize);
    }

}

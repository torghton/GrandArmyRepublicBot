/**
 * @author Alexander Zlatev
 */

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseMacro {

    Robot robot;

    public MouseMacro() throws AWTException {
        robot = new Robot();
    }

    /**
     * Scrolls the mouse a certain number of notches at any speed
     *
     * @param amount
     * @param speed
     * @throws InterruptedException
     */
    public void scroll(int amount, int speed, int delay) throws InterruptedException {
        if(amount < 0){
            speed = -speed;
        }
        for(int i = Math.abs(amount);i >= 0; i -= Math.abs(speed)) {
            robot.mouseWheel(speed);
            Thread.sleep(delay);
        }

    }

    public void mouseMove(Point location) {
        robot.mouseMove(location.x, location.y);
    }

    public void mouseMoveByAmount(Point distance) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(mouseLocation.x + distance.x, mouseLocation.y + distance.y);
    }

    public void mouseClick() throws InterruptedException {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(50);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
    }

    public Point mousePos() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    /**
     *
     * @param location Location to move mouse to
     * @param increment amount ot increment by
     */
    public void mouseSlowMove(Point location, int increment) throws InterruptedException {
        // Make increment positive
        int incrementAbs = Math.abs(increment);


        // Get Mouse Location
        Point mousePos = mousePos();

        // Calculate distance between mouse location and
        int[] distance = {(mousePos.x - location.x), (mousePos.y - location.y)};

        // Move mouse towards point by increment
        for(int i = 0;i < Math.abs(distance[0]);i += incrementAbs) {
            // if distance is less then i, then we will add the remaining distance.
            if(Math.abs(distance[0]) < i) {
                incrementAbs = Math.abs(distance[0]);
            }
            // if the distance to the point is less then zero, subtract the increment
            if(distance[0] > 0) {
                mouseMove(new Point(mousePos.x - i, mousePos.y));
                // if the distance to the point is greater then 0, add the increment
            } else if(distance[0] < 0) {
                mouseMove(new Point(mousePos.x + i, mousePos.y));
                // if all else fails, that means we are on the point we want, so we stop the loop
            } else {
                break;
            }


        }
        //Reset incrementAbs
        Thread.sleep(20);
        incrementAbs = Math.abs(increment);
        mousePos = mousePos();
        Thread.sleep(20);

        for(int i = 0;i < Math.abs(distance[1]);i += incrementAbs) {
            // if distance is less then i, then we will add the remaining distance.
            if(Math.abs(distance[1]) < i) {
                incrementAbs = Math.abs(distance[0]);
            }
            // if the distance to the point is less then zero, subtract the increment
            if(distance[1] > 0) {
                mouseMove(new Point(mousePos.x, mousePos.y - i));
                // if the distance to the point is greater then 0, add the increment
            } else if(distance[1] < 0) {
                mouseMove(new Point(mousePos.x, mousePos.y + i));
                // if all else fails, that means we are on the point we want, so we stop the loop
            } else {
                break;
            }


        }
    }
}

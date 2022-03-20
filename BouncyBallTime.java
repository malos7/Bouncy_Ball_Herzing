
package jmcclure_oop_unit6assignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class BouncyBallTime extends JPanel implements Runnable {
    //var for ball location and direction
    private Point ballPoint;
    private Direction currentDirection;

    public BouncyBallTime() {
        
        //start location
        ballPoint = new Point(730 / 2, 700 / 2);
        
        //handler for mouse click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                    startMove();
            }
        });
        
        //pane background color
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        
        //paints ball onto pane
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(ballPoint.x, ballPoint.y, 50, 50);
    }

    public void startMove() {
        //thread
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                //var for direction of ball and method pull for new direction
                Direction nextDirection = generateDirection();

                    while (nextDirection == currentDirection)
                        //generate new direction so that it does
                        //not equal current direction
                        nextDirection = generateDirection();
                        currentDirection = nextDirection;

                        do {
                            move(currentDirection);
                                //repaint redraws any necessary elements on pane
                                repaint();
                                Thread.sleep(2);

                                if (isEdge(currentDirection))
                                    break;
                        } while (true);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    //helps decide current direction of ball
    private void move(Direction direction) {
        switch (direction) {
        case DOWN:
            ballPoint.y++;
            break;
        case RIGHT:
            ballPoint.x++;
            break;
        case UP:
            ballPoint.y--;
            break;
        case LEFT:
            ballPoint.x--;
            break;
        case DIAGONAL_LEFT_DOWN:
            ballPoint.x--;
            ballPoint.y++;
            break;
        case DIAGONAL_LEFT_UP:
            ballPoint.x++;
            ballPoint.y--;
            break;
        case DIAGONAL_RIGHT_DOWN:
            ballPoint.x++;
            ballPoint.y++;
            break;
        case DIAGONAL_RIGHT_UP:
            ballPoint.x--;
            ballPoint.y--;
            break;
        }
    }
    // checks for edge of pane
    private boolean isEdge(Direction direction) {
        switch (direction) {
        case DOWN:
            if (ballPoint.y >= 710)
                    return true;
            break;
        case RIGHT:
            if (ballPoint.x >= 730)
                    return true;
            break;
        case UP:
            if (ballPoint.y <= 0)
                    return true;
            break;
        case LEFT:
            if (ballPoint.x <= 0)
                    return true;
            break;
        case DIAGONAL_LEFT_DOWN:
            if (ballPoint.x <= 0 || ballPoint.y >= 710)
                    return true;
            break;
        case DIAGONAL_LEFT_UP:
            if (ballPoint.x >= 730 || ballPoint.y <= 0)
                    return true;
            break;
        case DIAGONAL_RIGHT_DOWN:
            if (ballPoint.x >= 730 || ballPoint.y >= 710)
                    return true;
            break;
        case DIAGONAL_RIGHT_UP:
            if (ballPoint.x <= 0 || ballPoint.y <= 0)
                    return true;
            break;
        }

        return false;
    }
    //generates new direction
    private Direction generateDirection() {
        switch (new SecureRandom().nextInt(8)) {
        case 0:
                return Direction.DOWN;
        case 1:
                return Direction.RIGHT;
        case 2:
                return Direction.UP;
        case 3:
                return Direction.LEFT;
        case 4:
                return Direction.DIAGONAL_LEFT_DOWN;
        case 5:
                return Direction.DIAGONAL_LEFT_UP;
        case 6:
                return Direction.DIAGONAL_RIGHT_DOWN;
        default:
                return Direction.DIAGONAL_RIGHT_UP;
        }
    }
}
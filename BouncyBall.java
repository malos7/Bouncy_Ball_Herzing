
package jmcclure_oop_unit6assignment;

import javax.swing.JFrame;

public class BouncyBall {

    public static void main(String[] args) {
        //name window pane
        JFrame application = new JFrame("Bouncy Ball Time");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //adds ball to pane
        application.add(new BouncyBallTime());
        
        //dimensions for window pane
        application.setSize(800, 800);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
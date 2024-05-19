package lab1.task2;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
class Ball {
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    private boolean isInPocket = false;
    private final List<Pocket> pockets;

    public List<Pocket> getPockets() {
        return pockets;
    }

    private boolean isBallInPocket() {
        for (Pocket pocket : pockets) {
            if (pocket.isBallInPocket(x, y)) {
                return true;
            }
        }
        return false;
    }

    public Ball(ArrayList<Pocket> pockets, Component c) {
        this.canvas = c;
        this.pockets = pockets;
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

/*    public static void f() { // ?
        int a = 0;
    }*/

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));

    }

    public void move() {
        x += dx;
        y += dy;
        if (isBallInPocket()) {
            isInPocket = true;
            System.err.println("Ball is in pocket");
            this.canvas.repaint();
            return;
        }
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }
}
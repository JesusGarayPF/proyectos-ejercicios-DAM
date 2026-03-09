package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class GamePanel extends JPanel implements Runnable {

    private int y = 0;
    private int x = 0;
    private Thread gameLoopThread;

    //region control de fps
    private static final long NANOS_IN_ONE_SECOND = 1_000_000_000;
    private static final long DESIRED_FPS = 30;
    private static final long FRAME_DURATION = NANOS_IN_ONE_SECOND / 30;
    private long currentFps = 0;

    //end region
    public GamePanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.yellow);
        g2d.drawRect(x, y, 50, 50);
        Font currentFont = g2d.getFont();
        Font fpsFont = currentFont.deriveFont(currentFont.getSize() * 2f);
        g2d.setFont(fpsFont);
        g2d.setColor(Color.white);
        g2d.drawString(String.format("%d FPS", this.currentFps),10,790);
        System.out.println("En paint componente");
    }

    @Override
    public void run() {
        try {
            long fpsCount = 0;
            long lastCheckFps = System.nanoTime();
            while (true) {
                fpsCount++;
                long elapsed = System.nanoTime() - lastCheckFps;
                if (elapsed > NANOS_IN_ONE_SECOND) {
                    this.currentFps = fpsCount;
                    lastCheckFps = System.nanoTime();
                    fpsCount = 0;
                }
                Thread.sleep(FRAME_DURATION/1000000);
                x++;
                if (x > this.getWidth()) {
                    this.x = 0;
                }
                y++;
                if (y > this.getHeight()) {
                    this.y = 0;
                }
                System.out.printf("Posicion: %d,%d\n", x, y);
                this.repaint();

                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Saliendo del bucle de juego");
        }
    }

    public void startGameLoop() {
        this.gameLoopThread = new Thread(this);
        this.gameLoopThread.start();
    }

    public void endGameLoop() {
        this.gameLoopThread.interrupt();
    }
}

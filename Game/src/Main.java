import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    JPanel mainPanel;
    JPanel controlPanel;

    JButton resetButton;

    Model game = new Model();

    BufferedImage robotD;
    BufferedImage robotL;
    BufferedImage robotR;
    BufferedImage robotU;
    BufferedImage groundImage;
    BufferedImage wallImage;
    BufferedImage goalImage;
    BufferedImage boxBlueImage;
    char dir;

    Main() throws IOException {
        //Importing the images for the project
        robotD = ImageIO.read(new File("Game/images/RobotD.png"));
        robotL = ImageIO.read(new File("Game/images/RobotL.png"));
        robotR = ImageIO.read(new File("Game/images/RobotR.png"));
        robotU = ImageIO.read(new File("Game/images/RobotU.png"));


        groundImage = ImageIO.read(new File("Game/images/Ground.png"));
        wallImage = ImageIO.read(new File("Game/images/Wall.png"));
        goalImage = ImageIO.read(new File("Game/images/Goal.png"));
        boxBlueImage = ImageIO.read(new File("Game/images/BoxBlue.png"));

        setLayout(new BorderLayout());
        mainPanel = new CanvasPanel();
        mainPanel.setSize(600, 700);
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.setFocusable(true);
        mainPanel.requestFocus();

        mainPanel.addKeyListener(new MovementAdapter());

        controlPanel = new DataPanel();
        controlPanel.setBackground(Color.GRAY);
        add(controlPanel, BorderLayout.LINE_END);


        resetButton = new JButton("RESET");
        resetButton.addActionListener(e -> {
            game.reset();
            repaint();
            mainPanel.requestFocus();
        });

    }

    public static void main(String[] args) {
        try {
            Main frame = new Main();
            frame.setTitle("Sokoban-1");
            frame.setSize(1200, 700);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something wrong with Game file");
        }
    }

    class CanvasPanel extends JPanel {
        private void drawGroundImage(Graphics g, int r, int c) {
            int cellWidth = groundImage.getWidth();
            int cellHeight = groundImage.getHeight();
            int topLeftX = getWidth() / 2 - cellWidth * game.getPuzzleWidth() / 2;
            int topLeftY = getHeight() / 2 - cellHeight * game.getPuzzleHeight() / 2;
            g.drawImage(groundImage,
                    topLeftX + c * cellWidth + cellWidth / 2 - cellWidth / 2,
                    topLeftY + r * cellHeight + cellHeight / 2 - cellHeight / 2,
                    null);
        }

        private void drawImage(Graphics g, BufferedImage image, int r, int c) {
            int cellWidth = groundImage.getWidth();
            int cellHeight = groundImage.getHeight();
            int topLeftX = getWidth() / 2 - cellWidth * game.getPuzzleWidth() / 2;
            int topLeftY = getHeight() / 2 - cellHeight * game.getPuzzleHeight() / 2;
            g.drawImage(image,
                    topLeftX + c * cellWidth + cellWidth / 2 - image.getWidth() / 2,
                    topLeftY + r * cellHeight + cellHeight / 2 - image.getHeight() / 2,
                    null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int r = 0; r < game.getPuzzleHeight(); r++) {
                for (int c = 0; c < game.getPuzzleWidth(); c++) {
                    switch (game.elementAt(r, c)) {
                        case ' ':
                            drawGroundImage(g, r, c);
                            break;
                        case '#':
                            drawGroundImage(g, r, c);
                            drawImage(g, wallImage, r, c);
                            break;
                        case '.':
                            drawGroundImage(g, r, c);
                            drawImage(g, goalImage, r, c);
                            break;
                        case '$':
                            drawGroundImage(g, r, c);
                            drawImage(g, boxBlueImage, r, c);
                    }
                }
            }
            //res
            drawGroundImage(g, game.getRobotRow(), game.getRobotColumn());
            if (game.getMoves()==0){
                drawImage(g, robotD, game.getRobotRow(), game.getRobotColumn());
            }
            switch (dir){
                case 'u':
                    drawImage(g, robotU, game.getRobotRow(), game.getRobotColumn());
                    break;
                case 'd':
                    drawImage(g, robotD, game.getRobotRow(), game.getRobotColumn());
                    break;
                case 'l':
                    drawImage(g, robotL, game.getRobotRow(), game.getRobotColumn());
                    break;
                case 'r':
                    drawImage(g, robotR, game.getRobotRow(), game.getRobotColumn());
                    break;
            }
        }
    }

    private class MovementAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                dir = 'u';
                game.moveUP();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                dir = 'd';
                game.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                dir = 'l';
                game.moveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dir = 'r';
                game.moveRight();
            }
            repaint();
            if (game.hasWon()) {
                JOptionPane.showMessageDialog(null, "You solved puzzle " + (game.getCurrentLevel() + 1) + "! Moves: " + game.getMoves());
                game.nextLevel();
                repaint();
            }
        }
    }

    private class DataPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.CYAN);
            g.fillRect(5, 20, 150, 60);
            g.setColor(Color.BLACK);
            g.drawString("MiniCosmos", 43, 50);
            g.setColor(Color.yellow);
            g.drawString("Levels", 5, 15);


            g.setColor(Color.CYAN);
            g.fillRect(5, 95, 150, 60);
            g.setColor(Color.BLACK);
            g.drawString((game.getCurrentLevel() + 1) + "", 75, 130);
            g.setColor(Color.yellow);
            g.drawString("Puzzle", 5, 90);


            g.setColor(Color.CYAN);
            g.fillRect(5, 170, 150, 60);
            g.setColor(Color.BLACK);
            g.drawString(game.getMoves() + "", 75, 200);
            g.setColor(Color.yellow);
            g.drawString("Moves", 5, 165);

            resetButton.setPreferredSize(new Dimension(150, 40));
            resetButton.setLocation(5, 250);
            controlPanel.add(resetButton);
        }
    }
}
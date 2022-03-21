package ru.nsu.ccfit.matus.task3;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GraphicView implements View{

    private JFrame window;
    private Timer timer;
    private JButton bomb;
    private CellButton[] cells;
    private int x;
    private int y;
    private int N;
    private int bombs;
    private int[] t;
    private ArrayList<Integer> game_time;
    private ArrayBlockingQueue<String[]> gameEventQueue = new ArrayBlockingQueue<String[]>(100);
    private ImageIcon iconFlag;// = new ImageIcon("flag.png");
    private ImageIcon iconBomb;

    public GraphicView(Model model, int N) throws IOException {
        File file = new File("red-flag.png");
        BufferedImage input = ImageIO.read(file);
        iconFlag = new ImageIcon(input);

        file = new File("bomb.png");
        input = ImageIO.read(file);
        iconBomb = new ImageIcon(input);

        this.N = N;
        bombs = 10;
        window = new JFrame();
        //window.setLocationRelativeTo(null);
        window.setTitle("Minesweeper");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(420, 470));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu settings = new JMenu("Settings");
        JMenu size = new JMenu("Size");
        JMenuItem nine = new JMenuItem("9");
        nine.addActionListener(e -> {
            this.N = 9;
            this.bombs = 10;
            gameEventQueue.add(new String[]{"size", "9"});
        });
        size.add(nine);
        JMenuItem sixteen = new JMenuItem("16");
        sixteen.addActionListener(e -> {
            this.N = 16;
            this.bombs = 30;
            gameEventQueue.add(new String[]{"size", "16"});
        });
        size.add(sixteen);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(mainPanel, "Welcome to minesweeper!"));
        settings.add(size);
        settings.add(about);
        menuBar.add(settings);

        JButton time = new JButton();
        game_time = new ArrayList<>();
        t = new int[]{0};
        ActionListener taskPerformer = e -> {
            t[0]++;
            time.setText(Integer.toString(t[0]));
        };
        timer = new Timer(1000, taskPerformer);
        timer.start();

        bomb = new JButton("Bombs: " + this.bombs);

        JButton newGame = new JButton("New game");
        newGame.addActionListener(e -> {
            gameEventQueue.add(new String[]{"new"});
        });

        JButton score = new JButton("Score");
        score.addActionListener(e -> {
            ArrayList<Boolean> games = model.getScore();
            if(games.size() == 0){
                JOptionPane.showMessageDialog(mainPanel, "No games played yet");
            }
            else {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < games.size(); i++) {
                    if (games.get(i)) {
                        str.append("Game " + i + ": win. Time: " + game_time.get(i) + "\n");
                    } else {
                        str.append("Game " + i + ": lose. Time: " + game_time.get(i) + "\n");
                    }
                }
                JOptionPane.showMessageDialog(mainPanel, str);
            }
        });

        menu.add(menuBar);
        menu.add(time);
        menu.add(bomb);
        menu.add(newGame);
        menu.add(score);


        JPanel field = new JPanel();
        field.setLayout(new GridLayout(N, N));

        cells = new CellButton[N * N];
        for(int i = 0; i < N * N; i++){
            int idx = i;
            cells[i] = new CellButton("");
            if (model.getCell(i / N, i % N).isMine()){
                cells[i].setVal("*");
            }
            else {
                cells[i].setVal(Integer.toString(model.getCell(i / N, i % N).getNum()));
            }
            cells[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ((e.getButton() ==  MouseEvent.BUTTON3)) {
                        if(model.isEndGame()){
                            gameEventQueue.add(new String[]{"new"});
                        }
                        else if (!cells[idx].isOpened() && !cells[idx].isFlag()) {
                            GraphicView.this.bombs--;
                            bomb.setText("Bombs: " + GraphicView.this.bombs);
                            cells[idx].setFlag(true);
                            cells[idx].setIcon(iconFlag);
                            //cells[idx].setText("flag");
                        }
                        else if (!cells[idx].isOpened() && cells[idx].isFlag()){
                            GraphicView.this.bombs++;
                            bomb.setText("Bombs: " + GraphicView.this.bombs);
                            cells[idx].setFlag(false);
                            cells[idx].setIcon(null);
                            cells[idx].setText("");
                        }
                    }
                    else if ((e.getButton() ==  MouseEvent.BUTTON1)) {
                        if (!cells[idx].isFlag()) {
                            x = idx / N;
                            y = idx % N;
                            cells[idx].setOpened(true);
                            gameEventQueue.add(new String[]{Integer.toString(x), Integer.toString(y)});
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.setBackground(new Color(101, 126, 47));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    CellButton btn = (CellButton) e.getSource();
                    btn.setBackground(new Color(126, 152, 61));
                }
            });

            field.add(cells[i]);
        }

        mainPanel.add(menu, BorderLayout.NORTH);
        mainPanel.add(field, BorderLayout.CENTER);

        window.add(mainPanel);
        window.pack();
        window.setVisible(true);
    }

    @Override
    public String[] getNextStep() throws InterruptedException {
        return gameEventQueue.take();
    }

    @Override
    public void update(Model model) {
        for (int i = 0; i < N * N; i++) {
            if (model.getCell(i / N, i % N).isOpen()) {
                if(cells[i].getVal().equals("*")) {
                    cells[i].setIcon(iconBomb);
                }
                else {
                    cells[i].setText(cells[i].getVal());
                }
                cells[i].setOpened(true);
                if(cells[i].isFlag()) {
                    cells[i].setIcon(null);
                    cells[i].setFlag(false);
                    bombs++;
                }
            }
        }
        if (model.isEndGame()) {
            if (model.isWin()) {
                for (int i = 0; i < N * N; i++) {
                    if(cells[i].getVal().equals("*")) {
                        cells[i].setIcon(iconBomb);
                    }
                    else {
                        cells[i].setIcon(null);
                        cells[i].setText(cells[i].getVal());
                    }
                }
                JOptionPane.showMessageDialog(window, "You win!");
            } else {
                for (int i = 0; i < N * N; i++) {
                    if(cells[i].getVal().equals("*")) {
                        cells[i].setIcon(iconBomb);
                    }
                    else {
                        cells[i].setIcon(null);
                        cells[i].setText(cells[i].getVal());
                    }
                }
                JOptionPane.showMessageDialog(window, "Boom! You lose!");
            }
            if (N == 9){
                bombs = 10;
            }
            else{
                bombs = 30;
            }
            game_time.add(t[0]);
        }
        bomb.setText("Bombs: " + bombs);
    }

    @Override
    public void clear(Model model) {
        if (N == 9){
            bombs = 10;
        }
        else{
            bombs = 30;
        }
        t[0] = 0;
        for(int i = 0; i < N * N; i++){
            cells[i].setOpened(false);
            cells[i].setFlag(false);
            cells[i].setIcon(null);
            cells[i].setText("");
            if (model.getCell(i / N, i % N).isMine()){
                cells[i].setVal("*");
            }
            else {
                cells[i].setVal(Integer.toString(model.getCell(i / N, i % N).getNum()));
            }
        }
    }

    @Override
    public void quit() {

    }

    @Override
    public void start() {

    }

    @Override
    public void showAbout() {

    }

    @Override
    public void showScore(Model model) {

    }

    @Override
    public void showException(Exception e) {

    }

    @Override
    public void changeSize(Model model) {
        window.setVisible(false);
        window = new JFrame();
        //window.setLocationRelativeTo(null);
        window.setTitle("Minesweeper");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(420, 470));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu settings = new JMenu("Settings");
        JMenu size = new JMenu("Size");
        JMenuItem nine = new JMenuItem("9");
        nine.addActionListener(e -> {
            this.N = 9;
            gameEventQueue.add(new String[]{"size", "9"});
        });
        size.add(nine);
        JMenuItem sixteen = new JMenuItem("16");
        sixteen.addActionListener(e -> {
            this.N = 16;
            gameEventQueue.add(new String[]{"size", "16"});
        });
        size.add(sixteen);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(mainPanel, "Welcome to minesweeper!"));
        settings.add(size);
        settings.add(about);
        menuBar.add(settings);

        JButton time = new JButton();
        t[0] = 0;
        timer.stop();
        ActionListener taskPerformer = e -> {
            time.setText(Integer.toString(t[0]));
            t[0]++;
        };
        timer = new Timer(1000, taskPerformer);
        timer.start();

        bomb = new JButton("Bombs: " + this.bombs);

        JButton newGame = new JButton("New game");
        newGame.addActionListener(e -> {
            gameEventQueue.add(new String[]{"new"});
        });

        JButton score = new JButton("Score");
        score.addActionListener(e -> {
            ArrayList<Boolean> games = model.getScore();
            if(games.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "No games played yet");
            }
            else {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < games.size(); i++) {
                    if (games.get(i)) {
                        str.append("Game " + i + ": win. Time: " + game_time.get(i) + "\n");
                    } else {
                        str.append("Game " + i + ": lose. Time: " + game_time.get(i) + "\n");
                    }
                }
                JOptionPane.showMessageDialog(mainPanel, str);
            }
        });

        menu.add(menuBar);
        menu.add(time);
        menu.add(bomb);
        menu.add(newGame);
        menu.add(score);


        JPanel field = new JPanel();
        field.setLayout(new GridLayout(N, N));

        cells = new CellButton[N * N];
        for(int i = 0; i < N * N; i++){
            int idx = i;
            cells[i] = new CellButton("");
            if (model.getCell(i / N, i % N).isMine()){
                cells[i].setVal("*");
            }
            else {
                cells[i].setVal(Integer.toString(model.getCell(i / N, i % N).getNum()));
            }
            cells[i].addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ((e.getButton() ==  MouseEvent.BUTTON3)) {
                        if (!cells[idx].isOpened() && !cells[idx].isFlag()) {
                            GraphicView.this.bombs--;
                            bomb.setText("Bombs: " + GraphicView.this.bombs);
                            cells[idx].setFlag(true);
                            cells[idx].setIcon(iconFlag);
                            //cells[idx].setIcon(new ImageIcon("flag.png"));
                            //cells[idx].setText("flag");
                        }
                        else if (!cells[idx].isOpened() && cells[idx].isFlag()){
                            GraphicView.this.bombs++;
                            bomb.setText("Bombs: " + GraphicView.this.bombs);
                            cells[idx].setIcon(null);
                            cells[idx].setFlag(false);
                            cells[idx].setText("");
                        }
                    }
                    else if ((e.getButton() ==  MouseEvent.BUTTON1)) {
                        if (!cells[idx].isFlag()) {
                            x = idx / N;
                            y = idx % N;
                            cells[idx].setOpened(true);
                            gameEventQueue.add(new String[]{Integer.toString(x), Integer.toString(y)});
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.setBackground(new Color(101, 126, 47));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    CellButton btn = (CellButton) e.getSource();
                    btn.setBackground(new Color(126, 152, 61));
                }
            });

            field.add(cells[i]);
        }

        mainPanel.add(menu, BorderLayout.NORTH);
        mainPanel.add(field, BorderLayout.CENTER);

        window.add(mainPanel);
        window.pack();
        window.setVisible(true);
    }
}

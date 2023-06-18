package Utils;

import javax.swing.*;
import java.awt.*;

public class Program extends JFrame implements Runnable {

    public int width;
    public int height;
    public boolean isRunning;
    protected JPanel background = new JPanel();
    protected GridLayout layout = new GridLayout();

    public Program() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        width = (int)screen.getWidth();
        height = (int)screen.getHeight();

        run();
        isRunning = true;
    }

    public void close() {
        isRunning = false;
        this.setVisible(false);
        System.exit(0);
    }

    @Override
    public void run() {
        layout.setColumns(1);
        layout.setRows(1);

        background.setLayout(layout);
        background.setAutoscrolls(true);
        background.setBackground(Color.decode("#272727"));

        this.setTitle("Welcome to this program");
        this.setBounds(0, 0, width, height);
        this.setResizable(true);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(background);

//        this.setVisible(true);
    }
}

package Utils;

import org.apache.commons.imaging.Imaging;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

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
        try {
            List<BufferedImage> images = Imaging.getAllBufferedImages(new File("res/images/favicon.ico"));
            this.setIconImage(images.get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(background);

//        this.setVisible(true);
    }

    public static void addEmpty(JPanel panel, GridBagConstraints c, int n, int prev, int fontSize) {
        for (int i = prev + 1; i <= n + 1; i++) {
//            JPanel emptyPanel = new JPanel();
//            emptyPanel.setBackground(Color.decode(color));
            JLabel emptyText = new JLabel(" ");
            emptyText.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
            c.gridx = 0;
            c.gridy = i;
            panel.add(emptyText, c);
        }
    }
}

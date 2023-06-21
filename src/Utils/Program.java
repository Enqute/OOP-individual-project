package Utils;

import org.apache.commons.imaging.Imaging;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
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

    public class TextBubbleBorder extends AbstractBorder {

        private Color color;
        private int thickness = 4;
        private int radii = 8;
//        private int pointerSize = 7;
        private Insets insets = null;
        private BasicStroke stroke = null;
        private int strokePad;
        private int pointerPad = 4;
        private boolean left = true;
        RenderingHints hints;

        TextBubbleBorder(
                Color color) {
            this(color, 4, 8);
        }

        public TextBubbleBorder(
                Color color, int thickness, int radii) {
            this.thickness = thickness;
            this.radii = radii;
//            this.pointerSize = pointerSize;
            this.color = color;

            stroke = new BasicStroke(thickness);
            strokePad = thickness / 2;

            hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int pad = radii + strokePad;
            int bottomPad = pad + strokePad; //  + pointerSize
            insets = new Insets(pad, pad, bottomPad, pad);
        }

        public TextBubbleBorder(
                Color color, int thickness, int radii, boolean left) {
            this(color, thickness, radii);
//            this.left = left;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x, int y,
                int width, int height) {

            Graphics2D g2 = (Graphics2D) g;

            int bottomLineY = height - thickness; //  - pointerSize

            RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                    0 + strokePad,
                    0 + strokePad,
                    width - thickness,
                    bottomLineY,
                    radii,
                    radii);

            Polygon pointer = new Polygon();

//            if (left) {
//                // left point
//                pointer.addPoint(
//                        strokePad + radii + pointerPad,
//                        bottomLineY);
//                // right point
//                pointer.addPoint(
//                        strokePad + radii + pointerPad + pointerSize,
//                        bottomLineY);
//                // bottom point
//                pointer.addPoint(
//                        strokePad + radii + pointerPad + (pointerSize / 2),
//                        height - strokePad);
//            } else {
//                // left point
//                pointer.addPoint(
//                        width - (strokePad + radii + pointerPad),
//                        bottomLineY);
//                // right point
//                pointer.addPoint(
//                        width - (strokePad + radii + pointerPad + pointerSize),
//                        bottomLineY);
//                // bottom point
//                pointer.addPoint(
//                        width - (strokePad + radii + pointerPad + (pointerSize / 2)),
//                        height - strokePad);
//            }

            Area area = new Area(bubble);
            area.add(new Area(pointer));

            g2.setRenderingHints(hints);

            // Paint the BG color of the parent, everywhere outside the clip
            // of the text bubble.
            Component parent  = c.getParent();
            if (parent!=null) {
                Color bg = parent.getBackground();
                Rectangle rect = new Rectangle(0,0,width, height);
                Area borderRegion = new Area(rect);
                borderRegion.subtract(area);
                g2.setClip(borderRegion);
                g2.setColor(bg);
                g2.fillRect(0, 0, width, height);
                g2.setClip(null);
            }

            g2.setColor(color);
            g2.setStroke(stroke);
            g2.draw(area);
        }
    }
}

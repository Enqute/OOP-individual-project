package Views;

import Utils.Program;

import javax.swing.*;
import java.awt.*;

public class MainPage extends Program {

    public MainPage() {
        this.setVisible(true);
    }

    @Override
    public void run() {
        super.run();

        this.layout.setColumns(1);
        this.layout.setRows(2);
        this.layout.setHgap(10);
        this.layout.setVgap(10);

        this.background.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.5;
        con.weighty = 1;
        con.insets = new Insets(5, 5, 5, 5);

        JPanel upLeft = new JPanel();
        upLeft.setBackground(Color.decode("#212121"));

        con.gridx = 0;
        con.gridy = 0;
        this.background.add(upLeft, con);

        JPanel downLeft = new JPanel();
        downLeft.setBackground(Color.decode("#212121"));

        con.gridx = 0;
        con.gridy = 1;
        this.background.add(downLeft, con);

        JPanel right = new JPanel();
        right.setBackground(Color.decode("#212121"));
        
        con.gridx = 1;
        con.gridy = 0;
        con.gridheight = 2;
        this.background.add(right, con);
    }

}

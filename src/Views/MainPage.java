package Views;

import Utils.Program;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPage extends Program {

    private static boolean isTextType = true;
    private static boolean isAES = true;

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
        con.weightx = 1;
        con.weighty = 1;
        con.insets = new Insets(5, 5, 5, 5);

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        top.setBackground(Color.decode("#212121"));

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1;
        c1.weighty = 1;

        JPanel topTextPanel = new JPanel();
        topTextPanel.setLayout(new GridBagLayout());
        topTextPanel.setBackground(Color.decode("#212121"));

        JLabel topTitle = new JLabel("Welcome to this Program!", JLabel.CENTER);
        topTitle.setForeground(Color.decode("#3eb6f7"));
        topTitle.setFont(new Font("Segoe UI", Font.PLAIN, 70));
        c1.gridx = 0;
        c1.gridy = 0;
        topTextPanel.add(topTitle, c1);

        Program.addEmpty(topTextPanel, c1, 1, 0, 20);

        JLabel topDescription = new JLabel("This program has a bit cryptographic function, " +
                "which does encryption and decryption operation on file or a text!", JLabel.CENTER);
        topDescription.setForeground(Color.decode("#9a9a9a"));
        topDescription.setFont(new Font("Segoe UI", Font.BOLD, 16));
        c1.gridx = 0;
        c1.gridy = 2;
        topTextPanel.add(topDescription, c1);

        c1.gridx = 0;
        c1.gridy = 0;
        top.add(topTextPanel, c1);

        Program.addEmpty(top, c1, 1, 0, 30);

        JLabel typeTopLbl = new JLabel("Configuration");
        typeTopLbl.setForeground(Color.decode("#818181"));
        typeTopLbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        c1.gridx = 0;
        c1.gridy = 2;
        top.add(typeTopLbl, c1);

        JRadioButton fileRbtn = new JRadioButton("File");
        fileRbtn.setSize(200, 60);
        fileRbtn.setForeground(Color.decode("#ffffff"));
        fileRbtn.setBackground(Color.decode("#212121"));
        fileRbtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JRadioButton textRbtn = new JRadioButton("Plain Text");
        textRbtn.setSize(200, 60);
        textRbtn.setForeground(Color.decode("#ffffff"));
        textRbtn.setBackground(Color.decode("#212121"));
        textRbtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textRbtn.setSelected(true);

        ButtonGroup textTypeGrp = new ButtonGroup();
        textTypeGrp.add(fileRbtn);
        textTypeGrp.add(textRbtn);

        fileRbtn.addActionListener(e -> {
            if (isTextType)
                isTextType = false;
        });

        textRbtn.addActionListener(e -> {
            if (!isTextType)
                isTextType = true;
        });

        JPanel textTypePanel = new JPanel();
        textTypePanel.setBackground(Color.decode("#212121"));
//        textTypePanel.setSize(width, height);

        JLabel textTypeLbl = new JLabel("Text type", JLabel.LEFT);
        textTypeLbl.setForeground(Color.decode("#3eb6f7"));
        textTypeLbl.setFont(new Font("Segoe UI", Font.BOLD, 17));

        textTypePanel.add(textTypeLbl);
        textTypePanel.add(new JLabel("                    "));
        textTypePanel.add(fileRbtn);
        textTypePanel.add(new JLabel("    "));
        textTypePanel.add(textRbtn);
        JLabel empLbl1 = new JLabel("     ");
        for (int i = 0; i < 100; i++)
            empLbl1.setText(empLbl1.getText() + "     ");
        textTypePanel.add(empLbl1);

        JRadioButton aesRbtn = new JRadioButton("AES");
        aesRbtn.setSize(200, 60);
        aesRbtn.setForeground(Color.decode("#ffffff"));
        aesRbtn.setBackground(Color.decode("#212121"));
        aesRbtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        aesRbtn.setSelected(true);

        JRadioButton desRbtn = new JRadioButton("DES");
        desRbtn.setSize(200, 60);
        desRbtn.setForeground(Color.decode("#ffffff"));
        desRbtn.setBackground(Color.decode("#212121"));
        desRbtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        ButtonGroup algoTypeGrp = new ButtonGroup();
        algoTypeGrp.add(aesRbtn);
        algoTypeGrp.add(desRbtn);

        aesRbtn.addActionListener(e -> {
            if (!isAES)
                isAES = true;
        });

        desRbtn.addActionListener(e -> {
            if (isAES)
                isAES = false;
        });

        JPanel algoTypePanel = new JPanel();
        algoTypePanel.setBackground(Color.decode("#212121"));
//        algoTypePanel.setSize(width, height);

        JLabel algoTypeLbl = new JLabel("Algorithm type", JLabel.LEFT);
        algoTypeLbl.setForeground(Color.decode("#3eb6f7"));
        algoTypeLbl.setFont(new Font("Segoe UI", Font.BOLD, 17));

        algoTypePanel.add(algoTypeLbl);
        algoTypePanel.add(new JLabel("    "));
        algoTypePanel.add(aesRbtn);
        algoTypePanel.add(new JLabel("    "));
        algoTypePanel.add(desRbtn);
        JLabel empLbl2 = new JLabel("     ");
        for (int i = 0; i < 102; i++)
            empLbl2.setText(empLbl2.getText() + "     ");
        algoTypePanel.add(empLbl2);

        AbstractBorder typeBorder = new Program.TextBubbleBorder(Color.decode("#3c3c3c"), 1, 20);
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridBagLayout());
        typePanel.setBackground(Color.decode("#212121"));
        typePanel.setBorder(typeBorder);

        c1.gridx = 0;
        c1.gridy = 0;
        typePanel.add(textTypePanel, c1);
        c1.gridx = 0;
        c1.gridy = 1;
        typePanel.add(algoTypePanel, c1);

        c1.gridx = 0;
        c1.gridy = 3;
        top.add(typePanel, c1);

//        Program.addEmpty(top, c1, 5, 3, 40);

        JLabel cryptoLbl = new JLabel("Encryption & Decryption");
        cryptoLbl.setForeground(Color.decode("#818181"));
        cryptoLbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        c1.gridx = 0;
        c1.gridy = 5;
        top.add(cryptoLbl, c1);

        AbstractBorder cryptoBorder = new Program.TextBubbleBorder(Color.decode("#404040"), 1, 20);
        JPanel cryptoPanel = new JPanel();
        cryptoPanel.setLayout(new GridBagLayout());
        cryptoPanel.setBackground(Color.decode("#212121"));
        cryptoPanel.setBorder(cryptoBorder);

        

        c1.gridx = 0;
        c1.gridy = 6;
        top.add(cryptoPanel, c1);

        Program.addEmpty(top, c1, 15, 3, 40);

        con.gridx = 0;
        con.gridy = 0;
        this.background.add(top, con);
//
//        JPanel bottom = new JPanel();
//        bottom.setBackground(Color.decode("#212121"));
//
//        con.gridx = 0;
//        con.gridy = 1;
//        this.background.add(bottom, con);

//        JPanel middleRight = new JPanel();
//        middleRight.setBackground(Color.decode("#212121"));
//
//        con.gridx = 1;
//        con.gridy = 1;
//        con.gridwidth = 1;
//        this.background.add(middleRight, con);
//
//        JPanel bottom = new JPanel();
//        bottom.setBackground(Color.decode("#212121"));
//
//        con.gridx = 0;
//        con.gridy = 2;
//        con.gridwidth = 2;
//        this.background.add(bottom, con);
    }

}

package Views;

import Utils.Decrypt;
import Utils.Encrypt;
import Utils.Generator;
import Utils.Program;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MainPage extends Program {

    private static boolean isTextType = true;
    private static boolean isAES = true;

    public MainPage() {
        if (isAES) {
            Program.ivParams = Generator.generateIvForAES();
        } else {
            Program.ivParams = Generator.generateIvForDES();
        }
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
        c1.fill = GridBagConstraints.BOTH;
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

        Program.addEmpty(topTextPanel, c1, 1, 0, 20, true);

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

        Program.addEmpty(top, c1, 1, 0, 30, true);

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

        JPanel textTypePanel = new JPanel();
        textTypePanel.setBackground(Color.decode("#212121"));
//        textTypePanel.setSize(width, height);

        JLabel textTypeLbl = new JLabel("Text type", JLabel.LEFT);
        textTypeLbl.setForeground(Color.decode("#3eb6f7"));
        textTypeLbl.setFont(new Font("Segoe UI", Font.BOLD, 17));

        textTypePanel.add(textTypeLbl);
        textTypePanel.add(new JLabel("            "));
        textTypePanel.add(fileRbtn);
        textTypePanel.add(new JLabel("    "));
        textTypePanel.add(textRbtn);
        JLabel empLbl1 = new JLabel("     ");
        int n;
        if (width >= 1900) { // 1080 HD Screen resolution
            n = 102;
        } else {
            n = 65;
        }
        for (int i = 0; i < n; i++)
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

        aesRbtn.addActionListener(e -> {
            if (!isAES)
                isAES = true;
        });

        desRbtn.addActionListener(e -> {
            if (isAES)
                isAES = false;
        });

        ButtonGroup algoTypeGrp = new ButtonGroup();
        algoTypeGrp.add(aesRbtn);
        algoTypeGrp.add(desRbtn);

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
        for (int i = 0; i < n; i++)
            empLbl2.setText(empLbl2.getText() + "     ");
        algoTypePanel.add(empLbl2);

        AbstractBorder typeBorder = new TextBubbleBorder(Color.decode("#3c3c3c"), 1, 20);
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

        JLabel cryptoLbl = new JLabel("Operation");
        cryptoLbl.setForeground(Color.decode("#818181"));
        cryptoLbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        c1.gridx = 0;
        c1.gridy = 5;
        top.add(cryptoLbl, c1);

        AbstractBorder cryptoBorder = new TextBubbleBorder(Color.decode("#404040"), 1, 20);
        JPanel cryptoPanel = new JPanel();
        cryptoPanel.setLayout(new GridBagLayout());
        cryptoPanel.setBackground(Color.decode("#212121"));
        cryptoPanel.setBorder(cryptoBorder);

        JPanel leftSideCrypto = new JPanel();
        leftSideCrypto.setLayout(new GridBagLayout());
        leftSideCrypto.setBackground(Color.decode("#212121"));

        JLabel inputLbl = new JLabel("Input text");
        inputLbl.setForeground(Color.decode("#e5e5e5"));
        inputLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JTextArea inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        inputArea.setTabSize(4);
        inputArea.setWrapStyleWord(true);
        inputArea.setBackground(Color.decode("#323232"));
        inputArea.setForeground(Color.decode("#f5f5f5"));
        inputArea.setBorder(cryptoBorder);
        inputArea.setToolTipText("Enter the plain text here");

        c1.gridx = 0;
        c1.gridy = 0;
        leftSideCrypto.add(inputLbl, c1);
        c1.gridx = 0;
        c1.gridy = 1;
        leftSideCrypto.add(inputArea, c1);

        JPanel middleCrypto = new JPanel();
        middleCrypto.setLayout(new GridBagLayout());
        middleCrypto.setBackground(Color.decode("#212121"));

        AbstractBorder buttonBorder = new TextBubbleBorder(Color.decode("#1c94d5"), 2, 14);
        JButton encryptBtn = new JButton("Encrypt");
        encryptBtn.setBackground(Color.decode("#1c94d5"));
        encryptBtn.setForeground(Color.decode("#ffffff"));
        encryptBtn.setBorder(buttonBorder);
        encryptBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton decryptBtn = new JButton("Decrypt");
        decryptBtn.setBackground(Color.decode("#1c94d5"));
        decryptBtn.setForeground(Color.decode("#ffffff"));
        decryptBtn.setBorder(buttonBorder);
        decryptBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        c1.gridx = 0;
        c1.gridy = 0;
        middleCrypto.add(encryptBtn, c1);
        Program.addEmpty(middleCrypto, c1, 1, 0, 10, true);
        c1.gridx = 0;
        c1.gridy = 2;
        middleCrypto.add(decryptBtn, c1);

        JPanel rightSideCrypto = new JPanel();
        rightSideCrypto.setLayout(new GridBagLayout());
        rightSideCrypto.setBackground(Color.decode("#212121"));

        JLabel resultLbl = new JLabel("Result");
        resultLbl.setForeground(Color.decode("#e5e5e5"));
        resultLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JTextArea resultArea = new JTextArea();
        resultArea.setLineWrap(true);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultArea.setTabSize(4);
        resultArea.setWrapStyleWord(false);
        resultArea.setBackground(Color.decode("#323232"));
        resultArea.setForeground(Color.decode("#f5f5f5"));
        resultArea.setBorder(cryptoBorder);
//        resultArea.setEnabled(false);

        encryptBtn.addActionListener(ev -> {
            try {
                if (isTextType) {
                    resultArea.setText(Encrypt.encrypt(
                            isAES ? Program.AESAlgorithm : Program.DESAlgorithm,
                            inputArea.getText(), Program.key, Program.ivParams));
                } else {
                    String[] temp = inputArea.getText().split("\\.");
                    File outputFile = new File(temp[0] + ".encrypted");
                    File intputFile = new File(inputArea.getText());
                    Encrypt.encryptFile(
                            isAES ? Program.AESAlgorithm : Program.DESAlgorithm,
                            Program.key, Program.ivParams, intputFile, outputFile);
                    resultArea.setText(outputFile.toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        decryptBtn.addActionListener(ev -> {
            try {
                if (isTextType) {
                    resultArea.setText(Decrypt.decrypt(
                            isAES ? Program.AESAlgorithm : Program.DESAlgorithm,
                            inputArea.getText(), Program.key, Program.ivParams));
                } else {
                    String[] temp = inputArea.getText().split("\\.");
                    File outputFile = new File(temp[0] + ".decrypted");
                    File intputFile = new File(temp[0] + ".encrypted");
                    Decrypt.decryptFile(
                            isAES ? Program.AESAlgorithm : Program.DESAlgorithm,
                            Program.key, Program.ivParams, intputFile, outputFile);
                    resultArea.setText(outputFile.toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        fileRbtn.addActionListener(e -> {
            if (isTextType) {
                isTextType = false;
                inputLbl.setText("Input filepath");
                inputArea.setToolTipText("Enter file path here");
            }
        });

        textRbtn.addActionListener(e -> {
            if (!isTextType) {
                isTextType = true;
                inputLbl.setText("Input text");
                inputArea.setToolTipText("Enter plain text here");
            }
        });

        c1.gridx = 0;
        c1.gridy = 0;
        rightSideCrypto.add(resultLbl, c1);
        c1.gridx = 0;
        c1.gridy = 1;
        rightSideCrypto.add(resultArea, c1);

        c1.gridx = 0;
        c1.gridy = 0;
        cryptoPanel.add(leftSideCrypto, c1);

        Program.addEmpty(cryptoPanel, c1, 3, 0, 30, false);

        c1.gridx = 4;
        c1.gridy = 0;
        cryptoPanel.add(middleCrypto, c1);

        Program.addEmpty(cryptoPanel, c1, 6, 4, 30, false);

        c1.gridx = 11;
        c1.gridy = 0;
        cryptoPanel.add(rightSideCrypto, c1);

        c1.gridx = 0;
        c1.gridy = 6;
        top.add(cryptoPanel, c1);

        int vSpacing;
        if (width >= 1900)
            vSpacing = 15;
        else vSpacing = 7;
        c1.gridheight = 1;
        Program.addEmpty(top, c1, vSpacing, 6, 40, true);

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

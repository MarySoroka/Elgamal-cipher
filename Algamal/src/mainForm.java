import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class mainForm extends JFrame {

    //definition of components
    private  JComboBox<String> chooseCipher = new JComboBox<>();
    private   JLabel p = new JLabel("Your prime number - P");
    private JTextField keyP = new JTextField();
    private   JLabel x = new JLabel("Private key - X");
    private JTextField keyX = new JTextField();
    private   JLabel k = new JLabel("K");
    private JTextField keyK = new JTextField();
    private   JLabel root = new JLabel("Your roots");
    private JTextField keyRoot = new JTextField();
    private   JLabel g = new JLabel("Your selected root - G");
    private JTextField keyG = new JTextField();
    private   JLabel nameOfFile = new JLabel("Your file");
    private JButton openFile = new JButton("Find file");
    private  JButton enc = new JButton("DO");


    private mainForm() {

        //cretion of main form (size and so on)
        super("StreamSchifer");
        this.setBounds(100, 100, 1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(20, 1));
        p.setVerticalAlignment(JLabel.CENTER);
        keyP.addKeyListener(new KeyListener() {
            // limit for input
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE)))
                {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(p);
        container.add(keyP);
        k.setVerticalAlignment(JLabel.CENTER);
        keyK.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if (!((c >= '0') && (c <= '9')|| (c == KeyEvent.VK_DELETE)))
                {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(k);
        container.add(keyK);
        x.setVerticalAlignment(JLabel.CENTER);
        keyX.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if (!((c >= '0') && (c <= '9')|| (c == KeyEvent.VK_DELETE)))
                {
                    getToolkit().beep();
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(x);
        container.add(keyX);
        root.setVerticalAlignment(JLabel.CENTER);
        keyRoot.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == ' ') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
                {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(root);
        container.add(keyRoot);
        g.setVerticalAlignment(JLabel.CENTER);
        keyG.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE)))
                {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(g);

        container.add(keyG);
        container.add(nameOfFile);
        container.add(openFile);
        container.add(enc);
        openFile.addActionListener(new OpenFile());

        enc.addActionListener( new EventListener());


    }
    // listener of opening file
    class  OpenFile implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JFileChooser fileopen = new JFileChooser();
            fileopen.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int ret = fileopen.showDialog(null, "OpenFile");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                nameOfFile.setText(file.getAbsolutePath());

            }

        }
    }

    //output of 15  bytes
    private String output(String str) {
        if (str.length() >= 15*8) {
            return str.substring(0, 15*8) + "...";
        }
        return  str;
    }
    private void showResult(String message, String title) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }

    private void showRoots(List roots){
        String rootStr = "";
        for (int i = 0; i< roots.size(); i++){
            rootStr = rootStr + ", " + (roots.get(i)).toString();
        }
        keyRoot.setText(rootStr);
    }
    private BigInteger convertToBigInteger(String val){
        return new BigInteger(val,10);
    }
   //event class for components of main form
    class EventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                String key = " ";
                String text = " ";
                String cipher = " ";
                MathCalculations roots = new MathCalculations();
                List<BigInteger> listOfRoots = new LinkedList<>(roots.getPrimitivesRoot(convertToBigInteger(keyP.getText())));
                showRoots(listOfRoots);

                Algamal algamal = new Algamal(convertToBigInteger(keyP.getText()),convertToBigInteger(keyG.getText()),convertToBigInteger(keyX.getText()));


        }
    }
    //main function for creation of main form
    public static void main(String[] args) {
        mainForm app = new mainForm();
        app.setVisible(true);

    }
}
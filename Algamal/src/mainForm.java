import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class mainForm extends JFrame {
    private  JComboBox<String> chooseCipher = new JComboBox<>();
    private   JLabel p = new JLabel("P");
    private JTextField keyP = new JTextField();
    private   JLabel g = new JLabel("G");
    private JTextField keyG = new JTextField();
    private   JLabel x = new JLabel("X");
    private JTextField keyX = new JTextField();
    private   JLabel k = new JLabel("K");
    private JTextField keyK = new JTextField();
    private   JLabel root = new JLabel("Your roots");
    private JTextField keyRoot = new JTextField();
    private   JLabel nameOfFile = new JLabel("Your file");
    private JButton openFile = new JButton("Find file");
    private  JButton enc = new JButton("DO");


    private mainForm() {
        super("StreamSchifer");
        this.setBounds(100, 100, 1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(20, 1));
        p.setVerticalAlignment(JLabel.CENTER);
        container.add(p);
        container.add(keyP);
        g.setVerticalAlignment(JLabel.CENTER);
        container.add(g);
        container.add(keyG);
        k.setVerticalAlignment(JLabel.CENTER);
        container.add(k);
        container.add(keyK);
        x.setVerticalAlignment(JLabel.CENTER);
        container.add(x);
        container.add(keyX);
        root.setVerticalAlignment(JLabel.CENTER);
        container.add(root);
        container.add(keyRoot);
        container.add(nameOfFile);
        container.add(openFile);
        container.add(enc);


    }

    public static void main(String[] args) {
        mainForm app = new mainForm();
        app.setVisible(true);

    }
}
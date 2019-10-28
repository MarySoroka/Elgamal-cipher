import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class mainForm extends JFrame {

    //definition of components
    private   JLabel p = new JLabel("Your prime number - P");
    private JTextField keyP = new JTextField();
    private JButton generateRoots = new JButton("Generate roots");
    private   JLabel root = new JLabel("Your roots");
    private JTextField keyRoot = new JTextField();
    private   JLabel x = new JLabel("Private key - X");
    private JTextField keyX = new JTextField();
    private   JLabel k = new JLabel("K");
    private JTextField keyK = new JTextField();
    private JScrollPane scroll = new JScrollPane(keyRoot);
    private   JLabel g = new JLabel("Your selected root - G");
    private JTextField keyG = new JTextField();
    private   JLabel nameOfFile = new JLabel("Your file");
    private JButton openFile = new JButton("Find file");
    private  JButton enc = new JButton("DO");
    private  JComboBox<String> chooseAction = new JComboBox<>();

    private mainForm() {

        //cretion of main form (size and so on)
        super("StreamSchifer");
        this.setBounds(100, 100, 1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(20, 40));
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
        root.setVerticalAlignment(JLabel.CENTER);
        keyRoot.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if ((c == ' '))
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
        container.add(generateRoots);
        generateRoots.addActionListener(new GenerateRoots());
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



        container.add(nameOfFile);
        container.add(openFile);
        chooseAction.addItem("Encode");
        chooseAction.addItem("Decode");

        ComboboxCheck check = new ComboboxCheck();
        chooseAction.addItemListener(check);
        container.add(chooseAction);
        container.add(enc);
        openFile.addActionListener(new OpenFile());

        enc.addActionListener( new EventListener());


    }

    class ComboboxCheck implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (chooseAction.getSelectedIndex() == 1) {
                keyK.setVisible(true);
                keyG.setVisible(true);
                keyRoot.setVisible(false);
                k.setVisible(false);
                root.setVisible(false);
                g.setVisible(false);
                generateRoots.setVisible(false);
            } else{
                keyK.setVisible(true);
                keyG.setVisible(true);
                keyRoot.setVisible(true);
                k.setVisible(true);
                root.setVisible(true);
                g.setVisible(true);
                generateRoots.setVisible(true);
            }
        }
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
    // listener of opening file
    class  GenerateRoots implements ActionListener {
        public void actionPerformed(ActionEvent e){
            GetListOfRoots roots = new GetListOfRoots();
           // List<Integer> listOfRoots = new LinkedList<>();
            BigInteger p = new BigInteger(keyP.getText());
            if(!p.isProbablePrime(p.intValue())) {
                showResult("Value must be prime", "ERROR");
            }else{
           List<BigInteger>  listOfRoots = roots.getPrimitiveRoots(new BigInteger(keyP.getText()));
            showRoots(listOfRoots);}
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
        StringBuilder rootStr = new StringBuilder();
        int i =1;
        for (Object root1 : roots) {
            if (i ==1 ){
                rootStr.append(root1.toString());
            }else {
                rootStr.append(", ").append(root1.toString());
            }
            i++;
        }
        keyRoot.setText(String.valueOf(rootStr));
    }

    private BigInteger convertToBigInteger(String val){
        return new BigInteger(val,10);
    }


   //event class for components of main form
    class EventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                String[] result;
            BigInteger p = new BigInteger(keyP.getText());
            BigInteger k = new BigInteger(keyK.getText());
            BigInteger g = new BigInteger(keyG.getText());
            BigInteger x = new BigInteger(keyX.getText());
            boolean exeption = true;
            if(!p.isProbablePrime(p.intValue())) {
                showResult("Incorrect number P (It should be prime)", "ERROR");
                exeption = false;
            }else{

            if((x.compareTo(p.subtract(BigInteger.ONE))>= 0 ) || (x.compareTo(BigInteger.ONE) <= 0)) {
                showResult("Incorrect number X. (Need: 1 < x < p - 1)", "ERROR");
                exeption = false;
            }else{
            if((k.compareTo(p.subtract(BigInteger.ONE)) >= 0) || (k.compareTo(BigInteger.ONE) <=0 )) {
                showResult("Incorrect number K. (Need: 1 < k < p - 1)", "ERROR");
                exeption = false;
            }else{
            BigInteger num = p.subtract(BigInteger.ONE);
            if(!num.gcd(k).equals(BigInteger.ONE)) {
                showResult("Incorrect number K. (Need: gcd(p-1, k) == 1)", "ERROR");
                exeption = false;
            }else{
            List<BigInteger> primitiveRoots = GetListOfRoots.getPrimitiveRoots(p);
            if(!primitiveRoots.contains(g)) {
                showResult("G is not a primitive root of p", "ERROR");
                exeption = false;
            }}}}}
            if (exeption){
            try {
                switch (chooseAction.getSelectedIndex()){
                    case 0: {
                        Elgamal elgamal = new Elgamal();
                        result = ((elgamal.encElgamal(convertToBigInteger(keyP.getText()), convertToBigInteger(keyG.getText()), convertToBigInteger(keyX.getText()), convertToBigInteger(keyK.getText()), nameOfFile.getText(), 0)));
                        showResult(String.format("\nKey: %s\nSrc: %s\nFile: %s",
                                output(result[1]), output(result[0]), output(result[2])), "Encode");
                    }break;
                    case 1:{
                        Elgamal elgamal = new Elgamal();
                        result =((elgamal.encElgamal(convertToBigInteger(keyP.getText()), convertToBigInteger(keyG.getText()), convertToBigInteger(keyX.getText()), convertToBigInteger(keyK.getText()), nameOfFile.getText(), 1)));
                        showResult(String.format("\nKey: %s\nSrc: %s\nFile: %s",
                                output(result[1]), output(result[0]), output(result[2])), "Decode");
                    }break;
                    }

        }
            catch(IOException ex) {
                showResult("Invalid file", "ERROR");
            }
            catch (NumberFormatException ex) {
                showResult("Invalid register", "ERROR");
                ex.printStackTrace();
            }}
        }}
    //main function for creation of main form
    public static void main(String[] args) {
        mainForm app = new mainForm();
        app.setVisible(true);

    }
}
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

class Elgamal {


//class for encoder and decoder
    private BigInteger p;
    private BigInteger g;
    private BigInteger y;
    private BigInteger x;

    List<BigInteger> encElgamal(BigInteger p, BigInteger g, BigInteger x,String name) throws IOException {
        this.p = p;
        this.x = x;
        this.g = g;
        MathCalculations newY = new MathCalculations();
        this.y = newY.calculateY(x,g,p);
        readWriteFile file = new readWriteFile();
        List<BigInteger> text = new LinkedList<BigInteger>(file.readFromFile(name));
        List<BigInteger> cipher = encryption(text, p, x,y,g);
        file.WriteInFile(name,cipher);
        return cipher;
    }
    private List<BigInteger> encryption(List<BigInteger> plaintext, BigInteger p, BigInteger k, BigInteger y, BigInteger g) {
        List<BigInteger> ciphertext = new LinkedList<>();
        for(int i = 0; i < (plaintext.size()*2); i += 2) {
            BigInteger num = plaintext.get(i);
            ciphertext.add(MathCalculations.power(g,k,p));
            ciphertext.add((( (MathCalculations.power(y,k,p)).multiply((plaintext.get(i/2))).mod(p)))); // b
        }
        return ciphertext;
    }




    public List<BigInteger> decrypt(List<BigInteger> ciphertext,BigInteger p, BigInteger x) {
        List<BigInteger> plaintext = new LinkedList<>();

        for (int i = 0; i < (ciphertext.size() / 2); i += 2) {
            BigInteger a = ciphertext.get(i);
            BigInteger b = ciphertext.get(i + 1);
            BigInteger timimg = b.multiply(MathCalculations.power(MathCalculations.power(a, x, p),
                    BigInteger.valueOf(MathCalculations.ElerFunc(p.intValue()) - 1), p));
            plaintext.add(timimg.mod(p));
        }
        return plaintext;

    }
}

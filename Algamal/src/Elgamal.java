import java.awt.event.MouseAdapter;
import java.math.BigInteger;

public class Elgamal {
//class for encoder and decoder
     private  BigInteger k;
     private BigInteger privateKey;

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    //decoder
    public BigInteger[] encryption(byte[] plaintext) {
        BigInteger[] ciphertext = new BigInteger[][2*plaintext.length];

        BigInteger p = smthingToWorkWith.getP();
        BigInteger g = smthingToWorkWith.getG();
        BigInteger y = smthingToWorkWith.getY();

        for(int i = 0; i < ciphertext.length; i += 2) {
            ciphertext[i] = MathCalculations.power(g,k,p); // a
            ciphertext[i + 1] = (int) ((MathCalculations.power(y,k,p) * (plaintext[i/2])) % p); // b
        }

        return ciphertext;
    }

    public byte[] decryption(int[] ciphertext) {
        byte[] plaintext = new byte[ciphertext.length/2];

        long p = smthingToWorkWith.getP();

        for(int i = 0; i < ciphertext.length ; i += 2) {
            int a = ciphertext[i];
            int b = ciphertext[i + 1];
            plaintext[i/2] = (byte) ( b * MathCalculations.power(MathCalculations.power(a,privateKey, p),
                    MathCalculations.ElerFunc((int) p)-1, p) % p);
        }
        return plaintext;
    }

}

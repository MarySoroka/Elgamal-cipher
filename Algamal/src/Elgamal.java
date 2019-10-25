import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

class Elgamal {


    List<BigInteger> encElgamal(BigInteger p, BigInteger g, BigInteger x,String name) throws IOException {
        GetListOfRoots newY = new GetListOfRoots();
        BigInteger y = newY.calculateY(x,g,p);
        readWriteFile file = new readWriteFile();
        List<BigInteger> text = new LinkedList<BigInteger>(file.readFromFile(name));
        List<BigInteger> cipher = encryption(text, p, x,y,g);
        file.WriteInFile(name,cipher);
        return cipher;
    }
    private List<BigInteger> encryption(List<BigInteger> plaintext, BigInteger p, BigInteger k, BigInteger y, BigInteger g) {
        List<BigInteger> ciphertext = new LinkedList<>();
        BigInteger promezutok;
        for(int i = 0; i < (plaintext.size()); i += 1) {
            BigInteger num = plaintext.get(i);
            promezutok = GetListOfRoots.power(g,k,p);
            ciphertext.add(promezutok);
            promezutok = GetListOfRoots.power(y,k,p);
            promezutok = promezutok.multiply(plaintext.get(i));
            promezutok = promezutok.mod(p);
            ciphertext.add(promezutok); // b
        }
        return ciphertext;
    }




    public List<BigInteger> decrypt(List<BigInteger> ciphertext,BigInteger p, BigInteger x) {
        List<BigInteger> plaintext = new LinkedList<>();

        for (int i = 0; i < (ciphertext.size() / 2); i += 2) {
            BigInteger a = ciphertext.get(i);
            BigInteger b = ciphertext.get(i + 1);
            BigInteger timimg = b.multiply(GetListOfRoots.power(GetListOfRoots.power(a, x, p),
                    GetListOfRoots.ElerFunc(p).subtract(BigInteger.ONE), p));
            plaintext.add(timimg.mod(p));
        }
        return plaintext;

    }
}

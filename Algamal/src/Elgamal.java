import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

class Elgamal {


    String[] encElgamal(BigInteger p, BigInteger g, BigInteger x, BigInteger k , String name, int type) throws IOException {

        GetListOfRoots newY = new GetListOfRoots();
        BigInteger y = newY.calculateY(x,g,p);
        readWriteFile file = new readWriteFile();
        List<BigInteger> text = new LinkedList<BigInteger>(file.readFromFile(name));
        List<BigInteger> cipher;
        String s;
        if (type == 0){
            cipher = encryption(text, p,k,y,g);
            s = ".encode";
            file.WriteInFile(name+s,cipher);
            return new String[]{bigToStr(text),bigToStr(covertToList(p,g,y)), bigToStr(cipher)};
        }else{
            cipher = decrypt(text, p,x);
            s = ".decode";
            file.WriteInFile(name+s,cipher);
            return new String[]{bigToStr(text),bigToStr(covertToList(p,g,y)), bigToStr(cipher)};
        }

    }
    private String bigToStr (List<BigInteger> list){
        String str = " ";
        for(BigInteger num : list){
            str = str + " " + num.toString(10);
        }
        return str;
    }
    private List<BigInteger> covertToList(BigInteger p,BigInteger g,BigInteger y){
        List<BigInteger> publicKey =new LinkedList<>();
        publicKey.add(p);
        publicKey.add(g);
        publicKey.add(y);
        return publicKey;
    }
    private List<BigInteger> encryption(List<BigInteger> plaintext, BigInteger p, BigInteger k, BigInteger y, BigInteger g) {
        List<BigInteger> ciphertext = new LinkedList<>();
        BigInteger promezutok;
        for(int i = 0; i < (plaintext.size()); i += 1) {
            BigInteger num = plaintext.get(i);
            promezutok = GetListOfRoots.power(g,k,p);
            ciphertext.add(promezutok);
            promezutok = y.pow(k.intValue());
            promezutok = promezutok.multiply(plaintext.get(i));
            promezutok = promezutok.mod(p);
            ciphertext.add(promezutok); // b
        }
        return ciphertext;
    }
    public List<BigInteger> decrypt(List<BigInteger> ciphertext,BigInteger p, BigInteger x) {
        List<BigInteger> plaintext = new LinkedList<>();

        for (int i = 0; i < (ciphertext.size() ); i += 2) {
            BigInteger a = ciphertext.get(i);
            BigInteger b = ciphertext.get(i + 1);
            BigInteger timimg = a.pow(x.intValue());
            timimg = (BigInteger.ONE).divide(timimg);
            timimg = b.multiply(timimg);
            timimg = timimg.mod(p);
            plaintext.add(timimg);
        }
        return plaintext;

    }





}

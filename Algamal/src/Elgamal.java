import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

class Elgamal {


    String[] encElgamal(long p, long g, long x, long k , String name, int type) throws IOException {

        GetListOfRoots newY = new GetListOfRoots();
        Long y = newY.calculateY(x,g,p);
        readWriteFile file = new readWriteFile();

        int[] cipher;
        byte[] decipher;
        String s;
        if (type == 0){
            byte[] text = file.readFromFile(name);
            cipher = encryption(text, p,k,y,g);
            s = ".encode";
            file.WriteInFile(name+s,cipher);
            return new String[]{textToStrForByte(text),keyToStr(covertToList(p,g,y)), textToStrForInt(cipher)};
        }else{
            int[] text = file.readFromFileDecrypt(name);
            decipher = decrypt(text, p,x);
            s = ".decode";
            file.WriteInFileDecrypt(name+s,decipher);
            return new String[]{textToStrForInt(text),keyToStr(covertToList(p,g,y)), textToStrForByte(decipher)};
        }

    }

    private String textToStrForInt (int[] list){
        String str = " ";
        for(int num : list){
            str = str + " " + num;
        }
        return str;
    }

    private String textToStrForByte (byte[] list){
        String str = " ";
        for(byte num : list){
            str = str + " " + unsignedToBytes(num);
        }
        return str;
    }

    private String keyToStr (List<Long> list){
        String str = " ";
        for(Long num : list){
            str = str + " " + Long.toString(num,10);
        }
        return str;
    }
    private List<Long> covertToList(long p,long g,long y){
        List<Long> publicKey =new LinkedList<>();
        publicKey.add(p);
        publicKey.add(g);
        publicKey.add(y);
        return publicKey;
    }

    private int[] encryption(byte[] plaintext, long p, long k, long y, long g) {
        int[] ciphertext = new int[2*plaintext.length];
        for(int i = 0; i < ciphertext.length; i += 2) {
            ciphertext[i] = (int) GetListOfRoots.power(g,k,p); // a
            ciphertext[i + 1] = (int) ((GetListOfRoots.power(y,k,p) * unsignedToBytes(plaintext[i/2])) % p); // b
        }
        return ciphertext;
    }
    private static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }

    private byte[] decrypt(int[] ciphertext, long p, long x) {
        byte[] plaintext = new byte[ciphertext.length/2];


        for(int i = 0; i < ciphertext.length-1 ; i += 2) {
            int a = ciphertext[i];
            int b = ciphertext[i + 1];
            plaintext[i/2] = (byte) ( b * GetListOfRoots.power(GetListOfRoots.power(a,x, p), GetListOfRoots.phi((int) p)-1, p) % p);
        }
        return plaintext;
    }





}

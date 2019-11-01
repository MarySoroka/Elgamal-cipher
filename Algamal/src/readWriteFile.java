import java.io.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class readWriteFile {

    List<BigInteger> readFromFile (String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        List<BigInteger> text = new LinkedList<>();
        byte[] inpText = file.readAllBytes();
        BigInteger someCharecter;
        for (int i =0; i <inpText.length; i++){
            someCharecter = (BigInteger.valueOf(((int)inpText[i])).abs());
            text.add(someCharecter);
        }
        file.close();
        return text;
    }
    void WriteInFile (String fileName,List<BigInteger> cipherText) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        int[] l = new int[cipherText.size()*2];
        int i = 0;
        for (BigInteger bigInteger : cipherText) {
            int a = bigInteger.intValue();
            if (a < 256 ){
                l[i] = 0;
                BigInteger num = new BigInteger(String.valueOf(l[i]));
                file.write(num.toByteArray());
                l[i+1] = bigInteger.intValue();
                num = new BigInteger(String.valueOf(l[i+1]));
                file.write(num.toByteArray());
                i +=2;

            }else{
                l[i] = bigInteger.intValue();
                BigInteger num = new BigInteger(String.valueOf(l[i]));
                file.write(num.toByteArray());
                i ++;
            }

        }
        file.close();
    }
    void WriteInFileDecrypt (String fileName,List<BigInteger> cipherText) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        for (BigInteger bigInteger : cipherText) {
                file.write(bigInteger.toByteArray());
        }
        file.close();
    }

    List<BigInteger> readFromFileDecrypt(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        List<BigInteger> text = new LinkedList<>();
        byte[] inpText = file.readAllBytes();
        byte[] num = new byte[2];

        BigInteger someCharecter;
        for (int i =0; i <inpText.length-1; i += 2){
            num[0] = inpText[i];
            num[1] = inpText[i+1];
            someCharecter = new BigInteger(num);
            text.add(someCharecter);
        }
        file.close();
        return text;
    }


}

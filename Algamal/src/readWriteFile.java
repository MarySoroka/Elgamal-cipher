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
            someCharecter = BigInteger.valueOf((long)inpText[i]);
            text.add(someCharecter);
        }
        return text;
    }
    void WriteInFile (String fileName,List<BigInteger> cipherText) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        for (BigInteger bigInteger : cipherText) {
            file.write((bigInteger.toByteArray()));
        }
    }

}

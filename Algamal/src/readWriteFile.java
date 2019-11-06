import java.io.*;

class readWriteFile {

    byte[] readFromFile (String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        byte[] inpText = file.readAllBytes();
        file.close();
        return inpText;
    }
    void WriteInFile (String fileName,int[] cipherText) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        file.write(int2byte(cipherText));
        file.close();
    }
    void WriteInFileDecrypt (String fileName,byte[] cipherText) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        file.write(cipherText);
        file.close();
    }

    int[] readFromFileDecrypt(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);

        byte[] inpText = file.readAllBytes();
        int[] text = byte2int(inpText);
        file.close();

        return text;
    }

    private static byte[] int2byte(int[] src) {
        int srcLength = src.length;
        byte[]dst = new byte[srcLength << 2];

        for (int i=0; i<srcLength; i++) {
            int x = src[i];
            int j = i << 2;
            dst[j++] = (byte) ((x) & 0xff);
            dst[j++] = (byte) ((x >>> 8) & 0xff);
            dst[j++] = (byte) ((x >>> 16) & 0xff);
            dst[j++] = (byte) ((x >>> 24) & 0xff);
        }
        return dst;
    }

    private static int[] byte2int(byte[] src) {
        int dstLength = src.length >>> 2;
        int[]dst = new int[dstLength];

        for (int i=0; i<dstLength; i++) {
            int j = i << 2;
            int x = 0;
            x += (src[j++] & 0xff);
            x += (src[j++] & 0xff) << 8;
            x += (src[j++] & 0xff) << 16;
            x += (src[j++] & 0xff) << 24;
            dst[i] = x;
        }
        return dst;
    }



}

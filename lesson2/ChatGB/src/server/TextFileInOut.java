package server;

import java.io.*;

public class TextFileInOut {


    public static void writeHistory(String str, String file) {
        byte[] outData = str.getBytes();
        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(file, true);
            outFile.write(outData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readHistory(String file) {
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(file);
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);
        return text;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}



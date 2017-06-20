package com.hx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class NioApp {
    public static void main(String[] args) {
        File f = new File("/tmp/TestWeb.log.2016-11-14.log");
        try (
                FileChannel inChannel = new FileInputStream(f).getChannel();
                FileChannel outChannel = new FileOutputStream("/tmp/copy.txt").getChannel();
        ) {
            MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            outChannel.write(byteBuffer);
            byteBuffer.clear();
            CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
            CharBuffer charBuffer = decoder.decode(byteBuffer);
            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] star = new int[10];
        int[] star2 = new int[10];
        System.out.println(star == star2);
        Map<int[], String> map = new HashMap<>();
        System.out.println("Hello World!");
    }
}

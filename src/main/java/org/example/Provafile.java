package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


public class Provafile {

    public static void main(String[] args) {
        String frase = " prima riga file \n";

        File file = new File("filepath/file.txt");

        try{
            FileUtils.writeStringToFile(file, frase, Charset.defaultCharset(), true);

            String s = FileUtils.readFileToString(file, Charset.defaultCharset());
            System.out.println(s);

            FileUtils.createParentDirectories(file);

            File file2 = new File("filepath");

            System.out.println(file2.length());

            System.out.println(file2.getAbsolutePath());

            System.out.println(Arrays.toString(file2.list()));

            Arrays.stream(file2.list()).forEach(System.out::println);

            System.out.println(file2.list().length);

            /* FileUtils.deleteQuietly(file);
            FileUtils.deleteDirectory(new File("filepath")); */


            List<String> lista = FileUtils.readLines(file, Charset.defaultCharset());

            System.out.println(lista);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}

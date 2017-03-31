package ch.hesge.capitao.techmarket.outils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author jonathan.capitao
*/
public class FileToStr {

    private static final int EOF = -1;
  
    // Lit un fichier texte et retourne son contenu.
    public static String read (String fileName) {
        try {
            FileInputStream fileToRead = new FileInputStream(fileName);
            StringBuilder reader = new StringBuilder(fileToRead.available());
            int charNum = fileToRead.read();
            while (charNum != EOF) {
              reader.append((char)charNum);
              charNum = fileToRead.read();
            }
            fileToRead.close();
            return reader.toString();
        }
        catch (FileNotFoundException e0) {
            System.err.println("Erreur : "+e0.getMessage());
            return "";
        }
        catch (IOException e1) {
            System.err.println("Erreur Systême : "+e1.getMessage());
            return "";
        }
    } // read

    // Ecrit dans un fichier texte
    public static void write (String fileName, String[] str) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(fileName), 
                    java.nio.charset.Charset.forName("ISO-8859-1")
            );
            for (int lignActu = 0; lignActu < str.length; lignActu++) {
              writer.write(str[lignActu], 0, str[lignActu].length());
              writer.write("\r\n", 0, 2);
            }
            writer.close();
        }
        catch (FileNotFoundException e0) {
            System.err.println("Erreur : "+e0.getMessage());
        }
        catch (IOException e1) {
            System.err.println("Erreur Systême : "+e1.getMessage());
        }
    } // write
} // FileToStr
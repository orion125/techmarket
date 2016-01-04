package capitao.outils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author jonathan.capitao
*/
public class FileToStr {

  private static final int EOF = -1;
  
  public static String read (String fileName) {
    try {
      FileInputStream f = new FileInputStream(fileName);
      StringBuilder b = new StringBuilder(f.available());
      int charNum = f.read();
      while (charNum != EOF) {
        b.append((char)charNum);
        charNum = f.read();
      }
      f.close();
      return b.toString();
    }
    catch (FileNotFoundException e0) {e0.printStackTrace(); return "";}
    catch (IOException e1) {e1.printStackTrace(); return "";}
  } // read

} // FileToStr
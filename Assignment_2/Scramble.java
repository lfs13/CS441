import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Scramble {
  
  boolean wordsleft = true;
  private String word = "";
  private Scanner getword;
  private File words;
  private boolean called = true;
  private StringBuilder holder= new StringBuilder("");
    
    public Scramble(String s) {
    try {
      words = new File(s);
      getword = new Scanner(words);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
    
    public String getRealWord() {
      
      if (getword.hasNextLine() && called==true) {
        word = getword.next();
        getword.nextLine();
      }else if(called==false){
        word=word;
      }else
        word = null;
      
      called = false;
      return word;
      
    }
    
    public String getScrambledWord() {
      if(called)
        return holder.toString();
      
      String scrambler ="";
      
      StringBuilder reg = new StringBuilder(word);
      StringBuilder scram = new StringBuilder();
      while (reg.length() != 0) {
        int randPicker = (int) (Math.random() * reg.length());
        scram.append(reg.charAt(randPicker));
        reg.deleteCharAt(randPicker);
      }
      scrambler += scram;
      holder.replace(0,holder.length(),scrambler);
      called = true;
      return scrambler;
      
    }
    
    
    
}

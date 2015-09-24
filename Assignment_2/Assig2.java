import java.util.Scanner;

public class Assig2 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome new player, please enter your name:");
    String player = input.nextLine();
    boolean play = true;
    Scramble s = new Scramble("words.txt");
    Results r = new Results("results.txt");
    // Play Loop//
    while (play == true) {
      boolean win = false;
      String word = s.getRealWord();
      if (word == null)
        break;
      String scramble = s.getScrambledWord();
      System.out.println(word);
      System.out.println(player + ", you have 3 guesses, good luck!");
      for (int i = 3; i > 0; i--) {
        System.out.println("You have " + i + " guesses left.");
        System.out.println("Scramble:" + scramble);
        System.out.println("Enter a Guess:");
        String guess = input.next();
        if (guess.equalsIgnoreCase(word)) {
          win = true;
          break;
        }
        
      }
      
      if (win) {
        System.out.println("Yay! You won this round!");
        r.won();
      } else {
        System.out.println("Round over. The correct word was " + word
                             + ".");
        r.lost();
      }
      
      System.out.println("Play again?(y/n)");
      String y = input.next();
      if (y.equalsIgnoreCase("n"))
        play = false;
      
    }
    r.saved();
    System.out.println("Game over. Here are the results:");
    System.out.println(r.toString());
    
  }
}

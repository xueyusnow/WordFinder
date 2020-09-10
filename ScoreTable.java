import java.util.HashMap;
import java.util.Map;

public class ScoreTable {
   private char[] letters;
   private Map<Character, Integer> score;

   /**
    * Store all character and their score in a map.
    */
   
   public ScoreTable(){
      letters = new char[]{'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
      score = new HashMap<Character, Integer>();
      for(int i = 0; i < letters.length; i++){
         if(letters[i] == 'a' || letters[i] == 'e' || letters[i] == 'i' || letters[i] == 'o' || letters[i] == 'l' || letters[i] == 'u' || letters[i] == 'n' || letters[i] == 's' || letters[i] == 't' || letters[i] == 'r'){
            score.put(letters[i], 1);
            score.put((char) (letters[i] - 32), 1);
         }
         else if(letters[i] == 'd' || letters[i] == 'g'){
            score.put(letters[i], 2);
            score.put((char) (letters[i] - 32), 2);
         }
         else if(letters[i] == 'b' || letters[i] == 'c' || letters[i] == 'm' || letters[i] == 'p'){
            score.put(letters[i], 3);
            score.put((char) (letters[i] - 32), 3);
         }
         else if(letters[i] == 'a' || letters[i] == 'f' || letters[i] == 'h' || letters[i] == 'v' || letters[i] == 'w' || letters[i] == 'y'){
            score.put(letters[i], 4);
            score.put((char) (letters[i] - 32), 4);
         }
         else if(letters[i] == 'k'){
            score.put(letters[i], 5);
            score.put((char) (letters[i] - 32), 5);
         }
         else if(letters[i] == 'j' || letters[i] == 'x'){
            score.put(letters[i], 8);
            score.put((char) (letters[i] - 32), 8);
         }
         else{
            score.put(letters[i], 10);
            score.put((char) (letters[i] - 32), 10);
         }
      }
	      
   }
   
   /**
    * Get the score of the input word.
    * @param word  the word needs to compute the score.
    * @return  the score of the given word.
    */
   
   public Integer getScore(String word){
      int sum = 0;
      for(int i = 0; i < word.length(); i++){
         sum += score.get(word.charAt(i));
      }
      return sum;
   }
}

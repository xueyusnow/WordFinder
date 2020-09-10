import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
   This contains the main method. This class will have a main that's responsible for processing the    command-line argument, and handling any error processing. It also have the main command loop. 
*/
public class WordFinder{
   public static void main(String[] args) throws FileNotFoundException {       
	  AnagramDictionary dictionary = new AnagramDictionary("sowpods.txt");
      String filename = new String();
      try {
         if(args.length != 0){
            filename = args[0];
            dictionary = new AnagramDictionary(filename);
         }
         Scanner in = new Scanner(System.in);
         boolean first = false;
         aa: while(in.hasNextLine()){
            String word = in.nextLine();
            if(word.equals(".")){
               break aa;
            }
            else{
               Rack rack = new Rack(word, dictionary);
               if(first == false){
                  System.out.println("Type . to quit.");
                  first = true;
               }
               rack.oneRack(); 
            }
         }
         System.out.println("Rack? ");
      }
      catch (FileNotFoundException exception){
         System.out.println("File not found: " + filename);
      }
   }
}

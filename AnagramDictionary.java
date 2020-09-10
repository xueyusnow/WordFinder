//Name: Xueyu Wang
//USC NetID: 2670589054
//CS 455 PA4
//Fall 2019

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

   /**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
   */

public class AnagramDictionary {
   private Set<String> allwords;
   private Map<String, ArrayList<String>> anadictionary;
  
   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName. 
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      allwords = new HashSet<String>();
      anadictionary = new HashMap<String, ArrayList<String>>();
      File file = new File(fileName);
      Scanner in = new Scanner(file);
      while(in.hasNext()){
         String word = in.next();
         this.allwords.add(word);
      }
      Iterator<String> iter = allwords.iterator();
      while(iter.hasNext()) {
    	  String ana = iter.next();
    	  handleDictionary(ana);
	  }
      
   }
   /**
    * Preprocessing the dictionary so that you organize the words by the set of letters 
    * each one contains (this set is actually a multiset, because letters can appear 
    * more than once in a word; the rack itself is also a multiset).
    * @param word  the string word that needs to find multiset and contributes to the anagram dictionary.
    */
   private void handleDictionary(String word) {
	   char[] alph = word.toCharArray();
       Arrays.sort(alph);
       String alphword = String.valueOf(alph);
       if(!this.anadictionary.containsKey(alphword)) {
    	   anadictionary.put(alphword, new ArrayList<String>());
    	   anadictionary.get(alphword).add(word);
       }
       else {
    	   anadictionary.get(alphword).add(word);
       }
   }
   
   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string needs to process
    * @return a list of the anagrams of s
    */
   
   public ArrayList<String> getAnagramsOf(String s) {
	   char[] gets = s.toCharArray();
       Arrays.sort(gets);
       String alls = String.valueOf(gets);
       return anadictionary.get(alls);
   }
   /**
    * Return the set of all words in the dictionary.
    * @return the anagram dictionary.
    */
   public Map<String, ArrayList<String>> getDictionary() {
	   return this.anadictionary;
   }
}

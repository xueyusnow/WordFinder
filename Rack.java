//Name: Xueyu Wang
//USC NetID: 2670589054
//CS 455 PA4
//Fall 2019

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
A Rack of Scrabble tiles
*/

public class Rack {
   private AnagramDictionary ad;
   private String unique;
   private ScoreTable st;
   private String word;
   private TreeMap<String, Integer> onerack;

   /**
    * Creat an empty rack and initialize all the fields.
    * @param s  the string need to handle in a rack.
    * @param ad  the anagram dictionary used by the rack.
    */
   
   public Rack(String s, AnagramDictionary ad){
      onerack = new TreeMap<String, Integer>();
      word = new String(s);
      st = new ScoreTable();
      unique = new String();
      this.ad = ad;
   } 
   
   /**
    * Create variables for one rack.
    * @return  the multiplicity of each character of one word.
    */
   
   private int[] handleRack(){
      int j = 0;
      Map<Character, Integer> uniqueset = new TreeMap<Character, Integer>();		
      for(int i = 0; i < word.length(); i++){
         if(uniqueset.containsKey(word.charAt(i))){
            uniqueset.put(word.charAt(i), uniqueset.get(word.charAt(i)) + 1);
         }
         else {
            uniqueset.put(word.charAt(i), 1);
         }
      }
      int[] mult = new int[uniqueset.size()];
      Iterator<Map.Entry<Character, Integer>> iter = uniqueset.entrySet().iterator();
      while(iter.hasNext()) {
         Map.Entry<Character, Integer> temp = iter.next();
         unique += Character.toString(temp.getKey());
         mult[j++] = temp.getValue();
      }
      return mult;
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
   
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
   
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
   
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
   
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                     // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
   
      return allCombos;
   }

   /**
    * Output all subsets and anagrams of one rack in the order required which is in decreasing order by score and for words with the 
	* same scrabble score in alphabetical order by words.
    */
   
   public void oneRack(){
      int[] mult = handleRack();
      ArrayList<String> onewordsubsets = allSubsets(this.unique, mult, 0);
      for(int i = 0; i < onewordsubsets.size(); i++){
    	  char[] wordsubset = onewordsubsets.get(i).toCharArray();
    	  Arrays.sort(wordsubset);
    	  String alphword = String.valueOf(wordsubset);
    	  if(ad.getDictionary().containsKey(alphword)) {
    		  for(int j = 0; j < ad.getAnagramsOf(alphword).size(); j++) {
    			  onerack.put(ad.getAnagramsOf(alphword).get(j), st.getScore(alphword));
    		  }
    	  }
      }
      List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(onerack.entrySet());
      Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
         public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
            if(o1.getValue() < o2.getValue()){
               return 1;
            }
            else if(o1.getValue() > o2.getValue()){
               return -1;
            }
            else{
               return o1.getKey().compareTo(o2.getKey());
            }
         }
      });
      System.out.println("Rack? We can make "+ onerack.size() +" words from \""+ word +"\"");
      if(onerack.size() != 0) {
	      System.out.println("All of the words with their scores (sorted by score):");
	      for (Map.Entry<String, Integer> curr : list) {
	   	   System.out.println(curr.getValue() + ":" + curr.getKey());
	      }
      }
   }
}

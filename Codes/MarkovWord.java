import java.util.*;

public class MarkovWord  implements IMarkovModel {
      private String[] myText;
      private Random myRandom;
      private int myOrder;
      
      public MarkovWord(int order){
          myOrder = order;
          myRandom = new Random();
      }
        
      public void setRandom(int seed) {
    	  myRandom = new Random(seed);
      }
      
      public void setTraining(String text){
    	  myText = text.split("\\s+");
      }
	
      private int indexOf(String[] words, WordGram key, int startPos){
    	  int count =words.length-myOrder+1;
    	  for (int k =startPos; k<count; k++){
    		  WordGram currentWG = new WordGram(words,k,myOrder);
    		  if (currentWG.equals(key)){
    			  return k;
    		  }
    	  }
    	  return -1;
      }

      public ArrayList<String> getFollows (WordGram kGram){ 
    	  ArrayList<String> follows = new ArrayList<String>();
    	  int pos = 0;
    	  while (pos<myText.length){
    		  int start = indexOf(myText,kGram,pos);
    		  if (start ==-1){
    			  break;
	          }
    		  if (start + myOrder>=myText.length){
    			  break;
    		  }
    		  String next = myText[start+myOrder];
    		  follows.add(next);
    		  pos = start+1;
	      }
	      return follows;
	 }
	  
	   
      public String getRandomText(int numWords){
    	  StringBuilder sb = new StringBuilder();
    	  int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
    	  String[] keys = new String[myOrder];
    	  for ( int i =0; i< keys.length;i++){
		       keys[i]=myText[index];
		       sb.append(myText[index]);
		       sb.append(" ");
		       index +=1;
		  }
    	  WordGram key = new WordGram(keys,0,keys.length);
    	  for(int k=0; k < numWords-myOrder; k++){
				ArrayList<String> follows = getFollows(key);
				if (follows.size() == 0 || sb.length()>numWords) {
					break;
				}
				index = myRandom.nextInt(follows.size());
				String next = follows.get(index);
				sb.append(next);
				sb.append(" ");
				key = key.shiftAdd(next);
	
		  }
    	  return sb.toString().trim();
	   }
}

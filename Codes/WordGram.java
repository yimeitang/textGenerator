

public class WordGram {
		private String[] myWords;
		

		public WordGram(String[] source, int start, int size) {
			myWords = new String[size];
			System.arraycopy(source, start, myWords, 0, size);
		}

		public String wordAt(int index) {
			if (index < 0 || index >= myWords.length) {
				throw new IndexOutOfBoundsException("bad index in wordAt "+index);
			}
        return myWords[index];
		}

		public int length(){
			return myWords.length;
		}

		public String toString(){
			String ret = "";
			for ( int k =0; k< myWords.length; k++){
				ret += myWords[k] + " ";
			}  
			return ret.trim();
		}

		public boolean equals(Object o) {
			WordGram other = (WordGram) o;
			if (myWords.length != other.length()){
				return false;
			}
			for (int k =0 ; k< myWords.length; k++){
				if (!myWords[k].equals(other.wordAt(k))){
					return false;
				}
			}
			return true;
		}

		public WordGram shiftAdd(String word) {	
			String[] words = new String[myWords.length];
			for (int k = 1;k < myWords.length;k++){
				words[k-1]=myWords[k];
			}
			words[myWords.length-1]=word;
			WordGram out = new WordGram(words, 0, words.length);
			return out;
		}
   
		public int hashCode(){
			int code = toString().hashCode();
			return code;
		}
    
}
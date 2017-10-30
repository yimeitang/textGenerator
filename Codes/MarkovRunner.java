


public class MarkovRunner {
		
	
		public static void main(String[] args) {
			String dataSource = "data/red_lyrics.txt";
			int order = 5;
			int numCopies = 2;
			int seed = 139;
			
			runModel(dataSource, order, numCopies, seed);
			
			
		}
		
		public static void runModel(String dataSource, int order, int numCopies, int seed) {
			In fileRead = new In(dataSource);
	        String origText = fileRead.readAll(); 
	        System.out.println("Original Text:");
	        printOut(origText);
	        origText = origText.replace('\n', ' '); 
	        int size = origText.length();
	        MarkovWord markov = new MarkovWord(order);
	        markov.setTraining(origText); 
	        markov.setRandom(seed);
	        System.out.println("Running with " + order + "-gram");
	        System.out.println("----------------------------------");
	        for(int k=0; k < numCopies; k++){ 
	        	System.out.println("Copy " + (k+1) +" :");
	            String output = markov.getRandomText(size); 
	            printOut(output); 
	        } 
	        
		}
			
	    private static void printOut(String s){
	        String[] words = s.split("\\s+");
	        int psize = 0;
	        System.out.println("----------------------------------");
	        for(int k=0; k < words.length; k++){
	            System.out.print(words[k]+ " ");
	            psize += words[k].length() + 1;
	            if (psize > 60) {
	                System.out.println(); 
	                psize = 0;
	            } 
	        } 
	        System.out.println("\n----------------------------------");
	    } 

	}


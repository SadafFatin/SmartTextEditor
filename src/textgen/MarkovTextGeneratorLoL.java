package textgen;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;


	//To get the list of words from a text



	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
        if (sourceText.length() == 0) {
            System.out.println("There is no input string!");
        } else {
            String[] sourceWords = sourceText.split("[\\s]+");
            starter = sourceWords[0];
            String prevWord = starter;
            String w;
            ListNode node;
            for (int i = 1; i <= sourceWords.length; i++) {
                if (i == sourceWords.length) {
                    w = sourceWords[0];
                } else {
                    w = sourceWords[i];
                }

                node = findNode(prevWord);
                if (node == null) {
                    node = new ListNode(prevWord);
                    node.addNextWord(w);
                    wordList.add(node);
                } else {
                    node.addNextWord(w);
                }
                prevWord = w;

            }
        }


    }

    private ListNode findNode(String word) {
        for (ListNode node : wordList) {
            if (word.equals(node.getWord())) {
                return node;
            }
        }
        return null;
    }
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method

		String output = "";
		if (wordList.isEmpty()) {
			System.out.println("Haven't trained yet!!");
			return output;
		}
		if (numWords == 0) {
			return output;
		}
		String currWord = starter;
		output = output + currWord;
		int count = 1;
		while (count < numWords) {
			ListNode node = findNode(currWord);
			String w = node.getRandomNextWord(rnGenerator);
			output = output + " " + w;
			currWord = w;
			count++;
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.

	   this.starter = "";
	   this.wordList.clear();
	   this.train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));

		String textString3 = "hi there hi Leo";
        System.out.println(textString3);
        gen.train(textString3);
        System.out.println(gen);
        System.out.println(gen.generateText(4));


	}




}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
        int randomPos = generator.nextInt(this.nextWords.size());
	    return this.nextWords.get(randomPos);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
}





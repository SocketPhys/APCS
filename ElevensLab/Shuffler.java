public class Shuffler
{
	/**
	 * The number of consecutive shuffle steps to be performed in each call
	 * to each sorting procedure.
	 */
	private static final int SHUFFLE_COUNT = 8;

	/**
	 * The number of values to shuffle.
	 */
	private static final int VALUE_COUNT = 16;

	/**
	 * Tests shuffling methods.
	 * @param args is not used.
	 */
	public static void main(String[] args) 
	{
		System.out.println("\n\n\nResults of " + SHUFFLE_COUNT +
								 " consecutive perfect shuffles:\n");
		int[] values1 = new int[VALUE_COUNT];
		for (int i = 0; i < values1.length; i++) 
		{
			values1[i] = i;
		}
		for (int j = 1; j <= SHUFFLE_COUNT; j++) 
		{
			perfectShuffle(values1);
			System.out.print("  " + j + ":");
			for (int k = 0; k < values1.length; k++) 
			{
				System.out.printf("%4d",values1[k]);
			}
			System.out.println();
		}
		System.out.println("\n");

		System.out.println("Results of " + SHUFFLE_COUNT +
								 " consecutive efficient selection shuffles:\n");
		int[] values2 = new int[VALUE_COUNT];
		for (int i = 0; i < values2.length; i++) 
		{
			values2[i] = i;
		}
		for (int j = 1; j <= SHUFFLE_COUNT; j++) 
		{
			selectionShuffle(values2);
			System.out.print("  " + j + ":");
			for (int k = 0; k < values2.length; k++) 
			{
				System.out.printf("%4d",values2[k]);
			}
			System.out.println();
		}
		System.out.println("\n\n");
		
		int FLIP_COUNT = 300000;
		int headcount = 0, tailcount = 0;
		for(int i = 0; i < FLIP_COUNT; i++)
		{
			String result = flip();
			if(result.equals("heads"))
			{
				headcount++;
			}
			else if(result.equals("tails"))
			{
				tailcount++;
			}
		}
		System.out.println("Results of " + FLIP_COUNT +
								 " consecutive flips:\n");
		System.out.println("heads: " + headcount);
		System.out.println("tails: " + tailcount);
		System.out.println("\n\n");
		
		System.out.println("Results of checking for permutations:\n");
		int [] array1 = {1, 2, 3, 4, 5, 6};
		int [] array2 = {2, 1, 6, 5, 4, 3};
		print(array1);
		print(array2);
		System.out.println("  These two arrays are permutations of one another: " + arePermutations(array1, array2) + "\n");
		
		array1 = new int[] {1, 2, 3, 4, 5, 6};
		array2 = new int[] {2, 1, 6, 7, 4, 3};
		print(array1);
		print(array2);
		System.out.println("  These two arrays are permutations of one another: " + arePermutations(array1, array2) + "\n");
		
		array1 = new int[] {1, 2, 3, 4, 5, 6};
		array2 = new int[] {1, 2, 3, 4, 5, 6};
		print(array1);
		print(array2);
		System.out.println("  These two arrays are permutations of one another: " + arePermutations(array1, array2) + "\n");
		
		array1 = new int[] {1, 2, 3, 4, 5, 6};
		array2 = new int[] {1, 2, 3, 4, 5, 1};
		print(array1);
		print(array2);
		System.out.println("  These two arrays are permutations of one another: " + arePermutations(array1, array2) + "\n");
		
		array1 = new int[] {1, 2, 3, 4, 5, 6};
		array2 = new int[] {2, 1, 4, 3, 6, 5};
		print(array1);
		print(array2);
		System.out.println("  These two arrays are permutations of one another: " + arePermutations(array1, array2) + "\n");
		
		array1 = new int[] {1, 2, 3, 4, 5, 6};
		array2 = new int[] {2, 1, 6, 5, 4, 1};
		print(array1);
		print(array2);
		System.out.println("  These two arrays are permutations of one another: " + arePermutations(array1, array2) + "\n");
		
		System.out.println("\n\n\n");
	}


	/**
	 * Apply a "perfect shuffle" to the argument.
	 * The perfect shuffle algorithm splits the deck in half, then interleaves
	 * the cards in one half with the cards in the other.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void perfectShuffle(int[] values) 
	{

	}

	/**
	 * Apply an "efficient selection shuffle" to the argument.
	 * The selection shuffle algorithm conceptually maintains two sequences
	 * of cards: the selected cards (initially empty) and the not-yet-selected
	 * cards (initially the entire deck). It repeatedly does the following until
	 * all cards have been selected: randomly remove a card from those not yet
	 * selected and add it to the selected cards.
	 * An efficient version of this algorithm makes use of arrays to avoid
	 * searching for an as-yet-unselected card.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void selectionShuffle(int[] values) 
	{

	}
	
	public static String flip ( )
	{
		return "heads";
	}
	
	public static boolean arePermutations(int [] a1, int [] a2)
	{
		return true;
	}
	
	public static void print(int [] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i]);
		}
		System.out.println();
	}
}

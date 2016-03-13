import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoardAutoPlay extends Board 
{
	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoardAutoPlay() 
	 {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) 
	{
		if(selectedCards.size()==3){
			if(containsJQK(selectedCards).size()==2){
			return true;
			}
		}else if(selectedCards.size()<3){
			 if(containsPairSum11(selectedCards).size()==3){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() 
	{
		if(containsPairSum11(cardIndexes()).size()==2 || containsJQK(cardIndexes()).size()==3){
			return true;
		}
		
		return false;
	}

	/**
	 * Look for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return a list of the indexes of an 11-pair, if an 11-pair was found;
	 *         an empty list, if an 11-pair was not found.
	 */
	private List<Integer> containsPairSum11(List<Integer> selectedCards) 
	{
		List<Integer> answer = new ArrayList<Integer>();
		boolean sum=false;
		boolean done=false;
		for(int i=0;i<selectedCards.size() - 1;i++){
			for(int j = 0; j < selectedCards.size(); j++){
				if(done==false && i!=j && (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue()==11 )){
					answer.add(selectedCards.get(i));
					answer.add(selectedCards.get(j));
					done=true;
				}
			}
		}
		outerloop:
		return answer;
	}

	/**
	 * Look for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return a list of the indexes of a JQK, if a JQK was found;
	 *         an empty list, if a JQK was not found.
	 */
	private List <Integer> containsJQK(List<Integer> selectedCards) 
	{
			List<Integer> answer = new ArrayList<Integer>();
			boolean jack=false;
		boolean queen=false;
		boolean king=false;
		for(int i=0;i<selectedCards.size();i++){
			if(cardAt(selectedCards.get(i)).rank().equals("king")&& king==false){
				answer.add(selectedCards.get(i));
				king=true;
			}else if(cardAt(selectedCards.get(i)).rank().equals("queen")&&queen==false){
				answer.add(selectedCards.get(i));
				queen=true;
			}else if(cardAt(selectedCards.get(i)).rank().equals("jack")&&jack==false){
				answer.add(selectedCards.get(i));
				jack=true;
			}
		}
		
		return answer;
	}
	

	/**
	 * Looks for a legal play on the board.  If one is found, it plays it.
	 * @return true if a legal play was found (and made); false othewise.
	 */
	public boolean playIfPossible() 
	{
		
		List<Integer> answer2 = containsJQK(cardIndexes());
		if(playPairSum11IfPossible()==true){
			
			return true;
		}else if(playJQKIfPossible()==true){
			
			return true;
		}
		
		return false;
	}

	/**
	 * Looks for a pair of non-face cards whose values sum to 11.
	 * If found, replace them with the next two cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if an 11-pair play was found (and made); false othewise.
	 */
	private boolean playPairSum11IfPossible() 
	{
		List<Integer> answer = containsPairSum11(cardIndexes());
		
		 if(answer.size()==2){
			replaceSelectedCards(answer);
			return true;
		}
		return false;
	}

	/**
	 * Looks for a group of three face cards JQK.
	 * If found, replace them with the next three cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if a JQK play was found (and made); false othewise.
	 */
	private boolean playJQKIfPossible() 
	{
		List<Integer> answer2 = containsJQK(cardIndexes());
		 if(answer2.size()==3){
			replaceSelectedCards(answer2);
			return true;
		}
		return false;
	}
}



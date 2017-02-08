/** Defines an object representing a single card
 */
public class CardClass {

	/** Class Constants */
	private static final int NUM_CATEGORIES = 5;

	/** Instance Variables */
	private String description;

	private int cat1, cat2, cat3, cat4, cat5;

	private int [] cardValues;

	/** Default Constructor */
	public CardClass() {
	}
	
	/** Non-default constructor */
	public CardClass(String cardInfo) {
		
		String [] tokens = cardInfo.split(" ");
		
		for (int i=0; i<tokens.length; i++)
		{
			description = tokens[0];
			cat1 = Integer.parseInt(tokens[1]);
			cat2 = Integer.parseInt(tokens[2]);
			cat3 = Integer.parseInt(tokens[3]);	
			cat4 = 	Integer.parseInt(tokens[4]);
			cat5 = Integer.parseInt(tokens[5]);	
		}

		cardValues = new int [NUM_CATEGORIES+1];

		//The cardValues indices correspond to the category numbering
		cardValues[0] = 0;
		cardValues[1] = cat1;
		cardValues[2] = cat2;
		cardValues[3] = cat3;
		cardValues[4] = cat4;
		cardValues[5] = cat5;
		
	}

	/** Accessor for description */
	public String getDescription() {
		return description;
	}

	/** Accessor for cat1 */
	public int getCatOne() {
		return cat1;
	}

	/** Accessor for cat2 */
	public int getCatTwo() {
		return cat2;
	}

	/** Accessor for cat3 */
	public int getCatThree() {
		return cat3;
	}

	/** Accessor for cat4 */
	public int getCatFour() {
		return cat4;
	}

	/** Accessor for cat4 */
	public int getCatFive() {
		return cat5;
	}

	/**
	 * Returns the value at a given index which indicates the category
	 * for the user's topcard.
	 * @param  indexCat - the index of the category 
	 * @return  the value at that index
	 */
	public int catAtIndex(int indexCat){
		return cardValues[indexCat];
	}

	/**
	 * Method which finds the highest category value for a 
	 * given card.
	 * This method uses the find the maxinum value of an array algorithm.
	 * @return the index of the category with the highest value.
	 */
	 
	public int getHighestValue() {

		int largest = cardValues[0];
		int i=1;
		int largestIndex = 0;

		while (i<cardValues.length) {
			if (cardValues[i]>largest) {
				largest = cardValues[i];
				largestIndex=i;
			}
				i++;		
		}
		return largestIndex;
	}

    /**
    * Returns a string representation of the card, including both
    * the description and the values.
    * Example: "TRex 6 6 12 9 9"
    * @return card details as a String
    */
  	public String toString() {
   		return description +" " +cat1 +" "+ cat2 +" "+ cat3 +" "+ cat4 +" " +cat5;
	}  

}
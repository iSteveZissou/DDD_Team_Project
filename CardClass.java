/** Defines an object representing a single card
 */
public class CardClass implements Comparable<CardClass> {

	/** Class Constants */
	private static final int NUM_CATEGORIES = 5;

	/** Instance Variables */
	private String description;

	private int height;

	private int weight;

	private int length;
	
	private int ferocity;

	private int intelligence;

	private int [] cardValues;

	private String [] CardCategories = { "description", 
		"Height", "Weight", "Length", "Ferocity", "Intelligence"};


	/** Default Constructor */
	public CardClass() {
	}
	
	/** Non-default constructor */
	public CardClass(String cardInfo) {
		String info = cardInfo;
		
		String [] tokens = info.split(" ");
		
		for (int i=0; i<tokens.length; i++)
		{
			description = tokens[0];
			height = Integer.parseInt(tokens[1]);
			weight = Integer.parseInt(tokens[2]);
			length = Integer.parseInt(tokens[3]);	
			ferocity = 	Integer.parseInt(tokens[4]);
			intelligence = Integer.parseInt(tokens[5]);	
		}
		
	}

	/** Accessor for description */
	public String getDescription() {
		return description;
	}

	/** Accessor for height */
	public int getHeight() {
		return height;
	}

	/** Accessor for weight */
	public int getWeight() {
		return weight;
	}

	/** Accessor for length */
	public int getLength() {
		return length;
	}

	/** Accessor for ferocity */
	public int getFerocity() {
		return ferocity;
	}

	/** Accessor for intelligence */
	public int getIntelligence() {
		return intelligence;
	}


	/** Mutator for description */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Mutator for height */
	public void setHeight(int height) {
		this.height = height;
	}

	/** Mutator for weight */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/** Mutator for length */
	public void setLength(int length) {
		this.length = length;
	}

	/** Mutator for ferocity */
	public void setFerocity(int ferocity) {
		this.ferocity = ferocity;
	}

	/** Mutator for intelligence */
	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}

	/**
	*	Converts string name of a category to index in the category array
	*	@param String name of category
	*	@return index value
	*/
	public int getCategory(String category) {
		return 0;
	}

	/** Compares values of each category 
	*	a card
	*	@return name of the category	 
	*/ 
	public String compareCategory() {
		return "";
	}

	/** Compare two CardClass objects
	*	@param other CardClass object to compare against
	*	@return 
	*/
	public int compareTo(CardClass other) {
	  	return 0; 
    }

    /**
    * Returns a string representation of the card, including both
    * the description and the values.
    * Example: "TRex 6 6 12 9 9"
    * @return card details 
    */
   public String toString() {
   		return description +" " +height +" "+ weight +" "+ length +" "+ ferocity +" " +intelligence;
	}  

}
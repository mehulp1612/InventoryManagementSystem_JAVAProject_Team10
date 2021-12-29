
public class Product {

	private String name;
	
	private int price;
	private String category;
	private String quantity;

	/**
	 * Constructor used to make a generic task
	 */
	public Product()
	{
		this.name = "name";
		this.price = 0;
		this.category = "category";
		this.quantity = "quantity";
	}
	/**
	 * Method used to create a task
	 * @param name task name
	 * @param price
	 * @param category task category
	 * @param note task notes
	 */
	public Product(String name, int price, String category, String quantity)
	{
		this.name = name;
		
		this.price = price;
		this.category = category;
		this.quantity = quantity;
	}
	/**
	 * Method used to return the name of the task
	 * @return the task's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Method used to set the task's name
	 * @param category the task's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method used to return the percentage complete of the task
	 * @return the task's percentage complete
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * Method used to set the task's percentage complete
	 * @param category the task's percentage complete
	 */
	public void setPercentComplete(int price) {
		this.price = price;
	}
	
	
	/**
	 * Method used to set the task's category
	 * @param category the task's category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * Method used to return the category of the task
	 * @return the task's category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Method used to set the task notes
	 * @param category the task notes
	 */
	public void setNote(String quantity) {
		this.quantity = quantity;
	}
	/**
	 * Method used to return the task notes
	 * @return the task notes
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * Method used to return a textual representation of the task
	 * @return the representation of the task
	 */
	public String toString() {
		return "Name: "+ getName() + "\n" +  "Price: "+ getPrice() + "Category: " + getCategory() + "Quantity: " + getQuantity();
	}
	/**
	 * Method used to output the task data used for exporting into files
	 * @return task data
	 */
	public String toStringExport() {
		return getName() +"�"+ getPrice() +"�"+ getCategory() +"�"+ getQuantity();
	}
}

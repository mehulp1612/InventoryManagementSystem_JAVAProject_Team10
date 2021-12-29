import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class AddWindow extends JFrame implements ActionListener{
	
	private static JLabel taskName;

	private static JLabel price;
	private static JLabel category;
	private static JLabel quantity;
	private static JTextField taskNameDisplay;
	
	private static JTextField priceDisplay;
	private static JComboBox categoryCombo;
	private static JTextField quantityDisplay;
	private static JPanel mainPanel;
	private static JPanel lowerPanel;
	private static JButton add;
	private static JButton cancel;
	
	
	
	private static String[] categoryList = {"Stationary", "Construction", "Household", "Electronics", "Other"};
	
	/**
	 * Method used to create the Add window
	 */
	AddWindow()
	{	
		this.setLayout(new BorderLayout());
		this.setTitle("Add Product");
		this.setSize(350,220);
		this.setResizable(false);
		
		taskName = new JLabel("Product Name: ");
		
		price = new JLabel("Price ");
		category = new JLabel("Category: ");
		quantity = new JLabel("Quantity: ");
		taskNameDisplay  = new JTextField("");
		
		priceDisplay  = new JTextField("");
		categoryCombo  	 = new JComboBox(categoryList);
		quantityDisplay  	 = new JTextField("");
		
		mainPanel = new JPanel(new GridLayout(7, 2));				//Create a panel with a grid layout
		mainPanel.add(taskName);		mainPanel.add(taskNameDisplay);
		mainPanel.add(price);		mainPanel.add(priceDisplay);
		mainPanel.add(category);		mainPanel.add(categoryCombo);
		mainPanel.add(quantity);			mainPanel.add(quantityDisplay);
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);	//Add the panel to the CENTER of the BorderLayout
		
		
		add = new JButton("Add");
		cancel = new JButton("Cancel");
		add.addActionListener(this);
		cancel.addActionListener(this);

		
		lowerPanel = new JPanel(new FlowLayout());
		lowerPanel.add(add);
		lowerPanel.add(cancel);
		this.getContentPane().add(BorderLayout.SOUTH, lowerPanel);	//Add the panel to the SOUTH of the BorderLayout
		/**
		 * Method used to clear the text fields when the window is closed
		 */
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
		    {
		        clearText();
		    }
		});
		
		clearText();
		validate();
	}
	/**
	 * Method used to reset the window to its default state, empty text fields etc
	 */
	public void clearText()
	{
		taskNameDisplay.setText("");
		priceDisplay.setText("");
		categoryCombo.setSelectedItem("Others");
		quantityDisplay.setText("");
	}
	/**
	 * Method used to track actions performed
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add)
		{
			try
			{
				if(taskNameDisplay.getText().equals("") || priceDisplay.getText().equals("") || quantityDisplay.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill in all of the fields.");
				}
				else if(taskNameDisplay.getText().equals("") || Integer.parseInt(priceDisplay.getText()) < 0)
				{
					JOptionPane.showMessageDialog(null, "Please fill in a number greater than '0' in price.");
				}
				else if(!(Integer.parseInt(priceDisplay.getText()) >= 0 ))
				{
					JOptionPane.showMessageDialog(null, "Please fill in a number greater than '0' in price");
				}
				else if(Integer.parseInt(priceDisplay.getText()) >= 0)
				{
					Inventory.myProducts.add(new Product(taskNameDisplay.getText(),Integer.parseInt(priceDisplay.getText()),
					 (String) categoryCombo.getSelectedItem(), quantityDisplay.getText()));
					Inventory.listModel.addElement(Inventory.myProducts.get(Inventory.myProducts.size()-1).getName());
					clearText();
					Inventory.add1.setVisible(false);
					Inventory.exported = false;
					JOptionPane.showMessageDialog(null, "Product Added.");
				}
			}
			catch(NumberFormatException n)
			{
				JOptionPane.showMessageDialog(null, "Please fill in a number greater than '0' ");
			}
		}
		if(e.getSource() == cancel)
		{
			clearText();
			Inventory.add1.setVisible(false);
		}
	}
	
}

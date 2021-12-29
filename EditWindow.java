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

public class EditWindow extends JFrame implements ActionListener{
	
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
	private static JButton save;
	private static JButton cancel;
	
	

	private static String[] categoryList = {"Stationary", "Construction", "Household", "Electronics", "Other"};
	
	
	EditWindow()
	{
		this.setLayout(new BorderLayout());
		this.setTitle("Edit Product");
		this.setSize(350,220);
		this.setResizable(false);
		
		taskName = new JLabel("Product Name: ");
		
		price = new JLabel("Cost: ");
		category = new JLabel("Category: ");
		quantity = new JLabel("Note: ");
		
		taskNameDisplay  = new JTextField();
		
		priceDisplay  = new JTextField();
		categoryCombo  	 = new JComboBox(categoryList);
		quantityDisplay  	 = new JTextField();
		
		mainPanel = new JPanel(new GridLayout(4, 2));				//Create a panel with a grid layout
		mainPanel.add(taskName);	mainPanel.add(taskNameDisplay);
		
		mainPanel.add(price);	mainPanel.add(priceDisplay);
		mainPanel.add(category);	mainPanel.add(categoryCombo);
		mainPanel.add(quantity);		mainPanel.add(quantityDisplay);
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);	//Add the panel to the CENTER of the BorderLayout
		
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		lowerPanel = new JPanel(new FlowLayout());
		lowerPanel.add(save);
		lowerPanel.add(cancel);
		this.getContentPane().add(BorderLayout.SOUTH, lowerPanel);	//Add the panel to the SOUTH of the BorderLayout
		
		this.addWindowListener(new WindowAdapter()
		{
			/**
			 * Method used to manage variables when the task window is closed
			 */
		    public void windowClosing(WindowEvent we)
		    {
		        Inventory.listenerOn = true;	//turn the JList listener on as editing has ended
		    }
		});
		
		
		validate();
	}
	/**
	 * Method used to take task information and populate the window using the information
	 * so that the user can edit the task
	 */
	public static void populate()
	{
		taskNameDisplay.setText(Inventory.myProducts.get(Inventory.currentEditIndex).getName());
		
		
		priceDisplay.setText("" + Inventory.myProducts.get(Inventory.currentEditIndex).getPrice());
		categoryCombo.setSelectedItem(Inventory.myProducts.get(Inventory.currentEditIndex).getCategory());
		quantityDisplay.setText(Inventory.myProducts.get(Inventory.currentEditIndex).getQuantity());
	}
	/**
	 * Method to track actions performed
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save)
		{
			try
			{
				if(taskNameDisplay.getText().equals("") || priceDisplay.getText().equals("") || quantityDisplay.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill in all of the fields.");
				}
				else if(Integer.parseInt(priceDisplay.getText()) < 0 )
				{
					JOptionPane.showMessageDialog(null, "Please fill in a number greater than 0 in price.");
				}
				else
				{
					Inventory.myProducts.get(Inventory.currentEditIndex).setName(taskNameDisplay.getText());
					
					Inventory.myProducts.get(Inventory.currentEditIndex).setPercentComplete(Integer.parseInt(priceDisplay.getText()));
					Inventory.myProducts.get(Inventory.currentEditIndex).setCategory((String) categoryCombo.getSelectedItem());
					Inventory.myProducts.get(Inventory.currentEditIndex).setNote(quantityDisplay.getText());
					
					Inventory.listModel.remove(Inventory.currentEditIndex);
					Inventory.listModel.add(Inventory.currentEditIndex, Inventory.myProducts.get(Inventory.currentEditIndex).getName());
					Inventory.listenerOn = true;
					Inventory.edit1.setVisible(false);
					Inventory.changeOutputDetails(Inventory.currentEditIndex);
					Inventory.exported = false;
					JOptionPane.showMessageDialog(null, "Edit task Successful.");
				}
			}
			catch(NumberFormatException n)
			{
				JOptionPane.showMessageDialog(null, "Please fill in a number greater than '0' ");
			}
		}
		if(e.getSource() == cancel)
		{
			Inventory.listenerOn = true;
			Inventory.edit1.setVisible(false);
		}
	}
	
	
	
	
}

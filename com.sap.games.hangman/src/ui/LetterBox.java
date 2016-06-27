package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class LetterBox extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char letter;
	
	public LetterBox() {
		super(3);
		Font font = new Font("Courier", Font.BOLD, 60);
		setFont(font);
		
		setHorizontalAlignment(JTextField.CENTER);
		setEnabled(false);
		setEnabled(true);
	}
	
	public LetterBox(char letter) {
		super(3);
		this.letter = letter;
		Font font = new Font("Courier", Font.BOLD, 30);
		setFont(font);
		
		setHorizontalAlignment(JTextField.CENTER);
		setEnabled(false);
		//display();
	}
	
	private void init() {
		
		
		
	}
	
	@Override
	public Document createDefaultModel() {
		return new SingleCharDocument();
	}
	
	private class SingleCharDocument extends PlainDocument {
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;
		
		    if ((getLength() + str.length()) <= 1) {
		        super.insertString(offset, str.toUpperCase(), attr);
		    }
		}       
	}
	
	public void display() {
		setText(letter+"");
	}
	
	public boolean isDisplayed() {
		return getText().equals(letter+"");
	}
}

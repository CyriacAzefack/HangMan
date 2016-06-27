package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Main frame of the hangman game
 * @author I327247
 *
 */
public class Window extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Game game;
	private LetterBox[] letterBoxes;
	
	
	public Window(String title, int width, int height, Game game) {
		frame = new JFrame(title);
		this.game = game;
		System.out.println("Launch Window");
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setResizable(false);
		//Default Options
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		initComponents();
		
		
		
		
		
	}
	
	private void initComponents() {
		//Init Panels
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLUE);
		
		JPanel hangmanPanel = new JPanel();
		hangmanPanel.setBackground(Color.gray);
		
		JPanel playPanel = new JPanel();
		playPanel.setBackground(Color.ORANGE);
		
		JPanel wordsPanel = new JPanel();
		wordsPanel.setBackground(Color.YELLOW);
		
		JPanel guessPanel = new JPanel();
		guessPanel.setBackground(Color.GREEN);
		
		JPanel usedPanel = new JPanel();
		usedPanel.setBackground(Color.CYAN);
		
		// Add components
		
		
		//HangMan Panel;
		HangMan hm = new HangMan();
		hangmanPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//Fill horizontally and vertically
		gc.fill = GridBagConstraints.BOTH;
		
		//define margin
		gc.insets = new Insets(5, 5, 5, 5);
		
		//Where to place the component if we don't fill the available space
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;
		
		//Define the number of cases available in x and y
		gc.weightx = 1;
		gc.weighty = 1;
		
		//Define where to add the componenent
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		
		hangmanPanel.add(hm, gc);
		
		//Play Panel
			//Letter Boxes 
		String word = game.getWord();
		letterBoxes = new LetterBox[word.length()];
		for(int i=0; i < letterBoxes.length; i++) {
			letterBoxes[i] = new LetterBox(word.charAt(i));
			wordsPanel.add(letterBoxes[i]);
		}
		//Used Panel
		JTextField usedLetters = new JTextField();
		Font font = new Font("Courier", Font.BOLD, 30);
		usedLetters.setFont(font);
		usedLetters.setToolTipText("Letters Used");
		usedLetters.setHorizontalAlignment(JTextField.LEFT);
		usedLetters.setEnabled(false);
		
		usedPanel.setLayout(new GridBagLayout());
		
		usedPanel.add(usedLetters, gc);
		// Guess Panel
		JButton play = new JButton("PLAY");
		LetterBox in = new LetterBox();
		in.requestFocusInWindow();
		mainPanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Press button : " + e.getKeyChar());
					play.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String letter = in.getText();
				if (letter.isEmpty())
					return;
				in.setText("");
				in.requestFocus();
				ArrayList<Integer> positions = game.play(letter.charAt(0));
				if (positions.isEmpty()) {
					String txt = usedLetters.getText();
					if (!txt.contains(letter))
						txt += letter +",";
					usedLetters.setText(txt);
					if(!hm.next())
						game.stop(false);
					return;
				}
				
				for (int i : positions) {
					letterBoxes[i].display();
				}
				
				if (allDisplayed())
					game.stop(true);
			}
		
		});
		
		guessPanel.setLayout(new GridLayout(2,1));
		guessPanel.add(in);
		guessPanel.add(play);
			
		playPanel.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridheight = 1;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		playPanel.add(wordsPanel, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		playPanel.add(guessPanel, gc);
		gc.gridx = 1;
		gc.gridheight = 1;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		playPanel.add(usedPanel, gc);
		
		
		
		//Main Panel
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc1 = new GridBagConstraints();
		gc1.fill = GridBagConstraints.BOTH;
		gc1.insets = new Insets(5, 5, 5, 5);
		gc1.ipady = gc.anchor = GridBagConstraints.CENTER;
		
		gc1.weightx = 1;
		gc1.weighty = 1;
		
		//Define where to add the componenent
		gc1.gridx = 0;
		gc1.gridy = 0;
		//gc.gridheight = 1;
		gc1.gridwidth = 3;
		mainPanel.add(playPanel, gc1);
		
		gc1.gridx = 3;
		gc1.gridwidth = 1;
		mainPanel.add(hangmanPanel, gc1);
		
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
    
    
	}

	public void dispose() {
		frame.dispose();
	}
	
	public boolean allDisplayed() {
		boolean over = true;
		
		for (LetterBox b : letterBoxes) {
			over = over && b.isDisplayed();
		}
		
		return over;
	}
	

}

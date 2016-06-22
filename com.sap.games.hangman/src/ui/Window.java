package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public Window(String title, int width, int height, Game game) {
		frame = new JFrame(title);
		
		System.out.println("Launch Window");
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//Default Options
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		initComponents();
		
		
		
		// Add components
		/*
		HangMan hm = new HangMan();
		JButton next = new JButton("Next Image");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hm.next();
			}
		});
		frame.add("West", next);
		frame.add("East", hm);
		frame.add("North", new LetterBox('A'));
		frame.pack();
		frame.setVisible(true);
		*/
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
		
		//Layout Settings
			//Main Panel
		for (int i = 0; i<10; i++) {
			mainPanel.add(new LetterBox('A'));
		}
        
        /*
        c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(hangmanPanel, c);
		*/
		
       
        
       frame.add(mainPanel);
       frame.pack();
       frame.setVisible(true);
        
        
	}

}

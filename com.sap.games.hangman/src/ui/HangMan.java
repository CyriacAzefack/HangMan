package ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HangMan extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 100, HEIGHT = 300;
	private static final int NB_STATES = 8;
	private static final String IMG_SRC_PATH = Game.INPUT_PATH + "/img";
	
	
	private int state;
	
	
	
	
	public HangMan() {
		this.state = 0;
		//setBackground(Color.gray);
		setSize(WIDTH, HEIGHT);
	}
	
	public void paint(Graphics g) {
		//super.paint(g);
		Graphics2D g2;
		g2 = (Graphics2D) g;
		
		Image img = getImage();
		
		int width = getSize().width;
		int height = getSize().height;
		
		
		
		//resize the image
		BufferedImage resizedImage = resizeImage(img, width, height);
		
		g2.drawImage(resizedImage, 0, 0, null);
		
		
		
		
		
	
	}
	
	/**
	 * @return The image representing this state of the process
	 */
	private Image getImage() {
		
		File imgFile = new File(IMG_SRC_PATH+"/man-"+state+".png");
		Image img = null;
		try {
			img = ImageIO.read(imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return img;
	}
	
	private  BufferedImage resizeImage(Image originalImage, int width, int height){
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
			
		return resizedImage;
	    }
	
	/**
	 * Displays the next image
	 */
	public boolean next() {
		if (state >= NB_STATES-1)
			return false;
		
		state++;
		repaint();
		return true;
		
	}
	
	/**
	 * Displays the previous image
	 */
	public void previous() {
		if (state > 0) {
			state--;
			System.out.println("Previous Image");
			repaint();
		}		
	}
	
}

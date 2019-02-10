import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Langtons_ant {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int imgHeight = 0;
		int imgWidth = 0;
		BufferedImage img = null;
		int posX = 0;
		int posY = 0;
		int direction = 0;	//0 UP
							//1 RIGHT
							//2 DOWN
							//3 LEFT
		
		int MAX_STEPS = 3280000; //Number of steps the ant will take
		boolean WRAP = true;
		
		String filePath = "C:\\Users\\Andreas\\Desktop\\mazefinder\\ant\\ant.bmp"; //filePath is the path to the empty image that the ant will roam in
		String outputPath = "C:\\Users\\Andreas\\Desktop\\mazefinder\\ant\\"; //outputPath is the path where the output images will be placed
		
		int imgNum = 0;
		
		try {
		File file = new File(filePath);
		img = ImageIO.read(file);
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
		System.out.println("File loaded: " + filePath);
		}
		catch (IOException e) {
			System.out.println("Fil flyttad eller så");
		}
		
		posX = imgWidth/2;
		posY = imgHeight/2;
		System.out.println("Ant placed in the middle; x: " + posX + ", y: " + posY);
		
		//RED = 	-65536
		//WHITE =	-1
		//BLUE =	-16776961
		//GREEN = 	-16711936
		//BLACK = 	-16777216
		
		
		for(int i = 0; i < MAX_STEPS; i++) {
			if(WRAP) {
				if(posX == img.getWidth()) {
					posX = 1;
				}
				
				if(posX == 0) {
					posX = img.getWidth()-1;
				}
				
				if(posY == img.getHeight()) {
					posY = 1;
				}
				
				if(posY == 0) {
					posY = img.getHeight()-1;
				}
			} else {
				if(posX == img.getWidth()) {
					posX = img.getWidth()-1;
				}
				
				if(posX == 0) {
					posX = 1;
				}
				
				if(posY == img.getHeight()) {
					posY = img.getHeight()-1;
				}
				
				if(posY == 0) {
					posY = 1;
				}
			}

			
			switch(img.getRGB(posX, posY)) {
				case (-1):	img.setRGB(posX, posY, 0xFF0000); //If current tile is white, change it to red. Turn right
									switch (direction) {
										case (0):	posX++;
													direction = 1;
													break;
										case (1):	posY--;
													direction = 2;
													break;
										case (2):	posX--;
													direction = 3;
													break;
										case (3):	posY++;
													direction = 0;
													break;
									}
									break;
				case (-16777216):	img.setRGB(posX, posY, 0xFFFFFF); //If current tile is black, change it to white. Go straight
									switch (direction) {
										case (0):	posY++;
													direction = 0;
													break;
										case (1):	posX++;
													direction = 1;
													break;
										case (2):	posY--;
													direction = 2;
													break;
										case (3):	posX--;
													direction = 3;
													break;
									}
									break;
				case (-65536):	img.setRGB(posX, posY, 0x00FF00); //If current tile is red, change it to green. Turn left
									switch (direction) {
										case (0):	posX--;
													direction = 3;
													break;
										case (1):	posY++;
													direction = 0;
													break;
										case (2):	posX++;
													direction = 1;
													break;
										case (3):	posY--;
													direction = 2;
													break;
									}
									break;
				case (-16776961):	img.setRGB(posX, posY, 0x000000); //If current tile is blue, change it to black. Go straight
									switch (direction) {
										case (0):	posY++;
													direction = 0;
													break;
										case (1):	posX++;
													direction = 1;
													break;
										case (2):	posY--;
													direction = 2;
													break;
										case (3):	posX--;
													direction = 3;
													break;
									}
									break;
				case (-16711936):	img.setRGB(posX, posY, 0x0000FF); //If current tile is green, change it to blue. Turn right
									switch (direction) {
										case (0):	posX++;
													direction = 1;
													break;
										case (1):	posY--;
													direction = 2;
													break;
										case (2):	posX--;
													direction = 3;
													break;
										case (3):	posY++;
													direction = 0;
													break;
									}
									break;
				default:			System.out.println("Error walking");
									i = MAX_STEPS;
			}
			if(i < 300) {
				writeFile(outputPath,(""+imgNum), img);
				imgNum++;
			} else if((i > 300) && (i < 7500)) {
				if(i%20 == 0) {
					writeFile(outputPath,(""+imgNum), img);
					imgNum++;
				}
			} else if((i > 7520) && (i < 90000)) {
				if(i%500 == 0) {
					writeFile(outputPath,(""+imgNum), img);
					imgNum++;
				}
			} else if((i > 91500) && (i < 280000)) {
				if(i%(5000) == 0) {
					writeFile(outputPath,(""+imgNum), img);
					imgNum++;
				}
			} else if((i > 281000) && (i < 880000)) {
				if(i%(20000) == 0) {
					writeFile(outputPath,(""+imgNum), img);
					imgNum++;
				}
			} else if((i > 884000) && (i < MAX_STEPS)) {
				if(i%(70000) == 0) {
					writeFile(outputPath,(""+imgNum), img);
					imgNum++;
				}
			}
		}
		
		writeFile(outputPath,""+imgNum, img);
		in.close();
	}
	
	public static void writeFile(String path, String name, BufferedImage img) {
		try {
			File output = new File(path + name + ".png");
			ImageIO.write(img, "png", output);
			System.out.println("Wrote file to: " + path + name + ".png");
		} catch (IOException e) {
			System.out.println("Error writing file");
			System.out.println(e);
		}
	}
}

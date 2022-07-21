package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

	void create(InputStream inputStream,String imageName) throws IOException {
		//InputStream inputStream = new FileInputStream(new File("input/movie.jpg"));
		//InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
		
		BufferedImage originalImage = ImageIO.read(inputStream);
		
		int width = originalImage.getWidth();
		int heigth = originalImage.getHeight();
		int newHeigth = heigth + 200;
		BufferedImage newImage = new BufferedImage(width,newHeigth,BufferedImage.TRANSLUCENT); 
		//New Image(memory)
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.drawImage(originalImage, 0, 0, null);
		
		//FontSetup
		var font = new Font(Font.SANS_SERIF,Font.BOLD,128);
		graphics.setFont(font);
		graphics.setColor(Color.YELLOW);
		//Writing text
		graphics.drawString("Bala", 220 , newHeigth-100);		
		//New Image
		ImageIO.write(newImage, "png", new File(imageName));
	}
}

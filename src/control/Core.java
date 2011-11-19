package control;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;

import model.EdgeDetection;
import model.PolygonDetection;
import view.GUI;

public class Core {

	public static void main(String[] args) throws Exception {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		System.setProperty("com.sun.media.imageio.disableCodecLib", "true");

		File file = new File("images/8ball.jpg");
		BufferedImage imageOriginal = ImageIO.read(file);

		PlanarImage edgeDetection = EdgeDetection.execute(imageOriginal);
		PolygonDetection.execute(edgeDetection, imageOriginal);

		new GUI(imageOriginal);
	}

}

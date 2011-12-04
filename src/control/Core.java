package control;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;

import model.Binarization;
import model.EdgeDetection;
import model.HoughTransform;
import model.HoughTransformReturn;
import view.GUI;

public class Core {

	public static void main(String[] args) throws Exception {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		System.setProperty("com.sun.media.imageio.disableCodecLib", "true");

		File file = new File("images/8ball.jpg");
		BufferedImage imageOriginal = ImageIO.read(file);

		PlanarImage binarization = Binarization.execute(imageOriginal);
		PlanarImage edge = EdgeDetection.execute(binarization);
		
		HoughTransformReturn ht = HoughTransform.execute(edge.getAsBufferedImage());

		new GUI(imageOriginal, ht);
	}
	
}

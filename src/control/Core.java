package control;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;

import model.Binarization;
import model.CoreReturn;
import model.EdgeDetection;
import model.HoughTransform;
import model.HoughTransformReturn;

public class Core {

	public static CoreReturn execute(String fileName) throws Exception {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		System.setProperty("com.sun.media.imageio.disableCodecLib", "true");

		File file = new File(fileName);
		BufferedImage imageOriginal = ImageIO.read(file);

		PlanarImage binarization = Binarization.execute(imageOriginal);
		PlanarImage edge = EdgeDetection.execute(binarization);

		HoughTransformReturn ht = HoughTransform.execute(edge
				.getAsBufferedImage());

		CoreReturn coreReturn = new CoreReturn();
		coreReturn.setHoughTransformReturn(ht);
		coreReturn.setImages(new BufferedImage[] {
				binarization.getAsBufferedImage(), edge.getAsBufferedImage(),
				imageOriginal });

		return coreReturn;
	}

}

package model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.media.jai.PlanarImage;

public class PolygonDetection {

	public static final int[] PIXEL = new int[] { 0, 0, 255 };

	public static void execute(PlanarImage image, BufferedImage imageOriginal) {
		BufferedImage imageBuffer = image.getAsBufferedImage();
		WritableRaster imageRaster = imageBuffer.getRaster();
		int[] pixel = new int[3];

		for (int i = 0; i < imageBuffer.getHeight(); i++) {
			for (int j = 0; j < imageBuffer.getWidth(); j++) {
				imageRaster.getPixel(j, i, pixel);

				if (pixel[0] > 0) {

				} else {
					// pixels of the coin
					int k = i;
					int m = j;
					while (k < imageBuffer.getHeight()
							&& m < imageBuffer.getWidth()) {
						imageRaster.getPixel(m, k, pixel);

						if (pixel[0] > 0) {
							// find pixel of coin, go to right
							imageOriginal.getRaster().setPixel(j, i, PIXEL);
							m++;
						} else {
							// else, go down
							k++;
						}
					}
					return;
				}
			}
		}
	}
}

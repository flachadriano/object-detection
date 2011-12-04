package model;

import java.awt.image.BufferedImage;

public class ObjectDetectionReturn {

	private HoughTransformReturn houghTransformReturn;
	private BufferedImage[] images;

	public HoughTransformReturn getHoughTransformReturn() {
		return houghTransformReturn;
	}

	public void setHoughTransformReturn(
			HoughTransformReturn houghTransformReturn) {
		this.houghTransformReturn = houghTransformReturn;
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public void setImages(BufferedImage[] images) {
		this.images = images;
	}

}

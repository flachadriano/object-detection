package model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.media.jai.PlanarImage;

public class PolygonDetection {

	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int BOTTOM = 3;

	public static int[] pixel = new int[3];
	public static WritableRaster raster = null;
	public static int lastBottom = -1;
	public static int lastTop = -1;

	public static final int[] COIN = new int[] { 0, 0, 255 };

	public static BufferedImage execute(PlanarImage image,
			BufferedImage imageOriginal) {
		BufferedImage imageBuffer = image.getAsBufferedImage();
		raster = imageBuffer.getRaster();

		// ignore a pixel of border
		for (int y = 1; y < imageBuffer.getHeight() - 1; y++) {
			for (int x = 5; x < imageBuffer.getWidth() - 1; x++) {

				raster.getPixel(x, y, pixel);
				if (pixel[0] == 0) {
					System.out.println(x + "," + y);
					turnCoin(x, y, imageBuffer, imageOriginal);
					return imageOriginal;
				}
			}
		}

		return imageOriginal;
	}

	private static void turnCoin(int x, int y, BufferedImage imageBuffer,
			BufferedImage imageOriginal) {

		int direction;
		int initX = x;
		int initY = y;

		do {

			direction = getDirection(x, y);

			switch (direction) {
			case TOP:
				y--;
				break;
			case RIGHT:
				x++;
				break;
			case LEFT:
				x--;
				break;
			case BOTTOM:
				y++;
				break;
			default:
				return;
			}

			imageOriginal.getRaster().setPixel(x, y, COIN);

		} while (x < imageBuffer.getWidth() - 1
				&& y < imageBuffer.getHeight() - 1
				&& (x != initX || y != initY));
	}

	public static int getDirection(int x, int y) {
		int direction = -1;

		raster.getPixel(x, y, pixel);
		int point = pixel[0];
		raster.getPixel(x, y - 1, pixel);
		int top = pixel[0];
		raster.getPixel(x + 1, y, pixel);
		int right = pixel[0];
		raster.getPixel(x - 1, y, pixel);
		int left = pixel[0];
		raster.getPixel(x, y + 1, pixel);
		int bottom = pixel[0];

		// in the border of the coin
		if (point == 0) {

			if (right == 0) {
				if (bottom == 0)
					direction = TOP;
				else if (top == 0)
					direction = LEFT;

			} else if (left == 0)
				if (bottom == 0)
					direction = RIGHT;
				else if (top == 0)
					direction = BOTTOM;

		} else {

			if (bottom == 0)
				direction = RIGHT;
			else if (lastBottom == 0)
				direction = BOTTOM;
			else if (left == 0)
				direction = LEFT;
			else if (top == 0)
				direction = LEFT;
			else if (lastTop == 0)
				direction = TOP;
			else if (right == 0)
				direction = RIGHT;

		}

		System.out.println("(" + x + "," + y + ") " + "P:" + point + " T:"
				+ top + " R:" + right + " L:" + left + " B:" + bottom
				+ " DIRECTION:" + direction);

		lastBottom = bottom;
		lastTop = top;
		return direction;
	}
}

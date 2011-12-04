package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class HoughTransform {

	public static final int LIMIAR_RGB = -16777216;

	public static HoughTransformReturn execute(BufferedImage image) {
		Point[] points = getFourthAleatoryPoints(image);
		Point[] ordered = getOrderedPoints(points);

		Point center = getCenter(ordered);
		double distance = getDistance(center, ordered[0]);

		HoughTransformReturn htReturn = new HoughTransformReturn();
		htReturn.setPoints(points);
		htReturn.setCenter(center);
		htReturn.setDistance(distance);

		return htReturn;
	}

	private static Point[] getFourthAleatoryPoints(BufferedImage image) {
		int rgb = 0;
		int yMin = 0;
		int yMax = 0;

		// obtain upper Y
		for (int y = 1; y < image.getHeight() - 1; y++) {
			for (int x = 1; x < image.getWidth() - 1; x++) {
				rgb = image.getRGB(x, y);
				if (rgb != LIMIAR_RGB) {
					yMin = y;
					break;
				}
			}
			if (yMin > 0)
				break;
		}

		// obtain bottom Y
		for (int y = image.getHeight() - 2; y > 1; y--) {
			for (int x = image.getWidth() - 2; x > 1; x--) {
				rgb = image.getRGB(x, y);
				if (rgb != LIMIAR_RGB) {
					yMax = y;
					break;
				}
			}
			if (yMax > 0)
				break;
		}

		// obtain 4 aleatory points
		double midY = (yMax - yMin) / 2;
		System.out.println(midY);
		Random r = new Random();
		int[] ys = new int[4];
		for (int i = 0; i < 4; i++) {
			ys[i] = r.nextInt(yMax - yMin) + yMin;
			if (i < 2) {
				if (ys[i] > midY)
					ys[i] -= midY;
			} else {
				if (ys[i] < midY)
					ys[i] += midY;
			}
		}

		// 2 points at left and 2 at right
		// 0-left 1-right
		int[] directions = new int[4];
		directions[0] = r.nextInt(2);
		directions[1] = (directions[0] == 0) ? 1 : 0;
		directions[2] = r.nextInt(2);
		directions[3] = (directions[2] == 0) ? 1 : 0;

		Point[] points = new Point[4];

		for (int i = 0; i < 4; i++) {
			if (directions[i] == 0) {
				for (int x = 1; x < image.getWidth() - 1; x++) {
					rgb = image.getRGB(x, ys[i]);
					if (rgb != LIMIAR_RGB) {
						points[i] = new Point(x, ys[i]);
						break;
					}
				}
			} else {
				for (int x = image.getWidth() - 1; x > 1; x--) {
					rgb = image.getRGB(x, ys[i]);
					if (rgb != LIMIAR_RGB) {
						points[i] = new Point(x, ys[i]);
						break;
					}
				}
			}
		}

		return points;
	}

	private static Point[] getOrderedPoints(Point[] points) {
		LinkedList<Point> result = new LinkedList<Point>();

		result.add(points[0]);

		if (result.get(0).getY() > points[1].getY())
			result.add(0, points[1]);
		else
			result.add(points[2]);

		if (result.get(0).getY() > points[2].getY())
			result.add(0, points[2]);
		else if (result.get(1).getY() > points[2].getY())
			result.add(1, points[2]);
		else
			result.add(points[2]);

		if (result.get(0).getY() > points[3].getY())
			result.add(0, points[3]);
		else if (result.get(1).getY() > points[3].getY())
			result.add(1, points[3]);
		else if (result.get(2).getY() > points[3].getY())
			result.add(2, points[3]);
		else
			result.add(points[3]);

		return result.toArray(new Point[4]);
	}

	private static Point getCenter(Point[] points) {
		double[] top = getPerpendicular(points[0], points[1]);
		double a1 = top[0];
		double b1 = top[1];
		double c1 = top[2];

		double[] bottom = getPerpendicular(points[2], points[3]);
		double a2 = bottom[0];
		double b2 = bottom[1];
		double c2 = bottom[2];

		double xc = 0;
		double yc = 0;

		// XC
		// =
		// {(-C1 -C2).[B1.(A1 +A2) -A1.(B1 +B2)]} - [(B1 +B2).(A1.C2 -A2.C1)]
		// ------------------------------------------------------------------
		// B1.(A1 + A2) - A1.(B1 + B2)
		xc = ((-c1 - c2) * (b1 * (a1 + a2) - a1 * (b1 + b2)))
				- ((b1 + b2) * (a1 * c2 - a2 * c1));
		xc /= (b1 * (a1 + a2) - a1 * (b1 + b2));

		// YC
		// =
		// A1.C2 - A2.C1
		// -------------
		// B1.(A1 + A2) - A1.(B1 + B2)
		yc = a1 * c2 - a2 * c1;
		yc /= (b1 * (a1 + a2) - a1 * (b1 + b2));

		Point p = new Point();
		p.setLocation(xc, yc);
		return p;
	}

	private static double[] getPerpendicular(Point a, Point b) {
		double x1 = a.getX();
		double y1 = a.getY();
		double x2 = b.getX();
		double y2 = b.getY();
		double a1 = 0;
		double b1 = 0;
		double c1 = 0;

		// A1 = 2.x2 – 2.x1
		a1 = 2 * x2 - 2 * x1;

		// B1 = 2.y2 – 2.y1
		b1 = 2 * y2 - 2 * y1;

		// Q = SQUARE
		// C1 = x1Q + y1Q – y2Q – x2Q
		c1 = Math.pow(x1, 2) + Math.pow(y1, 2) - Math.pow(y2, 2)
				- Math.pow(x2, 2);
		// c1 = Math.sqrt(x1) + Math.sqrt(y1) - Math.sqrt(y2) - Math.sqrt(x2);

		return new double[] { a1, b1, c1 };
	}

	private static double getDistance(Point center, Point border) {
		double d = 0;
		double x1 = center.getX();
		double y1 = center.getY();
		double x2 = border.getX();
		double y2 = border.getY();

		// Q = SQUARE
		// D = root((X2 - X1)Q + (Y2 - Y1)Q)
		d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return d;
	}
}
package model;

import java.awt.Point;

public class HoughTransformReturn {

	private Point[] points;
	private Point center;
	private double radius;

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double distance) {
		this.radius = distance;
	}

}

package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ObjectDetection;

import model.ObjectDetectionReturn;
import model.HoughTransformReturn;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	public Panel(String fileName) throws Exception {
		ObjectDetectionReturn objectDetectionReturn = ObjectDetection.execute(fileName);

		BufferedImage original = objectDetectionReturn.getImages()[2];
		HoughTransformReturn ht = objectDetectionReturn.getHoughTransformReturn();
		BufferedImage[] images = objectDetectionReturn.getImages();

		Graphics2D ga = (Graphics2D) original.getGraphics();
		ga.setPaint(Color.BLUE);
		Point[] ps = ht.getPoints();

		// draw points at image
		Point p = null;
		for (int i = 0; i < 5; i++) {
			if (i == 4)
				p = ht.getCenter();
			else
				p = ps[i];
			for (int j = 1; j < 5; j++) {
				ga.drawOval((int) p.getX(), (int) p.getY(), j, j);
			}
		}

		// draw lines between the points
		ga.drawLine((int) ps[0].getX(), (int) ps[0].getY(), //
				(int) ps[1].getX(), (int) ps[1].getY());

		ga.drawLine((int) ps[2].getX(), (int) ps[2].getY(), //
				(int) ps[3].getX(), (int) ps[3].getY());

		// draw the detected object
		double radius = ht.getRadius();
		double doubleRadius = radius + radius;
		double x = ht.getCenter().getX() - radius;
		double y = ht.getCenter().getY() - radius;
		for (int i = 0; i < 5; i++) {
			ga.drawOval((int) x+i, (int) y+i, (int) doubleRadius,
					(int) doubleRadius);
		}

		// renderize image
		for (int i = 0; i < images.length; i++) {
			ImageIcon imageIcon = new ImageIcon(images[i]);
			JLabel imageLabel = new JLabel(imageIcon);
			add(imageLabel);
		}
	}

}

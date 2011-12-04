package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.HoughTransformReturn;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI(BufferedImage image, HoughTransformReturn ht) {
		setTitle("Detecção de objetos - Adriano Flach de Araujo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(new BorderLayout());

		Graphics2D ga = (Graphics2D) image.getGraphics();
		ga.setPaint(Color.RED);
		Point[] ps = ht.getPoints();

		// draw points at image
		Point p = null;
		for (int i = 0; i < 5; i++) {
			if (i == 4)
				p = ht.getCenter();
			else
				p = ps[i];
			ga.drawOval((int) p.getX(), (int) p.getY(), 1, 1);
			ga.drawOval((int) p.getX(), (int) p.getY(), 2, 2);
			ga.drawOval((int) p.getX(), (int) p.getY(), 3, 3);
			ga.drawOval((int) p.getX(), (int) p.getY(), 4, 4);
		}

		// draw lines between the points
		ga.drawLine((int) ps[0].getX(), (int) ps[0].getY(), //
				(int) ps[1].getX(), (int) ps[1].getY());

		ga.drawLine((int) ps[2].getX(), (int) ps[2].getY(), //
				(int) ps[3].getX(), (int) ps[3].getY());

		// draw the detected object
		image.getGraphics().drawOval((int) ht.getCenter().getX(), 20, 20, 20);

		// renderize image
		ImageIcon imageIcon = new ImageIcon(image);
		JLabel imageLabel = new JLabel(imageIcon);
		add(BorderLayout.CENTER, imageLabel);

		setVisible(true);
	}

}

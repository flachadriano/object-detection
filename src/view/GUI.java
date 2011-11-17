package view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI(BufferedImage image) {
		setTitle("Detecção de objetos - Adriano Flach de Araujo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(new BorderLayout());

		ImageIcon imageIcon = new ImageIcon(image);
		JLabel imageLabel = new JLabel(imageIcon);
		add(BorderLayout.CENTER, imageLabel);

		setVisible(true);
	}

}

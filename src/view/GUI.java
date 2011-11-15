package view;

import java.awt.BorderLayout;

import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI(PlanarImage image) {
		setTitle("Detecção de objetos - Adriano Flach de Araujo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(new BorderLayout());

		JPanel jPanel = new JPanel(new BorderLayout());
		ImageIcon imageIcon = new ImageIcon(image.getAsBufferedImage());
		JLabel imageLabel = new JLabel(imageIcon);
		jPanel.add(BorderLayout.CENTER, imageLabel);
		add(BorderLayout.CENTER, jPanel);

		setVisible(true);
	}

}

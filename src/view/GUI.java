package view;

import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI() {
		setTitle("Detecção de objetos - Adriano Flach de Araujo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}

	public void setImage(PlanarImage image) {
		removeAll();
		
		ImageIcon imageIcon = new ImageIcon(image.getAsBufferedImage());
		JLabel label = new JLabel(imageIcon);
		getContentPane().add(new JScrollPane(label));
	}

}

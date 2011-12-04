package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI() throws Exception {
		setTitle("Detecção de objetos - Adriano Flach de Araujo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);

		String[] fileNames = new String[] { "images/tennis.jpg",
				"images/8ball.jpg", "images/basketball.jpg" };

		JPanel global = new JPanel();
		JScrollPane scroll = new JScrollPane(global);
		add(BorderLayout.CENTER, scroll);
		
		JPanel jPanel;
		for (int i = 0; i < fileNames.length; i++) {
			jPanel = new Panel(fileNames[i]);
			global.add(jPanel);
		}

		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new GUI();
	}

}

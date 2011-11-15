package control;

import javax.media.jai.PlanarImage;

import model.EdgeDetection;
import view.GUI;

public class Core {

	public static void main(String[] args) throws Exception {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		System.setProperty("com.sun.media.imageio.disableCodecLib", "true");
		
		PlanarImage edgeDetection = EdgeDetection.execute("images/moedas-espanha.jpg");
		
		new GUI(edgeDetection);
	}
	
}

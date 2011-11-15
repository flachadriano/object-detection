package control;

import javax.media.jai.PlanarImage;

import view.GUI;

import model.EdgeDetection;

public class Core {

	public static void main(String[] args) throws Exception {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		System.setProperty("com.sun.media.imageio.disableCodecLib", "true");
		
		PlanarImage edgeDetection = EdgeDetection.execute("images/moedas-espanha.jpg");
		
		GUI gUI = new GUI();
		gUI.setImage(edgeDetection);
	}
	
}

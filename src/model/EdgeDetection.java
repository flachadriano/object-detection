package model;

import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

public class EdgeDetection {

	public static PlanarImage execute(BufferedImage image) throws Exception {
		PlanarImage erode = erode(image);
		PlanarImage binarization = binarize(erode);
		return binarization;
	}

	private static PlanarImage erode(BufferedImage image) throws Exception {
		float[] erode = new float[] //
		{ 0, 1, 0, //
				1, 1, 1, //
				0, 1, 0 };

		ParameterBlock pb = new ParameterBlock();
		
		pb.addSource(image);

		KernelJAI kernel = new KernelJAI(3, 3, erode);
		pb.add(kernel);

		return JAI.create("erode", pb);
	}

	private static PlanarImage binarize(PlanarImage image) {
		// convert to grayscale
		ParameterBlock bandCombineParams = new ParameterBlock();
		bandCombineParams.addSource(image);
		bandCombineParams.add(new double[][] { { 0.114, 0.587, 0.299, 0 } });
		image = JAI.create("bandcombine", bandCombineParams);

		// binarize
		ParameterBlock pb = new ParameterBlock();
		pb.add(new Double(128));
		pb.addSource(image);
		return JAI.create("binarize", pb);
	}

}

package model;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

public class EdgeDetection {

	public static PlanarImage execute(PlanarImage image) throws Exception {
		PlanarImage result = dilate(image);
		result = create("subtract", result, image);
		return result;
	}

	public static PlanarImage dilate(PlanarImage image) throws Exception {
		ParameterBlock pb = new ParameterBlock();

		pb.addSource(image);

		float[] dilate = new float[] //
		{ 0, 1, 0 //
				, 1, 1, 1//
				, 0, 1, 0 };
		KernelJAI kernel = new KernelJAI(3, 3, dilate);
		pb.add(kernel);

		return JAI.create("dilate", pb);
	}

	public static PlanarImage create(String opName, PlanarImage first,
			PlanarImage second) {
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(first);
		pb.addSource(second);
		PlanarImage result = JAI.create(opName, pb);

		return result;
	}

}

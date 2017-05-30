package gcrfs.algorithms;

import gcrfs.calculations.BasicCalcs;
import gcrfs.calculations.CalculationsGCRF;
import gcrfs.data.datasets.Dataset;
import gcrfs.learning.GradientAscent;
import gcrfs.learning.Parameters;

public class GCRF extends Basic {

	private GradientAscent learning;

	/**
	 * Class constructor specifying parameters for Gradient descent learning
	 * algorithm and data for GCRF.
	 * 
	 */
	public GCRF(Parameters parameters, Dataset data) {
		this.expectedY = data.getY();
		this.calcs = new CalculationsGCRF(data.getS(), data.getR());
		this.learning = new GradientAscent(parameters, calcs, expectedY, false, null);
		double[] params = learning.learn();
		this.alpha = params[0];
		this.beta = params[1];
	}

	public double[] predictOutputsForTest(double[][] testS, double[] testR) {
		CalculationsGCRF calc = new CalculationsGCRF(testS,testR);
		return calc.mu(alpha, beta);
	}

}

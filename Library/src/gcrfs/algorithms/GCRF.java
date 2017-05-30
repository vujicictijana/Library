package gcrfs.algorithms;

import gcrfs.calculations.BasicCalcs;
import gcrfs.calculations.Calculations;
import gcrfs.calculations.CalculationsGCRF;
import gcrfs.data.datasets.Dataset;
import gcrfs.learning.GradientDescent;
import gcrfs.learning.Parameters;
\\test
public class GCRF implements Algorithm {

	private double alpha;
	private double beta;
	private double[] expectedY;
	private Calculations calcs;
	private GradientDescent learning;

	/**
	 * Class constructor specifying parameters for Gradient descent learning
	 * algorithm and data for GCRF.
	 * 
	 */
	public GCRF(Parameters parameters, Dataset data) {
		super();
		this.expectedY = data.getY();
		this.calcs = new CalculationsGCRF(data.getS(), data.getR());
		this.learning = new GradientDescent(parameters, calcs, expectedY, false, null);
		double[] params = learning.learn();
		this.alpha = params[0];
		this.beta = params[1];
	}

	public double[] predictOutputs() {
		return calcs.mu(alpha, beta);
	}

	public double rSquared() {
		return BasicCalcs.rSquared(predictOutputs(), expectedY);
	}

}

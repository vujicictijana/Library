package algorithms;

import calculations.BasicCalcs;
import calculations.Calculations;
import calculations.CalculationsGCRF;
import data.datasets.Dataset;
import learning.GradientDescent;
import learning.Parameters;

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

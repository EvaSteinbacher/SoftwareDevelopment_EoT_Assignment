/* Authors: David Juricevic, Eva Missoni */

package plus.swe.processing;

public class CoordinatesTransformer {
	
	public static double[] transformCoordinates(double eastValue, double northValue)
{	
	
	// Transforming N / E Coordinates to X / Y  Screen coordinates
	// Cartesian origin (x=0 y=0 ) is at SWE: -180 E 90 N  (Eastern Siberian Sea)
	double xValue = 180 + eastValue;
	double yValue = 90 - northValue;
	
	// Adapting to screen size of 1000 px * 500 px
	double xWidthFactor = (double)1000/360;
	double yWidthFactor = (double)500/180;
	
	// multiplying x and y-values with screen resolution factor and saving it in a double array containing the two values
	//the array is returned as a result-parameter and will be used in the class PointVisualizer.java//Hello Kitty
	double[] resultCoordinates ={ xValue * xWidthFactor, yValue * yWidthFactor};

	return resultCoordinates;
	
}

}

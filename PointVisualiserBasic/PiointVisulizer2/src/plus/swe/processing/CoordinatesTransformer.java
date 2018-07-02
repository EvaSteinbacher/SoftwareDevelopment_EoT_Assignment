package plus.swe.processing;

public class CoordinatesTransformer {
	
	public static Coordinates transformCoordinates(double eastValue, double northValue)
	{
		// Creating Object containing the result
		Coordinates resultCoordinates = new Coordinates();
		
		// Transforming N / E Coordinates to X / Y  Screen coordinates
		// Cartesian origin (x=0 y = 0 ) is at SWE: -180 E 90 N
		// somewhere over far-eastern Siberian sea
		double xValue = 180 + eastValue;
		double yValue = 90 - northValue;
		
		// Adapting to screen length 1000
		double xWidthFactor = (double)1000/360;
		double yWidthFactor = (double)500/180;
		resultCoordinates.setX(xValue*xWidthFactor);
		resultCoordinates.setY(yValue*yWidthFactor);
		
		return resultCoordinates;
		
	}

}

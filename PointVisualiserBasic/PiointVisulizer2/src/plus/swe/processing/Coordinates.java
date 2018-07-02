/* -----------------------------------------------------
 * Helper Class for passing X and Y Coordinates to ensure type-safety
 * 13.06.2018
 * code adapted from: https://stackoverflow.com/questions/2036970/how-do-getters-and-setters-work 
 -------------------------------------------------------*/

package plus.swe.processing;

public class Coordinates {
	
	//private Variables
	double xValue;
	double yValue;
	
	// getter for X
	public double getX()
	{
	     //include validation, logic, logging or whatever you like here
	    return this.xValue;
	}
	// setter for X
	public void setX(double value)
	{
	     //include more logic
	     this. xValue = value;
	}
	
	// getter for Y
	public double getY()
	{
	     //include validation, logic, logging or whatever you like here
	    return this.yValue;
	}
	// setter for Y
	public void setY(double value)
	{
	     //include more logic
	     this. yValue = value;
	}

}

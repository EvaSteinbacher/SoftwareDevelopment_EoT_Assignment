package plus.swe.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.MultiPolygon;

import processing.core.PApplet;

public class PointVisualiserBasic extends PApplet  { 
	
	// global variables for width and height of the output window
	int width = 1000;
	int height = 500;
	static double wgs84UserEast = 0;
	static double wgs84UserNorth = 0;
	
	public static void main(String[] args) {
		// reference the Java class
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{

	        System.out.println("Enter N Coordinate");
	        wgs84UserNorth = Double.parseDouble(br.readLine());
	        System.out.println("Enter E Coordinate:");
	        wgs84UserEast = Double.parseDouble(br.readLine());
            br.close();
        }catch(NumberFormatException nfe)
		{
            System.err.println("Invalid Format!");
        }
		catch(IOException IO)
		{
            System.err.println("Invalid Format!");
        }

        PApplet.main("plus.swe.processing.PointVisualiserBasic");
    }
	
	public void settings() {
		// set the width and the height of the output window
		size(width,height);
	} // settings

	
	public void setup() {
		// define the colour mode and set the background colour
		background(240);
    	
		// define the style for the circle
    	fill(255,159,3);
        stroke(0,0,70);
      
        // define settings for the circle
        ellipseMode(CENTER);
        smooth();
        
        // define a 2D integer array containing the positions of the circles
        double wgs84East = 13.03978;
        double wgs84North = 47.82281;
        /*int[][] positions = {{200,200},{100,150},{300,100},{200,400},{600,150}};*/
        
        /*with new Coordinates () an instance of the class Coordinates is generated and assigned 
         * to the variable technoZcoordinates which is of type Coordinates*/
        Coordinates technoZ_Coordinates = new Coordinates(); 	
        Coordinates userCoordinates = new Coordinates();
        
        /*technoZcoordinates variable is filled with result of wgs-to-screen-transformation: */
        technoZ_Coordinates = CoordinatesTransformer.transformCoordinates(wgs84East, wgs84North);
        userCoordinates = CoordinatesTransformer.transformCoordinates(wgs84UserEast, wgs84UserNorth);
        // display the circles on the specified positions
       // for(int i=0; i<positions.length; i++)
        	ellipse((int)technoZ_Coordinates.getX(), (int)technoZ_Coordinates.getY(), 15, 15);
        System.out.println(userCoordinates.getX()+"  "+userCoordinates.getY());
        	ellipse((int)userCoordinates.getX(), (int)userCoordinates.getY(), 15, 15);
	}
	public void draw() {
		
	} // draw
} // class

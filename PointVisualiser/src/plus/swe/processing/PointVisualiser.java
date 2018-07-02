/* Authors: David Juricevic, Eva Missoni */

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

public class PointVisualiser extends PApplet  { 
	
	// global variables for width and height of the output window
	int width = 1000;
	int height = 500;
	static double wgs84UserEast = 0;
	static double wgs84UserNorth = 0;
	
	public static void main(String[] args) {
		
		// Enable displaying wgs84 location also through user-input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			//asks for user input as wgs84 (saved as Double) 
	        System.out.println("Enter North Coordinate:");
	        wgs84UserNorth = Double.parseDouble(br.readLine());		
	        System.out.println("Enter East Coordinate:");
	        wgs84UserEast = Double.parseDouble(br.readLine());
            br.close();
            
        // exception handling to deal with invalid user-input
        }catch(NumberFormatException nfe)
		{
            System.err.println("Invalid Format!");
        }
		catch(IOException IO)
		{
            System.err.println("IO Exception occurred");
        }
		
		// runs a PApplet application window 
        PApplet.main("plus.swe.processing.PointVisualiser"); 
    }
	
	public void settings() {
		// setting width and height of the output window
		size(width,height);
	}

	
	public void setup() {
		// color mode definition and setting of background color
		background(240);
    	
		// style definition for the circle
    	fill(255,159,3);
        stroke(0,0,70);
      
        // define settings for the circle
        ellipseMode(CENTER);
        smooth();
        
        // Define WGS84 coordinates for Techno-Z-Location (as double for precision) 
        double wgs84East = 13.03978;
        double wgs84North = 47.82281;
        
        // variables are filled with results of wgs-to-screen-transformation from Techno-Z coordinates and user-input coordinates
	    double[] technoZ_Coordinates = CoordinatesTransformer.transformCoordinates(wgs84East, wgs84North);
	    double[] userCoordinates = CoordinatesTransformer.transformCoordinates(wgs84UserEast, wgs84UserNorth);	
	    
	    // draws two ellipses: 1st with Techno-Z-Coordinates, 2nd with user-input coordinates   
		ellipse((int)technoZ_Coordinates[0], (int)technoZ_Coordinates[1], 15, 15);
     	ellipse((int)userCoordinates[0], (int)userCoordinates[1], 15, 15);
	}
	public void draw() {
		
	} 
} 

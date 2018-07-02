package plus.swe.processing;

import java.io.File;
import java.io.IOException;

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
	
	public static void main(String[] args) {
		
		// reference the Java class
        PApplet.main("plus.swe.processing.PointVisualiserBasic");
        
    }
	
	
	public void settings() {
		
		// set the width and the height of the output window
		size(width,height);
		
	} // settings

	
	public void setup() {

		// define the colour mode and set the background colour
		colorMode(HSB, 360, 100, 100);
		background(0);
    	
		// define the style for the circle
    	fill(0,0,20);
        stroke(0,0,70);
      
        // define settings for the circle
        ellipseMode(CENTER);
        smooth();
        
        // define a 2D integer array containing the positions of the circles
       // int[][] positions = {{200,200},{100,150},{300,100},{200,400},{600,150}};
        
        ShapefileDataStoreFactory ssf = new ShapefileDataStoreFactory(); 	// creates the shapefile data store 
        
        try {
        	//https://www.programcreek.com/java-api-examples/?api=org.geotools.data.shapefile.ShapefileDataStore
        	File f = new File("C:/Users/Eva/Desktop/GEOINFORMATIK_Salzburg/2_Semester/PractiseSoftwareDevelopment/pop_pnt");
           
        	ShapefileDataStore shapefile = new ShapefileDataStore(f.toURI().toURL());
      
            //SimpleFeatureIterator features = shapefile.getFeatureSource().getFeatures().features();
            //SimpleFeature shp;
            
            SimpleFeatureSource source = shapefile.getFeatureSource();
            SimpleFeatureCollection fc = source.getFeatures(); 			//contains all features 
            SimpleFeatureIterator iterator = fc.features(); 
          
            while(iterator.hasNext() == true){
            	
            	Feature currentFeature = iterator.next();
            	String the_geom = currentFeature.getProperty("the_geom").getValue().toString();
            	
            	System.out.println(the_geom);
            	
            	//SimpleFeature feature =  iterator.next();
            	// System.out.println(feature); 
           }
            
           
        
	} catch (IOException ex) {				//requested by the program
	    ex.printStackTrace();
	}
 	
	catch (Exception e) {					//general exception if sthg unexpected happened
		e.printStackTrace();
	}
	}

            
           /* features.close();
            shapefile.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        }
        // display the circles on the specified positions
        for(int i=0; i<positions.length; i++)
        	ellipse(positions[i][0], positions[i][1], 10, 10);
		
	} // setup */
	
	public void draw() {
		
	} // draw

} // class

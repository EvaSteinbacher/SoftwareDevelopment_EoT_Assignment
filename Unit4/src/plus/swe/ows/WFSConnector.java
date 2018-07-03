package plus.swe.ows;
import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.DefaultQuery;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.data.wfs.WFSDataStore;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.Identifier;
import org.opengis.filter.spatial.Intersects;


public class WFSConnector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		try {
			
		// Create a hash map: structure that can map keys to values; uses a hash function to compute an index into an array of buckets or slots, from which the desired value can be found.
					String getCapabilities = "http://data.stadt-salzburg.at/geodaten/wfs?service=wfs&version=1.1.0"; //without: "&request=GetFeature" 
					Map connectionParameters = new HashMap();		
					//entry in a hashmap: "put" requires key and value: put(key, value)
					connectionParameters.put(WFSDataStoreFactory.URL.key, getCapabilities);	//URL as key for hashmap and verkehrszeichen hyperlink as value
					connectionParameters.put(WFSDataStoreFactory.TIMEOUT.key, 10000);		//set timeout for establishing connection in ms
					
					WFSDataStoreFactory  dsf = new WFSDataStoreFactory(); 	// creates things
					    WFSDataStore dataStore = dsf.createDataStore(connectionParameters); // tells where the things sit
					   
					    SimpleFeatureSource source = dataStore.getFeatureSource("ogdsbg:verkehrszeichen"); // within the datastore which featuretype we actually want to get
					    //https://gis.stackexchange.com/questions/77639/geotools-wfs-feature-retrieval
					    SimpleFeatureCollection fc = source.getFeatures(); //contains all features (Verkehrszeichen)
					    SimpleFeatureIterator iterator = fc.features(); 	//declares iterator (zeigt vors erste Element)
					    
					    while( iterator.hasNext() )							//checks whether there are further elements 
					    {
					    	SimpleFeature feature =  iterator.next();		// goes to next element and reads it out to variable "feature" 
					    
					    	String verkehrszeichenbezeichnung = (String) feature.getAttribute("VZBEZ"); //progr. requires "add cast to string"
					    	if (verkehrszeichenbezeichnung.startsWith("Halten und Parken"))
					    			System.out.println(feature.getAttribute("ID")+" : "+verkehrszeichenbezeichnung);
					    }
					    	
					    	
				    	   	 /*//https://www.programcreek.com/java-api-examples/?api=org.geotools.data.simple.SimpleFeatureCollection
					    	while(iterator.hasNext()){
					     		SimpleFeature sf=iterator.next();
					     		Geometry gm=(Geometry)sf.getDefaultGeometry();
					     		if(!gm.isValid()){
					     			gm=JTSUtil.repair(gm);
					     			System.out.println(gm.isValid());
					     		}*/
					    
					    
					    
		} catch (IOException ex) {				//requested by the program
		    ex.printStackTrace();
		}
	 	
		catch (Exception e) {					//general exception if sthg unexpected happened
			e.printStackTrace();
		}

}
}
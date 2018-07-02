import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_access {

    public static void main(String[] args) {
        Connection conn = null; // declare the connection object

        try {
            Class.forName("org.postgresql.Driver"); // the JDBC driver is dynamically loaded at runtime --> how you load external postgres-library
            String url = "jdbc:postgresql://ZGIS221.geo.sbg.ac.at/lv856152_153";   //--> path to server, where database lies on: its a jdbc connection and for postgres
            String username = "sgroup01";
            String password = "salzach2017$";
            conn = DriverManager.getConnection(url, username, password); // initialization of the connection object

            String query = "SELECT * FROM resch.twitter;"; // SQL query

            Statement statement = conn.createStatement(); // statement object based on the given connection
            ResultSet rs = statement.executeQuery(query); // rs object contains the result of the query
            
            int count_total_tweets=0;
            int count_happiness=0;
            
            int count_airport_tweets=0;
            int total =0;

            while (rs.next()) // iterate as long as a next row in the result set exits
            {
                // for each row assign the values of the corresponding column to the java variable
                long id = rs.getLong("Tweet_ID");
                String txt = rs.getString("txt");
                String emotion  = rs.getString("JEmotion");
                float longitude = rs.getFloat("Tweet_long");
                float latitude = rs.getFloat("Tweet_lati");
                
                if (longitude > -71.032207 && longitude < -70.990236) {	
                	if (latitude > 42.349904 && latitude < 42.378093) {
                		count_airport_tweets++;
                	}
                	}
                total++;
                /* if (emotion.equals("happiness")) {
            	count_happiness++; 
            }*/
                
                }
        /*  
         *while(rs.next()){
			    System.out.println("COUNT(*)="+rs.getInt("COUNT(*)"));				
			  }
         * */
            System.out.println("Total tweets: " + total);
            System.out.println("Number at airports: " + count_airport_tweets);
            System.out.println("Happiness: " + count_happiness);

               /* if (emotion.equals("happiness")) {
                	count_happiness++; 
                }*/

                // print the values of the row to the console
                /*
                System.out.println("ID: " + id +
                        ", Text: " + txt +
                        ", Emotion: " + emotion +
                        ", (" + longitude + "/" + latitude + ")");
               */
        
               /* count_total_tweets++;
            
            System.out.println("Total count: " + count_total_tweets); // if it is done within wile counting, it will iterate every time
            System.out.println("Happiness: " + count_happiness);
            
            /* when you calculate integer, even though its float, you will end up with an integer result.
             * but we can treat happyness as a floating point. this is done with a casting : you cast an integer as a float (its no longer intgeger but a float now)
             */
           /* float percentage_happy = (100  * (float) count_happiness) / count_total_tweets; 
                    
            System.out.println("Percentage happy: " + percentage_happy);*/
            
            
            
// what percentage of tweets are labeled with happiness? 
            
            
            // gracefully close record set, statement and connection
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("SQLException occurred:\n");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred:\n");
            e.printStackTrace();
        }
    }
}

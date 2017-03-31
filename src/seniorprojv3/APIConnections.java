package seniorprojv3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Nick Schillaci
 */
public class APIConnections {
    
    private static final String address = "http://<IP_ADDRESS>";
    
    public static String getSong(String seachTerm) throws Exception {
        //TODO: currently returns a string of json objects, will return a list of Song objects
      StringBuilder result = new StringBuilder();
      URL url = new URL(address + "/songs/search/" + seachTerm);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         result.append(line);
      }
      rd.close();
      return result.toString();
    }
    
    public static String createSong(String title, String artist, String album, String url, String src) throws Exception {
        URL object;
        object = new URL(address + "/songs");
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Accept", "application/json");
	con.setRequestMethod("POST");
        
        // create json of song information to send to the API
	String input = "{ \"title\": \"" + title + "\", \"artist\": \"" + artist + "\", \"album\": \"" + album
            + "\", \"url\": \"" + url +"\", \"src\": \"" + src + "\" }";

	OutputStream os = con.getOutputStream();
	os.write(input.getBytes());
	os.flush();

	//TODO: currently returns a string of a json object, will return a Song object that was created
	StringBuilder sb = new StringBuilder();
	int HttpResult = con.getResponseCode();
	if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            } 
            br.close();
            return "" + sb.toString();  
	} else {
            return con.getResponseMessage();  
	}
    }
    
    public static String updateSong(int id, String title, String artist, String album, String url, String src) throws Exception {
        URL object;
        object = new URL(address + "/songs/update");
	HttpURLConnection con = (HttpURLConnection) object.openConnection();
	con.setDoOutput(true);
	con.setDoInput(true);
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Accept", "application/json");
	con.setRequestMethod("PUT");
        
	String input = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"artist\": \"" + artist
            + "\", \"album\": \"" + album + "\", \"url\": \"" + url +"\", \"src\": \"" + src + "\" }";

	OutputStream os = con.getOutputStream();
	os.write(input.getBytes());
	os.flush();

	//TODO: currently returning a string of a json object, will return a Song object that was updated
	StringBuilder sb = new StringBuilder();  
	int HttpResult = con.getResponseCode(); 
	if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }
            br.close();
            return "" + sb.toString();  
	} else {
            return con.getResponseMessage();  
	}  
    }
    
    public static boolean deleteSong(int id) throws Exception {
        URL object;
        object = new URL(address + "/songs/delete");
	HttpURLConnection con = (HttpURLConnection) object.openConnection();
	con.setDoOutput(true);
	con.setDoInput(true);
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Accept", "application/json");
	con.setRequestMethod("DELETE");
        
	String input = "{ \"id\": " + id + " }";

	OutputStream os = con.getOutputStream();
	os.write(input.getBytes());
	os.flush();
 
        // return true if song was successfully deleted
	int HttpResult = con.getResponseCode(); 
	if (HttpResult == HttpURLConnection.HTTP_OK) {
            return true;  
	} else {
            return false;  
	}  
    }
    
}

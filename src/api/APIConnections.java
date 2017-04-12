package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.Song;

/**
 *
 * @author Nick Schillaci
 */
public class APIConnections {
    
    private static final String address = "http://54.227.171.79";
    public static final int GET_SEARCH = 0, GET_TITLE = 1, GET_ARTIST = 2, GET_ALBUM = 3;
    public static final String SRC_YOUTUBE = "youtube";
    
    public static ArrayList<Song> getSongs(int getType, String seachTerm) throws Exception {
      StringBuilder result = new StringBuilder();
      URL url = null;
      switch(getType) {
          case GET_SEARCH:
              url = new URL(address + "/songs/search/" + seachTerm);
              break;
          case GET_TITLE:
              url = new URL(address + "/songs/title/" + seachTerm);
              break;
          case GET_ARTIST:
              url = new URL(address + "/songs/artist/" + seachTerm);
              break;
          case GET_ALBUM:
              url = new URL(address + "/songs/album/" + seachTerm);
              break;
          default:
              url = new URL(address + "/songs/search/" + seachTerm);
              break;
      }
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         result.append(line);
      }
      rd.close();
      
      // parse json array to ArrayList
      ArrayList<Song> songs = new Gson().fromJson(result.toString(), new TypeToken<ArrayList<Song>>(){}.getType());
      return songs;
    }
    
    public static Song createSong(String title, String artist, String album, String url, String src) throws Exception {
        // if the source is youtube, we only need the video id from the url
        if(src.equals(SRC_YOUTUBE)) {
            String videoId = getYoutubeVideoId(url);
            if (videoId.equals("")) {
                throw new InvalidYoutubeVideoUrlException();
            } else
                url = videoId;
        }
        URL object = new URL(address + "/songs");
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
            Gson gson = new Gson();
            return gson.fromJson(sb.toString(), Song.class); // parse JSON response to Song object 
	} else {
            return null; // client will know that the endpoint was not reached
	}
    }
    
    public static Song updateSong(int id, String title, String artist, String album, String url, String src) throws Exception {
        URL object;
        object = new URL(address + "/songs/update");
	HttpURLConnection con = (HttpURLConnection) object.openConnection();
	con.setDoOutput(true);
	con.setDoInput(true);
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Accept", "application/json");
	con.setRequestMethod("PUT");
        
        // create json of song information to send to the API
	String input = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"artist\": \"" + artist
            + "\", \"album\": \"" + album + "\", \"url\": \"" + url +"\", \"src\": \"" + src + "\" }";

	OutputStream os = con.getOutputStream();
	os.write(input.getBytes());
	os.flush();

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
            Gson gson = new Gson();
            return gson.fromJson(sb.toString(), Song.class); // parse JSON response to Song object
	} else {
            return null; // client will know that the endpoint was not reached
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
        
        // put id in json format to send to the API
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
    
    public static String getYoutubeVideoId(String youtubeUrl) {
        String video_id="";
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {
            String expression = "^.*((youtu.be"+ "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*";
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String groupIndex1 = matcher.group(7);
                if(groupIndex1!=null && groupIndex1.length()==11)
                    video_id = groupIndex1;
            }
	}
        return video_id; // Returns empty string if video URL does not match pattern
    }
    
}

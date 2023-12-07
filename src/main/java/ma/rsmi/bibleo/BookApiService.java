package ma.rsmi.bibleo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import ma.rsmi.bibleo.models.Book;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BookApiService {

    public BookApiService() {
    }

    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?orderBy=newest&";
    private static final String IMAGE_URL = "https://books.google.com/books/publisher/content/images/frontcover/";

    private static Image loadImageFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream inputStream = connection.getInputStream()) {
            // Convert InputStream to byte array
            byte[] imageData = inputStream.readAllBytes();

            // Create an Image from the byte array
            return new Image(new ByteArrayInputStream(imageData));
        } finally {
            connection.disconnect();
        }
    }

    public  String getResponseFromGoogleBookAPI(String query, int maxResults) throws IOException {
        URL url = new URL(API_URL +"maxResults="+maxResults+"&q=" +  query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            System.out.println("Fetching response from: " + url);
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } finally {
            connection.disconnect();
        }
    }

    public static void main(String[] args) {
        try {
            BookApiService bookApiService = new BookApiService();
            String apiResponse = bookApiService.getResponseFromGoogleBookAPI("t", 4 );
            ObservableList<Book> books = bookApiService.getBooks(apiResponse);
            System.out.println("Books -> " + books);
        } catch (JSONException | IOException e) {
            System.out.println("Error fetching data from the API.");
            e.printStackTrace();
        }
    }

    public ObservableList<Book> getBooks(String apiResponse) throws IOException {
        JSONObject data = new JSONObject(apiResponse);
        JSONArray JSONbooks = data.getJSONArray("items");
        ObservableList<Book> books = FXCollections.observableArrayList();
        for (int i = 0; i < JSONbooks.length(); i++) {
            JSONObject JSONbook = JSONbooks.getJSONObject(i);
            JSONObject volumeInfo = JSONbook.getJSONObject("volumeInfo");

            String id = JSONbook.getString("id");
            String title = volumeInfo.getString("title");
            String[] authors = fromJSONArrayToArray(volumeInfo, "authors");
            String subtitle = getStringProperty(volumeInfo, "subtitle");
            String description = getStringProperty(volumeInfo, "description");
            String publisher = getStringProperty(volumeInfo, "publisher");
            String publishedDate = getStringProperty(volumeInfo, "publishedDate");
            String imageUrl = IMAGE_URL + id +"?fife=w400-h720&source=gbs_api";

            books.add(new Book(id,  title, authors,subtitle,description,publisher,publishedDate, loadImageFromUrl(imageUrl))) ;
        }
        return books;
    }

    private static String[] fromJSONArrayToArray(JSONObject object, String propertyName) {
        String[] array = {};
        if (object.has(propertyName)){
            JSONArray JSONArray = object.getJSONArray(propertyName);
            array = new String[JSONArray.length()];
            if (JSONArray.length() > 0)
                for ( int j=0; j<JSONArray.length(); j++) array[j] = JSONArray.getString(j);
        }
        return array;
    }

    private static String getStringProperty(JSONObject object, String propertyName) {
        if (object.has(propertyName)) {
            return object.getString(propertyName);
        }
        return "";
    }
}


package ma.rsmi.bibleo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ma.rsmi.bibleo.models.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    private final BookApiService bookApiService = new BookApiService();

    @FXML
    private HBox hbCardsList;
    @FXML
    private GridPane gpBooksContainer;

    @FXML
    private Circle cAvatar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cropImage("milo-ert.jpg");
        setRecentlyAddedBooks();
        setRecommendedBooks();

    }

    private void setRecommendedBooks() {
        try {
            int row = 1;
            int column = 0;
            String apiResponse = bookApiService.getResponseFromGoogleBookAPI("Django+Python", 40);
            ObservableList<Book> books = bookApiService.getBooks(apiResponse);
            for (Book book : books) {
                try{

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/book.fxml"));
                    Parent root = loader.load();
                    BookController controller = loader.getController();
                    controller.setBookData(book);
                    if (column == 6){
                        column = 0;
                        ++row;
                    }
                    gpBooksContainer.add(root,column++,row);
                    gpBooksContainer.setHgap(20);
                    gpBooksContainer.setVgap(20);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cropImage(String filename) {
        Image image = new Image(getClass().getResource("/images/"+filename).toExternalForm(), false);
        cAvatar.setFill(new ImagePattern(image));
        cAvatar.setEffect(new DropShadow(5d,0d,0d, Color.GRAY));
    }
    private void setRecentlyAddedBooks() {

        try {
            String apiResponse = bookApiService.getResponseFromGoogleBookAPI("Java", 4);
            ObservableList<Book> books = bookApiService.getBooks(apiResponse);
            for (Book book : books) {
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/card.fxml"));
                    Parent root = loader.load();
                    CardController controller = loader.getController();
                    controller.initialize(book);
                    hbCardsList.getChildren().add(root);


                }catch(IOException e){
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
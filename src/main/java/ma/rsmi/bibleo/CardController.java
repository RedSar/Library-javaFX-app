package ma.rsmi.bibleo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ma.rsmi.bibleo.models.Book;
import ma.rsmi.bibleo.utils.Color;
import ma.rsmi.bibleo.utils.Helper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController {


    @FXML
    private HBox hbCard;
    @FXML
    private Label lblAuthors;

    @FXML
    private Label lblPublishedDate;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblSUbtitle;

    @FXML
    private Label lblTitle;

    @FXML
    private Rectangle rCoverImage;


    public void initialize(Book book) {
        setBookData(book);
    }



    private void setBookData(Book book) {
        rCoverImage.setFill(new ImagePattern(book.getImage()));
        lblTitle.setText(book.getTitle());
        lblSUbtitle.setText(book.getSubtitle());
        lblPublisher.setText(book.getPublisher());
        lblPublishedDate.setText(book.getPublishedDate());
        lblAuthors.setText(String.join(", ",book.getAuthors()));

        hbCard.setStyle("-fx-background-color: " + Helper.getColor());
    }


}



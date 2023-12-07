package ma.rsmi.bibleo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ma.rsmi.bibleo.models.Book;

public class BookController {

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblPublishedDate;

    @FXML
    private Label lblTitle;

    @FXML
    private Rectangle rcCover;


    public void setBookData(Book book){
        rcCover.setFill(new ImagePattern(book.getImage()));
        lblTitle.setText(book.getTitle());
        lblAuthor.setText(String.join(", ", book.getAuthors()));
        lblPublishedDate.setText( book.getPublishedDate());
    }

}

package ma.rsmi.bibleo.models;

import javafx.scene.image.Image;

import java.util.Arrays;

public class Book {
    private String id;
    private String title;

    private String[] authors;
    private String subtitle;
    private String description;
    private String publisher;
    private String publishedDate;
    private Image image;

    public Book(String id, String title, String[] authors, String subtitle, String description, String publisher, String publishedDate, Image image) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.subtitle = subtitle;
        this.description = description;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", subtitle='" + subtitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", image=" + image +
                '}';
    }
}

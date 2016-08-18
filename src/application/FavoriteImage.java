package application;

import javafx.beans.property.SimpleStringProperty;

public class FavoriteImage {

    private final SimpleStringProperty favoriteImage = new SimpleStringProperty();

    public FavoriteImage(String imagePath) {
        setFavoriteImage(imagePath);
    }

    public void setFavoriteImage(String favoriteImageFile) {
        favoriteImage.set(favoriteImageFile);
    }

    public String getFavoriteImage() {
        return favoriteImage.get();
    }
}
package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeleteImageModel {
    //private final StringProperty nameProperty = new SimpleStringProperty();
    private final ObjectProperty<FavoriteImage> favoriteImageProperty = new SimpleObjectProperty();
   
    private final BooleanProperty favoriteProperty;
        private final String BOOKMARK_IMG = "file:images/delete.jpg"; 

    public DeleteImageModel(boolean favorite) {
        //setName(name);
        this.favoriteProperty = new SimpleBooleanProperty(this, "favorite", favorite);
        hasFavoriteImage(getFavorite());
    }            
            
    public Object getFavoriteImage() {
        return this.favoriteImageProperty.get();
    }

    public ObjectProperty<FavoriteImage> favoriteImageProperty() {
        return favoriteImageProperty;
    }

    public void setFavoriteImage(FavoriteImage favoriteImage) {
        this.favoriteImageProperty.set(favoriteImage);
    }

    public void hasFavoriteImage(boolean image) {
        if (image) {
            setFavoriteImage(new FavoriteImage(BOOKMARK_IMG));
        } else {
            setFavoriteImage(null);
        }
    }        
    
    public Boolean getFavorite() {
        return this.favoriteProperty.get();
    }

    public BooleanProperty favoriteProperty() {
        return favoriteProperty;
    }

    public void setFavorite(Boolean favorite) {
        this.favoriteProperty.set(favorite);
    }    
        
}

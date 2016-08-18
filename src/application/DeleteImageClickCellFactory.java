package application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class DeleteImageClickCellFactory implements Callback<TableColumn, TableCell> {

    private final EventHandler<Event> click;

    public DeleteImageClickCellFactory(EventHandler click) {
        this.click = click;
    }

    @Override
    public TableCell call(TableColumn p) {
        TableCell<Object, FavoriteImage> cell
                = new TableCell<Object, FavoriteImage>() {

                    private final VBox vbox;
                    private final ImageView imageview;

                    // Constructor
                    {
                        vbox = new VBox();
                        vbox.setAlignment(Pos.CENTER);
                        imageview = new ImageView();
                        imageview.setFitHeight(35);
                        imageview.setFitWidth(35);
                        imageview.setVisible(true);
                        imageview.setCache(true);
                        vbox.getChildren().addAll(imageview);
                        setGraphic(vbox);
                    }

                    @Override
                    protected void updateItem(FavoriteImage item,
                            boolean empty) {

                        // calling super here is very important - don't skip this!
                        super.updateItem(item, empty);

                        if (empty) {
                            setText(null);
                            setGraphic(null);

                        } else {
                        	//System.out.println("PATH TO IMAGE:::::\n"+getClass().getResourceAsStream(
                            //                item.getFavoriteImage()));
                            /*Image image = new Image(
                                    getClass().getResourceAsStream(
                                            item.getFavoriteImage()));*/
                           Image image = new Image("file:images/delete.jpg");
                            imageview.setImage(image);
                            setGraphic(vbox);
                        }
                    }
                };

        // Simple click
        if (click != null) {
            cell.setOnMouseClicked(click);
        }

        return cell;
    }
}
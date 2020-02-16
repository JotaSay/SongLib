package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class Controller {
    @FXML
    ListView<Song> listview;
    @FXML Button okButton, deleteButton, cancelButton;
    @FXML TextField songName;

    @FXML

    private ObservableList<Song> obsList;

    public void start(Stage mainStage){
        // here we're making our obs list from the arraylist
    	
    	SongEditor songlib = new SongEditor();
    	
        obsList = FXCollections.observableArrayList(songlib.Songview);
        listview.setItems(obsList);
		FXCollections.sort(listview.getItems());

        listview.getSelectionModel().select(0);

    }
}

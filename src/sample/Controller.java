package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
    @FXML
    ListView<Song> listview;

    private ObservableList<Song> obsList;

    public void Start(){
        // here we're making our obs list from the arraylist
    	
    	SongEditor song
    	
    	
        obsList = FXCollections.observableArrayList(//fill this with the stuff from our text function
        );
        listview.setItems(obsList);

    }
}

package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }


    @FXML
    ListView<Song> listview;
    @FXML Button okButton, deleteButton, cancelButton;
    @FXML TextField songName;
    @FXML ChoiceBox<String> choice;

    private ObservableList<Song> obsList;

    public void start(Stage mainStage){
        // here we're making our obs list from the arraylist
    	
    	SongEditor songlib = new SongEditor();
    	
        obsList = FXCollections.observableArrayList(songlib.Songview);
        listview.setItems(obsList);
		FXCollections.sort(listview.getItems());

        listview.getSelectionModel().select(0);
        

        
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (listview.getItems().isEmpty()) {
					return;
				}
				
				Song s = listview.getSelectionModel().getSelectedItem();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				      //alert.initModality(Modality.NONE);
				      alert.initOwner(mainStage);
				      alert.setTitle("Delete");
				      alert.setHeaderText(
				           "Are you sure you want to delete the song "+s.getName()+" by "+s.getArtist()+"?");

//				      String content = "Index: " + 
//				          listView.getSelectionModel()
//				                   .getSelectedIndex() + 
//				          "\nValue: " + 
//				          listView.getSelectionModel()
//				                   .getSelectedItem();
//
//				          alert.setContentText(content);
				      Optional<ButtonType> res = alert.showAndWait();
				      
		

			}
		});
        

    }
}

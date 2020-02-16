package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // this is where we will initialize our code

    }


    @FXML
    ListView<Song> listview;
    @FXML Button okButton, deleteButton, cancelButton;
    @FXML TextField songName,artistName,album,Year;
    @FXML ChoiceBox<String> choice;
    @FXML TextArea topCurr,botCurr;


    private ObservableList<Song> obsList;

    public void start(Stage mainStage){
        // here we're making our obs list from the arraylist
    	
    	SongEditor songlib = new SongEditor();
    	
        obsList = FXCollections.observableArrayList(songlib.Songview);
        listview.setItems(obsList);
		FXCollections.sort(listview.getItems());

        listview.getSelectionModel().select(0);
        selectedSong();

        
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (listview.getItems().isEmpty()) {
					selectedSong();
					return;
				}
				
				Song s = listview.getSelectionModel().getSelectedItem();
				int index = listview.getSelectionModel().getSelectedIndex();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(mainStage);
				alert.setTitle("Delete");
				alert.setHeaderText(
						"Are you sure you want to delete the song "+s.getName()+" by "+s.getArtist()+"?");
				      
				Optional<ButtonType> result = alert.showAndWait();
				
				if(result.get() == ButtonType.OK) {
					obsList.remove(index);
					if (index == listview.getItems().size()) {
				        listview.getSelectionModel().select(index--);
					} else {
						listview.getSelectionModel().select(index++);
					}
				}
				selectedSong();
				alert.close();
				
			}
		});
        

    }

    public void selectedSong(){
        // this is where we gather the data of the selected song.
    	if (listview.getItems().isEmpty()) {
            topCurr.setText("");
            botCurr.setText("");

    	}
        Song song = listview.getSelectionModel().getSelectedItem();
        topCurr.setText(song.getName()+song.getArtist());
        botCurr.setText(song.getAlbum()+" " +song.getYear());



    }
}

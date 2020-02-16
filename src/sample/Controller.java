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
    public void editsong(){
        Song songy = listview.getSelectionModel().getSelectedItem();
        String s,art,al,ye;
        s = songName.getText();
        art = artistName.getText();
        al = album.getText();
        ye = Year.getText();
        if(!s.trim().isEmpty()){
            songy.setName(s);
        }
        if(!art.trim().isEmpty()){
            songy.setArtist(art);
        }
        if(!al.trim().isEmpty()){
            songy.setAlbum(al);
        }
        if(!ye.trim().isEmpty()){
            if(isInt(ye)){
                int meep = Integer.parseInt(ye);
                songy.setYear(meep);
            }
        }

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


    // this gets all the fields from the top bar and just filters them and then adds them to the classes.
    public void gather(){
        String s,art,al,ye;
        s = songName.getText();
        art = artistName.getText();
        al = album.getText();
        ye = Year.getText();
        // make sure that the text fields are valid
        if(songName.getText().trim().isEmpty()||artistName.getText().trim().isEmpty()){
            //have an error pop up
        }
        if(album.getText().trim().isEmpty()&& Year.getText().trim().isEmpty()){
            // the first two are good now add without album and year

            Song newsong = new Song(s,art,null,null);
            obsList.add(newsong);
        }
        else{
            if(isInt(ye)){
                Song newsong = new Song(s,art,al,Integer.parseInt(ye));
                obsList.add(newsong);
            }
        }
    }
    //Simply checks to see if our string for year is an int
    public boolean isInt(String year){
        try{
            int age = Integer.parseInt(year);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
}

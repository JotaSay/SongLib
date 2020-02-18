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
    @FXML ListView<Song> listview;
    @FXML Button okButton, deleteButton, editButton;
	@FXML TextField songName,artistName,album,Year, songDisp, artistDisp, albumDisp, yearDisp;
//	@FXML ChoiceBox<String> choice;
//    @FXML TextArea topCurr,botCurr;

	private ObservableList<Song> obsList;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		// this is where we will initialize our code

	}

    public void start(Stage mainStage){
        // here we're making our obs list from the arraylist
    	
    	mainStage.setOnCloseRequest(e->{
    	    e.consume();
    	    closeProgram(mainStage);
        });
        SongEditor songlib = new SongEditor();
        obsList = FXCollections.observableArrayList(songlib.Songview);
        listview.setItems(obsList);
		FXCollections.sort(listview.getItems());
        listview.getSelectionModel().select(0);
        selectedSong();

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				deletesong(mainStage);
			}
		});

		editButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				editsong(mainStage);
			}
		});

		okButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gather(mainStage);
			}
		});

    }


	public void deletesong(Stage mainStage){
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

    //checks song name and artist are not changed to a song that exists
    //alerts for confirmation
    public void editsong(Stage mainStage){
        if (listview.getItems().isEmpty()) {
				selectedSong();
				return;
		}
        
        String s,art,al,ye;
        s = songDisp.getText().trim();
        art = artistDisp.getText().trim();
        al = albumDisp.getText().trim();
    	ye = yearDisp.getText().trim();

    	Song songy = listview.getSelectionModel().getSelectedItem();
    	int index = listview.getSelectionModel().getSelectedIndex();
    	Song songy2 = processing(mainStage, s, art, al, ye);

    	if (songy2 != null) {
    		if (songExists(songy2) != -1 && songExists(songy2) != index) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.initOwner(mainStage);
    			alert.setTitle("Duplicate");
    			alert.setHeaderText("Song with same name and artist already exists. Song cannot be edited.");
    			alert.showAndWait();
    			alert.close();
    			return;
    		} else {
	            Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.initOwner(mainStage);
	    		alert.setTitle("Success");
	    		alert.setContentText(
	    				"Name: "+songy.getName()+" --> "+ songy2.getName()+"\n"+
	    				"Artist: "+songy.getArtist()+" --> "+ songy2.getArtist()+"\n"+
	    				"Album: "+songy.getAlbum()+" --> "+ songy2.getAlbum()+"\n"+
	    				"Year: "+songy.getYear()+" --> "+ songy2.getYear()+"\n"
	    				);

	    		Optional<ButtonType> result = alert.showAndWait();

	    		if(result.get() == ButtonType.OK) {
	    			obsList.remove(songy);
		            obsList.add(songy2);
		            FXCollections.sort(listview.getItems());
		            index = obsList.indexOf(songy2);
		            listview.getSelectionModel().select(index);
	    		}

	    		alert.close();

    		}


    	}

		selectedSong();

    }

    public void selectedSong() {
		// this is where we gather the data of the selected song.
		if (listview.getItems().isEmpty()) {
			songDisp.setText("");
			artistDisp.setText("");
			albumDisp.setText("");
			yearDisp.setText("");
		}
		Song song = listview.getSelectionModel().getSelectedItem();

		songDisp.setText(song.getName());
		artistDisp.setText(song.getArtist());
		albumDisp.setText(song.getAlbum());
		yearDisp.setText(song.getYear());
	}


    // this gets all the fields from the top bar and just filters them and then adds them to the classes.
    //checks song doesnt already exist
    public void gather(Stage mainStage) {
        String s,art,al,ye;
        s = songName.getText().trim();
        art = artistName.getText().trim();
        al = album.getText().trim();
    	ye = Year.getText().trim();


    	Song songy = processing(mainStage, s, art, al, ye);

        if (songy != null) {
        	if (songExists(songy) != -1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(mainStage);
				alert.setTitle("Duplicate");
				alert.setHeaderText("Song with same name and artist already exists. Song cannot be added.");
				alert.showAndWait();
				alert.close();
			} else {

		        Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(mainStage);
				alert.setTitle("Success");
				alert.setHeaderText("Song "+songy+" was added.");
				Optional<ButtonType> result = alert.showAndWait();

	    		if(result.get() == ButtonType.OK) {
	    			obsList.add(songy);
			        FXCollections.sort(listview.getItems());
			        int index = obsList.indexOf(songy);
			        listview.getSelectionModel().select(index);
	    		}
				alert.close();
			}

           	songName.setText("");
        	artistName.setText("");
        	album.setText("");
        	Year.setText("");
        }

		selectedSong();


    }


    //makes sure song name and artist are not empty, the year is an integer
    //throws alerts
    public Song processing(Stage mainStage, String s, String art, String al, String ye) {
        if (s.isEmpty() || art.isEmpty()) {
        	Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainStage);
			alert.setTitle("Empty Fields");
			alert.setHeaderText("Song name and artist cannot be empty.");
			alert.showAndWait();
			alert.close();
			return null;
        }

        if (!isInt(ye)) {
        	Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainStage);
			alert.setTitle("Year not a number");
			alert.setHeaderText("Please choose a number for year.");
			alert.showAndWait();
			return null;
        }

        Song songy = new Song(s, art, al, ye);
		return songy;
    }
    //Simply checks to see if our string for year is an int
    public boolean isInt(String year){
        if (year.isEmpty()) {
        	return true;
        }

    	try{
            int age = Integer.parseInt(year);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    //this is run when the user clicks the exit at the top right. Also gives them a chance to cancel the exit
    //This is also when the session is then saved and everything is rewritten.
    public void closeProgram(Stage mainStage){
        // create a checkbox class that allows us to double check things
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(mainStage);
		alert.setTitle("Closing Program");
		alert.setHeaderText("Are you sure you want to close");
		Optional<ButtonType> result = alert.showAndWait();

		if(result.get() == ButtonType.OK) {
			SongEditor.updateCanciones(obsList);
			mainStage.close();
		}
		alert.close();



    }
    //Simply just clears the fields written on
    public void clearFields(){
        songName.clear();
        artistName.clear();
        album.clear();
        Year.clear();
    }

    public int songExists(Song s1) {
    	for (Song s2 : obsList) {
    		if (s1.equals(s2)){
    			return listview.getItems().indexOf(s2);
    		}
    	}

    	return -1;
    }


}

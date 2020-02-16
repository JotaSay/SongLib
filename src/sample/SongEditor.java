package sample;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SongEditor {
    //this is our class that will hold an array list of songs and can delete and edit them
    ArrayList<Song> Songview = new ArrayList<Song>();

    //this will go through the song list and edit the contents of an individual song

    public void add(Song song){
    	this.Songview.add(song);
    }
    
    public SongEditor() {
		//loads in data from .txt file into the songview 
    	
    	
    	Scanner scanner = null;
		try {
			scanner = new Scanner(new File("data.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNextLine()){
    	    String line = scanner.nextLine();
    		String[] info = line.split(",");
    		if (info.length == 2) {
        	    Song s = new Song(info[0].trim(), info[1].trim(), "", "");
        	    this.add(s);
    		} else if (info.length == 3) {
    			Song s = new Song(info[0].trim(), info[1].trim(), info[2].trim(), "");
        	    this.add(s);
    		} else {
        	    Song s = new Song(info[0], info[1], info[2], info[3]);
        	    this.add(s);

    		}  
    	}
    	
    	scanner.close();

    }
    //this is called to update the text file lol
    public void updateCanciones(ObservableList<Song> yeet){
    	Scanner scanner = null;
    	try{
    		scanner = new Scanner (new File("data.txt"));
		}catch (FileNotFoundException e){
    		e.printStackTrace();
		}
    	for (Song m: yeet){

		}

	}

}

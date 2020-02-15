package sample;

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

    //
    public void edit(){

    }

    //
    public void delete(){

    }
    
    public SongEditor() throws FileNotFoundException {
		//loads in data from .txt file into the songview 
    	
    	
    	Scanner scanner = new Scanner(new File("data.txt"));
		while(scanner.hasNextLine()){
    	    String line = scanner.nextLine();
    		String[] info = line.split(",");
    		if (info.length == 2) {
        	    Song s = new Song(info[0], info[1]);
        	    this.add(s); 
    		} else {
    			if (info[3] == "") {
    				Song s = new Song(info[0], info[1], info[2], -1);
            	    this.add(s);
    			} else {
    				Song s = new Song(info[0], info[1], info[2], Integer.parseInt(info[3]));
            	    this.add(s);
    			}
    		}
    		  
    	}
    	
    	scanner.close();

    }

}

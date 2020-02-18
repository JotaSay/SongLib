package sample;

import javafx.collections.ObservableList;

import java.io.*;
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
        	    Song s = new Song(info[0], info[1]);
        	    this.add(s); 
    		} else {
    			if(info[3] == "") {
    				Song s = new Song(info[0], info[1], info[2], info[3]);
            	    this.add(s);
    			} else {
    				Song s = new Song(info[0], info[1], info[2], info[3]);
            	    this.add(s);
    			}
    		}
    		  
    	}
    	
    	scanner.close();

    }
    //this is called to update the text file lol
    public static void updateCanciones(ObservableList<Song> yeet)  {
    	try{
			BufferedWriter out = new BufferedWriter(new FileWriter("data.txt"));
			for (Song m: yeet){
				out.write(m.getName()+",");
				out.write(m.getArtist()+",");
				if(m.getAlbum()==null){
					out.write(",");
				}else{
					out.write(m.getAlbum()+",");

				}
				if(m.getYear()==null){
					out.write("\n");
				}else{
					out.write(m.getYear()+"\n");

				}
			}
			out.close();


		}catch (IOException e){
    		e.printStackTrace();
		}


	}

}

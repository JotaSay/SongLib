package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.print.DocFlavor.URL;

public class testing {
	
	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("data.txt"));
//    	Scanner scanner = new Scanner(text);
    	System.out.println("Read text file using Scanner");
    	
    	SongEditor songlib = new SongEditor();
    	while(scanner.hasNextLine()){
    	    //process each line
    	    String line = scanner.nextLine();
    		String[] info = line.split(",");
    		if (info.length < 4) {
        	    Song s = new Song(info[0], info[1]);
        	    songlib.add(s);
        	    
    		} else {
        	    Song s = new Song(info[0], info[1]);
        	    songlib.add(s);
    		}
    		  
    	}
    	
    	scanner.close();
    	
    	for (int i =0; i < songlib.Songview.length(); i++) {
    		System.out.println(songlib.Songview.get(i));
    	}
	}

}

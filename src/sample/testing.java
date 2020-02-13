package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.print.DocFlavor.URL;

public class testing {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("data.txt"));
    	SongEditor songlib = new SongEditor();
    	while(scanner.hasNextLine()){
    	    //process each line
    	    String line = scanner.nextLine();
    		String[] info = line.split(",");
    		if (info.length < 4) {
        	    Song s = new Song(info[0], info[1]);
        	    songlib.add(s);
        	    
    		} else {
        	    Song s = new Song(info[0], info[1], info[2], Integer.parseInt(info[3]));
        	    songlib.add(s);
    		}
    		  
    	}
    	scanner.close();
    	Iterator i = songlib.Songview.iterator();
    	while(i.hasNext()) {
    	    System.out.println(i.next());
    	}
	}

}

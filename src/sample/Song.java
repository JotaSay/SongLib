package sample;

public class Song {
    protected String name;
    protected String artist;
    private String album;
    private int year;


    // Our constructor, the last two fields will be null and -1 if not filled in prob have to figure out the -1 part
    public Song(String name, String artist, String album, int year) {

        this.name = name;
        this.artist = artist;
        this.album = album != null ? album : "";
        this.year = year != -1 ? year:-1;
    }
    
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }
    
    public Song() {
    }
    
    public String toString() {
    	return this.name+", "+this.artist+","+this.album+", "+this.year
    }
}

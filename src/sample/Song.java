package sample;

public class Song implements Comparable<Song>{
    private String name;
    private String artist;
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
    
    public String getName() {
		return this.name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
    
    public String toString() {
    	return this.name+" by "+this.artist;
    }
    
    
    public int compareTo(Song s) {
    	String name1 = this.name.toLowerCase();
    	String name2 = s.name.toLowerCase();
    	String artist1 = this.artist.toLowerCase();
    	String artist2 = s.artist.toLowerCase();
    	
    	if ( name1.equals(name2) ){
			return artist1.compareTo(artist2);
    	}
		
    	return name1.compareTo(name2); 

	}
}

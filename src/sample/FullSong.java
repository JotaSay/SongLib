package sample;

public class FullSong extends Song {
    private String album;
    private int year;

	public FullSong(String name, String artist, String album, int year) {
		super(name, artist);
		this.album = album != null ? album : "";
        this.year = year != -1 ? year:-1;
	}
	
	public FullSong(Song s, String album, int year) {
		super(s.name, s.artist);
		this.album = album != null ? album : "";
        this.year = year != -1 ? year:-1;
		
	}
	
	public edit(Song s, String album, int year) {
		super(s.name, s.artist);
		this.album = album != null ? album : "";
        this.year = year != -1 ? year:-1;
		
	}

	
	Song s
	
	s.edit(Artist, year)
	

}

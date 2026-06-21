package Music_Player.models;

public class Song {
    private String songTitle;
    private String songArtist;
    private String songPath;

    public Song(String songTitle, String songArtist, String songPath){
        this.songArtist = songArtist;
        this.songPath = songPath;
        this.songTitle = songTitle;
    }

    public String getSongTitle(){
        return songTitle;
    }

    public String getSongArtist(){
        return songArtist;
    }

    public String getSongPath(){
        return songPath;
    }
}

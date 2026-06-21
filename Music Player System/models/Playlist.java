package Music_Player.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String playlistName;
    private List<Song> playlist;

    public Playlist(String playlistName){
        this.playlistName = playlistName;
        playlist = new ArrayList<>();
    }

    public String getPlaylistName(){
        return playlistName;
    }

    public List<Song> getPlaylist(){
        return playlist;
    }

    public void addSongToPlaylist(Song song){
        if(song==null){
            throw new RuntimeException("There's no song like this... [null]");
        }
        playlist.add(song);
    }

    public int getPlaylistSize(){
        return playlist.size();
    }
}

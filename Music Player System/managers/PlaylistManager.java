package Music_Player.managers;

import Music_Player.models.Song;
import Music_Player.models.Playlist;

import java.util.HashMap;
import java.util.Map;

public class PlaylistManager {

    private static PlaylistManager instance = null;
    private Map<String,Playlist> playlists;

    private PlaylistManager(){
        playlists = new HashMap<>();
    }

    public static synchronized PlaylistManager getInstance(){
        if(instance==null){
            instance = new PlaylistManager();
        }
        return instance;
    }

    public void createPlaylist(String playlistName){
        if(playlists.containsKey(playlistName)){
            throw new RuntimeException("Playlist \"" + playlistName + "\" not found...");
        }
        playlists.put(playlistName,new Playlist(playlistName));
    }

    public void addSongToPlaylist(String playlistName,Song song){
        if(!playlists.containsKey(playlistName)){
            throw new RuntimeException("Playlist \"" + playlistName + "\" not found.");
        }
        playlists.get(playlistName).addSongToPlaylist(song);
    }

    public Playlist getPlaylist(String playlistName){
        if(!playlists.containsKey(playlistName)){
            throw new RuntimeException("Playlist \"" + playlistName + "\" not found.");
        }
        return playlists.get(playlistName);
    }
}

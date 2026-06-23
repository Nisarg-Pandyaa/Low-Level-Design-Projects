package Music_Player;

import Music_Player.managers.DeviceManager;
import Music_Player.managers.StrategyManager;
import Music_Player.models.Song;
import Music_Player.managers.PlaylistManager;
import Music_Player.enums.*;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerApplication {
    private static MusicPlayerApplication instance = null;
    private List<Song> songLibrary;

    private MusicPlayerApplication(){
        songLibrary = new ArrayList<>();
    }

    public static synchronized MusicPlayerApplication getInstance(){
        if(instance==null){
            instance = new MusicPlayerApplication();
        }
        return instance;
    }

    public void createNewSong(String songName,String songArtist, String songPath){
        Song song = new Song(songName,songArtist,songPath);
        songLibrary.add(song);
    }

    public Song findSongByTitle(String songName){

        for(Song s : songLibrary){
            if(s.getSongTitle().equals(songName)){
                return s;
            }
        }
        return null;
    }

    public void createNewPlaylist(String playlistName){
        PlaylistManager.getInstance().createPlaylist(playlistName);
    }

    public void addSongToPlaylist(String playlistName,String songTitle){
        Song song = findSongByTitle(songTitle);
        if(song==null){
            throw new RuntimeException("Song \"" + songTitle + "\" not found in library.");
        }
        PlaylistManager.getInstance().addSongToPlaylist(playlistName,song);
    }

    public void connectAudioDevice(DeviceType deviceType){
        MusicPlayerFacade.getInstance().connectDevice(deviceType);
    }

    public void selectPlayistPlayStrategy(PlayStrategyType strategyType){
        MusicPlayerFacade.getInstance().setPlaylistPlayStrategy(strategyType);
    }

    public void loadPlaylist(String playlistName){
        MusicPlayerFacade.getInstance().setLoadedPlaylist(playlistName);
    }

    public void playSingleSong(String songTitle){
        Song song = findSongByTitle(songTitle);
        if(song==null){
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        MusicPlayerFacade.getInstance().playSong(song);
    }

    public void pauseCurrSong(String songTitle){
        Song song = findSongByTitle(songTitle);
        if(song==null){
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        MusicPlayerFacade.getInstance().pauseSong(song);
    }

    public void playAllTracksInPlaylist(){
        MusicPlayerFacade.getInstance().playAllTracks();
    }

    public void playPreviousTrackInPlaylist(){
        MusicPlayerFacade.getInstance().playPrevTrack();
    }

    public void queueSongNext(String songTitle){
        Song song = findSongByTitle(songTitle);
        if(song==null){
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        MusicPlayerFacade.getInstance().enqueueNext(song);
    }
}

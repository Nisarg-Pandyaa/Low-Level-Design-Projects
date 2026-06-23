package Music_Player;

import Music_Player.core.AudioEngine;
import Music_Player.models.*;
import Music_Player.strategies.PlaylistPlayStrategy;
import Music_Player.enums.*;
import Music_Player.managers.*;
import Music_Player.devices.IAudioOutputDevice;

public class MusicPlayerFacade {

    private static MusicPlayerFacade instance = null;
    private Playlist loadedPlaylist;
    private AudioEngine audioEngine;
    private PlaylistPlayStrategy playlistPlayStrategy;

    private MusicPlayerFacade(){
        loadedPlaylist = null;
        audioEngine = new AudioEngine();
        playlistPlayStrategy = null;

    }

    public static synchronized MusicPlayerFacade getInstance(){
        if(instance==null){
            instance = new MusicPlayerFacade();
        }
        return instance;
    }

    public void connectDevice(DeviceType deviceType){
        DeviceManager.getInstance().connect(deviceType);
    }


    public void setPlaylistPlayStrategy(PlayStrategyType strategyType){
        playlistPlayStrategy = StrategyManager.getInstance().getStrategy(strategyType);
    }

    public void setLoadedPlaylist(String playlistName){
        loadedPlaylist = PlaylistManager.getInstance().getPlaylist(playlistName);
        if(playlistPlayStrategy == null){
            throw new RuntimeException("Play strategy not set before loading.");
        }
        playlistPlayStrategy.setPlaylist(loadedPlaylist);
    }

    public void playSong(Song song){
        if(!DeviceManager.getInstance().hasOutputDevice()){
            throw new RuntimeException("No Output Device Is Connected...");
        }
        IAudioOutputDevice device = DeviceManager.getInstance().getCurrentOutputDevice();
        audioEngine.play(device,song);
    }

    public void pauseSong(Song song){
        if(!audioEngine.getCurrentSongTitle().equals(song.getSongTitle())){
            throw new RuntimeException("Cannot pause \"" + song.getSongTitle() + "\"; not currently playing.");
        }
        audioEngine.pause();
    }

    public void playAllTracks(){
        if(loadedPlaylist==null){
            throw new RuntimeException("No Playlist Loaded...");
        }

        while(playlistPlayStrategy.hasNextSong()){
            Song nextSong = playlistPlayStrategy.nextSong();

            IAudioOutputDevice device = DeviceManager.getInstance().getCurrentOutputDevice();
            audioEngine.play(device,nextSong);
        }

        System.out.println("Completed playlist: " + loadedPlaylist.getPlaylistName());
    }

    public void playNextTrack(){
        if(loadedPlaylist==null){
            throw new RuntimeException("No Playlist Loaded...");
        }

        if(playlistPlayStrategy.hasNextSong()){
            Song nextSong = playlistPlayStrategy.nextSong();
            IAudioOutputDevice device = DeviceManager.getInstance().getCurrentOutputDevice();
            audioEngine.play(device,nextSong);
        }
        else{
            System.out.println("Completed playlist: " + loadedPlaylist.getPlaylistName());
        }
    }

    public void playPrevTrack(){
        if(loadedPlaylist==null){
            throw new RuntimeException("No Playlist Loaded...");

        }

        if(playlistPlayStrategy.hasPrevSong()){
            Song prevSong = playlistPlayStrategy.prevSong();
            IAudioOutputDevice device = DeviceManager.getInstance().getCurrentOutputDevice();
            audioEngine.play(device,prevSong);
        }
        else{
            System.out.println("Completed playlist: " + loadedPlaylist.getPlaylistName());
        }
    }

    public void enqueueNext(Song song) {
        playlistPlayStrategy.addToNext(song);
    }
}

package Music_Player.core;

import Music_Player.models.Song;
import Music_Player.devices.IAudioOutputDevice;

public class AudioEngine {

    private Song currentSong;
    private boolean songIsPaused;

    public AudioEngine(){
        currentSong = null;
        songIsPaused = false;
    }

    public String getCurrentSongTitle(){
        if(currentSong!=null){
            return currentSong.getSongTitle();
        }

        return "No Song Is Playing...";
    }

    public boolean isPaused(){
        return songIsPaused;
    }

    public void play(IAudioOutputDevice aod, Song song){
        if(song==null){
            throw new RuntimeException("This song does not exist [null]");
        }

        // Resume if same song was paused
        if(songIsPaused && song==currentSong){
            songIsPaused = false;
            System.out.println("Resuming Song : "+currentSong.getSongTitle());
            aod.playAudio(song);
        }
        else{   // Playing new song
            currentSong = song;
            songIsPaused = false;
            System.out.println("Playing New Song : "+currentSong.getSongTitle());
            aod.playAudio(song);
        }
    }

    public void pause(){
        if(currentSong==null){
            throw new RuntimeException("No Song Is Current Playing...");
        }

        if(songIsPaused){
            throw new RuntimeException("Song Is Already Paused...");
        }
        songIsPaused = true;
        System.out.println("Pausing Song : "+currentSong.getSongTitle());
    }
}

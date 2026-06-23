package Music_Player;

import Music_Player.enums.*;

public class Main {
    public static void main(String[] args) {
        try{

            MusicPlayerApplication spotify = MusicPlayerApplication.getInstance();

            // Populate library
            spotify.createNewSong("Kesariya", "Arijit Singh", "/music/kesariya.mp3");
            spotify.createNewSong("Chaiyya Chaiyya", "Sukhwinder Singh", "/music/chaiyya_chaiyya.mp3");
            spotify.createNewSong("Tum Hi Ho", "Arijit Singh", "/music/tum_hi_ho.mp3");
            spotify.createNewSong("Jai Ho", "A. R. Rahman", "/music/jai_ho.mp3");
            spotify.createNewSong("Zinda", "Siddharth Mahadevan", "/music/zinda.mp3");

            // Create playlist and add songs
            spotify.createNewPlaylist("Bollywood Vibes");
            spotify.addSongToPlaylist("Bollywood Vibes", "Kesariya");
            spotify.addSongToPlaylist("Bollywood Vibes", "Chaiyya Chaiyya");
            spotify.addSongToPlaylist("Bollywood Vibes", "Tum Hi Ho");
            spotify.addSongToPlaylist("Bollywood Vibes", "Jai Ho");

            // Connect device
            spotify.connectAudioDevice(DeviceType.HEADPHONE);

            //Play/pause a single song
            spotify.playSingleSong("Zinda");
            spotify.pauseCurrSong("Zinda");
            spotify.playSingleSong("Zinda");  // resume

            System.out.println("\n-- Sequential Playback --\n");
            spotify.selectPlayistPlayStrategy(PlayStrategyType.SEQUENTIAL);
            spotify.loadPlaylist("Bollywood Vibes");
            spotify.playAllTracksInPlaylist();

            System.out.println("\n-- Random Playback --\n");
            spotify.selectPlayistPlayStrategy(PlayStrategyType.RANDOM);
            spotify.loadPlaylist("Bollywood Vibes");
            spotify.playAllTracksInPlaylist();

            System.out.println("\n-- Custom Queue Playback --\n");
            spotify.selectPlayistPlayStrategy(PlayStrategyType.CUSTOM_QUEUE);
            spotify.loadPlaylist("Bollywood Vibes");
            spotify.queueSongNext("Kesariya");
            spotify.queueSongNext("Tum Hi Ho");
            spotify.playAllTracksInPlaylist();

            System.out.println("\n-- Play Previous in Sequential --\n");
            spotify.selectPlayistPlayStrategy(PlayStrategyType.SEQUENTIAL);
            spotify.loadPlaylist("Bollywood Vibes");
            spotify.playAllTracksInPlaylist();

            spotify.playPreviousTrackInPlaylist();
            spotify.playPreviousTrackInPlaylist();
        }
        catch (Exception error){
            System.err.println("Error: " + error.getMessage());
        }
    }
}

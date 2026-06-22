package Music_Player.devices;

import Music_Player.models.Song;
import Music_Player.externels.HeadphoneAPI;

public class HeadphoneAdapter implements IAudioOutputDevice{
    private HeadphoneAPI headphoneAPI;

    public HeadphoneAdapter(HeadphoneAPI api){
        this.headphoneAPI = api;
    }

    @Override
    public void playAudio(Song song){
        String playLoad = song.getSongTitle() +" by "+song.getSongArtist();
        headphoneAPI.playSoundViaHeadphone(playLoad);
    }

}

package Music_Player.devices;

import Music_Player.models.Song;
import Music_Player.externels.WiredEarbudAPI;

public class WiredEarbudAdapter implements IAudioOutputDevice{

    private WiredEarbudAPI wiredEarbudAPI;

    public WiredEarbudAdapter(WiredEarbudAPI api){
        this.wiredEarbudAPI = api;
    }

    @Override
    public void playAudio(Song song){
        String playLoad = song.getSongTitle() + " by " + song.getSongArtist();
        wiredEarbudAPI.soundViaWiredEarbud(playLoad);
    }
}

package Music_Player.devices;

import Music_Player.models.Song;
import Music_Player.externels.BluetoothSpeakerAPI;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice{

    private BluetoothSpeakerAPI bluetoothSpeakerAPI;

    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI api){
        this.bluetoothSpeakerAPI = api;
    }

    @Override
    public void playAudio(Song song){
        String playLoad = song.getSongTitle() + " by " + song.getSongArtist();
        bluetoothSpeakerAPI.playSoundViaBluetoothSpeaker(playLoad);
    }
}

package Music_Player.devices;
import Music_Player.models.Song;

public interface IAudioOutputDevice {
    void playAudio(Song song);
}

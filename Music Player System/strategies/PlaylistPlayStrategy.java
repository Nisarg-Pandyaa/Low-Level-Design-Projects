package Music_Player.strategies;

import Music_Player.models.Playlist;
import Music_Player.models.Song;

public interface PlaylistPlayStrategy {

    void setPlaylist(Playlist playlist);
    Song nextSong();
    boolean hasNextSong();
    Song prevSong();
    boolean hasPrevSong();
    default void addToNext(Song song){}
}

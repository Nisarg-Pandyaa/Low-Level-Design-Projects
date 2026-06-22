package Music_Player.strategies;

import Music_Player.models.*; //Song & Playlist

public class SequentialPlayingPlaylistStrategy implements PlaylistPlayStrategy{

    private Playlist currPlaylist;
    private int currentIndex;

    public SequentialPlayingPlaylistStrategy(){
        currPlaylist = null;
        currentIndex = -1;
    }

    @Override
    public void setPlaylist(Playlist playlist){
        this.currPlaylist = playlist;
        currentIndex = -1;
    }

    @Override
    public Song nextSong(){
        if(currPlaylist==null || currPlaylist.getPlaylistSize()==0){
            throw new RuntimeException("No Playlist Loaded Or It's empty.");
        }
        currentIndex++;
        return currPlaylist.getPlaylist().get(currentIndex);
    }

    @Override
    public boolean hasNextSong(){
        return (currentIndex + 1) < currPlaylist.getPlaylistSize();
    }

    @Override
    public Song prevSong(){
        if(currPlaylist==null || currPlaylist.getPlaylistSize()==0){
            throw new RuntimeException("No Playlist Loaded Or It's empty.");
        }
        currentIndex--;
        return currPlaylist.getPlaylist().get(currentIndex);
    }

    @Override
    public boolean hasPrevSong(){
        return (currentIndex - 1) > -1;
    }


}

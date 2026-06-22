package Music_Player.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import Music_Player.models.*;

public class RandomPlayingPlaylistStrategy implements PlaylistPlayStrategy {

    private Playlist currPlaylist;
    private List<Song> remainingSongs;
    private Stack<Song> history;
    private Random random;

    public RandomPlayingPlaylistStrategy(){
        currPlaylist = null;
        random = new Random();
    }

    @Override
    public void setPlaylist(Playlist playlist){
        currPlaylist = playlist;
        if(currPlaylist==null || currPlaylist.getPlaylistSize()==0) return;

        remainingSongs = new ArrayList<>(currPlaylist.getPlaylist());
        history = new Stack<>();
    }

    @Override
    public boolean hasNextSong(){
        return currPlaylist!=null && !currPlaylist.getPlaylist().isEmpty();
    }

    @Override
    public Song nextSong(){
        if (currPlaylist == null || currPlaylist.getPlaylistSize()== 0) {
            throw new RuntimeException("No playlist loaded or playlist is empty.");
        }
        if (remainingSongs.isEmpty()) {
            throw new RuntimeException("No songs left to play");
        }

        int idx = random.nextInt(remainingSongs.size());
        Song selectedSong = remainingSongs.get(idx);

        // Remove the selectedSong from the list. (Swap and pop to remove in O(1))
        int lastIndex = remainingSongs.size() - 1;
        remainingSongs.set(idx, remainingSongs.get(lastIndex));
        remainingSongs.remove(lastIndex);

        history.push(selectedSong);
        return selectedSong;
    }

    @Override
    public boolean hasPrevSong(){
        return !history.isEmpty();
    }

    @Override
    public Song prevSong(){
        if(history.isEmpty()){
            throw new RuntimeException("No previous song available.");
        }

        return history.pop();
    }
}

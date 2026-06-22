package Music_Player.strategies;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import Music_Player.models.*;

public class CustomQueuePlayingPlaylistStrategy implements PlaylistPlayStrategy{

    private Playlist currentPlaylist;
    private int currentIndex;
    private Queue<Song> nextQueue;
    private Stack<Song> prevStack;

    private Song nextSequential() {
        if (currentPlaylist.getPlaylistSize() == 0) {
            throw new RuntimeException("Playlist is empty.");
        }
        currentIndex = currentIndex + 1;
        return currentPlaylist.getPlaylist().get(currentIndex);
    }

    private Song previousSequential() {
        if (currentPlaylist.getPlaylistSize() == 0) {
            throw new RuntimeException("Playlist is empty.");
        }
        currentIndex = currentIndex - 1;
        return currentPlaylist.getPlaylist().get(currentIndex);
    }

    public CustomQueuePlayingPlaylistStrategy() {
        currentPlaylist = null;
        currentIndex = -1;
        nextQueue = new LinkedList<>();
        prevStack = new Stack<>();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        currentIndex = -1;
        nextQueue.clear();
        prevStack.clear();
    }

    @Override
    public boolean hasNextSong() {
        return ((currentIndex + 1) < currentPlaylist.getPlaylistSize());
    }

    @Override
    public Song nextSong() {
        if (currentPlaylist == null || currentPlaylist.getPlaylistSize() == 0) {
            throw new RuntimeException("No playlist loaded or playlist is empty.");
        }

        if (!nextQueue.isEmpty()) {
            Song s = nextQueue.poll();
            prevStack.push(s);

            // update index to match queued song
            for (int i = 0; i < currentPlaylist.getPlaylistSize(); ++i) {
                if (currentPlaylist.getPlaylist().get(i) == s) {
                    currentIndex = i;
                    break;
                }
            }
            return s;
        }

        // Otherwise sequential
        return nextSequential();
    }

    @Override
    public boolean hasPrevSong() {
        return (currentIndex - 1 > 0);
    }

    @Override
    public Song prevSong() {
        if (currentPlaylist == null || currentPlaylist.getPlaylistSize() == 0) {
            throw new RuntimeException("No playlist loaded or playlist is empty.");
        }

        if (!prevStack.isEmpty()) {
            Song s = prevStack.pop();

            // update index to match stacked song
            for (int i = 0; i < currentPlaylist.getPlaylistSize(); ++i) {
                if (currentPlaylist.getPlaylist().get(i) == s) {
                    currentIndex = i;
                    break;
                }
            }
            return s;
        }

        // Otherwise sequential
        return previousSequential();
    }

    @Override
    public void addToNext(Song song) {
        if (song == null) {
            throw new RuntimeException("Cannot enqueue null song.");
        }
        nextQueue.add(song);
    }
}

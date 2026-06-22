package Music_Player.managers;

import Music_Player.strategies.*;
import Music_Player.enums.PlayStrategyType;

public class StrategyManager {
    private static StrategyManager instance = null;
    private SequentialPlayingPlaylistStrategy sequentialStrategy;
    private RandomPlayingPlaylistStrategy randomStrategy;
    private CustomQueuePlayingPlaylistStrategy customQueueStrategy;

    private StrategyManager() {
        sequentialStrategy = new SequentialPlayingPlaylistStrategy();
        randomStrategy = new RandomPlayingPlaylistStrategy();
        customQueueStrategy = new CustomQueuePlayingPlaylistStrategy();
    }

    public static synchronized StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }
        return instance;
    }

    public PlaylistPlayStrategy getStrategy(PlayStrategyType type) {
        if (type == PlayStrategyType.SEQUENTIAL) {
            return sequentialStrategy;
        } else if (type == PlayStrategyType.RANDOM) {
            return randomStrategy;
        } else {
            return customQueueStrategy;
        }
    }
}


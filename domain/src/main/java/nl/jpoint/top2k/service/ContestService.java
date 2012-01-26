package nl.jpoint.top2k.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import nl.jpoint.top2k.domain.Artist;
import nl.jpoint.top2k.domain.Track;
import nl.jpoint.top2k.domain.User;

/**
 * Default implementation of the {@link IContestService}.
 */
public class ContestService implements IContestService {

    @Inject
    private IUserService userService;
    @Inject
    private ITrackService trackService;

    private final Random random = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Track> getTracksForContest() {
        List<Track> tracks = new ArrayList<Track>();
        
        List<Long> validIds = trackService.getValidTrackIds();
        tracks.add(trackService.getById(getRandomIdFrom(validIds)));
        tracks.add(trackService.getById(getRandomIdFrom(validIds)));
        return tracks;
    }

    /**
     * Returns a random id from the provided valid ids.
     * @param validIds list of valid ids.
     * @return
     */
    private long getRandomIdFrom(final List<Long> validIds) {
        int idIndex = random.nextInt(validIds.size());
        return validIds.get(idIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerContestResult(Track trackOne, Track trackTwo, int result, User user) {
    }

}

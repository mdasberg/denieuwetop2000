package nl.jpoint.top2k.service;

import java.util.List;

import nl.jpoint.top2k.domain.Track;
import nl.jpoint.top2k.domain.User;

/**
 * Contest Service that provides the methods for the actual contest. It provides a method to get the tracks for a
 * contest and a method to register the result of a Contest.
 */
public interface IContestService {

    List<Track> getTracksForContest();

    void registerContestResult(Track trackOne, Track trackTwo, int result, User user);

}

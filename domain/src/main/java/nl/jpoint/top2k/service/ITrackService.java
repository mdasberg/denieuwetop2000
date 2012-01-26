package nl.jpoint.top2k.service;

import java.util.List;

import nl.jpoint.top2k.domain.Track;

/** TrackService interface. */
public interface ITrackService {

    /**
     * Creates a new track.
     * @param track the new track to create.
     */
    void create(Track track);

    /**
     * Returns all tracks.
     * @return a list with all tracks.
     */
    List<Track> getAll();

    /**
     * Returns all tracks, but paged, where the provided <code>page</code> determines the page to show.
     * @param page the number of the page to show.
     * @return a list with tracks.
     */
    List<Track> getPagedList(int page);

    /**
     * Returns a single track referenced by the provided <code>id</code>.
     * @param id the id of the track to retrieve.
     * @return the track with provided id.
     */
    Track getById(long id);

    /**
     * Returns a list of all valid track ids.
     * @return a list of all valid track ids.
     */
    List<Long> getValidTrackIds();
}

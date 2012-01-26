package nl.jpoint.top2k.service;

import java.util.List;
import javax.persistence.Query;

import nl.jpoint.top2k.domain.Artist;

/** ArtistService interface. */
public interface IArtistService {

    /**
     * Creates a new artist.
     * @param artist the new artist to create.
     */
    void create(Artist artist);

    /**
     * Returns all artists.
     * @return a list with all artists.
     */
    List<Artist> getAll();

    /**
     * Returns all artists, but paged, where the provided <code>page</code> determines the page to show.
     * @param page the number of the page to show.
     * @return a list with artists.
     */
    List<Artist> getPagedList(int page);

    /**
     * Returns a single artist referenced by the provided <code>id</code>.
     * @param id the id of the artist to retrieve.
     * @return the artist with provided id.
     */
    Artist getById(long id);

}

package nl.jpoint.top2k.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Provider;
import nl.jpoint.top2k.domain.Track;

/**
 * Track Service.
 */
public class TrackService {

    @Inject
    private Provider<EntityManager> provider;

    public void create(final Track track) {

        provider.get().persist(track);
    }

    public List<Track> getAll() {
        return provider.get().createNamedQuery("Track.findAll").getResultList();
    }
}

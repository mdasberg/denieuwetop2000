package nl.jpoint.top2k.domain;

import java.util.Date;
import java.util.UUID;

/**
 * An artist is generally a musician, group of musicians, a collaboration of multiple musicians or other music
 * professional contributing to the works described in the MusicBrainz Database. As the MusicBrainz project evolves,
 * so does the definition of "artist", so that now the artist table is used to hold everything up to music industry
 * lawyers (see Miscellaneous Production Relationship Type). In general, however, artists are people who sing or play
 * instruments.
 */
public class Artist {
    
    private UUID mbId;
    private String name;
    private String alias;
    private String alternateName;
    private String comment;
    private ArtistType type;
    private Date beginDate;
    private Date endDate;

}

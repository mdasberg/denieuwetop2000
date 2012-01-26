package nl.jpoint.top2k.domain;

/**
 * An "artist" is usually either a person or a group of persons. While artists in MusicBrainz are (mainly) the people
 * responsible for creating music, they are also used in many different AdvancedRelationships, and in that context they
 * can fulfill all kinds of roles, from producers, publishers, lyricists, conductors and many more.
 *
 * To distinguish the different kinds of artists, there are different types available.
 */
public enum ArtistType {

    /** This indicates an individual person. */
    PERSON,
    /** This indicates a group of people that may or may not have a distinctive name. */
    GROUP,
    /** This type should be used if neither "person" nor "group" fits. */
    OTHER,
    /** This is to be used when the type of the artist is yet to be found. */
    UNKNOWN;
    
}

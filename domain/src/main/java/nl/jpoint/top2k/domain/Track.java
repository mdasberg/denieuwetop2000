package nl.jpoint.top2k.domain;

/**
 * In MusicBrainz, a track is the way a recording appears in a particular release (or, more exactly, in a particular
 * tracklist. Every track has a title (see the guidelines for titles) and is credited to one or more artists.
 */
public class Track {

    private double score;

    public void setScore(double score) {
        this.score = score;

    }

    public double getScore() {
        return score;
    }
}

package nl.jpoint.top2k.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Name of a track.
 */
@Entity
@Table(name = "TrackImport")
@NamedQueries({ @NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t ") })
@XmlRootElement
public class Track {

    @Id
    @Column(name = "id")
    @XmlElement(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement
    @Column(name = "name", nullable = false)
    @NotNull
    private String title;
    @XmlElement
    @Column(name = "album")
    private String album;
    @XmlElement
    @Column(name = "release")
    private Integer releaseYear;
    @XmlElement
    @ManyToOne
    @JoinColumn(name = "artist", updatable = false, nullable = false)
    @NotNull
    private Artist artist;
    @Column(name = "score", nullable = false)
    @NotNull
    @Min(value = 0)
    private Double score;
    @XmlElement
    @Column(name = "prevScore")
    @Min(value = 0)
    private Double prevScore;
    @XmlElement
    @Column(name = "youtubeid")
    private String youTubeId;

    protected Track() {

    }

    public Track(final String title, final Artist artist) {
        score = 1000d;
        prevScore = null;
        this.title = title;
        this.artist = artist;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        if (score == null) {
            score = 1000d;
        }
        return score;
    }

}

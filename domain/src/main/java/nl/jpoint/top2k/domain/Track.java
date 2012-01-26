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
@Table(name = "Track")
@NamedQueries({ @NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t ") })
@XmlRootElement
public class Track {

    @Id
    @Column(name = "id")
    @XmlElement(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement
    @Column(name = "title", nullable = false)
    @NotNull
    private String title;
    @XmlElement
    @Column(name = "album")
    private String album;
    @XmlElement
    @Column(name = "release")
    private int releaseYear;
    @XmlElement
    @ManyToOne
    @JoinColumn(name = "Artist", updatable = false, nullable = false)
    @NotNull
    private Artist artist;
    @XmlElement
    @Column(name = "score", nullable = false)
    @NotNull
    @Min(value = 0)
    private double score;
    @XmlElement
    @Column(name = "prevScore")
    @Min(value = 0)
    private double prevScore;
    @XmlElement
    @Column(name = "youtubeid")
    private String youTubeId;


    public Track(final String title, final Artist artist) {
        score = 1000d;
        this.title = title;
        this.artist = artist;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

}

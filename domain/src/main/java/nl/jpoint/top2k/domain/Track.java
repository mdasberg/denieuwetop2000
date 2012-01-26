package nl.jpoint.top2k.domain;

import javax.persistence.*;
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
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "Artist", insertable = false, updatable = false)
    private Artist artist;
    @Column(name = "score")
    private double score;

    public Track(final String name, final Artist artist) {
        this.name = name;
        this.artist = artist;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}

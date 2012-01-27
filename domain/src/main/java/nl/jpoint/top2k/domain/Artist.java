package nl.jpoint.top2k.domain;

import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An artist/band. Currently we only keep track of the name.
 */
@Entity
@Table(name = "Artists")
@NamedQueries({ @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a ") })
@XmlRootElement
public class Artist {

    @Id
    @Column(name = "id")
    @XmlElement(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;

    @XmlElement
    @Column(name = "name")
    private String     name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist")
    private Set<Track> tracks;

    protected Artist() {
    }

    public Artist(final String name) {
        this.name = name;
    }

}

package nl.jpoint.top2k.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** User domain class. */
@Entity(name = "User")
@NamedQueries({@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ")})
@XmlRootElement
public class User {
    @Id
    @Column(name = "id")
    @XmlElement(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "passwordHash")
    private String passwordHash;

    protected User() {
    }

    public User(final String username, final String email, final String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}

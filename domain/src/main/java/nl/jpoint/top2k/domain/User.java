package nl.jpoint.top2k.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** User domain class. */
@Entity(name = "Users")
@NamedQueries({@NamedQuery(name = "User.findAll", query = "SELECT u FROM Users u ")})
@XmlRootElement
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement
    @Column(name = "username")
    private String username;
    @XmlElement
    @Column(name = "email")
    private String email;
    @XmlElement
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

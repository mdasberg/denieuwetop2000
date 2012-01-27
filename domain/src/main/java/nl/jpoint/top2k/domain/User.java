package nl.jpoint.top2k.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** User domain class. */
@Entity(name = "Users")
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM Users u "),
		@NamedQuery(name = "User.byEmail", query = "SELECT u FROM Users u where u.email = :email ") })
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
	@XmlElement
	@Column(name = "isAdmin")
	private Boolean isAdmin = false;
	@XmlElement
	@Column(name = "finishedRegistration")
	private Boolean finishedRegistration = false;

	protected User() {
		isAdmin = false;
	}

	public User(final String username, final String email,
			final String passwordHash) {
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public boolean validatePassword(final String expectedPassword) {
		return passwordHash.equals(expectedPassword);
	}

	public String getPassword() {
		return passwordHash;
	}

	public void setFinishedRegistration(final boolean finishedRegistration) {
		this.finishedRegistration = finishedRegistration;
	}
}

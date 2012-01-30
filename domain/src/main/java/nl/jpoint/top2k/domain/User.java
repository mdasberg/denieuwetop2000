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

import nl.jpoint.top2k.util.SecurityUtil;

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

	private transient String password;

	protected User() {
		isAdmin = false;
	}

	/**
	 * Creates the user, this will hash the password using a sha hash. Get
	 * password will return the hashed password.
	 * 
	 * @param username
	 *            the username
	 * @param email
	 *            users email
	 * @param password
	 *            password that needs to be hashed
	 */
	public User(final String username, final String email, final String password) {
		this.username = username;
		this.email = email;
		this.passwordHash = SecurityUtil.createSHAHash(password);
	}

	public boolean validatePassword(final String expectedPassword) {
		if (expectedPassword != null
				&& passwordHash.equals(SecurityUtil
						.createSHAHash(expectedPassword))) {
			return true;
		} else {
			return false;
		}
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setFinishedRegistration(final boolean finishedRegistration) {
		this.finishedRegistration = finishedRegistration;
	}

	public String getEmail() {
		return email;
	}
}

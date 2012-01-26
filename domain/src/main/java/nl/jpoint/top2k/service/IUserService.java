package nl.jpoint.top2k.service;

import nl.jpoint.top2k.domain.User;

import java.util.List;

/** UserService interface.  */
public interface IUserService {

    /**
     * Creates the given user.
     * @param user The user.
     */
    void create(final User user);

    /**
     * Gets all the users.
     * @return users The users.
     */
    List<User> getAll();
}

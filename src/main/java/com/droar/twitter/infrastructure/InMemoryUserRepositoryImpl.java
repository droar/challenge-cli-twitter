package com.droar.twitter.infrastructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.model.User;
import com.droar.twitter.domain.port.UserRepository;
import lombok.NoArgsConstructor;

/**
 * 
 * @author droar
 *
 */
@NoArgsConstructor
public class InMemoryUserRepositoryImpl implements UserRepository {

  /** The in memory users. */
  private List<User> lstInMemoryUsers = new ArrayList<>();

  /** The in memory posts. */
  private List<Post> lstInMemoryUserPosts = new ArrayList<>();

  /**
   * Posts a new message, if no username matches it will be created.
   *
   * @param userName the user name
   * @param message the message
   * @return the string
   */
  @Override
  public void postByUsername(String userName, String message) {
    Optional<User> foundUser = this.findUserByUserName(userName);

    User userToPost = foundUser.isPresent() ? foundUser.get() : this.createUserByUserName(userName);
    lstInMemoryUserPosts.add(new Post(userToPost, message, new Date()));
  }

  /**
   * Shows posted messages with its timestamp.
   *
   * @param userName the user name
   * @return the string
   */
  @Override
  public Optional<List<Post>> getPostsByUsername(String userName) {
    return Optional.ofNullable(lstInMemoryUserPosts.stream()
        .filter(p -> p.getUser().getUserName().equalsIgnoreCase(userName)).collect(Collectors.toList()));
  }

  /**
   * Follows a given user.
   *
   * @param userName the user name
   * @param userToFollow the user to follow
   * @return the string
   */
  @Override
  public void followUser(String userName, String userToFollow) {
    Optional<User> foundUser = this.findUserByUserName(userName);

    if (foundUser.isPresent()) foundUser.get().getFollowedUsers().add(userToFollow);
  }

  /**
   * Shows the user wall.
   *
   * @param userName the user name
   * @return the string
   */
  @Override
  public Optional<List<Post>> getWallByUsername(String userName) {
    Optional<User> foundUser = this.findUserByUserName(userName);

    List<Post> lstWallPosts = lstInMemoryUserPosts.stream()
        .filter(p -> p.getUser().getUserName().equalsIgnoreCase(userName)).collect(Collectors.toList());

    if (foundUser.isPresent()) {
      // We add to the original posts list the followed users post (unordered)
      foundUser.get().getFollowedUsers().forEach(fu ->

      lstWallPosts.addAll(lstInMemoryUserPosts.stream().filter(p -> p.getUser().getUserName().equalsIgnoreCase(fu))
          .collect(Collectors.toList())));
    }

    return Optional.of(lstWallPosts);
  }

  /**
   * Finds user with userName provided
   *
   * @param userName the user name
   * @return the optional user
   */
  @Override
  public Optional<User> findUserByUserName(String userName) {
    return lstInMemoryUsers.stream().filter(s -> s.getUserName().equalsIgnoreCase(userName)).findAny();
  }

  /**
   * Find or create user with userName provided
   *
   * @param userName the user name
   * @return the created user
   */
  @Override
  public User createUserByUserName(String userName) {
    User newUser = new User(userName);
    lstInMemoryUsers.add(newUser);
    return newUser;
  }
}

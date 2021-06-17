package com.droar.twitter.domain.port;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.model.User;

/**
 * The User repository interface for domain
 * 
 * @author droar
 *
 */
public interface UserRepository {

  /**
   * Posts a new message.
   *
   * @param userName the user name
   * @param message the message
   * 
   */
  public void postByUsername(String userName, String message);

  /**
   * Shows posted messages with its timestamp.
   *
   * @param userName the user name
   * @return the string
   */
  public Optional<List<Post>> getPostsByUsername(String userName);

  /**
   * Follows a given user.
   *
   * @param userName the user name
   * @param userToFollow the user to follow
   * 
   */
  public void followUser(String userName, String userToFollow);

  /**
   * Shows the user wall posts.
   *
   * @param userName the user name
   * @return the wall posts
   */
  public Optional<List<Post>> getWallByUsername(String userName);

  /**
   * Finds an user, if not found it will return empty.
   *
   * @param userName the user name to find/create
   * @return the User
   */
  public Optional<User> findUserByUserName(String userName);

  /**
   * Creates a new user
   *
   * @param userName the user name to find/create
   * @return the User
   */
  public User createUserByUserName(String userName);
}

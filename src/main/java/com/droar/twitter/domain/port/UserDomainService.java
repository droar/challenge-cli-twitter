package com.droar.twitter.domain.port;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.domain.model.Post;

/**
 * The Class UserDomainService.
 * 
 * @author droar
 *
 */
public class UserDomainService {

  /** The user repository. */
  private UserRepository userRepository;

  /**
   * Constructor
   * 
   * @param userRepository
   */
  public UserDomainService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Posts a new message.
   *
   * @param userName the user name
   * @param message the message
   * 
   */
  public void postByUsername(String userName, String message) {
    this.userRepository.postByUsername(userName, message);
  }

  /**
   * Shows posted messages with its timestamp.
   *
   * @param userName the user name
   * @return the string
   */
  public Optional<List<Post>> getPostsByUsername(String userName) {
    return this.userRepository.getPostsByUsername(userName);
  }

  /**
   * Follows a given user.
   *
   * @param userName the user name
   * @param userToFollow the user to follow
   * 
   */
  public void followUser(String userName, String userToFollow) {
    this.userRepository.followUser(userName, userToFollow);
  }

  /**
   * Shows the user wall.
   *
   * @param userName the user name
   * @return the string
   */
  public Optional<List<Post>> getWallByUsername(String userName) {
    return this.userRepository.getWallByUsername(userName);
  }

}

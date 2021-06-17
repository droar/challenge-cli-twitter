package com.droar.twitter.domain.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * The Class User.
 * 
 * @author droar
 *
 */
@Getter
public class User {

  /** The name. */
  private String userName;

  /** The followed users. */
  private List<String> followedUsers = new ArrayList<>();

  /**
   * Users might have only userName when creating
   * 
   * @param userName
   */
  public User(String userName) {
    this.userName = userName;
  }
}

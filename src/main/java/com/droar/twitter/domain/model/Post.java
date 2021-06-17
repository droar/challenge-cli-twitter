package com.droar.twitter.domain.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Class Post.
 * 
 * @author droar
 *
 */
@Getter
@AllArgsConstructor
public class Post {

  /** The user. */
  private User user;

  /** The message. */
  private String message;

  /** The post date. */
  private Date postDate;
}

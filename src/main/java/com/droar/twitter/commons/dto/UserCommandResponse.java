package com.droar.twitter.commons.dto;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommandResponse {

  /** The detected user command. */
  private UserCommand detectedUserCommand;

  /** The lst user command posts. */
  private Optional<List<Post>> lstUserCommandPosts;
}

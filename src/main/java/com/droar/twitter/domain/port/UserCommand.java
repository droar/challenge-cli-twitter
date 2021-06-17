package com.droar.twitter.domain.port;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.domain.model.Post;

/**
 * The Interface UserCommand.
 */
public interface UserCommand {

  /**
   * Executes the command.
   *
   * @param command
   * @return
   */
  public Optional<List<Post>> submit(String command, UserRepository repository);
}

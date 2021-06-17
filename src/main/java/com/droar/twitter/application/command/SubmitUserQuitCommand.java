package com.droar.twitter.application.command;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.domain.port.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class QuitCommand.
 * 
 * @author droar
 *
 */
@Slf4j
public class SubmitUserQuitCommand implements UserCommand {

  @Override
  public Optional<List<Post>> submit(String message, UserRepository userRepository) {
    log.info("You're leaving cli twitter, 'till next time!!");
    return Optional.empty();
  }
}

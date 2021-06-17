package com.droar.twitter.application.command;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.domain.port.UserDomainService;
import com.droar.twitter.domain.port.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ReadCommand.
 * 
 * @author droar
 *
 */
@Slf4j
public class SubmitUserReadCommand implements UserCommand {

  @Override
  public Optional<List<Post>> submit(String userName, UserRepository userRepository) {
    log.info(" >.. Executing [reading] for username: " + userName);

    UserDomainService userService = new UserDomainService(userRepository);
    return userService.getPostsByUsername(userName);
  }
}

package com.droar.twitter.application.command;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.commons.Constants;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.domain.port.UserDomainService;
import com.droar.twitter.domain.port.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WallCommand.
 * 
 * @author droar
 *
 */
@Slf4j
public class SubmitUserWallCommand implements UserCommand {

  @Override
  public Optional<List<Post>> submit(String command, UserRepository userRepository) {
    log.info(" >.. Executing [wall] command : " + command);

    String userName = command.split(Constants.SPLIT_WALL_INDEX)[0];

    UserDomainService userService = new UserDomainService(userRepository);
    return userService.getWallByUsername(userName);
  }
}

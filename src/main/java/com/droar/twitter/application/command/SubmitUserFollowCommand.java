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
 * The Class FollowCommand.
 * 
 * @author droar
 */
@Slf4j
public class SubmitUserFollowCommand implements UserCommand {

  @Override
  public Optional<List<Post>> submit(String command, UserRepository userRepository) {
    log.info(" >.. Executing [follow] command : " + command);
    
    String[] commandSplitted = command.split(Constants.FOLLOW_SPLIT_INDEX);
    String follower = commandSplitted[0];
    String followed = commandSplitted[1];

    UserDomainService userService = new UserDomainService(userRepository);
    userService.followUser(follower, followed);
    
    return Optional.empty();
  }
}

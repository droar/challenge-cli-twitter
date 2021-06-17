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
 * The Class PostCommand.
 * 
 * @author droar
 */
@Slf4j
public class SubmitUserPostCommand implements UserCommand {

  @Override
  public Optional<List<Post>> submit(String command, UserRepository userRepository) {
    log.info(" >.. Executing [posting] command " + command);

    String[] commandSplitted = command.split(Constants.POST_SPLIT_INDEX);
    String userName = commandSplitted[0];
    String postMessage = commandSplitted[1];

    UserDomainService userService = new UserDomainService(userRepository);
    userService.postByUsername(userName, postMessage);
    
    return Optional.empty();
  }
}

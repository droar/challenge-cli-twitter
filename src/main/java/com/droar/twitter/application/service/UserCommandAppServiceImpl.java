package com.droar.twitter.application.service;

import java.util.List;
import java.util.Optional;
import com.droar.twitter.application.command.UserCommandFactory;
import com.droar.twitter.commons.PostUtils;
import com.droar.twitter.commons.dto.UserCommandResponse;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.domain.port.UserRepository;

/**
 * The Class UserCommandAppServiceImpl.
 */
public class UserCommandAppServiceImpl implements UserCommandAppService {

  /** The user repository. */
  private UserRepository userRepository;

  public UserCommandAppServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserCommandResponse detectAndSubmitCommand(String userInput) {
    UserCommandResponse userCommandResponse = new UserCommandResponse();

    UserCommandFactory commandFactory = new UserCommandFactory();

    UserCommand userCommand = commandFactory.detectUserCommand(userInput);
    Optional<List<Post>> lstCommandPosts = userCommand.submit(userInput, userRepository);

    PostUtils.orderPostsListByDate(lstCommandPosts);

    userCommandResponse.setDetectedUserCommand(userCommand);
    userCommandResponse.setLstUserCommandPosts(lstCommandPosts);

    return userCommandResponse;
  }

}

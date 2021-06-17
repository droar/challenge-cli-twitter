package com.droar.twitter.application.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.droar.twitter.application.command.SubmitUserFollowCommand;
import com.droar.twitter.application.command.SubmitUserPostCommand;
import com.droar.twitter.application.command.SubmitUserReadCommand;
import com.droar.twitter.application.command.SubmitUserWallCommand;
import com.droar.twitter.commons.dto.UserCommandResponse;
import com.droar.twitter.domain.port.UserRepository;
import com.droar.twitter.infrastructure.InMemoryUserRepositoryImpl;

@DisplayName("Test case for user command app service")
class UserCommandAppServiceTest {

  /** The in Memory User Database. */
  static UserRepository inMemoryUserDatabase;

  /** The user command app service. */
  static UserCommandAppService userCommandAppService;

  @BeforeEach
  void initRepository() {
    inMemoryUserDatabase = new InMemoryUserRepositoryImpl();
    userCommandAppService = new UserCommandAppServiceImpl(inMemoryUserDatabase);

    inMemoryUserDatabase.postByUsername("Andrea", "Hola Mundo!!!");
    inMemoryUserDatabase.postByUsername("Andrea", "¿Alguien café?");
  }

  @Test
  @DisplayName(value = "App Service Succeeds when submiting a valid post command")
  void givenPostMessageOnServiceWhenValidThenSucceed() {
    String messagePostOne = "Andrea -> Hola Mundo";
    
    UserCommandResponse userPostCommandResponse = userCommandAppService.detectAndSubmitCommand(messagePostOne);

    assertTrue(userPostCommandResponse.getDetectedUserCommand() instanceof SubmitUserPostCommand);
    assertEquals(Optional.empty(), userPostCommandResponse.getLstUserCommandPosts());
  }


  @Test
  @DisplayName(value = "App Service Succeeds when submiting a valid read command")
  void givenReadMessageOnServiceWhenValidThenSucceed() {
    String messageRead = "Andrea";

    UserCommandResponse userReadingCommandResponse = userCommandAppService.detectAndSubmitCommand(messageRead);
    
    assertTrue(userReadingCommandResponse.getDetectedUserCommand() instanceof SubmitUserReadCommand);
    assertTrue(userReadingCommandResponse.getLstUserCommandPosts().isPresent());
  }


  @Test
  @DisplayName(value = "App Service Succeeds when submiting a valid follow command")
  void givenFollowMessageOnServiceWhenValidThenSucceed() {
    String messageFollows = "Andrea follows Sergio";
    
    UserCommandResponse userFollowCommandResponse = userCommandAppService.detectAndSubmitCommand(messageFollows);
    assertTrue(userFollowCommandResponse.getDetectedUserCommand() instanceof SubmitUserFollowCommand);
    assertEquals(Optional.empty(), userFollowCommandResponse.getLstUserCommandPosts());
  }


  @Test
  @DisplayName(value = "App Service Succeeds when submiting a valid wall command")
  void givenWallMessageOnServiceWhenValidThenSucceed() {
    String messageWall = "Andrea wall";
    
    UserCommandResponse userWallCommandResponse = userCommandAppService.detectAndSubmitCommand(messageWall);
    assertTrue(userWallCommandResponse.getDetectedUserCommand() instanceof SubmitUserWallCommand);
    assertTrue(userWallCommandResponse.getLstUserCommandPosts().isPresent());
  }

}

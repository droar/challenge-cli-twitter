package com.droar.twitter.application.command;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.infrastructure.InMemoryUserRepositoryImpl;

/**
 * The Class SubmitUserFollowCommandTest.
 */
@DisplayName("Test case for user follows command")
class SubmitUserFollowCommandTest {
  
  /** The command factory. */
  static UserCommandFactory commandFactory;
  
  /** The in memory user database. */
  static InMemoryUserRepositoryImpl inMemoryUserDatabase;
  
  /**
   * Inits the repository and factory.
   */
  @BeforeAll
  static void initRepositoryAndFactory() {
    commandFactory = new UserCommandFactory();
    inMemoryUserDatabase = new InMemoryUserRepositoryImpl();
  }

  @BeforeEach
  void populateUserPosts() {
    inMemoryUserDatabase.postByUsername("Andrea", "Hola Mundo!!!");
    inMemoryUserDatabase.postByUsername("Andrea", "¿Alguien café?");
    inMemoryUserDatabase.postByUsername("Sergio", "Que soleado dia hace");

  }
  
  @Test
  @DisplayName(value = "Succeeds when posting a follow command in the correct format")
  void givenFollowedUserWhenUserHasMessagesThenSucceed() {
    String messageFollowUser = "Andrea follows Sergio";
    
    UserCommand userCommand = new SubmitUserFollowCommand();
    Assertions.assertEquals(Optional.empty(), userCommand.submit(messageFollowUser, inMemoryUserDatabase));
  }
}

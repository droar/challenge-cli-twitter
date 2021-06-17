package com.droar.twitter.application.command;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.infrastructure.InMemoryUserRepositoryImpl;

/**
 * The Class SubmitUserPostCommandTest.
 */
@DisplayName("Test case for posting command")
class SubmitUserPostCommandTest {
  
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

  @Test
  @DisplayName(value = "Succeeds when posting in the correct format")
  void givenPostMessageWhenValidPostThenSucceed() {
    String messagePostFirst = "Andrea -> Hola que tal? ";
    String messagePostSecond = "Juan -> Testing post method ";
    String messagePostThird = "Alejandro -> Buen lunes ";
    
    UserCommand userCommand = new SubmitUserPostCommand();
    
    Assertions.assertEquals(Optional.empty(), userCommand.submit(messagePostFirst, inMemoryUserDatabase));
    Assertions.assertEquals(Optional.empty(), userCommand.submit(messagePostSecond, inMemoryUserDatabase));
    Assertions.assertEquals(Optional.empty(), userCommand.submit(messagePostThird, inMemoryUserDatabase));
  }
}

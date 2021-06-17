package com.droar.twitter.application.command;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import com.droar.twitter.infrastructure.InMemoryUserRepositoryImpl;

/**
 * The Class SubmitUserWallCommandTest.
 */
@DisplayName("Test case for user wall command")
class SubmitUserWallCommandTest {
  
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
  }
  
  @Test
  @DisplayName(value = "Succeeds when posting a wall command in the correct format")
  void givenWallMessageWhenHavingWallThenSucceed() {
    String messageWallUser = "Andrea wall";
    
    UserCommand userCommand = new SubmitUserWallCommand();
    
    Optional<List<Post>> lstOptionalWallPosts = userCommand.submit(messageWallUser, inMemoryUserDatabase);
    Assertions.assertSame(Integer.valueOf(2), lstOptionalWallPosts.get().size());
  }
}

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
 * The Class SubmitUserReadCommandTest.
 */
@DisplayName("Test case for user read command")
class SubmitUserReadCommandTest {
  
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
    inMemoryUserDatabase.postByUsername("Juan", "¿Alguien café?");
  }
  
  @Test
  @DisplayName(value = "Succeeds when posting a read command in the correct format")
  void givenReadMessageWhenHavingPostsThenSucceed() {
    String messageReadFirst = "Andrea";
    String messageReadSecond = "Juan";
    
    UserCommand userCommand = new SubmitUserReadCommand();
    
    Optional<List<Post>> lstPostsAndrea = userCommand.submit(messageReadFirst, inMemoryUserDatabase);
    Optional<List<Post>> lstPostsJuan = userCommand.submit(messageReadSecond, inMemoryUserDatabase);
    
    Assertions.assertFalse(lstPostsAndrea.isEmpty());
    Assertions.assertFalse(lstPostsJuan.isEmpty());
  }
}

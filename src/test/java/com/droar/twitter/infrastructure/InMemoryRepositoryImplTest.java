package com.droar.twitter.infrastructure;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.model.User;
import com.droar.twitter.domain.port.UserRepository;

@DisplayName("Test case for in memory user repository")
class InMemoryRepositoryImplTest {

  /** The in Memory User Database. */
  static UserRepository inMemoryUserDatabase;

  @BeforeEach
  void initRepository() {
    inMemoryUserDatabase = new InMemoryUserRepositoryImpl();

    inMemoryUserDatabase.postByUsername("Andrea", "Hola Mundo!!!");
    inMemoryUserDatabase.postByUsername("Juan", "¿Alguien café?");
  }

  @Test
  @DisplayName(value = "Succeeds when creating an user by username")
  void givenUserNameWhenUserValidThenSucceed() {
    String userToBeCreated = "Andrea";
    User userCreated = inMemoryUserDatabase.createUserByUserName(userToBeCreated);

    Assertions.assertNotNull(userCreated);
  }

  @Test
  @DisplayName(value = "Succeeds when finds an existing username")
  void givenExistingUserNameWhenUserValidThenSucceed() {
    String toFindUser = "Andrea";
    Optional<User> userFound = inMemoryUserDatabase.findUserByUserName(toFindUser);

    Assertions.assertTrue(userFound.isPresent());
  }

  @Test
  @DisplayName(value = "Succeeds when user posts are found")
  void givenExistingUserNameWhenUserHasPostsValidThenSucceed() {
    String userToGetPosts = "Juan";
    Optional<List<Post>> lstUserFoundPosts = inMemoryUserDatabase.getPostsByUsername(userToGetPosts);

    Assertions.assertTrue(lstUserFoundPosts.isPresent());
  }

  @Test
  @DisplayName(value = "Succeeds when main user follows an user")
  void givenExistingUserNameWhenFollowingOtherUserThenSucceed() {
    String mainUser = "Andrea";
    String userToFollow = "Juan";
    inMemoryUserDatabase.followUser(mainUser, userToFollow);

    List<String> lstMainUserFollowedUsers = inMemoryUserDatabase.findUserByUserName(mainUser).get().getFollowedUsers();
    Assertions.assertTrue(lstMainUserFollowedUsers.size() > 0);
  }

  @Test
  @DisplayName(value = "Succeeds when showing wall posts for given username")
  void givenExistingUserNameWhenShowingWallPostsThenSucceed() {
    String mainUser = "Andrea";
    String userToFollow = "Juan";
    inMemoryUserDatabase.followUser(mainUser, userToFollow);

    Optional<List<Post>> lstUserFoundPosts = inMemoryUserDatabase.getWallByUsername(mainUser);
    Assertions.assertTrue(lstUserFoundPosts.isPresent());
  }

}

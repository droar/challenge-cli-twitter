package com.droar.twitter.commons;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import com.droar.twitter.application.command.SubmitUserWallCommand;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;

public class PostUtils {
  
  /**
   * Private constructor
   */
  private PostUtils() {}
  
  /**
   * Order posts list by its posted date (minor to major)
   *
   * @param lstUnorderedPostList the lst unordered post list
   * @return the optional
   */
  public static Optional<List<Post>> orderPostsListByDate(Optional<List<Post>> lstUnorderedPostList) {
    lstUnorderedPostList.ifPresent(lst -> lst.sort((p1, p2) -> p2.getPostDate().compareTo(p1.getPostDate())));
    return lstUnorderedPostList;
  }

  /**
   * Gets the post time in past minutes from the post itself
   *
   * @param originalPost the original post
   * @return the post time in past minutes
   */
  public static long getPostTimeInPastMinutes(Post originalPost) {
    long minutesFromPost = 0;

    Date postDate = originalPost.getPostDate();
    Date actualTime = new Date();
    minutesFromPost = (TimeUnit.MILLISECONDS.toMinutes(actualTime.getTime() - postDate.getTime()) % 60);

    return minutesFromPost;
  }

  /**
   * Format post message, differencing the ones from the wall of the user with the others
   *
   * @param userCommand the user command
   * @param post the post
   * @return the string
   */
  public static String formatPostMessage(UserCommand userCommand, Post post) {
    StringBuilder formattedMessage = new StringBuilder("");

    if (userCommand instanceof SubmitUserWallCommand) {
      formattedMessage.append(post.getUser().getUserName().concat(" - ").concat(post.getMessage()).concat(" (")
          .concat(PostUtils.getPostTimeInPastMinutes(post) + " minutes ago)"));
    } else {
      formattedMessage.append(post.getMessage().concat(" (").concat(PostUtils.getPostTimeInPastMinutes(post) + " minutes ago)"));
    }
    return formattedMessage.toString();
  }
}

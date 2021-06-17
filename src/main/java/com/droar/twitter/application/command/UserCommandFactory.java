package com.droar.twitter.application.command;

import java.util.regex.Pattern;
import com.droar.twitter.commons.Constants;
import com.droar.twitter.domain.port.UserCommand;

/**
 * A factory for creating UserCommand objects.
 */
public class UserCommandFactory {

  /**
   * Detects the command issued from the command itself
   *
   * @param command the command
   * @return the command
   */
  public UserCommand detectUserCommand(String command) {
    UserCommand issuedCommand = null;

    if (Pattern.matches(Constants.PATTERN_QUIT_COMMAND, command)) {
      issuedCommand = new SubmitUserQuitCommand();
    } else if (Pattern.matches(Constants.PATTERN_POST_COMMAND, command)) {
      issuedCommand = new SubmitUserPostCommand();
    } else if (Pattern.matches(Constants.PATTERN_FOLLOW_COMMAND, command)) {
      issuedCommand = new SubmitUserFollowCommand();
    } else if (Pattern.matches(Constants.PATTERN_WALL_COMMAND, command)) {
      issuedCommand = new SubmitUserWallCommand();
    } else if (Pattern.matches(Constants.PATTERN_READ_COMMAND, command)) {
      issuedCommand = new SubmitUserReadCommand();
    }

    return issuedCommand;
  }
}

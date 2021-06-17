package com.droar.twitter.commons;

public class Constants {
  /**
   * Instantiates new constants.
   */
  private Constants() {}

  /** The Constant QUIT_COMMAND_PATTERN. */
  public static final String PATTERN_QUIT_COMMAND = "(quit|exit)";

  /** The Constant POST_COMMAND_PATTERN. */
  public static final String PATTERN_POST_COMMAND = ".*\\s->\\s.*";

  /** The Constant FOLLOW_COMMAND_PATTERN. */
  public static final String PATTERN_FOLLOW_COMMAND = ".*\\sfollows\\s.*";

  /** The Constant WALL_COMMAND_PATTERN. */
  public static final String PATTERN_WALL_COMMAND = ".*\\swall";

  /** The Constant READ_COMMAND_PATTERN. */
  public static final String PATTERN_READ_COMMAND = ".*";
  
  /** The Constant FOLLOW_SPLIT_INDEX. */
  public static final String FOLLOW_SPLIT_INDEX = "\\s+follows\\s+";
  
  /** The Constant POST_SPLIT_INDEX. */
  public static final String POST_SPLIT_INDEX = "\\s+->\\s+";
  
  /** The Constant SPLIT_WALL_INDEX. */
  public static final String SPLIT_WALL_INDEX = "\\s+";
}

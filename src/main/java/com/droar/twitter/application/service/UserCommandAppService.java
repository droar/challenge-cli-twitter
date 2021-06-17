package com.droar.twitter.application.service;

import com.droar.twitter.commons.dto.UserCommandResponse;

public interface UserCommandAppService {

  /**
   * Detects and submits an user command.
   *
   * @param userInput the userInput
   * @return the userCommandResponse
   */
  public UserCommandResponse detectAndSubmitCommand(String userInput);
}

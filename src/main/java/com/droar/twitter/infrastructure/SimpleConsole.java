package com.droar.twitter.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.droar.twitter.application.command.SubmitUserQuitCommand;
import com.droar.twitter.application.service.UserCommandAppService;
import com.droar.twitter.application.service.UserCommandAppServiceImpl;
import com.droar.twitter.commons.PostUtils;
import com.droar.twitter.commons.dto.UserCommandResponse;
import com.droar.twitter.domain.model.Post;
import com.droar.twitter.domain.port.UserCommand;
import lombok.NoArgsConstructor;

/**
 * Custom console (app)
 * 
 * @author droar
 *
 */
@NoArgsConstructor
public class SimpleConsole {

  /** The user command prompt. */
  private String userCommandPrompt = "> ";

  /** The in memory user database. */
  private InMemoryUserRepositoryImpl inMemoryUserDatabase = new InMemoryUserRepositoryImpl();

  /** The user command app service. */
  private UserCommandAppService userCommandAppService = new UserCommandAppServiceImpl(inMemoryUserDatabase);

  /**
   * Run.
   */
  public void run() {
    this.printBannerScreen();
    try (Scanner in = new Scanner(System.in)) {

      while (true) {
        System.out.print(this.userCommandPrompt);
        String userInput = this.readline(in);

        UserCommandResponse userCommandResponse = this.userCommandAppService.detectAndSubmitCommand(userInput);
        Optional<List<Post>> lstResponsePosts = userCommandResponse.getLstUserCommandPosts();
        UserCommand detectedCommand = userCommandResponse.getDetectedUserCommand();

        if (detectedCommand != null) {
          lstResponsePosts.ifPresent(lst -> lst.stream().forEach(p -> System.out.println(PostUtils.formatPostMessage(detectedCommand, p))));

          this.printMessageAndFlush("Command entered " + System.lineSeparator());

          // Just to add a break condition to the while cicle
          if (detectedCommand instanceof SubmitUserQuitCommand) {
            this.printMessageAndFlush("Leaving" + System.lineSeparator());
            break;
          }
        }
      }
    }
  }

  /**
   * Readline.
   *
   * @return the string
   */
  public String readline(Scanner in) {
    return in.nextLine();
  }

  /**
   * Prints the message provided
   *
   * @param message the message
   */
  public void printMessageAndFlush(String message) {
    System.out.println(message);
    System.out.flush();
  }

  /**
   * Prints the login screen.
   */
  public void printBannerScreen() {
    System.out.println("##################################################################");
    System.out.println("##                                                              ##");
    System.out.println("##            CONSOLE OFFLINE TWITTER                  ##");
    System.out.println("##                                               Damian Roa     ##");
    System.out.println("## --------------------------------------------------------     ##");

    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("## Avalaible operations                    (mind the whitespaces) ##");
    System.out.println("## Posting : [userName] -> [message],       Reading: [userName]   ##");
    System.out.println("## Follow  : [userName] follows [userName], Wall: [userName] wall ##");
    System.out.println("## Quit    : 'quit' or 'exit' for exiting the application         ##");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println();
  }
}

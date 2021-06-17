package com.droar.twitter.infrastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Custom console simple test for multi input
 *
 * @author droar
 */
@DisplayName("Test case for console user interface")
class SimpleConsoleTest {

  /** The console Input. */
  static private ByteArrayInputStream consoleInput;

  /** The console Output. */
  static private ByteArrayOutputStream consoleOutput;

  /** The simple console. */
  static SimpleConsole simpleConsole;

  @BeforeAll
  static void setUpConsoleOutput() {
    simpleConsole = new SimpleConsole();
    consoleOutput = new ByteArrayOutputStream();
    System.setOut(new PrintStream(consoleOutput));
  }

  @Test
  @DisplayName(value = "Succeeds when process some commands")
  void givenConsoleInputWhenProcessingUserCommandsThenSucceed() {

    String consolePostInputOne = "Sergio -> Hola Mundo!";
    String consolePostInputTwo = "Juan -> Quiero un café";
    String consoleFollowInput = "Sergio follows Juan";
    String consoleReadInput = "Sergio";
    String consoleWallInput = "Sergio wall";
    String consoleQuitInput = "quit";

    String userInput = 
        consolePostInputOne + System.lineSeparator() +
        consolePostInputTwo + System.lineSeparator() +
        consoleFollowInput + System.lineSeparator() +
        consoleReadInput + System.lineSeparator() + 
        consoleWallInput + System.lineSeparator() + 
        consoleQuitInput;

    sendInputToConsole(userInput);

    SimpleConsole simpleConsole = new SimpleConsole();
    simpleConsole.run();

    String leavingAfterCommands = "Leaving";
    assertTrue(consoleOutput.toString().contains(leavingAfterCommands));
  }

  
  /**
   * Send input to console.
   *
   * @param userInputStream the data
   */
  static void sendInputToConsole(String userInputStream) {
    consoleInput = new ByteArrayInputStream(userInputStream.getBytes());
    System.setIn(consoleInput);
  }
}

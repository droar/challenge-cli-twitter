package com.droar.twitter;

import com.droar.twitter.infrastructure.SimpleConsole;

/**
 * @author droar
 *
 */
public class TwitterCliApp {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    SimpleConsole consoleApp = new SimpleConsole();
    consoleApp.run();
  }
}

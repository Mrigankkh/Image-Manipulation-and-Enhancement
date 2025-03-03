package controller;

/**
 * The Controller class in the MVC design pattern is responsible for handling user input, invoking
 * corresponding methods in the Model, and updating the View accordingly with an appropriate message
 * on successful completion and in exception cases.
 */

public interface IController {

  /**
   * The run method accepts inputs from user either through the keyboard or in the form of a script
   * , parses the input and call the respective method in the model to perform an operation if the
   * command is valid else it displays an invalid command message to use or throws an error.
   */
  void run();
}

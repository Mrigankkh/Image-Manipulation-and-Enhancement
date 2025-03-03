package controller.commands;

import model.ImageModel;

/**
 * Representation of commands that the Image Manipulation tool supports.
 */
public interface Command {

  /**
   * Execute the command using an ImageModel.
   * @param model is the imageModel on which the commands are executed.
   */
  public void execute(ImageModel model) throws IllegalArgumentException;

  /**
   * Check if arguments provided to the command are valid.
   * @param arguments string array of arguments.
   */
  public String argumentError(String[] arguments);
}

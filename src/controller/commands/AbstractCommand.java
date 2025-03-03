package controller.commands;

import java.util.Arrays;
import model.ImageModel;

/**
 * An abstraction for the Command Interface representing different commands that the tool accepts.
 */
public abstract class AbstractCommand implements Command {

  protected String[] operationParameters;
  protected int[] numParameters;
  protected String commandName;

  protected AbstractCommand() {
    this.commandName = "command";
  }

  /**
   * Execute the command using an ImageModel.
   *
   * @param model is the imageModel on which the commands are executed.
   */
  @Override
  public abstract void execute(ImageModel model) throws IllegalArgumentException;

  /**
   * Check if arguments provided to the command are valid.
   *
   * @param arguments string array of arguments.
   */
  @Override
  public String argumentError(String[] arguments) {
    StringBuilder error = new StringBuilder();
    if (Arrays.stream(numParameters).noneMatch(i -> i == arguments.length)) {
      error.append(
              "Error in command " + commandName + " Expected " + Arrays.toString(numParameters)
                  + " but found ")
          .append(arguments.length).append("\n");
    }

    return error.toString();
  }




}

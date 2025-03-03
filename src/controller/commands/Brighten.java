package controller.commands;

import java.util.Arrays;
import model.ImageModel;

/**
 * Command to perform bright on image.
 */
public class Brighten extends AbstractCommand {

  /**
   * Initialize a bright command.
   *
   * @param operationParameters here are old image name and new image name and the bright by value.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public Brighten(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{3};

  }

  /**
   * Execute the command using an ImageModel.
   *
   * @param model is the imageModel on which the commands are executed.
   */
  @Override
  public void execute(ImageModel model) throws IllegalArgumentException {

    if (!argumentError(operationParameters).isEmpty()) {
      throw new IllegalArgumentException(argumentError(operationParameters));
    }
    try {
      model.brighten(operationParameters[0], operationParameters[1],
          Integer.parseInt(operationParameters[2]));
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Check if arguments provided to the command are valid.
   *
   * @param arguments string array of arguments.
   */
  @Override
  public String argumentError(String[] arguments) {
    StringBuilder error = new StringBuilder();
    if (!Arrays.stream(numParameters).anyMatch(i -> i == arguments.length)) {
      error.append(
              "Error in command " + commandName + " Expected " + Arrays.toString(numParameters)
                  + " but found ")
          .append(arguments.length).append("\n");
    } else if (!arguments[2].matches("^-?\\d+$")) {
      error.append("The brighten-by value should be an integer.");
    }
    return error.toString();
  }
}

package controller.commands;

import java.util.Arrays;
import model.ImageModel;

/**
 * Command to perform bright on image.
 */
public class LevelAdjust extends AbstractCommand {

  /**
   * Initialize a bright command.
   *
   * @param operationParameters here are old image name and new image name and the bright by value.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public LevelAdjust(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{5, 7};

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
      double splitPercent = 100.0;
      if (operationParameters.length == 7) {
        if (operationParameters[5].equals("split")) {
          splitPercent = Double.parseDouble(operationParameters[6]);
        }
      }
      model.levelAdjust(Integer.parseInt(operationParameters[0]),
          Integer.parseInt(operationParameters[1]), Integer.parseInt(operationParameters[2]),
          operationParameters[3], operationParameters[4], splitPercent);
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
    if (Arrays.stream(numParameters).noneMatch(i -> i == arguments.length)) {
      error.append("Error in command ").append(commandName).append(" Expected ")
          .append(Arrays.toString(numParameters)).append(" but found ")
          .append(arguments.length).append("\n");
    } else if (!arguments[0].matches("^-?\\d+$")) {
      error.append("The black value should be an integer.");
    } else if (!arguments[1].matches("^-?\\d+$")) {
      error.append("The mid value should be an integer.");
    } else if (!arguments[2].matches("^-?\\d+$")) {
      error.append("The white value should be an integer.");
    } else if (!(-1 < Integer.parseInt(arguments[0])
        && Integer.parseInt(arguments[0]) < Integer.parseInt(arguments[1])
        && Integer.parseInt(arguments[1]) < Integer.parseInt(arguments[2])
        && Integer.parseInt(arguments[2]) < 256)) {
      throw new IllegalArgumentException(
          "PLease maintain the relation: 0 < black < mid < white < 255.");
    }
    if (!(Integer.parseInt(arguments[0]) < Integer.parseInt(arguments[1]) && (
        Integer.parseInt(arguments[1]) < Integer.parseInt(arguments[2])
            && Integer.parseInt(arguments[2]) <= 255 && Integer.parseInt(arguments[0]) >= 0))) {
      error.append(
          "The black, mid and white values must be in ascending order and between 0 and 255");
    }
    return error.toString();
  }
}

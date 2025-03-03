package controller.commands;

import model.ImageModel;

/**
 * A command to perform value operation.
 */
public class ValueComponent extends AbstractCommand {

  /**
   * Initialize a value component command.
   *
   * @param operationParameters here are old image name and new image name.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public ValueComponent(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{2, 4};
    this.commandName = "value component";

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
      double splitPercent = 100;
      if (operationParameters.length == 4) {
        if (operationParameters[2].equals("split")) {
          splitPercent = Double.parseDouble(operationParameters[3]);
        }
      }
      model.valueComponent(operationParameters[0], operationParameters[1], splitPercent);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


}

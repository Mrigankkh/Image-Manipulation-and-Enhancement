package controller.commands;

import model.ImageModel;

/**
 * Command to perform vertical flip on image.
 */
public class HorizontalFlip extends AbstractCommand {

  /**
   * Initialize a horizontal flip command.
   *
   * @param operationParameters here are old image name and new image name.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public HorizontalFlip(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{2};
    this.commandName = "horizontal flip";
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
      model.horizontalFlip(operationParameters[0], operationParameters[1]);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

}

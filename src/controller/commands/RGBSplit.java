package controller.commands;

import model.ImageModel;

/**
 * A command to split an image into RGB components.
 */
public class RGBSplit extends AbstractCommand {

  /**
   * Initialize a RGBSplit command.
   *
   * @param operationParameters here are old image name three new image names for three components.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public RGBSplit(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{4};
    this.commandName = "rgb split";

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
      model.rgbSplit(operationParameters[0], operationParameters[1], operationParameters[2],
          operationParameters[3]);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


}

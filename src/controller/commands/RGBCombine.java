package controller.commands;

import model.ImageModel;

/**
 * A command to combine an RGB components into an image.
 */
public class RGBCombine extends AbstractCommand {


  /**
   * Initialize a RGBSCombine command.
   *
   * @param operationParameters here are new image name three new image names for three components.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public RGBCombine(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{4};
    this.commandName = "rgb combine";
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
      model.rgbCombine(operationParameters[0], operationParameters[1], operationParameters[2],
          operationParameters[3]);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

}

package controller.commands;

import model.ImageModel;

/**
 * Command to get color components of image.
 */
public class ColorComponent extends AbstractCommand {



  private int channel;

  /**
   * Initialize a color component component command.
   * @param operationParameters here are old image name and new image name.
   * @param channel is 0 for red 1 for green 2 for blue.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public ColorComponent(String[] operationParameters, int channel) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{2};
    this.commandName = "color component";
    this.channel = channel;

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
      model.colorComponent(operationParameters[0], operationParameters[1],
          channel);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

}

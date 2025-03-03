package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import model.ImageModel;

/**
 * A command to save an image.
 */
public class Save extends AbstractCommand {

  /**
   * Initialize a save command.
   *
   * @param operationParameters here are path name and new image name.
   * @throws IllegalArgumentException if image with imageName doesn't exist.
   */
  public Save(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{2};
    this.commandName = "save";

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
    BufferedImage bufferedImage;
    String imagePath = operationParameters[0];
    String imageName = getImageName(operationParameters[1]);
    int[][][] imageArray = model.get(imageName);
    String imageFormat = getImageFormat(operationParameters[1]);
    if (imageFormat.equals("ppm")) {
      savePPM(imageArray, imagePath, imageName);
    } else {
      try {
        bufferedImage = getBufferedImage(imageArray, imageName);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e);
      }

      try {
        ImageIO.write(bufferedImage, imageFormat, new File(imagePath + "/"
            + imageName + "."+imageFormat));
      } catch (IOException e) {
        throw new IllegalArgumentException("Unable to save file at the provided location.");
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  private BufferedImage getBufferedImage(int[][][] imageArray, String imageName)
      throws IllegalArgumentException {
    if (imageArray == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    int numChannels = imageArray[0][0].length;
    BufferedImage bufferedImage = new BufferedImage(imageArray[0].length, imageArray.length,
        BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < imageArray.length - 1; x++) {
      for (int y = 0; y < imageArray[0].length - 1; y++) {
        int rgb = 0;

        // Build the RGB value from the array
        for (int channel = 0; channel < numChannels; channel++) {
          int channelValue = imageArray[x][y][channel];
          rgb |= (channelValue & 0xFF) << (8 * (numChannels - 1 - channel));
        }

        bufferedImage.setRGB(y, x, rgb);
      }
    }
    return bufferedImage;
  }


  private String getImageName(String imageName) {
    if (!imageName.contains(".")) {
      return imageName;
    }
    return imageName.substring(0, imageName.indexOf("."));
  }

  private String getImageFormat(String imageName) {
    String imageFormat = imageName.substring(new File(imageName).getName()
        .lastIndexOf('.') + 1);
    return imageFormat.isEmpty() ? "png" : imageFormat;
  }

  private boolean validImageFormat(String imageFormat) {

    String[] supportedFormats = {"png", "ppm", "jpg", "jpeg"};
    for (String supFormat : supportedFormats) {
      if (imageFormat.equals(supFormat)) {
        return true;
      }
    }
    return false;
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
    } else if (arguments[1].matches("!@#\\$%\\^&*\\)\\(,/?<>")) {
      error.append("The file name you provided had illegal characters.");
    } else if (!validImageFormat(getImageFormat(arguments[1]))) {
      error.append(
          "Incorrect image format provided. Program only supports image formats of type png,"
              + " jpg, ppm and jpeg. \n");
    }
    {
      return error.toString();
    }
  }


  private void savePPM(int[][][] imageArray, String savePath, String imageName) {
//    try {
//      int width = imageArray[0].length;
//      int height = imageArray.length;
//      FileOutputStream fos = new FileOutputStream(savePath + imageName + ".ppm");
//      fos.write(("P3\n" + width + " " + height + "\n255\n").getBytes());
//      for (int i = 0; i < height; i++) {
//        for (int j = 0; j < width; j++) {
//          fos.write((imageArray[i][j][0] + " ").getBytes());
//          fos.write((imageArray[i][j][1] + " ").getBytes());
//          fos.write((imageArray[i][j][2] + " ").getBytes());
//        }
//        fos.write("\n".getBytes());
//      }
//      fos.close();
//    } catch (FileNotFoundException f) {
//      System.out.print("");
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
    try {
      System.out.println(imageArray[0][0].length);
      int width = imageArray[0].length;
      int height = imageArray.length;
      FileOutputStream fos = new FileOutputStream(savePath + imageName + ".ppm" );
      fos.write(("P3\n" + width + " " + height + "\n255\n").getBytes());
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          fos.write((imageArray[i][j][0] + " ").getBytes());
          fos.write((imageArray[i][j][1] + " ").getBytes());
          fos.write((imageArray[i][j][2] + " ").getBytes());


        }
        fos.write("\n".getBytes());
      }
      fos.close();
    } catch (FileNotFoundException f) {
      System.out.print("");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
}
}

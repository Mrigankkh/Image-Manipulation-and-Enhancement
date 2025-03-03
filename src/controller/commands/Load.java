package controller.commands;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;
import model.ImageModel;

/**
 * A command to perform the load operation.
 */
public class Load extends AbstractCommand {

  /**
   * Initialize a load command.
   *
   * @param operationParameters here is the path to load from and imageName
   * @throws IllegalArgumentException if path or image name is invalid.
   */
  public Load(String[] operationParameters) throws IllegalArgumentException {

    this.operationParameters = operationParameters;
    this.numParameters = new int[]{2};
    this.commandName = "load";

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
      BufferedImage image = getImageFromPath(operationParameters[0]);
      model.add(operationParameters[1], (bufferedImageTo3DArray(threeDimension(image))));
    } catch (FileNotFoundException fnf) {
      throw new IllegalArgumentException("File " + operationParameters[0] + " not found.");
    } catch (IOException e) {
      throw new IllegalArgumentException(e);

    }
  }


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  private BufferedImage ppmToBufferImage(String filename) throws FileNotFoundException {
    BufferedImage image;
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(e.getMessage());
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.length() != 0) {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }
    }
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][][] imageArray = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imageArray[i][j][0] = sc.nextInt();
        imageArray[i][j][1] = sc.nextInt();
        imageArray[i][j][2] = sc.nextInt();
      }
    }
    return getBufferedImage(imageArray, "ppm_image");

  }


  private BufferedImage getImageFromPath(String path) throws FileNotFoundException {
    BufferedImage image;
    try {
      if (getImageFormat(Paths.get(path).getFileName().toString()).equals("ppm")) {
        image = ppmToBufferImage(path);
      } else {
        image = ImageIO.read(new File(path));
      }
    } catch (IOException e) {
      throw new FileNotFoundException("Cannot load the file at path " + path
          + " since the file doesnt exist.");
    }
    if (image == null) {
      throw new FileNotFoundException("Cannot load the file at path " + path
          + " since the file is not of an image readable format.");
    }

    return image;
  }


  private String getImageFormat(String imageName) {
    String imageFormat = imageName.substring(new File(imageName).getName().
        lastIndexOf('.') + 1);
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

  private int[][][] bufferedImageTo3DArray(BufferedImage image) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    int channels = image.getColorModel().getNumComponents();
    int[][][] imageArray = new int[channels][height][width];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int pixelValue = image.getRGB(x, y);
        for (int channel = 0; channel < channels; channel++) {
          imageArray[channel][y][x] = 0xFF & (pixelValue >> (8 * (channels - channel - 1)));
        }
      }
    }
    return imageArray;
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
    } else if (!validImageFormat(
        getImageFormat(Paths.get(arguments[0]).getFileName().toString()))) {
      error.append(
          "Incorrect image format provided. Program only supports image formats of type png, jpg, "
              + "ppm and jpeg. \n");
    }
    {
      return error.toString();
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

  BufferedImage threeDimension(BufferedImage fourDBufferedImage) {
    BufferedImage threeDImage = new BufferedImage(fourDBufferedImage.getWidth(),
        fourDBufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = threeDImage.createGraphics();
    graphics2D.setColor(Color.WHITE);
    graphics2D.fillRect(0, 0, threeDImage.getWidth(), threeDImage.getHeight());
    graphics2D.drawImage(fourDBufferedImage, 0, 0, null);
    graphics2D.dispose();
    return threeDImage;
  }
}

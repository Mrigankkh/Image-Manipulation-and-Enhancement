package controller;

import java.awt.image.BufferedImage;

/**
 * This class has one method which converts an image array to a buffered image.
 */
public abstract class AbstractImageController implements IController {

  BufferedImage arrayToBufferedImage(int[][][] imageArray)
      throws IllegalArgumentException {
    if (imageArray == null) {
      throw new IllegalArgumentException("Invalid image array error ");
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
}

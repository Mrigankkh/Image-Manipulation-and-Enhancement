package controller.menuitemclickhandlers;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class handles a click in the GUI's menu item and converts an image to an array and vice
 * versa.
 */
public abstract class AbstractMenuItemClickHandler implements MenuItemClickHandler {

  // protected because they will be accessed by subclasses
  protected String imageName;
  protected String operationName;


  protected boolean imageIsSaved;

  /**
   * Returns if the image is saved.
   *
   * @return if image is saved
   */
  public boolean isImageIsSaved() {
    return imageIsSaved;
  }

  /**
   * Sets the boolean imageIsSaved.
   *
   * @param imageIsSaved if the image is saved
   */
  public void setImageIsSaved(boolean imageIsSaved) {
    this.imageIsSaved = imageIsSaved;
  }

  /**
   * Sets image name to the image name sent to by the model.
   */
  AbstractMenuItemClickHandler() {
    this.imageName = getImageName();
    this.imageIsSaved = false;
  }

  /**
   * Converts a buffered image to a 3D integer array and returns it.
   *
   * @param image the BufferedImage image
   * @return the image array
   * @throws IOException if some sort of I/O exception has occurred.
   */
  int[][][] bufferedImageTo3DArray(BufferedImage image) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    int channels = image.getColorModel().getNumComponents();
    int[][][] imageArray = new int[3][height][width];
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
   * Converts a 3D integer array to a buffered image and returns it.
   *
   * @param imageArray the 3D integer array
   * @return a buffered image
   * @throws IllegalArgumentException if imageArray is null
   */
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

  /**
   * Get the image name sent to the model.
   *
   * @return image name
   */
  @Override
  public String getImageName() {
    return imageName;
  }

  /**
   * Set the image name sent to the model.
   */
  @Override
  public void setImageName(String newImageName) {
    this.imageName = newImageName;
  }

  /**
   * Returns true if the image is not loaded and false if the image is loaded.
   *
   * @return true if the length of the image name is 0
   */
  boolean imageNotLoaded() {
    return this.getImageName().isEmpty();
  }
}

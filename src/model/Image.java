package model;

import java.util.ArrayList;

/**
 * A representation of an image and its operations.
 */
public interface Image {

  /**
   * Level adjust an image.
   *
   * @param black        is the black value for level adjustment
   * @param mid          is the mid-value for level adjustment
   * @param white        is the white value for level adjustment
   * @param splitPercent is the split screen percent
   * @throws IllegalArgumentException if condition 0< black < mid < white < 255 breaks
   */
  Matrix[] levelAdjust(int black, int mid, int white, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Generate a histogram of the image.
   *
   * @return an image of the histogram
   */
  Image histogram();

  /**
   * Color correct an image.
   */
  Matrix[] colorCorrection(double splitPercent);

  /**
   * Flips an image horizontally to create a new image.
   */
  void horizontalFlip();

  /**
   * Flips an image vertically to create a new image.
   */
  void verticalFlip();

  /**
   * Visualize the R,B or the B component of an image y setting picel values of the other components
   * as 0.
   *
   * @param channelToVisualize is the component to visualize. 0 is red. 1 is green. 2 is blue.
   */
  void visualizergb(int channelToVisualize);

  /**
   * Visualize the Value of the image. Value is defined as the maximum value of the three components
   * for each pixel.
   *
   * @return a greyscale image representing the value of the image.
   */
  Image visualizeValue(double splitPercent);

  /**
   * Visualize the intensity of the image. Intensity is defined as the average of the three
   * components for each pixel.
   *
   * @return a greyscale image representing the intensity of the image.
   */
  Image visualizeIntensity(double splitPercent);

  /**
   * Visualize the luma of the image. Luma is defined as the weighted sum  0.2126*r + 0.7152*g +
   * 0.0722*b.
   *
   * @return a greyscale image representing the luma of the image.
   */
  Image visualizeLuma(double splitPercent);

  /**
   * Brighten an image by a constant factor.
   *
   * @param brightenBy the brifgtening factor.
   */
  void brighten(int brightenBy);

  /**
   * Blur the image using a predetermined filter.
   */
  Matrix[] blur(double splitPercent);

  /**
   * Sharpen the image using a predefined filter.
   */
  Matrix[] sharpen(double splitPercent);

  /**
   * Converts a greyscale or rgb image to a sepia tone. This is done by performing a color
   * transformation.
   */

  Matrix[] toSepia(double splitPercent);

  /**
   * Split an image into its R,G and B components.
   *
   * @param redImageName   name of the new R component image.
   * @param greenImageName name of the new G component image.
   * @param blueImageName  name of the new B component image.
   * @return list of component images.
   */
  ArrayList<Image> split(String redImageName, String greenImageName,
      String blueImageName);

  /**
   * Get the number of channels in the image.
   *
   * @return number of channels in the image.
   */
  int getNumChannels();

  /**
   * Get the pixel height of the image.
   *
   * @return the pixel height of the image.
   */
  int getHeight();

  /**
   * Get the pixel width of the image.
   *
   * @return the pixel width of the image.
   */
  int getWidth();

  /**
   * Change the image name.
   *
   * @param imageName is the new image name.
   */
  void setImageName(String imageName);

  /**
   * Get the name of the image.
   *
   * @return the name of the image.
   */
  String getImageName();

  /**
   * Convert an array of matrices to an array of integers.
   *
   * @return a 3d array of integers of the format channels, height, width.
   */
  int[][][] matrixToArray();

  /**
   * Compresses the image by a certain percentage.
   *
   * @param percentage An integer representing the percentage that the image should be compressed
   *                   by
   */
  void compress(double percentage);

}

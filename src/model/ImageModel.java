package model;

/**
 * A representation of a collection of images; each with a unique image name.
 */
public interface ImageModel {


  /**
   * Level adjust a given image based on ascending black , mid and white values.
   *
   * @param black        is the black value for level adjustment
   * @param mid          is the mdi value for level adjustment
   * @param white        is the white value for level adjustment
   * @param imageName    on which operation is to be applied
   * @param newImageName name of the new image after level adjustment
   * @param splitPercent is the split screen percent
   * @throws IllegalArgumentException if condition 0< black < mid < white < 255 breaks
   */
  void levelAdjust(int black, int mid, int white, String imageName, String newImageName,
      double splitPercent)
      throws IllegalArgumentException;


  /**
   * Create a histogram for an existing image and saves it as a new Image in the Gallery.
   *
   * @param imagName     is the name of the image whose histogram is to be generated
   * @param newImageName name of the generated image of histogram
   */
  void histogram(String imagName, String newImageName) throws IllegalArgumentException;

  /**
   * Create a color corrected histogram for an existing image and saves it as a new Image in the
   * Gallery.
   *
   * @param imageName    name of image to be color corrected
   * @param newImageName name of the new image after color correction
   * @param splitPercent percent of image to be color corrected
   */
  void colorCorrection(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Compress an image.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of compressed image.
   * @param compressBy   percentage to compress the image by.
   */
  void compress(double compressBy, String imageName, String newImageName);

  int[][][] look(String name) throws IllegalArgumentException;

  /**
   * Add an image to the gallery.
   *
   * @param imageName is the name of the image added.
   * @param newImage  is the image to be added.
   * @throws IllegalArgumentException if image name is invalid or if invalid image is passed.
   */
  void add(String imageName, int[][][] newImage)
      throws IllegalArgumentException; //check for same name, no same, etc.

  /**
   * Get an image in the gallery.
   *
   * @param imageName name of the image to be retrieved.
   * @return a 3D int representation of the image.
   * @throws IllegalArgumentException if image with imageName does not exist.
   */
  int[][][] get(String imageName) throws IllegalArgumentException;

  /**
   * Converts a rgb image to a sepia tone. This is done by performing a color transformation.
   */
  void sepia(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Visualises a color component of the image.
   *
   * @param imageName       is the name of the image in the gallery to be visualized.
   * @param newImageName    is the name of the visualized component.
   * @param componentNumber is the channel number to visualize.
   */
  void colorComponent(String imageName, String newImageName, int componentNumber);

  /**
   * Add a value component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with value component.
   */
  void valueComponent(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Add an intensity component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with intensity component.
   */
  void intensityComponent(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Add a luma component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with luma component.
   */
  void lumaComponent(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Flip an image along its vertical axis.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  void verticalFlip(String imageName, String newImageName);

  /**
   * Flip an image along its horizontal axis.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  void horizontalFlip(String imageName, String newImageName);

  /**
   * Split a rgb image into its rgb components.
   *
   * @param imageName name of image to be spit.
   * @param redName   name of red component.
   * @param blueName  name of blue component.
   * @param greenName name of green component.
   */
  void rgbSplit(String imageName, String redName, String greenName, String blueName);

  /**
   * Combine 3 greyscale images into  an rgb image.
   *
   * @param imageName name of image to be spit.
   * @param redName   name of red component.
   * @param blueName  name of blue component.
   * @param greenName name of green component.
   */
  void rgbCombine(String imageName, String redName, String greenName, String blueName);

  /**
   * Blur an image i.e, convolve with a 3x3 matrix.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of blurred image.
   */
  void blur(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  ;

  /**
   * Sharpen an image along i.e. convolve with a 5x5 matrix.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  void sharpen(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException;

  /**
   * Brighten an image.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of brightened image.
   * @param brightenBy   value to brighten each pixed by.
   */
  void brighten(String imageName, String newImageName, int brightenBy);


}



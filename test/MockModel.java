import java.util.ArrayList;
import model.Image;
import model.ImageModel;

/**
 * A mock model for testing the ImageController.
 */
public class MockModel implements ImageModel {

  private StringBuilder log;

  ArrayList<Image> gallery;

  /**
   * Creates an empty gallery.
   */
  public MockModel(StringBuilder log) {
    this.gallery = new ArrayList<Image>();
    this.log = log;

  }

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
  @Override
  public void levelAdjust(int black, int mid, int white, String imageName, String newImageName,
      double splitPercent) throws IllegalArgumentException {
    log.append(
        "LevelAdjust: " + imageName + " " + newImageName + " " + black + " " + mid + " " + white
            + "\n");

  }

  /**
   * Create a histogram for an existing image and saves it as a new Image in the Gallery.
   *
   * @param imageName    is the name of the image whose histogram is to be generated
   * @param newImageName name of the generated image of histogram
   */
  @Override
  public void histogram(String imageName, String newImageName) throws IllegalArgumentException {
    log.append("Histogram: " + imageName + " " + newImageName);

  }

  /**
   * Create a color corrected histogram for an existing image and saves it as a new Image in the
   * Gallery.
   *
   * @param imageName    name of image to be color corrected
   * @param newImageName name of the new image after color correction
   * @param splitPercent percent of image to be color corrected
   */
  @Override
  public void colorCorrection(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException {
    log.append("colorCorrection: " + imageName + " " + newImageName);

  }

  /**
   * Compress an image.
   *
   * @param compressBy   percentage to compress the image by.
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of compressed image.
   */
  @Override
  public void compress(double compressBy, String imageName, String newImageName) {
    log.append("Compressing ").append(imageName).append(" ").append(newImageName).append(" ")
        .append(compressBy).append("\n");
  }

  /**
   * Look at an image in the gallery without deleting it.
   *
   * @param name name of the image to look at
   * @return the image
   * @throws IllegalArgumentException if the image does not exist
   */
  @Override
  public int[][][] look(String name) throws IllegalArgumentException {
    log.append("Looking ").append(name);
    return new int[10][10][10];
  }

  /**
   * Add an image to the gallery.
   *
   * @param imageName is the name of the image added.
   * @param newImage  is the image to be added.
   * @throws IllegalArgumentException if image name is invalid or if invalid image is passed.
   */
  @Override
  public void add(String imageName, int[][][] newImage) throws IllegalArgumentException {
    log.append("Adding ").append(imageName).append("\n");
  }


  /**
   * Get an image in the gallery.
   *
   * @param imageName name of the image to be retrieved.
   * @return a 3D int representation of the image.
   * @throws IllegalArgumentException if image with imageName does not exist.
   */
  @Override
  public int[][][] get(String imageName) throws IllegalArgumentException {
    log.append("Getting ").append(imageName);
    return new int[10][10][10];
  }

  /**
   * Converts a rgb image to a sepia tone. This is done by performing a color transformation.
   *
   * @param imageName    the name of the image.
   * @param newImageName the new name of the image.
   */
  @Override
  public void sepia(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException {
    log.append("Sepia : ").append(imageName).append(" ").append(newImageName);
  }

  /**
   * Visualises a color component of the image.
   *
   * @param imageName       is the name of the image in the gallery to be visualized.
   * @param newImageName    is the name of the visualized component.
   * @param componentNumber is the channel number to visualize.
   */
  @Override
  public void colorComponent(String imageName, String newImageName, int componentNumber) {
    log.append("Colorcomponent : ").append(imageName).append("  ").append(newImageName).append(" ")
        .append(componentNumber);

  }

  /**
   * Add a value component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with value component.
   */
  @Override
  public void valueComponent(String imageName, String newImageName, double splitPercent) {
    log.append("Value component ").append(imageName).append(" ").append(newImageName);
  }

  /**
   * Add an intensity component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with intensity component.
   */
  @Override
  public void intensityComponent(String imageName, String newImageName, double splitPercent) {
    log.append("Intensity component : ").append(imageName).append(" ").append(newImageName);
  }

  /**
   * Add a luma component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with luma component.
   */
  @Override
  public void lumaComponent(String imageName, String newImageName, double splitPercent) {
    log.append("Luma component : ").append(imageName).append(" ").append(newImageName);
  }

  /**
   * Flip an image along its vertical axis.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  @Override
  public void verticalFlip(String imageName, String newImageName) {
    log.append("Vertical Flip : ").append(imageName).append(" ").append(newImageName);
  }

  /**
   * Flip an image along its horizontal axis.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  @Override
  public void horizontalFlip(String imageName, String newImageName) {
    log.append("Horizontal flip : " + imageName + " " + newImageName);
  }

  /**
   * Split an RGB image into its RGB components.
   *
   * @param imageName name of image to be spit.
   * @param redName   name of red component.
   * @param blueName  name of blue component.
   * @param greenName name of green component.
   */
  @Override
  public void rgbSplit(String imageName, String redName, String blueName, String greenName) {
    log.append(
        "RGB Split : " + imageName + " " + redName + " " + greenName + " " + blueName);
  }

  /**
   * Combine 3 greyscale images into  an RGB image.
   *
   * @param imageName name of image to be spit.
   * @param redName   name of red component.
   * @param blueName  name of blue component.
   * @param greenName name of green component.
   */
  @Override
  public void rgbCombine(String imageName, String redName, String blueName, String greenName) {
    log.append(
        "RGB Combine : " + imageName + " " + redName + " " + greenName + " " + blueName);

  }

  /**
   * Blur an image i.e, convolve with a 3x3 matrix.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of blurred image.
   */
  @Override
  public void blur(String imageName, String newImageName, double splitPercent) {
    log.append("Blur : " + imageName + " " + newImageName);

  }

  /**
   * Sharpen an image along i.e. convolve with a 5x5 matrix.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  @Override
  public void sharpen(String imageName, String newImageName, double splitPercent) {
    log.append("Sharpen : " + imageName + " " + newImageName);

  }

  /**
   * Brighten an image.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of brightened image.
   * @param brightenBy   value to brighten each pixed by.
   */
  @Override
  public void brighten(String imageName, String newImageName, int brightenBy) {
    log.append("Brighten: " + imageName + " " + newImageName);

  }
}

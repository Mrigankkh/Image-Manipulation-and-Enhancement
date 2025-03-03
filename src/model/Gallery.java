package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a gallery of images which can operate on images inside it.
 */
public class Gallery implements ImageModel {


  private List<Image> gallery;

  /**
   * Creates an empty gallery.
   */
  public Gallery() {
    this.gallery = new ArrayList<>();
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

    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    this.gallery.remove(image);
    return image.matrixToArray();

  }

  @Override
  public void sepia(String imageName, String newImageName, double splitPercent)
      throws IllegalArgumentException {

    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image processedImage = new RGBImage(newImageName, image.toSepia(splitPercent));
    this.add(processedImage);
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

    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    image.visualizergb(componentNumber);
    image.setImageName(newImageName);
  }

  /**
   * Add a value component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with value component.
   */
  @Override
  public void valueComponent(String imageName, String newImageName, double splitPercent) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image imageComponent = image.visualizeValue(splitPercent);
    imageComponent.setImageName(newImageName);
    add(imageComponent);
  }

  /**
   * Add an intensity component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with intensity component.
   */
  @Override
  public void intensityComponent(String imageName, String newImageName, double splitPercent) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image imageComponent = image.visualizeIntensity(splitPercent);
    imageComponent.setImageName(newImageName);
    add(imageComponent);
  }

  /**
   * Add a luma component of image to gallery.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of image with luma component.
   */
  @Override
  public void lumaComponent(String imageName, String newImageName, double splitPercent) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image imageComponent = image.visualizeLuma(splitPercent);
    imageComponent.setImageName(newImageName);
    add(imageComponent);
  }

  /**
   * Flip an image along its vertical axis.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  @Override
  public void verticalFlip(String imageName, String newImageName) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    image.verticalFlip();
    image.setImageName(newImageName);
  }

  /**
   * Flip an image along its horizontal axis.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  @Override
  public void horizontalFlip(String imageName, String newImageName) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    image.horizontalFlip();
    image.setImageName(newImageName);
  }

  /**
   * Split an rgb image into its rgb components.
   *
   * @param imageName name of image to be spit.
   * @param redName   name of red component.
   * @param blueName  name of blue component.
   * @param greenName name of green component.
   */
  @Override
  public void rgbSplit(String imageName, String redName, String greenName, String blueName) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }

    ArrayList<Image> splitComponents = image.split(redName, greenName, blueName);
    this.gallery.addAll(splitComponents);
  }

  /**
   * Combine 3 greyscale images into  an rgb image.
   *
   * @param imageName name of image to be spit.
   * @param redName   name of red component.
   * @param blueName  name of blue component.
   * @param greenName name of green component.
   */
  @Override
  public void rgbCombine(String imageName, String redName, String greenName, String blueName) {
    Image redImage = this.find(redName);
    Image blueImage = this.find(blueName);
    Image greenImage = this.find(greenName);
    Image newImage = this.find(imageName);
    if (redImage == null) {
      throw new IllegalArgumentException("No image exists with an image name " + redImage);

    }
    if (greenImage == null) {
      throw new IllegalArgumentException("No image exists with an image name " + greenImage);

    }
    if (blueImage == null) {
      throw new IllegalArgumentException("No image exists with an image name " + blueImage);

    }
    if (newImage != null) {
      throw new IllegalArgumentException("An image already exists with image name " + imageName
          + ". Consider using a different image name.");
    }

    gallery.add(new RGBImage(imageName, redImage, greenImage, blueImage));
  }

  /**
   * Blur an image i.e, convolve with a 3x3 matrix.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of blurred image.
   */
  @Override
  public void blur(String imageName, String newImageName, double splitPercent) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image blurImage = new RGBImage(newImageName, image.blur(splitPercent));
    this.add(blurImage);
  }

  /**
   * Sharpen an image along i.e. convolve with a 5x5 matrix.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of flipped image.
   */
  @Override
  public void sharpen(String imageName, String newImageName, double splitPercent) {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image sharpenImage = new RGBImage(newImageName, image.sharpen(splitPercent));
    this.add(sharpenImage);
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

    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    image.brighten(brightenBy);
    image.setImageName(newImageName);
  }

  private boolean validImageName(String imageName) {
    return !imageName.isEmpty() && !imageName.matches("!@#\\$%\\^&*\\)\\(,/?<>");

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
      double splitPercent)
      throws IllegalArgumentException {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image newImage = new RGBImage(newImageName, image.levelAdjust(black, mid, white, splitPercent));
    this.add(newImage);
  }

  /**
   * Create a histogram for an existing image and saves it as a new Image in the Gallery.
   *
   * @param imageName    is the name of the original image
   * @param newImageName is the name of the new image
   */
  @Override
  public void histogram(String imageName, String newImageName) throws IllegalArgumentException {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }

    Image hist = image.histogram();
    hist.setImageName(newImageName);
    this.add(hist);


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
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    Image newImage = new RGBImage(newImageName, image.colorCorrection(splitPercent));
    this.add(newImage);
  }

  /**
   * Compress an image.
   *
   * @param imageName    name of the image on which operation is performed.
   * @param newImageName name of compressed image.
   * @param compressBy   percentage to compress the image by.
   */
  @Override
  public void compress(double compressBy, String imageName, String newImageName) {

    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    image.compress(compressBy);
    image.setImageName(newImageName);
  }

  /**
   * Look at an image in the gallery wihout deleting it.
   *
   * @param imageName name of the image to be retrieved.
   * @return a 3D int representation of the image.
   * @throws IllegalArgumentException if image with imageName does not exist.
   */
  @Override
  public int[][][] look(String imageName) throws IllegalArgumentException {
    Image image = this.find(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image exists with an image name " + imageName);
    }
    return image.matrixToArray();
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

    if (validImageName(imageName)) {
      Image image = this.find(imageName);
      if (image != null) {
        throw new IllegalArgumentException(
            "Image already exists with  image name " + imageName + ".");
      }

      Matrix[] imageMatrix = new Matrix[newImage.length];
      for (int i = 0; i < newImage.length; i++) {
        imageMatrix[i] = new Matrix(newImage[i]);
      }
      if (imageMatrix.length == 3) {
        this.gallery.add(new RGBImage(imageName, imageMatrix));
      } else {
        throw new IllegalArgumentException(
            "Dimension of image provided is invalid. Please enter an image with 3 channels.");
      }


    } else {
      throw new IllegalArgumentException("Name of image empty or invalid.");
    }
  }

  private void add(Image newImage) {
    if (validImageName(newImage.getImageName())) {
      Image image = this.find(newImage.getImageName());
      if (image != null) {
        throw new IllegalArgumentException(
            "Image already exists with  image name " + newImage.getImageName() + ".");

      }

      this.gallery.add(newImage);

    } else {
      throw new IllegalArgumentException("Name of image is invalid.");
    }
  }


  /**
   * Find image in gallery with a particular image name.
   *
   * @param imageName is the name of the image to be found
   * @return the image if found else null.
   */
  private Image find(String imageName) {
    for (Image image : this.gallery) {
      if (image.getImageName().equals(imageName)) {
        return image;
      }
    }
    return null;

  }


}

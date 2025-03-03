package model;

import java.util.ArrayList;

/**
 * The rgbImage class is an extension of AbstractImage. rgbImages have red, green, and blue
 * components.
 */
public class RGBImage extends AbstractImage {

  /**
   * Constructs rgbImage with the name of the image and the image matrix.
   *
   * @param imageName the name of the image
   * @param image     the matrix image
   */
  public RGBImage(String imageName, Matrix[] image) {
    super(imageName, image);
  }

  /**
   * Constructs rgbImage with the name of the image and the images for the red, green, and blue
   * components of the image.
   *
   * @param imageName the name of the image
   * @param red       the red component image
   * @param green     the green component image
   * @param blue      the blue component image
   */
  public RGBImage(String imageName, Image red, Image green, Image blue) {
    super(imageName, red, green, blue);
  }

  @Override
  public ArrayList<Image> split(String redImageName, String greenImageName,
      String blueImageName) {
    Matrix[] rMat = createMatrixArray(getNumChannels(),this.getHeight(), this.getWidth());
    Matrix[] gMat = createMatrixArray(getNumChannels(),this.getHeight(), this.getWidth());
    Matrix[] bMat = createMatrixArray(getNumChannels(),this.getHeight(), this.getWidth());

    for (int i = 0; i < this.getNumChannels(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth(); k++) {
          if (i == 0) {
            rMat[0].set(j, k, this.image[i].get(j, k));
            gMat[0].set(j, k, 0);
            bMat[0].set(j, k, 0);
          }
          if (i == 1) {
            gMat[1].set(j, k, this.image[i].get(j, k));
            rMat[1].set(j, k, 0);
            bMat[1].set(j, k, 0);
          }
          if (i == 2) {
            bMat[2].set(j, k, this.image[i].get(j, k));
            rMat[2].set(j, k, 0);
            gMat[2].set(j, k, 0);
          }
        }
      }
    }
    Image redImage = new RGBImage(redImageName, rMat);
    Image greenImage = new RGBImage(greenImageName, gMat);
    Image blueImage = new RGBImage(blueImageName, bMat);
    ArrayList<Image> rgbList = new ArrayList<>();
    rgbList.add(redImage);
    rgbList.add(greenImage);
    rgbList.add(blueImage);
    return rgbList;
  }

  /**
   * Visualize the R,B or the B component of an image y setting pixel values of the other components
   * as 0.
   *
   * @param channelToVisualize is the component to visualize. 0 is red. 1 is green. 2 is blue.
   */
  @Override
  public void visualizergb(int channelToVisualize) {
    if (channelToVisualize < 0 || channelToVisualize > 2) {
      throw new IllegalArgumentException("Illegal channel");
    }
    for (int i = 0; i < this.getNumChannels(); i++) {
      if (i != channelToVisualize) {
        for (int j = 0; j < this.getHeight(); j++) {
          for (int k = 0; k < this.getWidth(); k++) {
            this.image[i].set(j, k, 0);
          }
        }
      }
    }
  }


}

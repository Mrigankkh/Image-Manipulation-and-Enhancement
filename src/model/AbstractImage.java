package model;

import static java.lang.Math.max;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;


/**
 * A representation of an image and its operations.
 */
public abstract class AbstractImage implements Image {

  private String imageName;
  protected Matrix[] image;


  /**
   * Set the image name.
   *
   * @param imageName is the new image name.
   */
  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  /**
   * Get the name of the image.
   *
   * @return the name of the image.
   */
  @Override
  public String getImageName() {
    return this.imageName;
  }

  /**
   * Initialize the image using an array of matrices.
   *
   * @param imageName is the name of the image.
   * @param image     is the matrix array representation of the image.
   */
  public AbstractImage(String imageName, Matrix[] image) {
    if (imageName.isEmpty()) {
      throw new IllegalArgumentException("imageName cannot be empty");
    }
    if (image[0] == null) {
      throw new IllegalArgumentException(
          "Image matrix cannot be empty. It needs to be at least 1 dimensional.");
    }
    this.image = image;
    this.imageName = imageName;
  }

  /**
   * Initialize the image using its RGB components.
   *
   * @param imageName is the name of the image.
   * @param red       is the red component of the image.
   * @param green     is the green component of the image.
   * @param blue      is the blue component of the image.
   */
  AbstractImage(String imageName, Image red, Image green, Image blue) {
    if (imageName.isEmpty()) {
      throw new IllegalArgumentException("imageName cannot be empty");
    }
    this.imageName = imageName;
    this.image = combine(red, green, blue);
  }

  /**
   * Get the pixel height of the image.
   *
   * @return the pixel height of the image.
   */
  @Override
  public int getHeight() {
    return this.image[0].getNumRows();
  }

  /**
   * Get the pixel width of the image.
   *
   * @return the pixel width of the image.
   */
  @Override
  public int getWidth() {
    return this.image[0].getNumColumns();
  }

  /**
   * Get the number of channels in the image.
   *
   * @return number of channels in the image.
   */
  @Override
  public int getNumChannels() {
    return this.image.length;
  }

  /**
   * Brighten an image by a constant factor.
   *
   * @param brightenBy the brightening factor.
   */
  @Override
  public void brighten(int brightenBy) {
    int height = this.getHeight();
    int width = this.getWidth();
    for (int k = 0; k < this.getNumChannels(); k++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          this.image[k].set(i, j, this.image[k].get(i, j) + brightenBy);
        }
      }


    }
  }

  /**
   * Blur the image using a predetermined filter.
   */
  @Override
  public Matrix[] blur(double splitPercent) {
    double[][] blurKernel = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    Matrix[] newImage = createMatrixArray(this.getNumChannels(), this.getHeight(), this.getWidth());

    for (int i = 0; i < getNumChannels(); i++) {
      newImage[i] = image[i].convolution(blurKernel, splitPercent);
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = (int) (this.getWidth() * splitPercent / 100); j < this.getWidth(); j++) {
        newImage[0].set(i, j, this.image[0].get(i, j));
        newImage[1].set(i, j, this.image[1].get(i, j));
        newImage[2].set(i, j, this.image[2].get(i, j));
      }
    }
    return newImage;
  }

  /**
   * Sharpen the image using a predefined filter.
   */
  @Override
  public Matrix[] sharpen(double splitPercent) {

    double[][] sharpenKernel = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};

    Matrix[] newImage = createMatrixArray(this.getNumChannels(), this.getHeight(), this.getWidth());

    for (int i = 0; i < getNumChannels(); i++) {
      newImage[i] = image[i].convolution(sharpenKernel, splitPercent);
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = (int) (this.getWidth() * splitPercent / 100); j < this.getWidth(); j++) {
        newImage[0].set(i, j, this.image[0].get(i, j));
        newImage[1].set(i, j, this.image[1].get(i, j));
        newImage[2].set(i, j, this.image[2].get(i, j));
      }
    }
    return newImage;

  }

  /**
   * Level adjust a given image based on ascending black , mid and white values.
   *
   * @param black        is the black value for level adjustment
   * @param mid          is the mid-value for level adjustment
   * @param white        is the white value for level adjustment
   * @param splitPercent is the split screen percent
   * @throws IllegalArgumentException if condition 0< black < mid < white < 255 breaks
   */
  @Override
  public Matrix[] levelAdjust(int black, int mid, int white, double splitPercent)
      throws IllegalArgumentException {
    if (!(-1 < black && black < mid && mid < white && white < 256)) {
      throw new IllegalArgumentException("Invalid arguments");
    }
    if (splitPercent < 0 || splitPercent > 100) {
      throw new IllegalArgumentException("Split percentage must be within 0 and 100.");
    }
    Matrix[] newImage = createMatrixArray(this.getNumChannels(), this.getHeight(), this.getWidth());
    double a = calculateLevelAdjustA(black, mid, white);
    double b = calculateLevelAdjustB(black, mid, white);
    double c = calculateLevelAdjustC(black, mid, white);
    for (int j = 0; j < this.getHeight(); j++) {
      for (int k = 0; k < (int) (this.getWidth() * (splitPercent / 100)); k++) {
        for (int i = 0; i < this.getNumChannels(); i++) {
          int x = this.image[i].get(j, k);
          newImage[i].set(j, k,
              (int) (a * x * x + b * x + c));
        }
      }
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = (int) (this.getWidth() * splitPercent / 100); j < this.getWidth(); j++) {
        newImage[0].set(i, j, this.image[0].get(i, j));
        newImage[1].set(i, j, this.image[1].get(i, j));
        newImage[2].set(i, j, this.image[2].get(i, j));
      }
    }
    return newImage;
  }

  private double calculateLevelAdjustAA(int black, int mid, int white) {
    return black * black * (mid - white) - black * (mid * mid - white * white) + white * mid * mid
        - mid * white * white;
  }

  private double calculateLevelAdjustA(int black, int mid, int white) {
    return (-black * (128 - 255) + 128 * white - 255 * mid) / calculateLevelAdjustAA(black, mid,
        white);
  }

  private double calculateLevelAdjustB(int black, int mid, int white) {
    return (black * black * (128 - 255) + 255 * mid * mid - 128 * white * white)
        / calculateLevelAdjustAA(black, mid,
        white);
  }

  private double calculateLevelAdjustC(int black, int mid, int white) {
    return (black * black * (255 * mid - 128 * white) - black * (255 * mid * mid
        - 128 * white * white)) / calculateLevelAdjustAA(black, mid,
        white);
  }

  private int[][] frequency() {
    int[][] frequency = new int[getNumChannels()][256];
    for (int i = 0; i < getNumChannels(); i++) {
      frequency[i] = this.image[i].frequency();
    }
    return frequency;
  }

  private int[] channelPeak() {
    int[] max = new int[getNumChannels()];
    int[][] frequency = this.frequency();
    for (int i = 0; i < getNumChannels(); i++) {
      max[i] = max(max[i], Arrays.stream(frequency[i]).max().getAsInt());
    }
    return max;
  }

  private void blackToWhite(Matrix[] image) {
    for (int j = 2; j <= 255; j++) {
      for (int k = 2; k <= 255; k++) {
        //Make all non-plotted values white.
        if (image[0].get(j, k) == 0 && image[1].get(j, k) == 0 && image[2].get(j, k) == 0) {
          image[0].set(j, k, 255);
          image[1].set(j, k, 255);
          image[2].set(j, k, 255);

        }
      }
    }
  }


  /**
   * A method to plot a histogram on an 2D Matrix array using frequency arrays and a fixed bin
   * size.
   */
  private void plotHistogram(Matrix[] newMat, int[][] frequency, int binSize) {

    for (int i = 0; i < 256; i++) {
      //First plot all points in the frequency array.
      for (int j = 0; j < frequency.length; j++) {
        newMat[j].set(Math.min(255, (int) Math.ceil(((double) frequency[j][i] / binSize))), i,
            255);
      }
      if (i > 0) {
        /*
        To make the histogram continuous and smooth, we also need to plot the points
        between any 2 frequency points in the frequency array.
         */

        int[][][] pointsBetween = new int[frequency.length][][];
        for (int j = 0; j < frequency.length; j++) {
          pointsBetween[j] = getPointsBetween(frequency[j][i] / binSize, i,
              frequency[j][i - 1] / binSize, i - 1);
        }
        for (int c = 0; c < frequency.length; c++) {
          for (int q = 0; q < pointsBetween[c].length; q++) {
            if (pointsBetween[c][q][0] > 255) {
              pointsBetween[c][q][0] = 255;
            }
            for (int otherChannels = 0; otherChannels < frequency.length; otherChannels++) {
              if (otherChannels != c) {
                newMat[otherChannels].set(pointsBetween[c][q][0], pointsBetween[c][q][1], 0);
              }
            }
            newMat[c].set(pointsBetween[c][q][0], pointsBetween[c][q][1], 255);
            for (int otherChannel = 0; otherChannel < getNumChannels(); otherChannel++) {
              if (otherChannel != c) {
                newMat[otherChannel].set(pointsBetween[c][q][0], pointsBetween[c][q][1], 0);
              }
            }
          }
        }
      }
    }
  }

  /**
   * Generate a histogram of the image.
   *
   * @return an image of the histogram
   */
  @Override
  public Image histogram() {

    // An N x 256  2D-Array representing frequency of each of the 0-255 values for each of
    // the N channels.
    int[][] frequency = this.frequency();
    // The new image matrix must be a 256X256 2D-Array.
    Matrix[] newMat = createMatrixArray(getNumChannels(), 256, 256);
    //Max frequency value of any channel.
    int mx = Arrays.stream(this.channelPeak()).max().getAsInt();

    /* The 256th row in the image from the bottom must have the highest peak value. We need to map
     the highest frequency value in the image to 256.
    Since our histogram image is 256 X 256, we need to find out the size of each bin since
    number of bins is fixed at 256.
     */

    int binSize = (int) Math.ceil((double) mx / 256);

    // Plot the histogram based on the frequency arrays and the bin size.
    plotHistogram(newMat, frequency, binSize);


    /*
    Since we initialized all points to be black, we make all the black points white for better
    image clarity.
     */
    blackToWhite(newMat);
    Image ans = new RGBImage("histogram_of " + this.imageName, newMat);
    ans.verticalFlip();

    return ans;
  }

  /**
   * Color correct an image.
   */
  @Override
  public Matrix[] colorCorrection(double splitPercent) {

    Matrix[] newImage = createMatrixArray(this.getNumChannels(), this.getHeight(), this.getWidth());
    int startOfRange = 11;
    int endOfRange = 244;
    // Write code to ignore peaks that are invalid.
    int[][] frequency = this.frequency();
    int mx = 1;
    int[] max = new int[getNumChannels()];
    int[] index = new int[getNumChannels()];
    int[] shift = new int[getNumChannels()];

    for (int i = 0; i < getNumChannels(); i++) {
      max[i] = max(max[i], Arrays.stream(frequency[i], startOfRange, endOfRange).max().getAsInt());
      int finalI = i;
      index[i] = IntStream.range(0, frequency[i].length)
          .filter(maxAt -> max[finalI] == frequency[finalI][maxAt]).findFirst().orElse(-1);
    }
    int avg = (Arrays.stream(index).sum()) / 3;
    for (int i = 0; i < getNumChannels(); i++) {
      shift[i] = (avg - index[i]);
    }
    for (int k = 0; k < getNumChannels(); k++) {
      for (int i = 0; i < this.getHeight(); i++) {
        for (int j = 0; j < this.getWidth() * splitPercent / 100; j++) {
          newImage[k].set(i, j, Math.min(255, max(0, this.image[k].get(i, j) + shift[k])));
        }
      }
    }
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = (int) (this.getWidth() * splitPercent / 100); j < this.getWidth(); j++) {
        newImage[0].set(i, j, this.image[0].get(i, j));
        newImage[1].set(i, j, this.image[1].get(i, j));
        newImage[2].set(i, j, this.image[2].get(i, j));
      }
    }
    return newImage;
  }

  /**
   * Get coordinates of points between 2 points.
   */
  private int[][] getPointsBetween(int x1, int y1, int x2, int y2) {
    int minX = Math.min(x1, x2);
    int maxX = max(x1, x2);
    int minY = Math.min(y1, y2);
    int maxY = max(y1, y2);

    int numPoints = (maxY - minY) * (maxX - minX);

    int[][] pointsBetween = new int[numPoints][2];

    int index = 0;

    for (int i = minX; i < maxX; i++) {
      for (int j = minY; j < maxY; j++) {
        pointsBetween[index][0] = i;
        pointsBetween[index][1] = j;
        index++;
      }
    }

    return pointsBetween;
  }

  /**
   * Flips an image horizontally to create a new image.
   */
  @Override
  public void horizontalFlip() {
    AbstractImage temp = this;
    for (int i = 0; i < this.getNumChannels(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth() / 2; k++) {
          int temp1 = this.image[i].get(j, k);
          this.image[i].set(j, k, temp.image[i].get(j, this.getWidth() - 1 - k));
          this.image[i].set(j, this.getWidth() - 1 - k, temp1);
        }
      }

    }
  }

  /**
   * Flips an image vertically to create a new image.
   */
  @Override
  public void verticalFlip() {
    AbstractImage temp = this;
    for (int i = 0; i < this.getNumChannels(); i++) {
      for (int j = 0; j < this.getHeight() / 2; j++) {
        for (int k = 0; k < this.getWidth(); k++) {
          int temp1 = this.image[i].get(j, k);
          this.image[i].set(j, k, temp.image[i].get(temp.getHeight() - j - 1, k));
          this.image[i].set(temp.getHeight() - j - 1, k, temp1);
        }
      }
    }

  }

  /**
   * Convert an array of matrices to an array of integers.
   *
   * @return a 3d array of integers of the format channels, height, width.
   */
  @Override
  public int[][][] matrixToArray() {

    int[][][] imageArray = new int[image[0].getNumRows()][image[0].getNumColumns()][image.length];
    for (int i = 0; i < image[0].getNumRows(); i++) {
      for (int j = 0; j < image[0].getNumColumns(); j++) {
        for (int k = 0; k < image.length; k++) {
          imageArray[i][j][k] = image[k].get(i, j);
        }
      }
    }
    return imageArray;
  }

  /**
   * Converts a rgb image to a sepia tone. This is done by performing a color transformation.
   *
   * @param splitPercent the percent of the image that will be converted to sepia tone
   */
  @Override
  public Matrix[] toSepia(double splitPercent) {
    double[] transformBy1 = {0.393, 0.349, 0.272};
    double[] transformBy2 = {0.769, 0.686, 0.534};
    double[] transformBy3 = {0.189, 0.168, 0.131};
    Matrix[] newImage = createMatrixArray(this.getNumChannels(), this.getHeight(), this.getWidth());
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = (int) (this.getWidth() * splitPercent / 100); j < this.getWidth(); j++) {
        newImage[0].set(i, j, this.image[0].get(i, j));
        newImage[1].set(i, j, this.image[1].get(i, j));
        newImage[2].set(i, j, this.image[2].get(i, j));
      }
    }
    double[][][] im = new double[3][this.getHeight()][this.getWidth()];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth(); k++) {
          im[i][j][k] = 0;
        }
      }
    }
    im = image[0].transform(im, transformBy1, splitPercent);
    im = image[1].transform(im, transformBy2, splitPercent);
    im = image[2].transform(im, transformBy3, splitPercent);
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < (int) (this.getWidth() * splitPercent / 100); j++) {
        newImage[0].set(i, j, (int) im[0][i][j]);
        newImage[1].set(i, j, (int) im[1][i][j]);
        newImage[2].set(i, j, (int) im[2][i][j]);
      }
    }

    return newImage;
  }

  /**
   * Visualize the Value of the image. Value is defined as the maximum value of the three components
   * for each pixel.
   *
   * @return a greyscale image representing the value of the image.
   */
  @Override
  public Image visualizeValue(double splitPercent) {
    Matrix[] newMat = createMatrixArray(getNumChannels(), this.getHeight(), this.getWidth());
    for (int i = 0; i < this.getNumChannels(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth() * splitPercent / 100; k++) {
          if (this.image[i].get(j, k) > newMat[0].get(j, k)) {
            newMat[0].set(j, k, this.image[i].get(j, k));
            newMat[1].set(j, k, this.image[i].get(j, k));
            newMat[2].set(j, k, this.image[i].get(j, k));
          }
        }
        for (int k = this.getWidth() * (int) splitPercent / 100; k < this.getWidth(); k++) {

          newMat[i].set(j, k, this.image[i].get(j, k));

        }
      }
    }

    return new RGBImage(this.imageName, newMat);
  }

  /**
   * Visualize the intensity of the image. Intensity is defined as the average of the three
   * components for each pixel.
   *
   * @return a greyscale image representing the intensity of the image.
   */
  @Override
  public Image visualizeIntensity(double splitPercent) {
    Matrix[] newMat = createMatrixArray(getNumChannels(), this.getHeight(), this.getWidth());

    for (int j = 0; j < this.getHeight(); j++) {
      for (int k = 0; k < this.getWidth() * splitPercent / 100; k++) {
        int newVal = max(0, Math.min(255,
            averageOfThree(this.image[0].get(j, k), this.image[1].get(j, k),
                this.image[2].get(j, k))));
        newMat[0].set(j, k, newVal);
        newMat[1].set(j, k, newVal);
        newMat[2].set(j, k, newVal);

      }
      for (int k = this.getWidth() * (int) splitPercent / 100; k < this.getWidth(); k++) {

        newMat[0].set(j, k, this.image[0].get(j, k));
        newMat[1].set(j, k, this.image[1].get(j, k));
        newMat[2].set(j, k, this.image[2].get(j, k));

      }
    }
    return new RGBImage(this.imageName, newMat);
  }

  private int averageOfThree(double a, double b, double c) {
    return (int) ((a + b + c) / 3.0);
  }

  /**
   * Visualize the luma of the image. Luma is defined as the weighted sum  0.2126*r + 0.7152*g +
   * 0.0722*b.
   *
   * @return a greyscale image representing the luma of the image.
   */
  @Override
  public Image visualizeLuma(double splitPercent) {
    Matrix[] newMat = createMatrixArray(getNumChannels(), this.getHeight(), this.getWidth());
    double[] transformBy1 = {0.2126, 0.2126, 0.2126};
    double[] transformBy2 = {0.7152, 0.7152, 0.7152};
    double[] transformBy3 = {0.0722, 0.0722, 0.0722};
    double[][][] im = new double[3][this.getHeight()][this.getWidth()];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth() * splitPercent / 100; k++) {
          im[i][j][k] = 0;
        }
        for (int k = (int) (this.getWidth() * splitPercent / 100); k < this.getWidth(); k++) {
          newMat[i].set(j, k, this.image[i].get(j, k));
        }
      }
    }

    im = image[0].transform(im, transformBy1, splitPercent);
    im = image[1].transform(im, transformBy2, splitPercent);
    im = image[2].transform(im, transformBy3, splitPercent);
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < (int) (this.getWidth() * splitPercent / 100); j++) {
        newMat[0].set(i, j, max(0, Math.min(255, (int) im[0][i][j])));
        newMat[1].set(i, j, max(0, Math.min(255, (int) im[1][i][j])));
        newMat[2].set(i, j, max(0, Math.min(255, (int) im[2][i][j])));
      }
    }

    return new RGBImage(this.imageName, newMat);
  }


  private static Matrix[] combine(Image red, Image green, Image blue) {

    if (!(red.getHeight() == blue.getHeight() && blue.getHeight() == green.getHeight()
        && red.getWidth() == blue.getWidth() && blue.getWidth() == green.getWidth())) {
      //throw an exception
      int x = 1;
    }
    Matrix[] newMat = new Matrix[3];
    newMat[0] = ((RGBImage) red).image[0];
    newMat[1] = ((RGBImage) green).image[1];
    newMat[2] = ((RGBImage) blue).image[2];
    return newMat;
  }

  Matrix[] createMatrixArray(int dimension, int height, int width) throws IllegalArgumentException {
    if (dimension < 2) {
      throw new IllegalArgumentException("Dimensions must be atleast 2");
    }

    Matrix[] newMat = new Matrix[dimension];
    for (int i = 0; i < dimension; i++) {
      newMat[i] = new Matrix(height, width);
    }

    return newMat;
  }

  private double[] getT(double[] s) {
    double[] avg = new double[s.length / 2];
    double[] diff = new double[s.length / 2];
    for (int i = 0; i < s.length; i += 2) {
      double a = s[i];
      double b = s[i + 1];
      double av = (a + b) / Math.sqrt(2);
      double di = (a - b) / Math.sqrt(2);
      avg[i / 2] = av;
      diff[i / 2] = di;
    }
    double[] result = new double[s.length];
    System.arraycopy(avg, 0, result, 0, s.length / 2);
    System.arraycopy(diff, 0, result, s.length / 2, s.length / 2);

    return result;
  }

  private double[] getI(double[] s) {
    double[] avg = new double[s.length / 2];
    double[] diff = new double[s.length / 2];
    for (int i = 0; i < s.length / 2; i++) {
      int j = s.length / 2 + i;
      double a = s[i];
      double b = s[j];
      double av = (a + b) / Math.sqrt(2);
      double de = (a - b) / Math.sqrt(2);
      avg[i] = av;
      diff[i] = de;
    }
    double[] result = new double[s.length];
    for (int i = 0; i < s.length; i += 2) {
      result[i] = avg[i / 2];
      result[i + 1] = diff[i / 2];
    }
    return result;
  }

  private double[][][] toSquare(int s) {
    double[][][] square = new double[3][s][s];
    int height = this.getHeight();
    int width = this.getWidth();
    for (int i = 0; i < this.getNumChannels(); i++) {
      for (int j = 0; j < s; j++) {
        for (int k = 0; k < s; k++) {
          if (j >= height || k >= width) {
            square[i][j][k] = 0;
          } else {
            square[i][j][k] = this.image[i].get(j, k);
          }
        }
      }
    }
    return square;
  }

  private int[][][] unpad(double[][][] invertRes) {
    int[][][] unpadded = new int[3][this.getHeight()][this.getWidth()];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth(); k++) {
          unpadded[i][j][k] = (int) Math.round(invertRes[i][j][k]);
        }
      }
    }
    return unpadded;
  }

  private double[][][] haar(double[][][] square, int s) {
    for (int k = 0; k < 3; k++) {
      int c = s;
      while (c > 1) {
        for (int i = 0; i < c; i++) {
          double[] input1 = new double[c];
          System.arraycopy(square[k][i], 0, input1, 0, c);
          double[] res1 = getT(input1);
          System.arraycopy(res1, 0, square[k][i], 0, c);
        }
        for (int j = 0; j < c; j++) {
          double[] input2 = new double[c];
          for (int a = 0; a < c; a++) {
            input2[a] = square[k][a][j];
          }
          double[] res2 = getT(input2);
          for (int b = 0; b < c; b++) {
            square[k][b][j] = res2[b];
          }
        }
        c = c / 2;
      }
    }
    return square;
  }


  private double[][][] invhaar(double[][][] square, int s) {
    for (int k = 0; k < 3; k++) {
      int c = 2;
      while (c <= s) {
        for (int j = 0; j < c; j++) {
          double[] input1 = new double[c];
          for (int a = 0; a < c; a++) {
            input1[a] = square[k][a][j];
          }
          double[] res1 = getI(input1);
          for (int b = 0; b < c; b++) {
            square[k][b][j] = res1[b];
          }
        }
        for (int i = 0; i < c; i++) {
          double[] input2 = new double[c];
          System.arraycopy(square[k][i], 0, input2, 0, c);
          double[] res2 = getI(input2);
          System.arraycopy(res2, 0, square[k][i], 0, c);
        }
        c = c * 2;
      }
    }
    return square;
  }

  private double[][][] calculateThreshold(double[][][] haarRes, int s, double percentage) {
    double[][][] absVals = new double[3][s][s];
    double[][][] compressed = new double[3][s][s];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < s; j++) {
        for (int k = 0; k < s; k++) {
          absVals[i][j][k] = Math.abs(haarRes[i][j][k]);
          compressed[i][j][k] = haarRes[i][j][k];
        }
      }
    }
    Set<Double> set1 = new TreeSet<>();
    for (int j = 0; j < s; j++) {
      for (int k = 0; k < s; k++) {
        for (int channel = 0; channel < getNumChannels(); channel++) {
          set1.add(absVals[channel][j][k]);
        }
      }
    }
    double[] noDupOrdered1 = set1.stream().mapToDouble(Double::doubleValue).toArray();
    Arrays.sort(noDupOrdered1);
    int index1 = (int) Math.floor(percentage / 100.0 * noDupOrdered1.length) - 1;
    double threshold1 = 0;
    if (index1 >= 0) {
      threshold1 = noDupOrdered1[index1];
    }

    for (int i = 0; i < s; i++) {
      for (int j = 0; j < s; j++) {
        for (int channel = 0; channel < getNumChannels(); channel++) {
          if (Math.abs(compressed[channel][i][j]) <= threshold1) {
            compressed[channel][i][j] = 0;
          }
        }
      }
    }

    return compressed;
  }

  /**
   * Compresses the image by a certain percentage.
   *
   * @param percentage An integer representing the percentage that the image should be compressed
   *                   by
   * @throws IllegalArgumentException if the percentage is less than zero or greater than 100
   */
  @Override
  public void compress(double percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Percentage must be between 0 and 100.");
    }
    int max = max(this.image[0].getNumRows(), this.image[0].getNumColumns());
    int p = 1;
    while (p < max) {
      p <<= 1;
    }
    int s = p;
    double[][][] square = toSquare(s);
    double[][][] haarRes = haar(square, s);
    double[][][] compressed = calculateThreshold(haarRes, s, percentage);
    double[][][] invertRes = invhaar(compressed, s);
    int[][][] unpadded = unpad(invertRes);
    for (int i = 0; i < getNumChannels(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth(); k++) {
          this.image[i].set(j, k, unpadded[i][j][k]);
        }
      }
    }
  }

}

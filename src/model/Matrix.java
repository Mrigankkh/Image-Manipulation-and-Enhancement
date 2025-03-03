package model;

/**
 * This class implements a 2D matrix of numbers using a 2D array. This implementation is efficient
 * if most of the entries of the matrix are not zero. But this wastes a lot of space and computing
 * time if most of its entries are zero.
 */
public class Matrix {

  private final int[][] mat;

  /**
   * Constructs a new matrix of the given dimensions. All entries of this matrix are by default, 0
   *
   * @throws IllegalArgumentException if the size is a non-positive number
   */
  public Matrix(int row, int column) throws IllegalArgumentException {
    if (row < 0 || column < 0) {
      throw new IllegalArgumentException("The size of an array matrix cannot be non-positive");
    }

    mat = new int[row][column];
    for (int i = 0; i < mat.length; i += 1) {
      for (int j = 0; j < mat[i].length; j += 1) {
        mat[i][j] = 0;
      }
    }
  }

  /**
   * Constructs a matrix from a 2D integer array.
   *
   * @param mat the 2D integer array
   * @throws IllegalArgumentException if the length of the array does not equal numCols
   */
  public Matrix(int[][] mat) throws IllegalArgumentException {
    int numRows = mat.length;
    int numCols = mat[0].length;
    //ensuring all rows have the same number of columns
    for (int i = 0; i < numRows; i += 1) {
      if (mat[i].length != numCols) {
        throw new IllegalArgumentException("Unequal number of columns");
      }
    }
    this.mat = new int[numRows][numCols];
    for (int i = 0; i < numRows; i += 1) {
      System.arraycopy(mat[i], 0, this.mat[i], 0, numCols);
    }
  }

  /**
   * Sets the matrix at the ith and jth index to a value.
   *
   * @param i     the i value
   * @param j     the j value
   * @param value the value to set it to
   * @throws IllegalArgumentException if values are not in correct range
   */
  public void set(int i, int j, int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      // throw new IllegalArgumentException("Invalid value. Value can only be in range (0,255) ");

    }

    if ((i < 0) || (i >= mat.length)) {
      throw new IllegalArgumentException(
          "Row number in set cannot be beyond the bounds of the matrix");
    }

    if ((j < 0) || (j >= mat[i].length)) {
      throw new IllegalArgumentException(
          "Column number in set cannot be beyond the bounds of the matrix");
    }
    mat[i][j] = Math.max(0, Math.min(255, value));
  }


  /**
   * Gets the value at i, j.
   *
   * @param i the i value
   * @param j the j value
   * @return the value in the matrix
   * @throws IllegalArgumentException if row or column values are invalid
   */
  public int get(int i, int j) throws IllegalArgumentException {
    if ((i < 0) || (i >= mat.length)) {
      throw new IllegalArgumentException(
          "Row number in get cannot be beyond the bounds of the matrix");
    }

    if ((j < 0) || (j >= mat[i].length)) {
      throw new IllegalArgumentException(
          "Column number in get cannot be beyond the bounds of the matrix");
    }
    return mat[i][j];
  }

  /**
   * Gets the number of rows.
   *
   * @return the number of rows
   */
  public int getNumRows() {
    return mat.length;
  }

  /**
   * Gets the number of columns.
   *
   * @return the number of columns
   */
  public int getNumColumns() {
    return mat[0].length;
  }


  /**
   * Perform a convolution operation using kernel.
   *
   * @param kernel       is the kernel of convolution.
   * @param splitPercent the percentage of the image that will be converted
   */
  public Matrix convolution(double[][] kernel, double splitPercent) {
    int kernel_range = (int) Math.floor((double) kernel.length / 2);
    Matrix convoluted = new Matrix(this.getNumRows(), this.getNumColumns());
    for (int i = 1; i < this.getNumRows(); i++) {
      for (int j = 1; j < (int) (this.getNumColumns() * splitPercent / 100); j++) {
        double sum = 0.0;
        for (int x = -kernel_range; x <= kernel_range; x++) {
          for (int y = -kernel_range; y <= kernel_range; y++) {
            if (i + x >= 0 && i + x < this.getNumRows() && j + y >= 0
                && j + y < this.getNumColumns()) {
              sum += (double) this.get(i + x, j + y) * kernel[x + kernel_range][y + kernel_range];
            }
          }
        }
        convoluted.set(i, j, (int) sum);
      }
    }
    return convoluted;
  }

  /**
   * Multiplies parts of the matrix by a double and adds it to the final result.
   *
   * @param im           the double matrix that is set and then returned
   * @param splitPercent the percentage of the image that will be converted
   * @return a 3D double array of values for the image
   */
  public double[][][] transform(double[][][] im, double[] arr, double splitPercent) {
    for (int i = 0; i < this.getNumRows(); i++) {
      for (int j = 0; j < (int) (this.getNumColumns() * splitPercent / 100); j++) {
        im[0][i][j] += arr[0] * this.get(i, j);
        im[1][i][j] += arr[1] * this.get(i, j);
        im[2][i][j] += arr[2] * this.get(i, j);
      }
    }
    return im;
  }

  /**
   * Get frequency array of the occurance of each value in the matrix.
   *
   * @return a frequency array
   */
  public int[] frequency() {
    int[] frequency = new int[256];
    for (int j = 0; j < this.getNumRows(); j++) {
      for (int k = 0; k < this.getNumColumns(); k++) {
        frequency[this.get(j, k)] += 1;
      }
    }
    return frequency;
  }

}
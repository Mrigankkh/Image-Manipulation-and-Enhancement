import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import model.Gallery;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class that tests methods in the AbstractImage class.
 */
public class AbstractImageTest {

  Gallery gallery;

  /**
   * Sets up the gallery.
   */
  @Before
  public void setup() {
    gallery = new Gallery();
  }

  /**
   * Expects IllegalArgumentException if the user enters an empty string for image name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyImageName() {
    int[][][] simple = new int[3][][];
    gallery.add("", simple);
  }

  /**
   * Creates an RGB image and compares horizontal flip matrix result to matrix that is manually
   * calculated.
   */
  @Test
  public void testHorizontal() {
    int[][][] origMat = new int[3][6][11];

    for (int j = 0; j < 6; j++) {
      for (int k = 0; k < 11; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
      }
    }
    gallery.add("image1", origMat);

    int[][][] matToCompareTo = new int[3][6][11];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 6; j++) {
        for (int k = 0; k < 11; k++) {
          matToCompareTo[i][j][k] = origMat[i][j][10 - k];
        }
      }
    }
    gallery.add("matcomp", matToCompareTo);

    gallery.horizontalFlip("image1", "image2");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 6; j++) {
        for (int k = 0; k < 11; k++) {
          assertEquals(gallery.look("matcomp")[j][k][i], gallery.look("image2")[j][k][i]);
        }
      }
    }


  }

  /**
   * Creates an RGB image and compares vertical flip matrix result to matrix that is manually
   * calculated.
   */
  @Test
  public void testVertical() {
    int[][][] origMat = new int[3][6][11];

    for (int j = 0; j < 6; j++) {
      for (int k = 0; k < 11; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
      }
    }
    gallery.add("image1", origMat);

    int[][][] matToCompareTo = new int[3][6][11];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 6; j++) {
        System.arraycopy(origMat[i][5 - j], 0, matToCompareTo[i][j], 0, 11);
      }
    }
    gallery.add("matcomp", matToCompareTo);

    gallery.verticalFlip("image1", "image2");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 6; j++) {
        for (int k = 0; k < 11; k++) {
          assertEquals(gallery.look("matcomp")[j][k][i], gallery.look("image2")[j][k][i]);
        }
      }
    }

  }

  /**
   * Creates two RGB images. One image is brightened and its matrix is compared with an expected
   * matrix for that operation. One image is darkened and its matrix is compared with an expected
   * matrix for that operation.
   */
  @Test
  public void testBrightenDarken() {
    int[][][] origMat = new int[3][6][11];
    int[][][] origMat2 = new int[3][6][11];
    int[][][] expMatInc = new int[3][6][11];
    int[][][] expMatDec = new int[3][6][11];

    for (int j = 0; j < 6; j++) {
      for (int k = 0; k < 11; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
        origMat2[0][j][k] = j * k + 15;
        origMat2[1][j][k] = j * k + 35;
        origMat2[2][j][k] = j * k + 55;
        expMatInc[0][j][k] = j * k + 35;
        expMatInc[1][j][k] = j * k + 55;
        expMatInc[2][j][k] = j * k + 75;
        expMatDec[0][j][k] = j * k + 5;
        expMatDec[1][j][k] = j * k + 25;
        expMatDec[2][j][k] = j * k + 45;
      }
    }

    gallery.add("image1", origMat);
    gallery.brighten("image1", "image1", 20);
    gallery.add("image2", origMat2);
    gallery.brighten("image2", "image2", -10);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 6; j++) {
        for (int k = 0; k < 11; k++) {
          assertEquals(expMatInc[i][j][k], gallery.look("image1")[j][k][i]);
          assertEquals(expMatDec[i][j][k], gallery.look("image2")[j][k][i]);
        }
      }
    }


  }

  /**
   * Creates an RGB image which is calls blur(). Three expected values in the matrix are calculated
   * and compared to the values in the RGB image.
   */
  @Test
  public void testBlur() {
    int[][][] origMat = new int[3][3][5];
    int[][][] expMat = new int[3][3][5];

    for (int j = 0; j < 3; j++) {
      for (int k = 0; k < 4; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
      }
    }

    double[][] blurKernel = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    for (int i = 0; i < 3; i++) {
      double value1 = blurKernel[0][0] * origMat[i][0][0] + blurKernel[0][1] * origMat[i][0][1]
          + blurKernel[0][2] * origMat[i][0][2] + blurKernel[1][0] * origMat[i][1][0]
          + blurKernel[1][1] * origMat[i][1][1] + blurKernel[1][2] * origMat[i][1][2]
          + blurKernel[2][0] * origMat[i][2][0] + blurKernel[2][1] * origMat[i][2][1]
          + blurKernel[2][2] * origMat[i][2][2];
      double value2 = blurKernel[0][0] * origMat[i][0][1] + blurKernel[0][1] * origMat[i][0][2]
          + blurKernel[0][2] * origMat[i][0][3] + blurKernel[1][0] * origMat[i][1][1]
          + blurKernel[1][1] * origMat[i][1][2] + blurKernel[1][2] * origMat[i][1][3]
          + blurKernel[2][0] * origMat[i][2][1] + blurKernel[2][1] * origMat[i][2][2]
          + blurKernel[2][2] * origMat[i][2][3];
      double value3 = blurKernel[0][0] * origMat[i][0][2] + blurKernel[0][1] * origMat[i][0][3]
          + blurKernel[0][2] * origMat[i][0][4] + blurKernel[1][0] * origMat[i][1][2]
          + blurKernel[1][1] * origMat[i][1][3] + blurKernel[1][2] * origMat[i][1][4]
          + blurKernel[2][0] * origMat[i][2][2] + blurKernel[2][1] * origMat[i][2][3]
          + blurKernel[2][2] * origMat[i][2][4];
      expMat[i][1][1] = Math.max(0, Math.min(255, (int) value1));
      expMat[i][1][2] = Math.max(0, Math.min(255, (int) value2));
      expMat[i][1][3] = Math.max(0, Math.min(255, (int) value3));
    }

    gallery.add("image1", origMat);
    gallery.blur("image1", "image1", 100);

    for (int i = 0; i < 3; i++) {
      assertEquals(expMat[i][1][1], gallery.look("image1")[1][1][i]);
      assertEquals(expMat[i][1][2], gallery.look("image1")[1][2][i]);
      assertEquals(expMat[i][1][3], gallery.look("image1")[1][3][i]);
    }

  }


  /**
   * Creates an RGB image which calls sharpen(). The expected values in the matrix are calculated
   * and compared to the values in the RGB image.
   */
  @Test
  public void testSharpen() {
    int[][][] origMat = new int[3][10][15];
    int[][][] expMat = new int[3][10][15];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 15; k++) {
          origMat[0][j][k] = j * k + 10;
          origMat[1][j][k] = j * k + 25;
          origMat[2][j][k] = j * k + 40;
          expMat[0][j][k] = j * k + 10;
          expMat[1][j][k] = j * k + 25;
          expMat[2][j][k] = j * k + 40;
        }
      }
    }

    gallery.add("image1", origMat);
    gallery.sharpen("image1", "image1", 100);

    double[][] sharpenKernel = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};

    for (int k = 0; k < 3; k++) {
      for (int i = 1; i < 10; i++) {
        for (int j = 1; j < 15; j++) {
          double sum = 0.0;
          for (int u = 0; u < sharpenKernel.length; u++) {
            for (int v = 0; v < sharpenKernel.length; v++) {
              int ni = i + u - sharpenKernel.length / 2;
              int nj = j + v - sharpenKernel.length / 2;
              if (ni >= 0 && ni < 10 && nj >= 0 && nj < 15) {
                sum += ((double) expMat[k][ni][nj] * sharpenKernel[u][v]);
              }
            }
          }
          expMat[k][i][j] = Math.max(0, Math.min((int) sum, 255));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 15; k++) {
          assertEquals(expMat[i][j][k], gallery.look("image1")[j][k][i]);
        }
      }
    }

  }

  /**
   * Creates a greyscale image and compares components of a pixel at one location to three manually
   * calculated values.
   */
  @Test
  public void testSepiaGreyscale() {

    int[][][] origMat = new int[3][10][15];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 15; k++) {
          origMat[i][j][k] = j * k + 15;
        }
      }
    }

    gallery.add("image1", origMat);
    int value = (int) (0.393 * origMat[0][0][1] + 0.769 * origMat[1][0][1]
        + 0.189 * origMat[2][0][1]);
    int value2 = (int) (0.349 * origMat[0][0][1] + 0.686 * origMat[1][0][1]
        + 0.168 * origMat[2][0][1]);
    int value3 = (int) (0.272 * origMat[0][0][1] + 0.534 * origMat[1][0][1]
        + 0.131 * origMat[2][0][1]);
    gallery.sepia("image1", "image1", 100);
    assertEquals(value, gallery.look("image1")[0][1][0]);
    assertEquals(value2, gallery.look("image1")[0][1][1]);
    assertEquals(value3, gallery.look("image1")[0][1][2]);
  }

  /**
   * Creates a rgb image and compares components of a pixel at one location to three manually
   * calculated values.
   */
  @Test
  public void testSepiaRGB() {
    int[][][] origMat = new int[3][10][15];

    for (int j = 0; j < 10; j++) {
      for (int k = 0; k < 15; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
      }
    }
    gallery.add("image1", origMat);
    int value = (int) (0.393 * origMat[0][0][1] + 0.769 * origMat[1][0][1]
        + 0.189 * origMat[2][0][1]);
    int value2 = (int) (0.349 * origMat[0][0][1] + 0.686 * origMat[1][0][1]
        + 0.168 * origMat[2][0][1]);
    int value3 = (int) (0.272 * origMat[0][0][1] + 0.534 * origMat[1][0][1]
        + 0.131 * origMat[2][0][1]);

    gallery.sepia("image1", "image1", 100);
    assertEquals(value, gallery.look("image1")[0][1][0]);
    assertEquals(value2, gallery.look("image1")[0][1][1]);
    assertEquals(value3, gallery.look("image1")[0][1][2]);

  }

  /**
   * Tests having an illegal parameter for visualizeRGB. Expects IllegalArgumentException to be
   * thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChannelVisualizeRGB() {
    int[][][] origMat = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;
      }
    }
    gallery.add("image1", origMat);
    gallery.colorComponent("image1", "image1", -1);
  }

  /**
   * Tests having an illegal parameter for visualizeRGB. Expects IllegalArgumentException to be
   * thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChannelVisualizeRGB2() {
    int[][][] origMat = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;
      }
    }
    //RGBImage image1 = new RGBImage("image1",origMat);
    gallery.add("image1", origMat);
    gallery.colorComponent("image1", "image1", 3);
  }


  /**
   * Has three matrices that three images are made from. Three additional matrices represent the
   * expected values for the red, green, and blue components. Each of the three objects call
   * visualizeRGB and then their results are compared to the expected values.
   */
  @Test
  public void testVisualizeRGB() {

    int[][][] origMat = new int[3][18][12];
    int[][][] origMat2 = new int[3][18][12];
    int[][][] origMat3 = new int[3][18][12];
    int[][][] redComp = new int[3][18][12];
    int[][][] greenComp = new int[3][18][12];
    int[][][] blueComp = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;

        origMat2[0][j][k] = j * k + 8;
        origMat2[1][j][k] = j * k + 4;
        origMat2[2][j][k] = j * k + 2;

        origMat3[0][j][k] = j * k + 8;
        origMat3[1][j][k] = j * k + 4;
        origMat3[2][j][k] = j * k + 2;

        redComp[0][j][k] = j * k + 8;
        redComp[1][j][k] = 0;
        redComp[2][j][k] = 0;

        greenComp[0][j][k] = 0;
        greenComp[1][j][k] = j * k + 4;
        greenComp[2][j][k] = 0;

        blueComp[0][j][k] = 0;
        blueComp[1][j][k] = 0;
        blueComp[2][j][k] = j * k + 2;
      }
    }
    gallery.add("redcomp", redComp);
    gallery.add("greencomp", greenComp);
    gallery.add("bluecomp", blueComp);

    gallery.add("image1", origMat);
    gallery.add("image2", origMat2);
    gallery.add("image3", origMat3);
    gallery.colorComponent("image1", "image1", 0);
    gallery.colorComponent("image2", "image2", 1);
    gallery.colorComponent("image3", "image3", 2);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          assertEquals(gallery.look("redcomp")[j][k][i], gallery.look("image1")[j][k][i]);
          assertEquals(gallery.look("greencomp")[j][k][i], gallery.look("image2")[j][k][i]);
          assertEquals(gallery.look("bluecomp")[j][k][i], gallery.look("image3")[j][k][i]);
        }
      }
    }

  }

  /**
   * Creates a rgb image. Then visualizeValue() is performed on the rgb image and that result is
   * compared to a matrix of the expected values.
   */
  @Test
  public void testValue() {

    int[][][] origMat = new int[3][18][12];
    int[][][] expValue = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 2;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 8;
        expValue[0][j][k] = j * k + 8;
        expValue[1][j][k] = j * k + 8;
        expValue[2][j][k] = j * k + 8;
      }
    }

    gallery.add("expval", expValue);
    gallery.add("image1", origMat);
    gallery.valueComponent("image1", "image2", 100);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          assertEquals(gallery.look("expval")[j][k][i], gallery.look("image2")[j][k][i]);
        }
      }
    }
  }

  /**
   * Creates a rgb image. Then visualizeIntensity() is performed on the rgb image and that result is
   * compared to a matrix of the expected intensity.
   */
  @Test
  public void testIntensity() {

    int[][][] origMat = new int[3][18][12];
    int[][][] expIntensity = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;
      }
    }

    for (int i = 0; i < 3; i++) {
      expIntensity[i][4][5] = Math.max(0,
          Math.min(255, origMat[0][4][5] + origMat[1][4][5] + origMat[2][4][5]));
      expIntensity[i][12][8] = Math.max(0,
          Math.min(255, origMat[0][12][8] + origMat[1][12][8] + origMat[2][12][8]));
      expIntensity[i][10][9] = Math.max(0,
          Math.min(255, origMat[0][10][9] + origMat[1][10][9] + origMat[2][10][9]));
      expIntensity[i][4][5] = Math.max(0, Math.min(255, expIntensity[i][4][5] / 3));
      expIntensity[i][12][8] = Math.max(0, Math.min(255, expIntensity[i][12][8] / 3));
      expIntensity[i][10][9] = Math.max(0, Math.min(255, expIntensity[i][10][9] / 3));
    }

    gallery.add("image1", origMat);
    gallery.add("expintensity", expIntensity);
    gallery.intensityComponent("image1", "image2", 100);

    for (int i = 0; i < 3; i++) {
      assertEquals(24, gallery.look("image2")[4][5][0]);
      assertEquals(100, gallery.look("image2")[12][8][0]);
      assertEquals(94, gallery.look("image2")[10][9][0]);
    }

  }

  /**
   * Creates a rgb image. Then visualizeLuma() is performed on the rgb image and that result is
   * compared to a matrix of the expected luma.
   */
  @Test
  public void testLuma() {

    int[][][] origMat = new int[3][18][12];
    int[][][] expLuma = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;
      }
    }

    for (int i = 0; i < 18; i++) {
      for (int j = 0; j < 12; j++) {
        expLuma[0][i][j] = Math.max(0, Math.min(255,
            (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + ((0.0722)
                * origMat[2][i][j]))));
        expLuma[1][i][j] = Math.max(0, Math.min(255,
            (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + ((0.0722)
                * origMat[2][i][j]))));
        expLuma[2][i][j] = Math.max(0, Math.min(255,
            (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + ((0.0722)
                * origMat[2][i][j]))));
      }
    }

    gallery.add("expluma", expLuma);
    gallery.add("image1", origMat);
    gallery.lumaComponent("image1", "image2", 100);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          assertEquals(gallery.look("expluma")[j][k][i], gallery.look("image2")[j][k][i]);
        }
      }
    }
  }

  /**
   * Creates an RGB image. Three matrices represent the expected values for the red, green, and blue
   * components. An ArrayList is made from the RGB image calling split and the images in the list
   * are compared to the expected values.
   */
  @Test
  public void testSplit() {

    int[][][] origMat = new int[3][70][100];
    int[][][] expRed = new int[3][70][100];
    int[][][] expGreen = new int[3][70][100];
    int[][][] expBlue = new int[3][70][100];

    for (int j = 0; j < 70; j++) {
      for (int k = 0; k < 100; k++) {
        origMat[0][j][k] = j + k + 20;
        origMat[1][j][k] = j + k + 40;
        origMat[2][j][k] = j + k + 60;

        expRed[0][j][k] = j + k + 20;
        expRed[1][j][k] = 0;
        expRed[2][j][k] = 0;

        expGreen[0][j][k] = 0;
        expGreen[1][j][k] = j + k + 40;
        expGreen[2][j][k] = 0;

        expBlue[0][j][k] = 0;
        expBlue[1][j][k] = 0;
        expBlue[2][j][k] = j + k + 60;
      }
    }

    gallery.add("expred", expRed);
    gallery.add("expgreen", expGreen);
    gallery.add("expblue", expBlue);
    gallery.add("image1", origMat);
    gallery.rgbSplit("image1", "red", "green", "blue");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 70; j++) {
        for (int k = 0; k < 100; k++) {
          assertEquals(gallery.look("expred")[j][k][i], gallery.look("red")[j][k][i]);
          assertEquals(gallery.look("expgreen")[j][k][i], gallery.look("green")[j][k][i]);
          assertEquals(gallery.look("expblue")[j][k][i], gallery.look("blue")[j][k][i]);
        }
      }
    }
  }

  /**
   * Creates three images to combine and a matrix of expected values. This matrix is compared to the
   * image result.
   */
  @Test
  public void testConstructorCombine() {
    int[][][] origRed = new int[3][70][100];
    int[][][] origGreen = new int[3][70][100];
    int[][][] origBlue = new int[3][70][100];
    int[][][] expMat = new int[3][70][100];

    for (int j = 0; j < 70; j++) {
      for (int k = 0; k < 100; k++) {
        expMat[0][j][k] = j + k + 20;
        expMat[1][j][k] = j + k + 40;
        expMat[2][j][k] = j + k + 60;

        origRed[0][j][k] = j + k + 20;
        origRed[1][j][k] = j + k + 20;
        origRed[2][j][k] = j + k + 20;

        origGreen[0][j][k] = j + k + 40;
        origGreen[1][j][k] = j + k + 40;
        origGreen[2][j][k] = j + k + 40;

        origBlue[0][j][k] = j + k + 60;
        origBlue[1][j][k] = j + k + 60;
        origBlue[2][j][k] = j + k + 60;
      }
    }

    gallery.add("expmat", expMat);
    gallery.add("image1", origRed);
    gallery.add("image2", origGreen);
    gallery.add("image3", origBlue);
    gallery.rgbCombine("image4", "image1", "image2", "image3");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 70; j++) {
        for (int k = 0; k < 100; k++) {
          assertEquals(gallery.look("expmat")[j][k][i], gallery.look("image4")[j][k][i]);
        }
      }
    }
  }

  /**
   * Attempts to compress an image for a percentage less than 0. This is expected to throw an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCompressPercentage() {
    int[][][] mat1 = new int[3][10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        mat1[0][i][j] = 1;
        mat1[1][i][j] = 1;
        mat1[2][i][j] = 1;
      }
    }
    gallery.add("image1", mat1);
    gallery.compress(-10, "image1", "image1");
  }

  /**
   * Attempts to compress an image for a percentage greater than 100. This is expected to throw an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCompressPercentage2() {
    int[][][] mat1 = new int[3][10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        mat1[0][i][j] = 1;
        mat1[1][i][j] = 1;
        mat1[2][i][j] = 1;
      }
    }
    gallery.add("image1", mat1);
    gallery.compress(105, "image1", "image1");
  }

  /**
   * Tests the compression of an image by creating it with a 3D array. Then, the image is compressed
   * by 0, 20, 50, 70, and 100 percent. Finally, the matrices of expected values for each of those
   * resulting images are compared to the actual output.
   */
  @Test
  public void testCompression() {
    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    int[][] exp1 = {{8, 2, 0, 7, 5, 6, 4, 1, 9}, {5, 12, 31, 13, 20, 16, 26, 21, 15}};
    int[][] exp2 = {{9, 2, 1, 8, 5, 5, 3, 0, 11}, {6, 12, 32, 14, 18, 18, 24, 20, 11}};
    int[][] exp3 = {{9, 9, 0, 6, 4, 4, 0, 0, 2}, {9, 9, 25, 12, 17, 17, 21, 21, 2}};
    int[][] exp4 = {{0, 0, 0, 0, 6, 6, 6, 6, 0}, {0, 0, 0, 0, 6, 6, 6, 6, 0}};

    int[][] exp5 = {{16, 4, 0, 14, 10, 12, 8, 2, 18}, {10, 24, 62, 26, 40, 32, 52, 42, 30}};
    int[][] exp6 = {{16, 3, 0, 14, 10, 15, 8, 0, 18}, {10, 23, 62, 26, 40, 35, 50, 42, 30}};
    int[][] exp7 = {{27, 14, 0, 15, 11, 11, 2, 2, 13}, {14, 27, 52, 27, 36, 36, 44, 44, 13}};
    int[][] exp8 = {{18, 18, 0, 12, 9, 9, 0, 0, 15}, {18, 18, 49, 24, 34, 34, 42, 42, 15}};

    int[][] exp9 = {{24, 6, 0, 21, 15, 18, 12, 3, 27}, {15, 36, 93, 39, 60, 48, 78, 63, 45}};
    int[][] exp10 = {{25, 5, 0, 21, 15, 18, 13, 1, 27}, {16, 35, 93, 39, 60, 48, 76, 64, 45}};
    int[][] exp11 = {{32, 12, 2, 23, 16, 16, 4, 4, 33}, {12, 32, 95, 41, 54, 54, 67, 67, 33}};
    int[][] exp12 = {{31, 31, 0, 22, 16, 16, 4, 4, 19}, {31, 31, 78, 40, 54, 54, 67, 67, 19}};

    int[][] exp13 = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    gallery.add("image1", mat1);
    gallery.add("image2", mat1);
    gallery.add("image3", mat1);
    gallery.add("image4", mat1);
    gallery.add("image5", mat1);

    gallery.compress(0, "image1", "image6");
    gallery.compress(20, "image2", "image7");
    gallery.compress(50, "image3", "image8");
    gallery.compress(70, "image4", "image9");
    gallery.compress(100, "image5", "image10");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 9; k++) {
          if (i == 0) {
            assertEquals(exp1[j][k], gallery.look("image6")[j][k][i]);
            assertEquals(exp2[j][k], gallery.look("image7")[j][k][i]);
            assertEquals(exp3[j][k], gallery.look("image8")[j][k][i]);
            assertEquals(exp4[j][k], gallery.look("image9")[j][k][i]);
          }
          if (i == 1) {
            assertEquals(exp5[j][k], gallery.look("image6")[j][k][i]);
            assertEquals(exp6[j][k], gallery.look("image7")[j][k][i]);
            assertEquals(exp7[j][k], gallery.look("image8")[j][k][i]);
            assertEquals(exp8[j][k], gallery.look("image9")[j][k][i]);
          }
          if (i == 2) {
            assertEquals(exp9[j][k], gallery.look("image6")[j][k][i]);
            assertEquals(exp10[j][k], gallery.look("image7")[j][k][i]);
            assertEquals(exp11[j][k], gallery.look("image8")[j][k][i]);
            assertEquals(exp12[j][k], gallery.look("image9")[j][k][i]);
          }
          assertEquals(exp13[j][k], gallery.look("image10")[j][k][i]);
        }
      }
    }
  }

  /**
   * Tests that an image compressed by 50 percent has fewer unique values inside its matrix than the
   * original image.
   */
  @Test
  public void testCompressedImageLess() {
    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }

    gallery.add("image1", mat1);
    gallery.add("image2", mat1);

    gallery.compress(0, "image1", "image3");
    gallery.compress(50, "image2", "image4");

    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    Set<Integer> set3 = new HashSet<>();
    Set<Integer> set4 = new HashSet<>();
    Set<Integer> set5 = new HashSet<>();
    Set<Integer> set6 = new HashSet<>();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 9; k++) {
          set1.add(gallery.look("image3")[j][k][0]);
          set2.add(gallery.look("image4")[j][k][0]);
          set3.add(gallery.look("image3")[j][k][1]);
          set4.add(gallery.look("image4")[j][k][1]);
          set5.add(gallery.look("image3")[j][k][2]);
          set6.add(gallery.look("image4")[j][k][2]);
        }
      }
    }
    int[] noDup1 = set1.stream().mapToInt(Integer::intValue).toArray();
    int[] noDup2 = set2.stream().mapToInt(Integer::intValue).toArray();
    int[] noDup3 = set3.stream().mapToInt(Integer::intValue).toArray();
    int[] noDup4 = set4.stream().mapToInt(Integer::intValue).toArray();
    int[] noDup5 = set5.stream().mapToInt(Integer::intValue).toArray();
    int[] noDup6 = set6.stream().mapToInt(Integer::intValue).toArray();
    assertTrue(noDup2.length < noDup1.length);
    assertTrue(noDup4.length < noDup3.length);
    assertTrue(noDup6.length < noDup5.length);
  }

  @Test
  public void testHistogram() {

    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("histTest", mat1);
    gallery.histogram("histTest", "histogram");
    assertEquals(255, gallery.look("histogram")[0][0][0]);
    assertEquals(255, gallery.look("histogram")[0][0][1]);
    assertEquals(255, gallery.look("histogram")[0][0][2]);
    assertEquals(255, gallery.look("histogram")[0][1][0]);
    assertEquals(255, gallery.look("histogram")[0][1][1]);
    assertEquals(255, gallery.look("histogram")[0][1][2]);
    assertEquals(255, gallery.look("histogram")[0][2][0]);
    assertEquals(255, gallery.look("histogram")[0][2][1]);
    assertEquals(255, gallery.look("histogram")[0][2][2]);
    assertEquals(255, gallery.look("histogram")[1][0][0]);
    assertEquals(255, gallery.look("histogram")[1][0][1]);
    assertEquals(255, gallery.look("histogram")[1][0][2]);
    assertEquals(255, gallery.look("histogram")[1][1][0]);
    assertEquals(255, gallery.look("histogram")[1][1][1]);
    assertEquals(255, gallery.look("histogram")[1][1][2]);
    assertEquals(255, gallery.look("histogram")[1][2][0]);
    assertEquals(255, gallery.look("histogram")[1][2][1]);
    assertEquals(255, gallery.look("histogram")[1][2][2]);
    assertEquals(255, gallery.look("histogram")[2][0][0]);
    assertEquals(255, gallery.look("histogram")[2][0][1]);
    assertEquals(255, gallery.look("histogram")[2][0][2]);
    assertEquals(255, gallery.look("histogram")[2][1][0]);
    assertEquals(255, gallery.look("histogram")[2][1][1]);
    assertEquals(255, gallery.look("histogram")[2][1][2]);
    assertEquals(255, gallery.look("histogram")[2][2][0]);
    assertEquals(255, gallery.look("histogram")[2][2][1]);
    assertEquals(255, gallery.look("histogram")[1][8][1]);


  }


  @Test
  public void testColorCorrect() {
    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("histTest", mat1);
    gallery.colorCorrection("histTest", "histogram", 100);
    assertEquals(13, gallery.look("histogram")[0][0][0]);
    assertEquals(21, gallery.look("histogram")[0][0][1]);
    assertEquals(14, gallery.look("histogram")[0][0][2]);
    assertEquals(7, gallery.look("histogram")[0][1][0]);
    assertEquals(9, gallery.look("histogram")[0][1][1]);
    assertEquals(0, gallery.look("histogram")[0][1][2]);
    assertEquals(5, gallery.look("histogram")[0][2][0]);
    assertEquals(5, gallery.look("histogram")[0][2][1]);
    assertEquals(0, gallery.look("histogram")[0][2][2]);
    assertEquals(10, gallery.look("histogram")[1][0][0]);
    assertEquals(15, gallery.look("histogram")[1][0][1]);
    assertEquals(5, gallery.look("histogram")[1][0][2]);
    assertEquals(17, gallery.look("histogram")[1][1][0]);
    assertEquals(29, gallery.look("histogram")[1][1][1]);
    assertEquals(26, gallery.look("histogram")[1][1][2]);
    assertEquals(36, gallery.look("histogram")[1][2][0]);
    assertEquals(67, gallery.look("histogram")[1][2][1]);
    assertEquals(83, gallery.look("histogram")[1][2][2]);


  }

  /**
   * Test level adjust operation.
   */
  @Test
  public void testLevelAdjust() {

    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("histTest", mat1);
    gallery.levelAdjust(20, 128, 255, "histTest", "histogram", 100);
    assertEquals(0, gallery.look("histogram")[0][0][0]);
    assertEquals(0, gallery.look("histogram")[0][0][1]);
    assertEquals(5, gallery.look("histogram")[0][0][2]);
    assertEquals(0, gallery.look("histogram")[0][1][0]);
    assertEquals(0, gallery.look("histogram")[0][1][1]);
    assertEquals(0, gallery.look("histogram")[0][1][2]);
    assertEquals(0, gallery.look("histogram")[0][2][0]);
    assertEquals(0, gallery.look("histogram")[0][2][1]);
    assertEquals(0, gallery.look("histogram")[0][2][2]);
    assertEquals(0, gallery.look("histogram")[1][0][0]);
    assertEquals(0, gallery.look("histogram")[1][0][1]);
    assertEquals(0, gallery.look("histogram")[1][0][2]);
    assertEquals(0, gallery.look("histogram")[1][1][0]);
    assertEquals(5, gallery.look("histogram")[1][1][1]);
    assertEquals(20, gallery.look("histogram")[1][1][2]);
    assertEquals(13, gallery.look("histogram")[1][2][0]);
    assertEquals(51, gallery.look("histogram")[1][2][1]);
    assertEquals(88, gallery.look("histogram")[1][2][2]);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testLevelAdjustInvalidArguments() {

    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("histTest", mat1);
    gallery.levelAdjust(200, 128, 25
        , "histTest", "histogram", 100);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSplitPercent() {
    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("histTest", mat1);
    gallery.levelAdjust(20, 128, 255, "histTest",
        "histogram", 1000);
  }

  @Test
  public void testSplitBlur() {
    int[][][] origMat = new int[3][3][5];
    int[][][] expMat = new int[3][3][5];

    for (int j = 0; j < 3; j++) {
      for (int k = 0; k < 4; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
      }
    }

    double[][] blurKernel = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    for (int i = 0; i < 3; i++) {
      double value1 = blurKernel[0][0] * origMat[i][0][0] + blurKernel[0][1] * origMat[i][0][1]
          + blurKernel[0][2] * origMat[i][0][2] + blurKernel[1][0] * origMat[i][1][0]
          + blurKernel[1][1] * origMat[i][1][1] + blurKernel[1][2] * origMat[i][1][2]
          + blurKernel[2][0] * origMat[i][2][0] + blurKernel[2][1] * origMat[i][2][1]
          + blurKernel[2][2] * origMat[i][2][2];
      double value2 = blurKernel[0][0] * origMat[i][0][1] + blurKernel[0][1] * origMat[i][0][2]
          + blurKernel[0][2] * origMat[i][0][3] + blurKernel[1][0] * origMat[i][1][1]
          + blurKernel[1][1] * origMat[i][1][2] + blurKernel[1][2] * origMat[i][1][3]
          + blurKernel[2][0] * origMat[i][2][1] + blurKernel[2][1] * origMat[i][2][2]
          + blurKernel[2][2] * origMat[i][2][3];
      double value3 = blurKernel[0][0] * origMat[i][0][2] + blurKernel[0][1] * origMat[i][0][3]
          + blurKernel[0][2] * origMat[i][0][4] + blurKernel[1][0] * origMat[i][1][2]
          + blurKernel[1][1] * origMat[i][1][3] + blurKernel[1][2] * origMat[i][1][4]
          + blurKernel[2][0] * origMat[i][2][2] + blurKernel[2][1] * origMat[i][2][3]
          + blurKernel[2][2] * origMat[i][2][4];
      expMat[i][1][1] = Math.max(0, Math.min(255, (int) value1));
      expMat[i][1][2] = Math.max(0, Math.min(255, (int) value2));
      expMat[i][1][3] = origMat[i][1][3];
    }

    gallery.add("image1", origMat);
    gallery.blur("image1", "image1", 66);

    for (int i = 0; i < 3; i++) {
      assertEquals(expMat[i][1][1], gallery.look("image1")[1][1][i]);
      assertEquals(expMat[i][1][2], gallery.look("image1")[1][2][i]);
      assertEquals(expMat[i][1][3], gallery.look("image1")[1][3][i]);
    }
  }

  /**
   * Test for sharpen when split percent is given.
   */
  @Test
  public void testSplitSharpen() {
    int[][][] origMat = new int[3][10][15];
    int[][][] expMat = new int[3][10][15];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 15; k++) {
          origMat[0][j][k] = j * k + 10;
          origMat[1][j][k] = j * k + 25;
          origMat[2][j][k] = j * k + 40;
          expMat[0][j][k] = j * k + 10;
          expMat[1][j][k] = j * k + 25;
          expMat[2][j][k] = j * k + 40;
        }
      }
    }

    gallery.add("image1", origMat);
    gallery.sharpen("image1", "image1", 100);

    double[][] sharpenKernel = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, -0.125, -0.125, -0.125, -0.125}};

    for (int k = 0; k < 3; k++) {
      for (int i = 1; i < 10; i++) {
        for (int j = 1; j < 15; j++) {
          double sum = 0.0;
          for (int u = 0; u < sharpenKernel.length; u++) {
            for (int v = 0; v < sharpenKernel.length; v++) {
              int ni = i + u - sharpenKernel.length / 2;
              int nj = j + v - sharpenKernel.length / 2;
              if (ni >= 0 && ni < 10 && nj >= 0 && nj < 15) {
                sum += ((double) expMat[k][ni][nj] * sharpenKernel[u][v]);
              }
            }
          }
          expMat[k][i][j] = Math.max(0, (int) Math.min(sum, 255));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 15; k++) {
          assertEquals(expMat[i][j][k], gallery.look("image1")[j][k][i]);
        }
      }
    }
  }

  /**
   * Tests value at splitting percentages of 0, 50, and 100 percent. toSepia() is performed on the
   * image and that result is compared to a 3D int array of the expected sepia.
   */
  @Test
  public void testSplitSepia() {
    int[][][] origMat = new int[3][10][16];
    int[][][] expSepia1 = new int[3][10][16];
    int[][][] expSepia2 = new int[3][10][16];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 16; k++) {
          origMat[i][j][k] = j * k + 15;
        }
      }
    }

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 16; j++) {
        int redValue =
            (int) (0.393 * origMat[0][i][j] + 0.769 * origMat[1][i][j]
                + 0.189 * origMat[2][i][j]);
        int greenValue =
            (int) (0.349 * origMat[0][i][j] + 0.686 * origMat[1][i][j]
                + 0.168 * origMat[2][i][j]);
        int blueValue = (int) (0.272 * origMat[0][i][j] + 0.534 * origMat[1][i][j]
            + 0.131 * origMat[2][i][j]);
        expSepia1[0][i][j] = redValue;
        expSepia1[1][i][j] = greenValue;
        expSepia1[2][i][j] = blueValue;
        if (j < 8) {
          expSepia2[0][i][j] = redValue;
          expSepia2[1][i][j] = greenValue;
          expSepia2[2][i][j] = blueValue;
        }
      }
      for (int k = 8; k < 16; k++) {
        expSepia2[0][i][k] = origMat[0][i][k];
        expSepia2[1][i][k] = origMat[1][i][k];
        expSepia2[2][i][k] = origMat[2][i][k];
      }
    }

    gallery.add("expsepia3", origMat);
    gallery.add("image1", origMat);
    gallery.sepia("image1", "image2", 100.0);
    gallery.add("image3", origMat);
    gallery.sepia("image3", "image4", 50);
    gallery.add("image5", origMat);
    gallery.sepia("image5", "image6", 0);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 16; k++) {
          assertEquals(expSepia1[i][j][k], gallery.look("image2")[j][k][i]);
          assertEquals(expSepia2[i][j][k], gallery.look("image4")[j][k][i]);
          assertEquals(gallery.look("expsepia3")[j][k][i],
              gallery.look("image6")[j][k][i]);
        }
      }
    }
  }

  /**
   * Test for split color correction.
   */
  @Test
  public void testSplitColorCorrection() {
    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("colorCorrectionTest", mat1);
    gallery.colorCorrection("colorCorrectionTest", "ccTest", 20);
    assertEquals(13, gallery.look("ccTest")[0][0][0]);
    assertEquals(21, gallery.look("ccTest")[0][0][1]);
    assertEquals(14, gallery.look("ccTest")[0][0][2]);
    assertEquals(7, gallery.look("ccTest")[0][1][0]);
    assertEquals(9, gallery.look("ccTest")[0][1][1]);
    assertEquals(0, gallery.look("ccTest")[0][1][2]);
    assertEquals(0, gallery.look("ccTest")[0][2][0]);
    assertEquals(0, gallery.look("ccTest")[0][2][1]);
    assertEquals(0, gallery.look("ccTest")[0][2][2]);
    assertEquals(10, gallery.look("ccTest")[1][0][0]);
    assertEquals(15, gallery.look("ccTest")[1][0][1]);
    assertEquals(5, gallery.look("ccTest")[1][0][2]);
    assertEquals(17, gallery.look("ccTest")[1][1][0]);
    assertEquals(29, gallery.look("ccTest")[1][1][1]);
    assertEquals(26, gallery.look("ccTest")[1][1][2]);
    assertEquals(31, gallery.look("ccTest")[1][2][0]);
    assertEquals(62, gallery.look("ccTest")[1][2][1]);
    assertEquals(93, gallery.look("ccTest")[1][2][2]);
  }

  @Test
  public void testSplitLevelAdjustment() {
    int[][][] mat1 = new int[3][2][9];
    for (int i = 0; i < 3; i++) {
      mat1[i][0][0] = (i + 1) * 8;
      mat1[i][0][1] = (i + 1) * 2;
      mat1[i][0][2] = 0;
      mat1[i][0][3] = (i + 1) * 7;
      mat1[i][0][4] = (i + 1) * 5;
      mat1[i][0][5] = (i + 1) * 6;
      mat1[i][0][6] = (i + 1) * 4;
      mat1[i][0][7] = i + 1;
      mat1[i][0][8] = (i + 1) * 9;

      mat1[i][1][0] = (i + 1) * 5;
      mat1[i][1][1] = (i + 1) * 12;
      mat1[i][1][2] = (i + 1) * 31;
      mat1[i][1][3] = (i + 1) * 13;
      mat1[i][1][4] = (i + 1) * 20;
      mat1[i][1][5] = (i + 1) * 16;
      mat1[i][1][6] = (i + 1) * 26;
      mat1[i][1][7] = (i + 1) * 21;
      mat1[i][1][8] = (i + 1) * 15;
    }
    gallery.add("histTest", mat1);
    gallery.levelAdjust(20, 128, 255, "histTest", "histogram", 25);
    assertEquals(0, gallery.look("histogram")[0][0][0]);
    assertEquals(0, gallery.look("histogram")[0][0][1]);
    assertEquals(5, gallery.look("histogram")[0][0][2]);
    assertEquals(0, gallery.look("histogram")[0][1][0]);
    assertEquals(0, gallery.look("histogram")[0][1][1]);
    assertEquals(0, gallery.look("histogram")[0][1][2]);
    assertEquals(0, gallery.look("histogram")[0][2][0]);
    assertEquals(0, gallery.look("histogram")[0][2][1]);
    assertEquals(0, gallery.look("histogram")[0][2][2]);
    assertEquals(0, gallery.look("histogram")[1][0][0]);
    assertEquals(0, gallery.look("histogram")[1][0][1]);
    assertEquals(0, gallery.look("histogram")[1][0][2]);
    assertEquals(0, gallery.look("histogram")[1][1][0]);
    assertEquals(5, gallery.look("histogram")[1][1][1]);
    assertEquals(20, gallery.look("histogram")[1][1][2]);
    assertEquals(31, gallery.look("histogram")[1][2][0]);
    assertEquals(62, gallery.look("histogram")[1][2][1]);
    assertEquals(93, gallery.look("histogram")[1][2][2]);
  }

  /**
   * Tests luma at splitting percentages of 0, 50, and 100 percent. visualizeLuma() is performed on
   * the image and that result is compared to a 3D int array of the expected luma.
   */
  @Test
  public void testSplitLuma() {
    int[][][] origMat = new int[3][18][12];
    int[][][] expLuma1 = new int[3][18][12];
    int[][][] expLuma2 = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;
      }
    }

    for (int i = 0; i < 18; i++) {
      for (int j = 0; j < 12; j++) {
        if (j < 6) {
          expLuma1[0][i][j] = Math.max(0, Math.min(255,
              (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + (
                  (0.0722) * origMat[2][i][j]))));
          expLuma1[1][i][j] = Math.max(0, Math.min(255,
              (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + (
                  (0.0722) * origMat[2][i][j]))));
          expLuma1[2][i][j] = Math.max(0, Math.min(255,
              (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + (
                  (0.0722) * origMat[2][i][j]))));
        }
        expLuma2[0][i][j] = Math.max(0, Math.min(255,
            (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + (
                (0.0722) * origMat[2][i][j]))));
        expLuma2[1][i][j] = Math.max(0, Math.min(255,
            (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + (
                (0.0722) * origMat[2][i][j]))));
        expLuma2[2][i][j] = Math.max(0, Math.min(255,
            (int) (((0.2126) * origMat[0][i][j]) + ((0.7152) * origMat[1][i][j]) + (
                (0.0722) * origMat[2][i][j]))));
      }
      for (int k = 6; k < 12; k++) {
        expLuma1[0][i][k] = origMat[0][i][k];
        expLuma1[1][i][k] = origMat[1][i][k];
        expLuma1[2][i][k] = origMat[2][i][k];
      }
    }

    gallery.add("expluma1", expLuma1);
    gallery.add("expluma2", expLuma2);
    gallery.add("expluma3", origMat);

    gallery.add("image1", origMat);
    gallery.lumaComponent("image1", "image2", 50.0);
    gallery.add("image3", origMat);
    gallery.lumaComponent("image3", "image4", 100);
    gallery.add("image5", origMat);
    gallery.lumaComponent("image5", "image6", 0);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          assertEquals(gallery.look("expluma1")[j][k][i],
              gallery.look("image2")[j][k][i]);
          assertEquals(gallery.look("expluma2")[j][k][i],
              gallery.look("image4")[j][k][i]);
          assertEquals(gallery.look("expluma3")[j][k][i],
              gallery.look("image6")[j][k][i]);
        }
      }
    }
  }

  /**
   * Tests intensity at splitting percentages of 0, 50, and 100 percent. visualizeIntensity() is
   * performed on the image and that result is compared to a 3D int array of the expected
   * intensity.
   */
  @Test
  public void testSplitIntensity() {
    int[][][] origMat = new int[3][18][12];
    int[][][] expIntensity1 = new int[3][18][12];
    int[][][] expIntensity2 = new int[3][18][12];

    for (int j = 0; j < 18; j++) {
      for (int k = 0; k < 12; k++) {
        origMat[0][j][k] = j * k + 8;
        origMat[1][j][k] = j * k + 4;
        origMat[2][j][k] = j * k + 2;
      }
    }

    for (int i = 0; i < 18; i++) {
      for (int j = 0; j < 12; j++) {
        int val = Math.max(0, Math.min(255, (int) ((origMat[0][i][j] + origMat[1][i][j]
            + origMat[2][i][j]) / 3.0)));
        expIntensity1[0][i][j] = val;
        expIntensity1[1][i][j] = val;
        expIntensity1[2][i][j] = val;
        if (j < 6) {
          expIntensity2[0][i][j] = val;
          expIntensity2[1][i][j] = val;
          expIntensity2[2][i][j] = val;
        }
      }
      for (int k = 6; k < 12; k++) {
        expIntensity2[0][i][k] = origMat[0][i][k];
        expIntensity2[1][i][k] = origMat[1][i][k];
        expIntensity2[2][i][k] = origMat[2][i][k];
      }
    }

    gallery.add("expintensity3", origMat);
    gallery.add("image1", origMat);
    gallery.intensityComponent("image1", "image2", 100);
    gallery.add("image3", origMat);
    gallery.intensityComponent("image3", "image4", 50);
    gallery.add("image5", origMat);
    gallery.intensityComponent("image5", "image6", 0.0);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          assertEquals(expIntensity1[i][j][k], gallery.look("image2")[j][k][i]);
          assertEquals(expIntensity2[i][j][k], gallery.look("image4")[j][k][i]);
          assertEquals(gallery.look("expintensity3")[j][k][i],
              gallery.look("image6")[j][k][i]);
        }
      }
    }
  }

  /**
   * Tests value at splitting percentages of 0, 50, and 100 percent. visualizeValue() is performed
   * on the image and that result is compared to a 3D int array of the expected value.
   */
  @Test
  public void testSplitValue() {
    int[][][] origMat = new int[3][18][12];
    int[][][] expValue1 = new int[3][18][12];
    int[][][] expValue2 = new int[3][18][12];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          origMat[i][j][k] = (i + 1) * j + k;
          expValue1[i][j][k] = 3 * j + k;
          if (k < 6) {
            expValue2[i][j][k] = 3 * j + k;
          }
        }
        for (int l = 6; l < 12; l++) {
          expValue2[i][j][l] = origMat[i][j][l];
        }
      }
    }

    gallery.add("expvalue3", origMat);
    gallery.add("image1", origMat);
    gallery.valueComponent("image1", "image2", 100.0);
    gallery.add("image3", origMat);
    gallery.valueComponent("image3", "image4", 50);
    gallery.add("image5", origMat);
    gallery.valueComponent("image5", "image6", 0);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        for (int k = 0; k < 12; k++) {
          assertEquals(expValue1[i][j][k], gallery.look("image2")[j][k][i]);
          assertEquals(expValue2[i][j][k], gallery.look("image4")[j][k][i]);
          assertEquals(gallery.look("expvalue3")[j][k][i],
              gallery.look("image6")[j][k][i]);
        }
      }
    }
  }


  @Test
  public void testMultipleOperations() {
    int[][][] origMat = new int[3][3][5];
    int[][][] expMat = new int[3][3][5];

    for (int j = 0; j < 3; j++) {
      for (int k = 0; k < 4; k++) {
        origMat[0][j][k] = j * k + 15;
        origMat[1][j][k] = j * k + 35;
        origMat[2][j][k] = j * k + 55;
      }
    }

    double[][] blurKernel = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    for (int i = 0; i < 3; i++) {
      double value1 = blurKernel[0][0] * origMat[i][0][0] + blurKernel[0][1] * origMat[i][0][1]
          + blurKernel[0][2] * origMat[i][0][2] + blurKernel[1][0] * origMat[i][1][0]
          + blurKernel[1][1] * origMat[i][1][1] + blurKernel[1][2] * origMat[i][1][2]
          + blurKernel[2][0] * origMat[i][2][0] + blurKernel[2][1] * origMat[i][2][1]
          + blurKernel[2][2] * origMat[i][2][2];
      double value2 = blurKernel[0][0] * origMat[i][0][1] + blurKernel[0][1] * origMat[i][0][2]
          + blurKernel[0][2] * origMat[i][0][3] + blurKernel[1][0] * origMat[i][1][1]
          + blurKernel[1][1] * origMat[i][1][2] + blurKernel[1][2] * origMat[i][1][3]
          + blurKernel[2][0] * origMat[i][2][1] + blurKernel[2][1] * origMat[i][2][2]
          + blurKernel[2][2] * origMat[i][2][3];
      double value3 = blurKernel[0][0] * origMat[i][0][2] + blurKernel[0][1] * origMat[i][0][3]
          + blurKernel[0][2] * origMat[i][0][4] + blurKernel[1][0] * origMat[i][1][2]
          + blurKernel[1][1] * origMat[i][1][3] + blurKernel[1][2] * origMat[i][1][4]
          + blurKernel[2][0] * origMat[i][2][2] + blurKernel[2][1] * origMat[i][2][3]
          + blurKernel[2][2] * origMat[i][2][4];
      expMat[i][1][1] = Math.max(0, Math.min(255, (int) value1));
      expMat[i][1][2] = Math.max(0, Math.min(255, (int) value2));
      expMat[i][1][3] = Math.max(0, Math.min(255, (int) value3));
    }

    gallery.add("image1", origMat);
    gallery.blur("image1", "image1-blur", 100);

    for (int i = 0; i < 3; i++) {
      assertEquals(expMat[i][1][1], gallery.look("image1-blur")[1][1][i]);
      assertEquals(expMat[i][1][2], gallery.look("image1-blur")[1][2][i]);
      assertEquals(expMat[i][1][3], gallery.look("image1-blur")[1][3][i]);
    }

    gallery.brighten("image1-blur", "image1-blur-brighten", -5);
    for (int i = 0; i < 3; i++) {
      assertEquals(expMat[i][1][1] - 5, gallery.look("image1-blur-brighten")[1][1][i]);
      assertEquals(expMat[i][1][2] - 5, gallery.look("image1-blur-brighten")[1][2][i]);
      assertEquals(expMat[i][1][3] - 5, gallery.look("image1-blur-brighten")[1][3][i]);
    }

  }


}

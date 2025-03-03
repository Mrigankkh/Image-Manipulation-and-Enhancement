import static org.junit.Assert.assertEquals;

import controller.ImageController;
import java.io.Reader;
import java.io.StringReader;
import model.ImageModel;
import org.junit.Before;
import org.junit.Test;
import view.ImageView;

/**
 * Tests for the Image Controller.
 */
public class ImageControllerTest {


  StringBuffer out;
  Reader in;

  @Before
  public void setup() {

    out = new StringBuffer();
    in = new StringReader(" ");

  }

  @Test
  public void testSepia() {
    in = new StringReader("sepia image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Sepia : image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments or"
            + " file path provided is invalid.Enter a command: Operation sepia complete.",
        outLog.toString());

  }


  @Test
  public void testVerticalFlip() {
    in = new StringReader("vertical-flip image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Vertical Flip : image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments"
            + " or file path provided is invalid.Enter a command: Operation "
            + "vertical-flip complete.",
        outLog.toString());

  }

  @Test
  public void testHorizontalFlip() {
    in = new StringReader("horizontal-flip image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Horizontal flip : image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command: Operation horizontal-flip "
            + "complete.",
        outLog.toString());

  }


  @Test
  public void testBrighten() {
    in = new StringReader("brighten image1 image2 10");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Brighten: image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command: Operation brighten "
            + "complete.",
        outLog.toString());

  }

  @Test
  public void testBrightenIllegal() {
    in = new StringReader("brighten image1 image2 ");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals(
        "No valid file path provided in arguments"
            + " or file path provided is invalid.Enter a command: Error in command command "
            + "Expected [3] but found 2\n",
        outLog.toString());
  }

  @Test
  public void testBrightenIllegal2() {
    in = new StringReader("brighten image1 image2 ten");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid."
            + "Enter a command: The brighten-by "
            + "value "
            + "should be an integer.",
        outLog.toString());
  }

  @Test
  public void testBlur() {
    in = new StringReader("blur image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Blur : image1 image2", log.toString());
    assertEquals("No valid file path provided in "
            + "arguments or file path provided is invalid.Enter a command: Operation blur "
            + "complete.",
        outLog.toString());
  }

  @Test
  public void testSharpen() {
    in = new StringReader("sharpen image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Sharpen : image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid.Enter"
            + " a command: Operation sharpen "
            + "complete.",
        outLog.toString());

  }

  @Test
  public void illegalSharpen() {
    in = new StringReader("sharpen  image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid."
            + "Enter a command: Error in command sharpen Expected [2, 4] but found 1\n",
        outLog.toString());

  }


  @Test
  public void testNoCommand() {
    in = new StringReader("");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());

  }

  @Test
  public void testRGBSplit() {
    in = new StringReader("rgb-split image a b c");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("RGB Split : image a c b", log.toString());
    assertEquals("No valid file path provided in arguments or file path provided is"
            + " invalid.Enter a command: Operation rgb-split complete.",
        outLog.toString());

  }

  @Test
  public void testRGBCombine() {
    in = new StringReader("rgb-combine image a b c");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("RGB Combine : image a c b", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command: Operation "
            + "rgb-combine complete.",
        outLog.toString());

  }

  @Test
  public void testLuma() {
    in = new StringReader("luma-component image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Luma component : image a", log.toString());
    assertEquals(
        "No valid file path provided in arguments or "
            + "file path provided is invalid.Enter a command: Operation luma-component complete.",
        outLog.toString());

  }

  @Test
  public void testIntensity() {
    in = new StringReader("intensity-component image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Intensity component : image a", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command: Operation"
            + " intensity-component complete.",
        outLog.toString());

  }

  @Test
  public void testValue() {
    in = new StringReader("value-component image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Value component image a", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command:"
            + " Operation value-component complete.",
        outLog.toString());

  }


  @Test
  public void testColorComponentR() {
    in = new StringReader("red-component image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Colorcomponent : image  a 0", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is"
            + " invalid.Enter a command: "
            + "Operation red-component complete.",
        outLog.toString());

  }

  @Test
  public void testColorComponentG() {
    in = new StringReader("green-component image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Colorcomponent : image  a 1", log.toString());
    assertEquals(
        "No valid file path provided in ar"
            + "guments or file path provided is invalid.Enter a command: Operation"
            + " green-component complete.",
        outLog.toString());

  }

  @Test
  public void testColorComponentB() {
    in = new StringReader("blue-component image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Colorcomponent : image  a 2", log.toString());
    assertEquals(
        "No valid file path provided in arguments"
            + " or file path provided is invalid.Enter a command: Operation blue-component "
            + "complete.",
        outLog.toString());

  }


  @Test
  public void testFileNotFoundRun() {
    in = new StringReader("run file.txt");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid."
            + "Enter a command:"
            + " The file at path file.txt cannot be found.Operation run complete.",
        outLog.toString());
  }

  @Test
  public void testRunIllegal() {
    in = new StringReader("run ");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is"
            + " invalid.Enter a command: Run expected "
            + "1 argument; found 0",
        outLog.toString());

  }

  @Test
  public void testLoad() {
    in = new StringReader(
        "load /Users/mrigank/College/CS5010/Assignments/pdp-assignment5/res/Road.j"
            + "pg testingLoad");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Adding testingLoad\n", log.toString());
    assertEquals(
        "No valid file path provided in arguments or f"
            + "ile path provided is invalid.Enter a command: Operation load complete.",
        outLog.toString());
  }

  @Test
  public void testLoadPPM() {
    in = new StringReader("loa"
        + "d /Users/mrigank/College/CS5010/Assignments/pdp-assignment5/res/Road.ppm");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Adding testingLoad\n", log.toString());
    assertEquals(
        "No valid file path provided in arguments or f"
            + "ile path provided is invalid.Enter a command: Operation load complete.",
        outLog.toString());
  }

  @Test
  public void testLoadJPG() {
    in = new StringReader("load image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid.Enter a"
            + " command: Incorrect image "
            + "format provided. Program only supports image formats of"
            + " type png, jpg, ppm and jpeg. \n",
        outLog.toString());
  }


  @Test
  public void testSave() {
    in = new StringReader("save image a.jpg");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Getting a", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid."
            + "Enter a command: java.lang.IllegalArgumentException: "
            + "No image exists with an image name a",
        outLog.toString());

  }

  @Test
  public void testSaveWithoutExtension() {
    in = new StringReader("save image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid.Enter "
            + "a "
            + "command"
            + ": Incorrect image format provided. Program only supports image formats"
            + " of type png, jpg, ppm and jpeg. \n",
        outLog.toString());

  }

  @Test
  public void testSavePPM() {
    in = new StringReader("save image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command"
            + ": Incorrect image format provided. Program only supports image formats"
            + " of type png, jpg, ppm and jpeg. \n",
        outLog.toString());

  }

  @Test
  public void testSaveJPG() {
    in = new StringReader("save image a.jpg");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Getting a", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is invalid.Enter"
            + " a command"
            + ": Incorrect image format provided. Program only supports image formats"
            + " of type png, jpg, ppm and jpeg. \n",
        outLog.toString());

  }

  @Test
  public void testSaveIllegalFileFormat() {
    in = new StringReader("save image a");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is"
            + " invalid.Enter a command"
            + ": Incorrect image format provided. Program only supports image formats"
            + " of type png, jpg, ppm and jpeg. \n",
        outLog.toString());

  }

  @Test
  public void testLoadIllegal() {
    in = new StringReader("load image a.txt");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is "
            + "invalid.Enter a command: Incorrect image format"
            + " provided. Program only supports image formats of type png, jpg, ppm and jpeg. \n",
        outLog.toString());
  }

  @Test
  public void testRunIllegalFilePath() {
    in = new StringReader("run ./does/not/exist/as5.txt");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided is"
            + " invalid.Enter a command: The file at path "
            + "./does/not/exist/as5.txt cannot be found.Operation run complete.",
        outLog.toString());

  }

  @Test
  public void testRun() {
    in = new StringReader("run ./as5.txt");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Adding man\n"
        + "colorCorrection: man manhGetting manh", log.toString());
    assertEquals(
        "No valid file path provided in arguments or file path provided "
            + "is invalid.Enter a command: "
            + "Operation load complete.Operation color-correct complete.java."
            + "lang.IllegalArgumentException: No image exists with an image name manhOperation"
            + " run complete.",
        outLog.toString());

  }

  @Test
  public void testHistogram() {
    in = new StringReader("histogram image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Histogram: image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments or"
            + " file path provided is invalid.Enter a command: Operation histogram complete.",
        outLog.toString());
  }

  @Test
  public void testLevelAdjust() {
    in = new StringReader("level-adjust 10 20 40 image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("LevelAdjust: image1 image2 10 20 40\n", log.toString());
    assertEquals(
        "No valid file path provided in arguments or"
            + " file path provided is invalid.Enter a command: Operation level-adjust complete.",
        outLog.toString());
  }

  /**
   * Test if color-correction command is being handled correctly.
   */
  @Test
  public void testColorCorrection() {
    in = new StringReader("color-correct image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("colorCorrection: image1 image2", log.toString());
    assertEquals(
        "No valid file path provided in arguments or"
            + " file path provided is invalid.Enter a command: Operation color-correct complete.",
        outLog.toString());
  }

  /**
   * Test if compression command is being handled correctly.
   */
  @Test
  public void testCompression() {
    in = new StringReader("compress 50 image1 image2");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Compressing image1 image2 50.0\n", log.toString());
    assertEquals(
        "No valid file path provided in arguments or"
            + " file path provided is invalid.Enter a command: Operation compress complete.",
        outLog.toString());
  }

  /**
   * Test luma when a split percent is provided.
   */
  @Test
  public void testLumaSplitPercent() {
    in = new StringReader("luma-component image a split 45");
    out = new StringBuffer();
    String[] args = {};
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageView mockView = new MockView(outLog);
    ImageController controller = new ImageController(in, out, mockView, mockModel, args);
    controller.run();
    assertEquals("Luma component : image a", log.toString());
    assertEquals(
        "No valid file path provided in arguments or "
            + "file path provided is invalid.Enter a command: Operation luma-component complete.",
        outLog.toString());

  }


}

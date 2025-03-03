import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.ImageControllerGUI;
import controller.menuitemclickhandlers.HandleColorComponent;
import controller.menuitemclickhandlers.HandleCompress;
import controller.menuitemclickhandlers.HandleHorizontalFlip;
import controller.menuitemclickhandlers.HandleLoad;
import controller.menuitemclickhandlers.HandleSave;
import controller.menuitemclickhandlers.HandleVerticalFlip;
import java.io.Reader;
import java.util.SortedMap;
import java.util.TreeMap;
import model.ImageModel;
import org.junit.Before;
import org.junit.Test;
import view.ImageViewG;

/**
 * Tests for the Image Controller for the graphical user interface.
 */
public class ImageControllerGUITest {

  StringBuffer out;
  Reader in;

  SortedMap<String, String[]> menu;


  //Model testing
  @Before
  public void setup() {

    menu = new TreeMap<>();
    menu.put("Level Adjust",
        new String[]{"Level Adjust"});
    menu.put("Filters",
        new String[]{"Blur", "Sharpen", "Brighten", "Sepia", "Color Correct"});
    menu.put("Flip",
        new String[]{"Horizontal", "Vertical"});
    menu.put("Visualize",
        new String[]{"Intensity", "Luma", "Value", "R-Component", "G-Component", "B-Component"});
    menu.put("File",
        new String[]{"Load", "Compress", "Save"});
  }

  @Test
  public void testControllerStart() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.run();
    assertEquals("Adding features", outLog.toString());
  }

  @Test
  public void testHandlePreviewConfirmed() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Sepia", new String[]{"one", "two"});
    assertEquals("Preview to be closed.Changing image and its histogram", outLog.toString());
  }

  @Test(expected = RuntimeException.class)
  public void testHandlePreviewConfirmedIllegalOperation() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("SepiaIllegal", new String[]{"one", "two"});
    assertEquals("Preview to be closed.Changing image and its histogram", outLog.toString());
  }

  @Test(expected = Exception.class)
  public void testHandlePreviewConfirmedIllegalArgument() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Sepia", new String[]{"one", "two", "illegal"});
    assertEquals("Preview to be closed.Changing image and its histogram", outLog.toString());
  }

  @Test
  public void testFileInputOpenedWhenLoaded() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);

    controller.handleMenuItemClick("Load");

    assertEquals("Adding featuresOpened File selectorError is thrown", outLog.toString());
  }

  @Test
  public void testMenuItemClickHandler() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handleMenuItemClick("VerticalFlip");
    assertEquals("Error is thrown", outLog.toString());

  }


  @Test
  public void testPreviewSlider() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewSlider("Sepia", 50, new String[]{});
    assertEquals("Preview image to be changed", outLog.toString());
  }

  @Test
  public void testPreviewSliderIllegalOperation() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewSlider("BrightenIllegal", 500, new String[]{});
    assertEquals("Error is thrown", outLog.toString());
  }

  @Test
  public void testCheckIfImageSaved() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);

    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    assertTrue(controller.isImageIsSaved());
  }

  @Test
  public void testSepia() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Sepia", new String[]{"imageName",
        "newImageName"});
    assertEquals(
        "Sepia : imageName newImageNameHistogram: _Sepia _Sepia_histogramL"
            + "ooking _SepiaGetting _Sepia_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());

  }


  @Test
  public void testVerticalFlip() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    HandleVerticalFlip compress = new HandleVerticalFlip();
    compress.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Vertical Flip : test testHistogram: test "
            + "test_histogramLooking testGetting test_histogram",
        log.toString());
    assertEquals(
        "Changing image and its histogram",
        outLog.toString());
  }

  @Test
  public void testHorizontalFlip() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    HandleHorizontalFlip compress = new HandleHorizontalFlip();
    compress.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Horizontal flip : test testHistogram:"
            + " test test_histogramLooking testGetting test_histogram",
        log.toString());
    assertEquals(
        "Changing image and its histogram",
        outLog.toString());

  }


  @Test
  public void testBrighten() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Brighten", new String[]{"imageName",
        "newImageName", "10"});
    assertEquals(
        "Brighten : imageName newImageNameHistogram: _Sepia "
            + "_Sepia_histogramLooking _SepiaGetting _Sepia_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());

  }


  @Test
  public void testBlur() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Blur", new String[]{"imageName", "newImageName"});
    assertEquals(
        "Blur : imageName newImageNameHistogram: _Blur "
            + "_Blur_histogramLooking _BlurGetting _Blur_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());
  }

  @Test
  public void testSharpen() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Sharpen", new String[]{"imageName", "newImageName"});
    assertEquals(
        "Sharpen : imageName newImageNameHistogram: _Sharpen _Sharpen_histogramLooking"
            + " _SharpenGetting _Sharpen_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());
  }


  @Test
  public void testLuma() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Luma", new String[]{"imageName", "newImageName"});
    assertEquals(
        "Luma component : imageName newImag"
            + "eNameHistogram: _Luma _Luma_histogramLooking _LumaGetting _Luma_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());

  }

  @Test
  public void testIntensity() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Intensity", new String[]{"imageName", "newImageName"});
    assertEquals(
        "Intensity component : imageName newImageNameHistogram: "
            + "_Intensity _Intensity_histogramLooking _IntensityGetting _Intensity_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());
  }

  @Test
  public void testValue() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Value", new String[]{"imageName",
        "newImageName"});
    assertEquals(
        "Value component imageName newImageNameHistogram: _Value "
            + "_Value_histogramLooking _ValueGetting _Value_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());
  }

  @Test
  public void testColorComponentR() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleColorComponent save = new HandleColorComponent(0);
    mockView.setFileSaverFormat(".txt");
    save.handle(mockView, mockModel, "test", false);
    assertEquals(
        "Colorcomponent : test  test_colorComponent 0Histogram: "
            + "test_colorComponent test_colorComponent_histogramLooking test_colorComponentGetting"
            + " test_colorComponent_histogram",
        log.toString());
    assertEquals(
        "Changing image and its histogram",
        outLog.toString());


  }

  @Test
  public void testColorComponentG() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleColorComponent save = new HandleColorComponent(1);
    mockView.setFileSaverFormat(".txt");
    save.handle(mockView, mockModel, "test", false);
    assertEquals(
        "Colorcomponent : test  test_colorComponent 1Histogram: "
            + "test_colorComponent test_colorComponent_histogramLooking test_colorComponentGetting"
            + " test_colorComponent_histogram",
        log.toString());
    assertEquals(
        "Changing image and its histogram",
        outLog.toString());
  }

  //
  @Test
  public void testColorComponentB() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleColorComponent save = new HandleColorComponent(2);
    mockView.setFileSaverFormat(".txt");
    save.handle(mockView, mockModel, "test", false);
    assertEquals(
        "Colorcomponent : test  test_colorComponent 2Histogram: "
            + "test_colorComponent test_colorComponent_histogramLooking test_colorComponentGetting"
            + " test_colorComponent_histogram",
        log.toString());
    assertEquals(
        "Changing image and its histogram",
        outLog.toString());


  }

  @Test
  public void testLoad() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleLoad save = new HandleLoad();
    mockView.setFileSaverFormat(".jpg");
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testLoadIllegal() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleLoad save = new HandleLoad();
    mockView.setFileSaverFormat(".txt");
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testLoadWithoutSave() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleLoad save = new HandleLoad();
    mockView.setFileSaverFormat(".txt");
    save.handle(mockView, mockModel, "test", false);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testLoadPPM() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleLoad save = new HandleLoad();
    mockView.setFileSaverFormat(".ppm");
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testLoadJPG() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleLoad save = new HandleLoad();
    mockView.setFileSaverFormat(".jpg");
    save.handle(mockView, mockModel, "test", false);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testSave() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    HandleSave save = new HandleSave();
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testSaveIllegalFormat() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleSave save = new HandleSave();
    mockView.setFileSaverFormat(".txt");
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saverError is thrown",
        outLog.toString());
  }

  @Test
  public void testSavePPMFormat() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleSave save = new HandleSave();
    mockView.setFileSaverFormat(".ppm");
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }

  @Test
  public void testSaveJPGFormat() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    MockViewGUI mockView = new MockViewGUI(menu, outLog);
    HandleSave save = new HandleSave();
    mockView.setFileSaverFormat(".jpg");
    save.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Looking test",
        log.toString());
    assertEquals(
        "Opened File saver",
        outLog.toString());
  }


  /**
   * Test if histogram is called successfully when performing an operation.
   */
  @Test
  public void testHistogram() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("Blur", new String[]{"imageName", "newImageName"});
    assertEquals(
        "Blur : imageName newImageNameHistogram: _Blur _Blur_hist"
            + "ogramLooking _BlurGetting _Blur_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());
  }

  @Test
  public void testLevelAdjust() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    HandleCompress compress = new HandleCompress();
    compress.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Compressing test test 50.0\n"
            + "Histogram: test test_histogramLooking testGetting test_histogram",
        log.toString());
    assertEquals(
        "Opened Dialog Box for input of operation Compress with message "
            + "Enter the compress-by value: Changing image and its histogram",
        outLog.toString());
  }

  /**
   * Test if color-correction command is being handled correctly.
   */
  @Test
  public void testColorCorrection() {
    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    ImageControllerGUI controller = new ImageControllerGUI(mockModel, mockView);
    controller.handlePreviewConfirm("ColorCorrect",
        new String[]{"imageName", "newImageName"});
    assertEquals(
        "colorCorrection: imageName newImageNameHistogram:"
            + " _ColorCorrect _ColorCorrect_histogramLooking _ColorCorrectGet"
            + "ting _ColorCorrect_histogram",
        log.toString());
    assertEquals(
        "Preview to be closed.Changing image and its histogram",
        outLog.toString());
  }

  /**
   * Test if compression command is being handled correctly.
   */
  @Test
  public void testCompression() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuilder outLog = new StringBuilder(); //log for mock view
    ImageModel mockModel = new MockModel(log);
    ImageViewG mockView = new MockViewGUI(menu, outLog);
    HandleCompress compress = new HandleCompress();
    compress.handle(mockView, mockModel, "test", true);
    assertEquals(
        "Compressing test test 50.0\n"
            + "Histogram: test test_histogramLooking testGetting test_histogram",
        log.toString());
    assertEquals(
        "Opened Dialog Box for input of operation Compress with message "
            + "Enter the compress-by value: Changing image and its histogram",
        outLog.toString());
  }


}

package controller.menuitemclickhandlers;

import controller.Features;
import model.ImageModel;
import view.ImageViewG;


/**
 * This class extends the menu item click handlers with preview and handles level adjust when level
 * adjust is selected from the menu.
 */
public class HandleLevelAdjust extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleLevelAdjust object.
   *
   * @param features the features passed to this constructor
   */
  public HandleLevelAdjust(Features features) {
    super(features);
    this.operationName = "LevelAdjust";
  }

  /**
   * Handles the click of the level adjust menu item.
   *
   * @param view         the ImageViewGUI view
   * @param model        the ImageModel model
   * @param imageName    the name of the image
   * @param imageIsSaved whether the image is saved or not
   */
  @Override
  public void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved) {
    this.setImageName(imageName);

    if (imageNotLoaded()) {
      throw new RuntimeException("Load an image before performing an operation");
    }
    String[] parameters = view.openDialogBox("Level Adjust",
        "Enter the level adjust values: ");
    if (parameters == null) {
      return;
    }
    int black;
    int mid;
    int white;
    try {
      black = Integer.parseInt(parameters[0]);
      mid = Integer.parseInt(parameters[1]);
      white = Integer.parseInt(parameters[2]);
    } catch (Exception e) {
      view.showError("Black, mid and white by can only take numerical values.");
      return;
    }

    if (!(0 < black && black < mid && mid < white && white < 256)) {
      view.showError(
          "The black, middle and white values must follow the rule: 0< black < middle < "
              + "white < 256");
      return;
    }
    this.openPreview(view, parameters);
    this.setImageName(imageName);
    view.changePreviewImage(arrayToBufferedImage(model.look(imageName)));

  }
}

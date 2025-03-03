package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles color correct when color
 * correct is selected from the menu.
 */
public class HandleColorCorrect extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleColorCorrect object.
   *
   * @param features the features passed to this constructor
   */
  public HandleColorCorrect(Features features) {
    super(features);
    this.operationName = "ColorCorrect";
  }


}
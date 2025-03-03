package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles blur when blur is
 * selected from the menu.
 */
public class HandleBlur extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleBlur object.
   *
   * @param features the features passed to this constructor
   */
  public HandleBlur(Features features) {
    super(features);
    this.operationName = "Blur";
  }

}
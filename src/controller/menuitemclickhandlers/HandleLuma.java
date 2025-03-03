package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles luma when luma is
 * selected from the menu.
 */
public class HandleLuma extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleLuma object.
   *
   * @param features the features passed to this constructor
   */
  public HandleLuma(Features features) {
    super(features);
    this.operationName = "Luma";
  }

}
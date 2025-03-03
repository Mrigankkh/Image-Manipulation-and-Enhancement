package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles sepia when sepia is
 * selected from the menu.
 */
public class HandleSepia extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleSepia object.
   *
   * @param features the features passed to this constructor
   */
  public HandleSepia(Features features) {
    super(features);
    this.operationName = "Sepia";
  }

}
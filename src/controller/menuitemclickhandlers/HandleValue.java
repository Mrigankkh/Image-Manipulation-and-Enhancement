package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles value when value is
 * selected from the menu.
 */
public class HandleValue extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleValue object.
   *
   * @param features the features passed to this constructor
   */
  public HandleValue(Features features) {
    super(features);
    this.operationName = "Value";
  }


}
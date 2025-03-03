package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles sharpen when sharpen is
 * selected from the menu.
 */
public class HandleSharpen extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleSharpen object.
   *
   * @param features the features passed to this constructor
   */
  public HandleSharpen(Features features) {
    super(features);
    this.operationName = "Sharpen";
  }

}
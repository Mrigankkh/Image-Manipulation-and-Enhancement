package controller.menuitemclickhandlers;

import controller.Features;

/**
 * This class extends the menu item click handlers with preview and handles intensity when intensity
 * is selected from the menu.
 */
public class HandleIntensity extends AbstractMenuItemClickHandlersWithPreview {

  /**
   * Constructs a new HandleIntensity object.
   *
   * @param features the features passed to this constructor
   */
  public HandleIntensity(Features features) {
    super(features);
    this.operationName = "Intensity";
  }

}
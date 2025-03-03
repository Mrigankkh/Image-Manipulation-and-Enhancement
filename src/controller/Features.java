package controller;

/**
 * This interface handles executing the given command, operations when a menu item is clicked, and
 * generating the preview.
 */
public interface Features {

  /**
   * Execute a command on the given image.
   *
   * @param operationParameters are the operation parameters required to execute that command.
   */
  void handlePreviewConfirm(String operationName, String[] operationParameters);

  /**
   * Perform the necesary operations when an item in the menu is clicked.
   *
   * @param menuItem name of the item that is clicked.
   */
  void handleMenuItemClick(String menuItem);

  /**
   * This method handles generating the image in the preview of the operation.
   * @param operationName the operation to be performed
   * @param splitPercent the percent that the image should be split
   * @param arguments the arguments that come with the operation
   */
  void handlePreviewSlider(String operationName, double splitPercent, String[] arguments);

}

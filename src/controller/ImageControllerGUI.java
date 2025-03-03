package controller;

import controller.commands.Command;
import controller.menuitemclickhandlers.MenuItemClickHandler;
import controller.menuitemclickhandlers.HandleBlur;
import controller.menuitemclickhandlers.HandleBrighten;
import controller.menuitemclickhandlers.HandleColorComponent;
import controller.menuitemclickhandlers.HandleColorCorrect;
import controller.menuitemclickhandlers.HandleCompress;
import controller.menuitemclickhandlers.HandleHorizontalFlip;
import controller.menuitemclickhandlers.HandleIntensity;
import controller.menuitemclickhandlers.HandleLevelAdjust;
import controller.menuitemclickhandlers.HandleLoad;
import controller.menuitemclickhandlers.HandleLuma;
import controller.menuitemclickhandlers.HandleSave;
import controller.menuitemclickhandlers.HandleSepia;
import controller.menuitemclickhandlers.HandleSharpen;
import controller.menuitemclickhandlers.HandleValue;
import controller.menuitemclickhandlers.HandleVerticalFlip;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import model.ImageModel;
import view.ImageViewG;

/**
 * This class contains the implementations of all the methods in features and provides the view
 * with and instance of features.
 */
public class ImageControllerGUI extends AbstractImageController implements Features {

  //controller is a Features
  private final ImageViewG view;
  private final ImageModel model;
  private String currentImageName;


  private boolean imageIsSaved;

  /**
   * Returns if the image is saved.
   * @return if image is saved
   */
  public boolean isImageIsSaved() {
    return imageIsSaved;
  }

  /**
   * Sets if image is saved.
   * @param imageIsSaved the boolean imageIsSaved
   */
  public void setImageIsSaved(boolean imageIsSaved) {
    this.imageIsSaved = imageIsSaved;
  }


  private final HashMap<String, MenuItemClickHandler> menuItemClickHandlerMap;

  /**
   * Sets the current image same.
   * @param currentImageName the String currentImageName
   */
  public void setCurrentImageName(String currentImageName) {
    this.currentImageName = currentImageName;
  }

  /**
   * Constructs a model and view as well as creates the menuItemClickHandler map and populates it.
   * @param model the ImageModel model
   * @param view the ImageViewGUI view
   */
  public ImageControllerGUI(ImageModel model, ImageViewG view) {
    this.model = model;
    this.view = view;
    this.currentImageName = "";
    this.imageIsSaved = true;
    this.menuItemClickHandlerMap = new HashMap<>();
    menuItemClickHandlerMap.put("Load", new HandleLoad());
    menuItemClickHandlerMap.put("Brighten", new HandleBrighten());
    menuItemClickHandlerMap.put("Blur", new HandleBlur(this));
    menuItemClickHandlerMap.put("Sepia", new HandleSepia(this));
    menuItemClickHandlerMap.put("Sharpen", new HandleSharpen(this));
    menuItemClickHandlerMap.put("Horizontal", new HandleHorizontalFlip());
    menuItemClickHandlerMap.put("Vertical", new HandleVerticalFlip());
    menuItemClickHandlerMap.put("Intensity", new HandleIntensity(this));
    menuItemClickHandlerMap.put("Luma", new HandleLuma(this));
    menuItemClickHandlerMap.put("Value", new HandleValue(this));
    menuItemClickHandlerMap.put("Color Correct", new HandleColorCorrect(this));
    menuItemClickHandlerMap.put("R-Component", new HandleColorComponent(0));
    menuItemClickHandlerMap.put("G-Component", new HandleColorComponent(1));
    menuItemClickHandlerMap.put("B-Component", new HandleColorComponent(2));
    menuItemClickHandlerMap.put("Compress", new HandleCompress());
    menuItemClickHandlerMap.put("Save", new HandleSave());
    menuItemClickHandlerMap.put("Level Adjust", new HandleLevelAdjust(this));
  }

  /**
   * The run method accepts inputs from user either through the keyboard, in the form of a script,
   * or in the GUI, and parses the input and call the respective method in the model to perform
   * an operation if the command is valid else it displays an invalid command message to use or
   * throws an error.
   */
  @Override
  public void run() {

    try {
      view.addFeatures(this);
    } catch (Exception e) {
      view.showError(e.getMessage());
    }
  }


  /**
   * Execute a command on the given image when confirm button is pressed.
   *
   * @param operationName       is the name of the operation to be executed
   * @param operationParameters are the operation parameters required to execute that command.
   */
  @Override
  public void handlePreviewConfirm(String operationName, String[] operationParameters) {
    if (operationParameters == null) {
      operationParameters = new String[0];
    }
    List<String> allParameters = new ArrayList<>(operationParameters.length + 2);
    Collections.addAll(allParameters, operationParameters);
    Collections.addAll(allParameters, this.currentImageName,
        this.currentImageName + "_" + operationName);
    operationParameters = allParameters.toArray(new String[0]);
    HashMap<String, Command> commandMap = new CommandMap(operationParameters).getCommandMap();

    Command command = commandMap.getOrDefault(operationName,
        null);
    if (command != null) {
      command.execute(model);
      this.setCurrentImageName(this.currentImageName + "_" + operationName);

      this.setImageIsSaved(false);
    } else {
      throw new RuntimeException("The menu command " + operationName + " has failed.");
    }
    view.disposePreviewDialog();
    model.histogram(this.currentImageName, this.currentImageName + "_histogram");
    view.changeImage(arrayToBufferedImage(model.look(this.currentImageName)),
        arrayToBufferedImage(model.get(this.currentImageName + "_histogram")));
  }

  /**
   * Perform the necessary operations when an item in the menu is clicked.
   * @param menuItem name of the item that is clicked.
   */
  @Override
  public void handleMenuItemClick(String menuItem) {

    try {
      MenuItemClickHandler menuItemClickHandler = menuItemClickHandlerMap.getOrDefault(menuItem,
          null);
      if (menuItemClickHandler != null) {
        menuItemClickHandler.handle(view, model, this.currentImageName, imageIsSaved);
        this.setCurrentImageName(menuItemClickHandler.getImageName());
        this.setImageIsSaved(menuItemClickHandler.isImageIsSaved());
      } else {
        throw new RuntimeException("The menu command " + menuItem + " has failed.");
      }
      System.out.println(menuItem);
    } catch (Exception e) {
      view.showError(e.getMessage());
    }


  }

  /**
   * This method handles generating the image in the preview of the operation.
   * @param operationName the operation to be performed
   * @param splitPercent the percent that the image should be split
   * @param operationParameters the arguments that come with the operation
   */
  @Override
  public void handlePreviewSlider(String operationName, double splitPercent,
      String[] operationParameters) {
    if (operationParameters == null) {
      operationParameters = new String[0];
    }
    List<String> allParameters = new ArrayList<>(operationParameters.length + 2);
    Collections.addAll(allParameters, operationParameters);
    Collections.addAll(allParameters, this.currentImageName,
        this.currentImageName + "_" + "preview");
    Collections.addAll(allParameters, "split",splitPercent + "");
    operationParameters = allParameters.toArray(new String[0]);
    HashMap<String, Command> commandMap = new CommandMap(operationParameters).getCommandMap();

    try {
      Command cmd = commandMap.getOrDefault(operationName, null);
      if (cmd != null) {
        cmd.execute(model);
        view.changePreviewImage(
            arrayToBufferedImage(model.get(this.currentImageName + "_preview")));


      } else {
        throw new RuntimeException("The menu command " + operationName + " has failed.");
      }
    } catch (Exception e) {
      view.showError(e.getMessage());
    }
  }


}

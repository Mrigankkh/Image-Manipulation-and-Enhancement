package controller.menuitemclickhandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import model.ImageModel;
import view.ImageViewG;

/**
 * This handles the image getting saved when save is selected in the menu.
 */
public class HandleSave extends AbstractMenuItemClickHandler {

  /**
   * Handle a click of a menu item.
   *
   * @param view         ImageViewGUI
   * @param model        ImageModel
   * @param imageName    the image name
   * @param imageIsSaved the boolean imageIsSaved
   */
  @Override
  public void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved) {
    this.setImageName(imageName);

    if (imageNotLoaded()) {
      throw new RuntimeException("Load an image before performing an operation");
    }
    File imageFile = view.openImageSelectionAndSaveImageFile();
    String savePath = imageFile.getAbsolutePath();
    String saveName = imageFile.getName();
    String format = "";
    if (saveName.contains(".")) {
      format = imageFile.getName().substring(1 + imageFile.getName().lastIndexOf('.'));
    }
    if (format.isEmpty()) {
      format = "jpg";
      savePath = savePath + ".jpg";
    }
    if ((!format.isEmpty()) && ((!format.equals("jpg")) && (!format.equals("png"))
        && (!format.equals("ppm")))) {
      view.showError("Only png, ppm and jpg files can be saved");
    }
    // getting whatever is after the '.' in the fileName
    String iName;
    int[][][] imageArray = model.look(imageName);

    if (!format.equals("ppm")) {
      try {
        ImageIO.write(arrayToBufferedImage(imageArray), format, new File(savePath));
      } catch (IOException e) {
        throw new IllegalArgumentException("Unable to save file at the provided location.");
      }
    }
    if (format.equals("ppm")) {
      try {
        int width = imageArray[0].length;
        int height = imageArray.length;
        FileOutputStream fos = new FileOutputStream(savePath);
        fos.write(("P3\n" + width + " " + height + "\n255\n").getBytes());
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            fos.write((imageArray[i][j][0] + " ").getBytes());
            fos.write((imageArray[i][j][1] + " ").getBytes());
            fos.write((imageArray[i][j][2] + " ").getBytes());
          }
          fos.write("\n".getBytes());
        }
        fos.close();
      } catch (FileNotFoundException f) {
        System.out.print("");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    this.setImageIsSaved(true);

  }
}
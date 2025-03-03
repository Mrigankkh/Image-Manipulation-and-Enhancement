package controller.menuitemclickhandlers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.ImageModel;
import view.ImageViewG;

/**
 * This class handles load when load is selected from the menu.
 */
public class HandleLoad extends AbstractMenuItemClickHandler {

  /**
   * Constructs a new HandleLoad object.
   */
  public HandleLoad() {
    super();
    this.setImageName("loading-image");
  }

  /**
   * Handles a click of load.
   *
   * @param view         the ImageViewGUI view
   * @param model        the ImageModel model
   * @param imageName    the name of the image
   * @param imageIsSaved whether the image is saved or not
   */
  @Override
  public void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved) {
    if (!imageIsSaved) {
      String[] parameters = view.openDialogBox("Load",
          "The current image is not saved.");
      if (parameters == null) {
        return;
      }
    }
    File imageFile = view.openImageSelectionAndGetImageFile();
    imageName = imageFile.getName();
    String imageNameNoFormat = imageName.substring(0, imageName.lastIndexOf('.'));
    this.setImageName(imageNameNoFormat);
    String format = "";
    if (imageName.contains(".")) {
      format = imageFile.getName().substring(1 + imageFile.getName().lastIndexOf('.'));
    }
    if (format.isEmpty()) {
      view.showError("Cannot load a file without a file format.");
      return;
    }
    if ((!format.isEmpty()) && ((!format.equals("jpg")) && (!format.equals("png"))
        && (!format.equals("ppm")))) {
      view.showError("Illegal file type to load.");
      return;
    }
    try {
      model.get(imageNameNoFormat);
      model.get(imageNameNoFormat + "_split_view");
    } catch (IllegalArgumentException e) {
      System.out.print("");
    }
    BufferedImage image;

    if (format.equals("ppm")) {
      handlePPM(view, model, format, imageNameNoFormat, imageFile);

    } else {
      try {
        image = threeDimension(ImageIO.read(imageFile));
        model.add(imageNameNoFormat, bufferedImageTo3DArray(image));
        model.add(imageNameNoFormat + "_split_view", bufferedImageTo3DArray(image));
        this.setImageName(imageNameNoFormat);
        model.histogram(imageNameNoFormat, (imageNameNoFormat + "_histogram"));
        view.changeImage(arrayToBufferedImage(model.look(imageNameNoFormat)),
            arrayToBufferedImage(model.get((imageNameNoFormat + "_histogram"))));
      } catch (IOException e) {
        view.showError("File must be an image file.");
      }
    }
  }

  private void handlePPM(ImageViewG view, ImageModel model, String format,
      String imageNameNoFormat, File imageFile) {
    BufferedImage image;
    Scanner sc = null;
    if (format.equals("ppm")) {
      try {
        sc = new Scanner(new FileInputStream(imageFile));
      } catch (FileNotFoundException e) {
        view.showError("File " + imageFile + " not found!");
      }
      StringBuilder builder = new StringBuilder();
      // read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.length() != 0) {
          if (s.charAt(0) != '#') {
            builder.append(s + System.lineSeparator());
          }
        }
      }
      sc = new Scanner(builder.toString());
      String token;

      token = sc.next();
      if (!token.equals("P3")) {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();

      int[][][] imageArray = new int[height][width][3];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          imageArray[i][j][0] = sc.nextInt();
          imageArray[i][j][1] = sc.nextInt();
          imageArray[i][j][2] = sc.nextInt();
        }
      }
      try {
        image = arrayToBufferedImage(imageArray);
        model.add(imageNameNoFormat, bufferedImageTo3DArray(image));
        model.add(imageNameNoFormat + "_split_view", bufferedImageTo3DArray(image));
        this.setImageName(imageNameNoFormat);
        model.histogram(imageNameNoFormat, (imageNameNoFormat + "_histogram"));
        view.changeImage(arrayToBufferedImage(model.look(imageNameNoFormat)),
            arrayToBufferedImage(model.get((imageNameNoFormat + "_histogram"))));
        this.setImageIsSaved(false);
      } catch (IOException e) {
        view.showError("File must be an image file.");
      }

    }

  }

  BufferedImage threeDimension(BufferedImage fourDBufferedImage) {
    BufferedImage threeDImage = new BufferedImage(fourDBufferedImage.getWidth(),
        fourDBufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = threeDImage.createGraphics();
    graphics2D.setColor(Color.WHITE);
    graphics2D.fillRect(0, 0, threeDImage.getWidth(), threeDImage.getHeight());
    graphics2D.drawImage(fourDBufferedImage, 0, 0, null);
    graphics2D.dispose();
    return threeDImage;
  }

}

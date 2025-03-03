import controller.ImageController;
import controller.ImageControllerGUI;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;
import model.Gallery;
import model.ImageModel;
import view.ImageView;
import view.ImageViewCLI;
import view.ImageViewG;
import view.ImageViewGUI;

/**
 * The main class of this program.
 */
public class Main {

  /**
   * The main method of this program.
   *
   * @param args is the file path of the file with commands.
   */
  public static void main(String[] args) {

    SortedMap<String, String[]> menu = new TreeMap<>();
    menu.put("Level Adjust",
        new String[]{"Level Adjust"});
    menu.put("Filters",
        new String[]{"Blur", "Sharpen", "Brighten", "Sepia", "Color Correct"});
    menu.put("Flip",
        new String[]{"Horizontal", "Vertical"});
    menu.put("Visualize",
        new String[]{"Intensity", "Luma", "Value", "R-Component", "G-Component", "B-Component"});
    menu.put("File",
        new String[]{"Load", "Compress", "Save"});
    ImageModel model = new Gallery();
    if (args.length == 0) {
      ImageViewG view = new ImageViewGUI(menu);
      ImageControllerGUI controller = new ImageControllerGUI(model, view);
      controller.run();
    } else if ((args.length == 1 && args[0].equals("-text")) || (args.length == 2 && args[0].equals(
        "-file"))) {
      ImageView view = new ImageViewCLI();
      ImageController controller = new ImageController(new InputStreamReader(System.in), System.out,
          view, model,
          args);
      controller.run();
    } else {
      System.out.println("Invalid arguments to JAR file.");
    }
  }
}
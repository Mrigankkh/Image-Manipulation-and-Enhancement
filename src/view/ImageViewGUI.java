package view;

import controller.Features;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.SortedMap;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import view.inputdialog.BrightenInputDialog;
import view.inputdialog.CompressInputDialog;
import view.inputdialog.InputDialog;
import view.inputdialog.LevelAdjustInputDialog;
import view.inputdialog.LoadInputDialog;

/**
 * An interactive Graphical User Interface for the Image Manipulation and Enhancement Tool that
 * displays the current image being worked on.
 */
public class ImageViewGUI extends JFrame implements ImageViewG {

  private final ImagePanel imagePanel;
  private final NavigationBar navigationBar;
  private final HistogramPanel histogramPanel;
  private PreviewDialog previewDialog;
  private final HashMap<String, InputDialog> inputDialogMap;

  /**
   * Constructs an instance of a Graphical User Interface.
   */
  public ImageViewGUI(SortedMap<String, String[]> menu) {
    super();
    this.setTitle("GRIME: Graphical Image Manipulation and Enhancement");
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridBagLayout());

    this.inputDialogMap = new HashMap<>();
    inputDialogMap.put("Brighten", new BrightenInputDialog());
    inputDialogMap.put("Level Adjust", new LevelAdjustInputDialog());
    inputDialogMap.put("Compress", new CompressInputDialog());
    inputDialogMap.put("Load", new LoadInputDialog());

    GridBagConstraints navigationBarConstraints = new GridBagConstraints();
    navigationBarConstraints.fill = GridBagConstraints.HORIZONTAL;
    navigationBarConstraints.gridx = 0;
    navigationBarConstraints.gridy = 0;
    navigationBarConstraints.weightx = 0.1;
    navigationBarConstraints.weighty = 0.1;
    navigationBarConstraints.gridwidth = GridBagConstraints.REMAINDER;
    navigationBarConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

    this.navigationBar = new NavigationBar(menu);
    this.add(navigationBar, navigationBarConstraints);

    GridBagConstraints imagePanelConstraints = new GridBagConstraints();
    imagePanelConstraints.fill = GridBagConstraints.BOTH;
    imagePanelConstraints.gridy = 1;
    imagePanelConstraints.gridx = 0;
    imagePanelConstraints.weighty = 1;
    imagePanelConstraints.weightx = 0.2;
    imagePanelConstraints.gridheight = 20;
    imagePanelConstraints.gridwidth = 2;
    imagePanelConstraints.insets = new Insets(60, 60, 40, 40);
    imagePanel = new ImagePanel();
    Border padding = BorderFactory.createEmptyBorder(50, 50, 50, 50);
    imagePanel.setBorder(padding);
    this.add(imagePanel, imagePanelConstraints);

    GridBagConstraints histogramConstraints = new GridBagConstraints();
    histogramConstraints.gridy = 1;
    histogramConstraints.gridx = 2;
    histogramConstraints.weighty = 1;
    histogramConstraints.weightx = 0.1;
    histogramConstraints.fill = GridBagConstraints.HORIZONTAL;
    histogramConstraints.gridwidth = 2;
    histogramConstraints.insets = new Insets(10, 20, 40, 40);
    histogramConstraints.ipady = 257;
    histogramPanel = new HistogramPanel();
    this.add(histogramPanel, histogramConstraints);

    JPanel hLabelPan = new JPanel();
    JLabel hLabel = new JLabel("Histogram");
    hLabel.setFont(new Font(hLabel.getName(), Font.BOLD, 20));
    hLabelPan.add(hLabel);
    GridBagConstraints hLabelConstraints = new GridBagConstraints();
    hLabelConstraints.gridy = 1;
    hLabelConstraints.gridx = 2;
    hLabelConstraints.weighty = 0;
    hLabelConstraints.insets = new Insets(10, 15, 445, 40);
    this.add(hLabelPan, hLabelConstraints);

    this.setVisible(true);

  }


  /**
   * Print a message in the view.
   *
   * @param message to be printed.
   */
  @Override
  public void printMessage(String message) {
    System.out.println("The Graphical User Interface is running!");
  }

  /**
   * Opens a fileChooser popup to select an image file.
   *
   * @return the imageFile chosen using the fileChooser
   */
  public File openImageSelectionAndGetImageFile() {
    JFileChooser fileChooser = new ImageFileChooser();
    this.add(fileChooser);
    int result = fileChooser.showOpenDialog(this);
    return fileChooser.getSelectedFile();
  }

  /**
   * Opens a save file popup to select the image file to save.
   *
   * @return the image file to save
   */
  public File openImageSelectionAndSaveImageFile() {
    JFileChooser fileChooser = new ImageFileChooser();
    this.add(fileChooser);
    int saveFile = fileChooser.showSaveDialog(this);
    if (saveFile == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();

    }
    // CHeck ig file type provided in PPM PNG or JPG
    return null;
  }

  /**
   * Open a dialog box to take inputs.
   *
   * @param operation operation for which inputs are required
   * @param message   input prompt message
   * @return parameters that have been received as inputs
   */
  public String[] openDialogBox(String operation, String message) {
    String[] parameters;
    InputDialog inputDialog = inputDialogMap.getOrDefault(operation, null);
    if (inputDialog != null) {
      int result = JOptionPane.showConfirmDialog(null, inputDialog, message,
          JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        parameters = new String[inputDialog.getData().size()];
        for (int i = 0; i < inputDialog.getData().size(); i++) {
          parameters[i] = inputDialog.getData().get(i).getText();
        }
        return parameters;
      }

    } else {
      throw new RuntimeException("This functionality should not take any inputs.");
    }

    return null;
  }

  /**
   * Provide the view with an instance of Features.
   *
   * @param features features of the controller
   */
  public void addFeatures(Features features) {
    navigationBar.addActionListener(features);
  }


  /**
   * Change the image displayed on the GUI along with its histogram.
   *
   * @param image     is the new image
   * @param histogram is the histogram of the new image
   */

  public void changeImage(BufferedImage image, BufferedImage histogram) {
    imagePanel.changeImage(image);
    histogramPanel.updateHistogram(histogram);
  }


  /**
   * Display errors using a pop-up dialog box.
   *
   * @param error the error message to be displayed
   */
  public void showError(String error) {

    JOptionPane.showMessageDialog(null, error);

  }

  /**
   * Call a preview screen for an operation to look at the split view of the operation.
   *
   * @param operationName name of the operation being performed
   * @param features      an instance of the controller
   */
  public void preview(String operationName, Features features) {

    previewDialog = new PreviewDialog(this, operationName);
    previewDialog.addActionListener(features);
  }

  /**
   * Call a preview screen for an operation to look at the split view of the operation.
   *
   * @param operationName name of the operation being performed
   * @param features      an instance of the controller
   * @param parameters    parameters that the operation may require
   */
  public void preview(String operationName, Features features, String[] parameters) {

    previewDialog = new PreviewDialog(this, operationName, parameters);
    previewDialog.addActionListener(features);
  }

  /**
   * Change the preview image based on change of split percentage in the slider.
   *
   * @param image the image replacing the existing image in the preview
   */
  public void changePreviewImage(BufferedImage image) {
    previewDialog.changeImage(
        image.getScaledInstance(previewDialog.getPreviewScreen().getImageScrollPane().getWidth(),
            previewDialog.getPreviewScreen().getImageScrollPane().getHeight(),
            Image.SCALE_DEFAULT));
    previewDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  }

  /**
   * If cancel button is clicked in the preview dialog, close the dialog box without performing any
   * operation.
   */
  public void disposePreviewDialog() {
    this.previewDialog.dispose();
  }

}

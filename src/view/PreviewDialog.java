package view;

import controller.Features;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * This class is used for making a dialog window that will preview an operation that will be
 * performed.
 */
class PreviewDialog extends JDialog {


  private SplitView previewScreen;
  private String operationName;
  private String[] parameters;

  /**
   * Constructs a new preview dialog.
   *
   * @param owner         the Frame that is the owner of this dialog
   * @param operationName the name of the operation that will be performed in the preview
   */
  PreviewDialog(Frame owner, String operationName) {
    super(owner);
    this.operationName = operationName;

    this.previewScreen = new SplitView();
    this.add(this.previewScreen);
    this.setSize(new Dimension(800, 800));
    this.setVisible(true);
    this.parameters = null;

  }

  /**
   * Constructs a new preview dialog.
   *
   * @param owner         the Frame that is the owner of this dialog
   * @param operationName the name of the operation that will be performed in the preview
   * @param parameters    the parameters for the operation
   */
  PreviewDialog(Frame owner, String operationName, String[] parameters) {
    super(owner);
    this.operationName = operationName;
    this.previewScreen = new SplitView();
    this.add(this.previewScreen);
    this.setSize(new Dimension(800, 800));
    this.setVisible(true);
    this.parameters = parameters;


  }

  /**
   * Adds action listeners to cancel and confirm buttons and adds mouse listener to the slider.
   *
   * @param feature the feature passed to this method
   */
  void addActionListener(Features feature) {

    this.previewScreen.getCancelButton().addActionListener(evt -> this.dispose());
    this.previewScreen.getConfirmButton().addActionListener(
        evt -> feature.handlePreviewConfirm(this.operationName, this.parameters));
    this.previewScreen.getSlider().addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        feature.handlePreviewSlider(operationName, previewScreen.getSlider().getValue(),
            parameters);
      }
    });
  }

  /**
   * Adds originalImage to the preview screen.
   *
   * @param originalImage the original image
   */
  void changeImage(Image originalImage) {
    this.previewScreen.getImageScrollPane()
        .setViewportView(new JLabel(new ImageIcon(originalImage)));
  }

  SplitView getPreviewScreen() {
    return previewScreen;
  }

}

package view;

/**
 * A representation of a command line interface view. It prints the output message in the command
 * line interface.
 */
public class ImageViewCLI implements ImageView {

  /**
   * Print the message in the Command Line Interface.
   *
   * @param message is the message to be printed.
   */
  @Override
  public void printMessage(String message) {
    System.out.println(message);
  }


}

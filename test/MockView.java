import view.ImageView;

/**
 * This class prints a message from a StringBuilder out.
 */
public class MockView implements ImageView {

  StringBuilder outLog;

  public MockView(StringBuilder outLog) {
    this.outLog = outLog;
  }

  /**
   * Print a message in the view.
   *
   * @param message to be printed.
   */
  @Override
  public void printMessage(String message) {
    outLog.append(message);
  }


}

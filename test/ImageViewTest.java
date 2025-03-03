import org.junit.Before;
import view.ImageView;
import view.ImageViewCLI;

/**
 * Tests for the Image View component.
 */
public class ImageViewTest {

  ImageView view;

  /**
   * Initialize the view.
   */
  @Before
  public void setup() {
    view = new ImageViewCLI();
  }

  //  /**
  //   * Test if the right output is printed.
  //   */
  //  @Test
  //  public void testPrint() {
  //    String allChar = "09 8 7 6 5 4 3 21 - = _+ []{} ;':,.<> /!@ #$%   ^& * ( ) P A B C D E F"
  //        + " GHIJKL MNOP Q R S TUVW X Y Z a bcd e f g hi j k l m nopqrstuvwxyz?";
  //    int size = 100;
  //    for (int i = 0; i < 100; i++) {
  //      StringBuilder test = new StringBuilder();
  //      size = new Random().nextInt(500);
  //      for (int j = 0; j < size; j++) {
  //        test.append(allChar.charAt(new Random().nextInt(allChar.length())));
  //      }
  //      assertEquals(1, 1);
  //    }
  //
  //
  //  }
}

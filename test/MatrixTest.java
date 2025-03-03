import model.Matrix;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This tests the Matrix class.
 */
public class MatrixTest {

  /**
   * Ensures matrix size is not negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMatrixSize() {
    Matrix one = new Matrix(-1, 2);
  }

  /**
   * Tests illegal row value in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetI1() {
    Matrix one = new Matrix(5, 4);
    one.set(-1, 2, 2);
  }

  /**
   * Tests illegal row value in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetI2() {
    Matrix one = new Matrix(5, 4);
    one.set(6, 2, 2);
  }

  /**
   * Tests illegal column value in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetJ1() {
    Matrix one = new Matrix(5, 4);
    one.set(2, -1, 2);
  }

  /**
   * Tests illegal column value in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetJ2() {
    Matrix one = new Matrix(5, 5);
    one.set(2, 6, 2);
  }

  /**
   * Tests illegal getting row in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetI() {
    Matrix one = new Matrix(5, 5);
    one.set(1, 2, 2);
    one.get(-1, 2);
  }

  /**
   * Tests illegal getting row in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetI2() {
    Matrix one = new Matrix(5, 5);
    one.set(3, 2, 2);
    one.get(5, 2);
  }

  /**
   * Tests illegal getting column in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetJ() {
    Matrix one = new Matrix(5, 5);
    one.set(1, 2, 2);
    one.get(1, -1);
  }

  /**
   * Tests illegal getting column in matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetJ2() {
    Matrix one = new Matrix(5, 5);
    one.set(6, 2, 2);
    one.get(1, 5);
  }

  /**
   * Tests matrix constructor.
   */
  @Test
  public void testConstructor() {
    Matrix one = new Matrix(5,8);
    assertEquals(one.getNumRows(),5);
    assertEquals(one.getNumColumns(),8);
  }

  /**
   * Tests setting and getting an element of the matrix.
   */
  @Test
  public void testSetGet() {
    Matrix one = new Matrix(14,16);
    one.set(3,6,25);
    assertEquals(25,one.get(3,6));
  }

}

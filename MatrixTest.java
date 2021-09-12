import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Matrix;
import org.junit.Test;

/**
 * Test class for Matrix class and methods.
 */
public class MatrixTest {

  // creating Matrix with nonequal row sizes
  @Test(expected = IllegalArgumentException.class)
  public void testNonEqualRowSizes() {
    List<List<Double>> kernel = new ArrayList<List<Double>>(
        Arrays.asList(new ArrayList<Double>(Arrays.asList(0.0, 2.0, 3.0, 4.0, 5.0)),
            new ArrayList<Double>(Arrays.asList(3.0, 2.0, 3.0))));
    Matrix matrix = new Matrix(kernel);


  }

  // testing creating Matrix with nonodd height
  @Test(expected = IllegalArgumentException.class)
  public void testNonOddHeight() {
    List<List<Double>> kernel = new ArrayList<List<Double>>(
        Arrays.asList(new ArrayList<Double>(Arrays.asList(0.0, 2.0, 3.0, 4.0, 2.0)),
            new ArrayList<Double>(Arrays.asList(3.0, 3.0, 3.0, 3.0, 2.0))));
    Matrix matrix = new Matrix(kernel);


  }

  // testing creating Matrix with nonOdd row
  @Test(expected = IllegalArgumentException.class)
  public void testNonOddRow() {
    List<List<Double>> kernel = new ArrayList<List<Double>>(
        Arrays.asList(new ArrayList<Double>(Arrays.asList(0.0, 2.0, 3.0, 4.0)),
            new ArrayList<Double>(Arrays.asList(3.0, 3.0, 3.0, 3.0, 2.0))));
    Matrix matrix = new Matrix(kernel);


  }

  // test creating Mat4rix with null Kernel
  @Test(expected = NullPointerException.class)
  public void testNullKernel() {
    List<List<Double>> kernel = null;
    Matrix matrix = new Matrix(kernel);

  }

  // test getValue method
  @Test
  public void testGetValue() {
    List<List<Double>> kernel = new ArrayList<List<Double>>(
        Arrays.asList(new ArrayList<Double>(Arrays.asList(0.0, 2.0, 3.0, 4.0, 5.0))));
    Matrix matrix = new Matrix(kernel);

    assertEquals(2.0, matrix.getValue(0, 1), .0001);


  }
}
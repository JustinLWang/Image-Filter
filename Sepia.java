package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the sepia operation.
 */
public class Sepia extends AbstractTransform {

  private final Matrix sepiaMatrix;

  /**
   * Constructor for the Sepia class; creates the matrix for the sepia operation.
   */
  public Sepia() {
    List sepiaFilter = new ArrayList<>();
    sepiaFilter.add(Arrays.asList(0.393, 0.769, 0.189));
    sepiaFilter.add(Arrays.asList(0.349, 0.686, 0.168));
    sepiaFilter.add(Arrays.asList(0.272, 0.534, 0.131));

    this.sepiaMatrix = new Matrix(sepiaFilter);
  }

  // returns this Sepia's matrix
  protected Matrix getMatrix() {
    return this.sepiaMatrix;
  }


}

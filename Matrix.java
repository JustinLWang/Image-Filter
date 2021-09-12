package model;

import java.util.List;

/**
 * Class to represent a matrix for a kernel/filter.
 */
public class Matrix implements IMatrix {

  private List<List<Double>> kernel;

  /**
   * The constructor for a matrix.
   *
   * @param kernel the kernel
   */
  public Matrix(List<List<Double>> kernel) {
    for (int i = 0; i < kernel.size(); i++) {
      if (kernel.get(0).size() != kernel.get(i).size()) {
        throw new IllegalArgumentException("Invalid Kernel Matrix");
      }
    }
    if (kernel.size() % 2 == 0) {
      throw new IllegalArgumentException("Kernel must be odd");
    }
    for (int j = 0; j < kernel.size(); j++) {
      if (kernel.get(j).size() % 2 == 0) {
        throw new IllegalArgumentException("Kernel must be odd");
      }
    }
    if (kernel == null) {
      throw new IllegalArgumentException("Kernel cannot be null");
    }

    this.kernel = kernel;
  }


  /**
   * Returns this matrix.
   */
  public List<List<Double>> getMatrix() {
    return this.kernel;
  }

  // gets the value at the specific row and column of the matrix
  @Override
  public double getValue(int row, int column) {
    if (row < 0 || column < 0) {
      throw new IllegalArgumentException("invalid parameters.");
    }

    return kernel.get(row).get(column);
  }

}

package model;

/**
 * Interface for an IMatrix, which is a 2D array of values.
 */
public interface IMatrix {

  /**
   * Returns the value in a specific position in the matrix.
   *
   * @param row    the row where the value is coming from
   * @param column the column where the value is coming from
   * @return the value at the specified position in the matrix
   */
  double getValue(int row, int column);

}

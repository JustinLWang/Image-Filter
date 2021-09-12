package model;

import java.util.List;

/**
 * Interface that represents a programmable image.
 */
public interface IProgramImage {

  /**
   * Creates a checkerboard image programmatically.
   *
   * @param rows    the number of rows
   * @param columns the number of columns
   * @return a 2D array of pixels representing the checkerboard image
   */
  List<List<Pixel>> checkerboard(int rows, int columns, int squareSide);

}

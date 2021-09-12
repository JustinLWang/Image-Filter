package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Programmable Image. Holds methods needed to create a programmable image.
 */
public class ProgramImage implements IProgramImage {

  /**
   * Creates a programmable image.
   */
  public ProgramImage() {
    //
  }

  /**
   * Creates a checkerBoard image.
   *
   * @param rows       number of rows
   * @param columns    number of columns
   * @param squareSide size of the size of each square
   * @return the 2D array of Pixel of the image
   */
  public List<List<Pixel>> checkerboard(int rows, int columns, int squareSide) {
    if (rows < 0 || columns < 0) {
      throw new IllegalArgumentException("Rows/columns cannot be negative");
    }

    if (squareSide <= 0) {
      throw new IllegalArgumentException("squareSide has to be greater than 0.");
    }

    List<List<Pixel>> image = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int m = 0; m < squareSide; m++) {
        image.add(new ArrayList<>());
        for (int j = 0; j < columns; j++) {
          for (int n = 0; n < squareSide; n++) {
            if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
              image.get((i * squareSide) + m).add(new Pixel(255, 0, 0));
            } else {
              image.get((i * squareSide) + m).add(new Pixel(0, 0, 255));
            }
          }
        }
      }
    }
    return image;
  }

}

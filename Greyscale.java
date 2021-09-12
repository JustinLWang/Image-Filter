package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the greyscale operation. Holds and implements methods needed to properly greyscale an
 * image.
 */
public class Greyscale extends AbstractTransform {

  private final Matrix greyscaleMatrix;

  /**
   * Constructor for the Greyscale class; creates the matrix for the greyscale operation.
   */
  public Greyscale() {
    List greyscaleFilter = new ArrayList<>();
    greyscaleFilter.add(Arrays.asList(0.2126, 0.7152, 0.0722));
    greyscaleFilter.add(Arrays.asList(0.2126, 0.7152, 0.0722));
    greyscaleFilter.add(Arrays.asList(0.2126, 0.7152, 0.0722));

    this.greyscaleMatrix = new Matrix(greyscaleFilter);
  }

  // gets the matrix
  protected Matrix getMatrix() {
    return this.greyscaleMatrix;
  }


}




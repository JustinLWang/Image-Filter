package model;

/**
 * Interface for IPixel.
 */
public interface IPixel {

  /**
   * Returns the fields of the pixel in String format.
   *
   * @return the string of the channel values
   */
  String toString();

  /**
   * Checks to see if two pixels are the same and overrides built-in equals.
   *
   * @param obj the other pixel that is being compared
   * @return true if both cards are the same
   */
  boolean equals(Object obj);

  /**
   * Checks to see if two pixels are the same using hashcode and overrides built-in hashCode.
   *
   * @return the integer hashcode
   */
  int hashCode();

  /**
   * Gets the value of the red field.
   *
   * @return the value of the red field
   */
  double getRed();

  /**
   * Gets the value of the green field.
   *
   * @return the value of the green field
   */
  double getGreen();

  /**
   * Gets the value of the blue field.
   *
   * @return the value of the blue field
   */
  double getBlue();

}

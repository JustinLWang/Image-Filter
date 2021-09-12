package model;

/**
 * Class to represent a pixel, with red, green, and blue fields.
 */
public class Pixel implements IPixel {

  private double red;
  private double green;
  private double blue;

  /**
   * The constructor for a Pixel.
   *
   * @param red   the value/intensity of red in a pixel
   * @param green the value/intensity of green in a pixel
   * @param blue  the value/intensity of blue in a pixel
   */
  public Pixel(double red, double green, double blue) {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid color value");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  // Overrided toString method
  @Override
  public String toString() {
    return red + " " + green + " " + blue;
  }

  // equals method (extensional equality)
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Pixel)) {
      return false;
    }
    Pixel that = (Pixel) obj;
    return this.red == that.red && this.green == that.green && this.blue == that.blue;
  }

  // overrided hashCode method
  @Override
  public int hashCode() {
    return (int) (this.red * 1000000 + this.green * 1000 + this.blue);
  }

  // gets this pixels red value
  @Override
  public double getRed() {
    return this.red;
  }

  // gets this pixels green value
  @Override
  public double getGreen() {
    return this.green;
  }

  // gets this pixels blue value
  @Override
  public double getBlue() {
    return this.blue;
  }
}

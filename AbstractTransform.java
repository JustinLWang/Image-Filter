package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that holds methods required for transforming a image.
 */
public abstract class AbstractTransform implements IImageManipulation {

  /**
   * Transforms the given image.
   *
   * @param img Image to filter
   * @return the 2D Array of Pixel of the transformed image
   */
  public List<List<Pixel>> transform(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("null parameter.");
    }

    List<List<Pixel>> originalImg = img.getPixelArray();
    List<List<Pixel>> transformedImg = new ArrayList<List<Pixel>>();

    for (int i = 0; i < originalImg.size(); i++) {
      transformedImg.add(new ArrayList<Pixel>());
    }

    for (int i = 0; i < originalImg.size(); i++) {
      for (int j = 0; j < originalImg.get(i).size(); j++) {

        double newRed = getMatrix().getValue(0, 0) * originalImg.get(i).get(j).getRed()
            + getMatrix().getValue(0, 1) * originalImg.get(i).get(j).getGreen()
            + getMatrix().getValue(0, 2) * originalImg.get(i).get(j).getBlue();
        double newGreen = getMatrix().getValue(1, 0) * originalImg.get(i).get(j).getRed()
            + getMatrix().getValue(1, 1) * originalImg.get(i).get(j).getGreen()
            + getMatrix().getValue(1, 2) * originalImg.get(i).get(j).getBlue();
        double newBlue = getMatrix().getValue(2, 0) * originalImg.get(i).get(j).getRed()
            + getMatrix().getValue(2, 1) * originalImg.get(i).get(j).getGreen()
            + getMatrix().getValue(2, 2) * originalImg.get(i).get(j).getBlue();

        // Try to write in Helper (for some reason didn't work first time I did it
        if (newRed < 0) {
          newRed = 0;
        }
        if (newRed > 255) {
          newRed = 255;
        }
        if (newGreen < 0) {
          newGreen = 0;
        }
        if (newGreen > 255) {
          newGreen = 255;
        }
        if (newBlue < 0) {
          newBlue = 0;
        }
        if (newBlue > 255) {
          newBlue = 255;
        }

        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        transformedImg.get(i).add(newPixel);
      }
    }
    return transformedImg;
  }

  /**
   * Returns the Matrix of the class.
   */
  protected abstract Matrix getMatrix();

  @Override
  public List<List<Pixel>> filter(Image img, int multiplier) {
    return null;
  }
}

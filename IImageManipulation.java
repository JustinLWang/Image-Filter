package model;

import java.util.List;

/**
 * Interface that represents the operations that can done on an Image. Model
 */
public interface IImageManipulation {

  /**
   * Performs the filter operation on the given image.
   *
   * @param img        the image to be altered
   * @param multiplier the number of times the filter is to be applied
   * @return the image, in a 2D array of pixels
   */
  List<List<Pixel>> filter(Image img, int multiplier);

  /**
   * Performs the transform operation on the given image.
   *
   * @param img the given image
   * @return the altered image
   */
  List<List<Pixel>> transform(Image img);

}

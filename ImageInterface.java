package model;

import java.util.List;

/**
 * Interface that represents an Image. Used to extend features/information of an Image.
 */
public interface ImageInterface {

  /**
   * Produces the pixelArray of the this Image.
   */
  List<List<Pixel>> getPixelArray();

  /**
   * Gets the images height.
   */
  int getImageHeight();

  /**
   * Gets the ImagesWidth.
   */
  int getImageWidth();
}

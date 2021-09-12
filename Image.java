package model;

import java.util.List;

/**
 * Class that represents an Image. Holds information regarding an Image.
 */
public class Image implements ImageInterface {

  private List<List<Pixel>> pixelArray;


  /**
   * Creates a Image using the sourcePath of the image and the fileType.
   */
  public Image(List<List<Pixel>> pixelArray) {
    if (pixelArray == null) {
      throw new IllegalArgumentException();
    }
    this.pixelArray = pixelArray;
  }

  // gets pixel Array
  @Override
  public List<List<Pixel>> getPixelArray() {
    return this.pixelArray;
  }

  // gets image height
  @Override
  public int getImageHeight() {
    return this.pixelArray.size();
  }

  // gets image width
  @Override
  public int getImageWidth() {
    return this.pixelArray.get(0).size();
  }


}

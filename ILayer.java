package model;

/**
 * Interface that represents a Layer. Holds methods regarding the a Layer.
 */
public interface ILayer {

  /**
   * Sets the visibility of the layer.
   *
   * @param visibility the visibility
   */
  void setLayerVisibility(boolean visibility);

  /**
   * Sets the Image of the layer.
   *
   * @param image the image
   */
  void setLayerImage(Image image);

  /**
   * Gets the image in this layer.
   *
   * @return the image
   */
  Image getLayerImage();

  /**
   * Gets the Visibility in this layer.
   *
   * @return the visibility as a boolean
   */
  boolean getLayerVisibility();

  /**
   * Gets the fileType of the image in this layer.
   *
   * @return the fileType
   */
  String getFileType();

  /**
   * Sets the fileType of this layer.
   *
   * @param type the fileType
   */
  void setFileType(String type);


}

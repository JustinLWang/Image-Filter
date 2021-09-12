package model;


/**
 * A class Layer to represent a layer within layers; contains an Image field to represent the. image
 * in which will be in the layer, and a boolean visible, which determines whether or not that. layer
 * will be visible to the user or not.
 */
public class Layer implements ILayer {

  private Image img;
  private String fileType;
  private boolean visible;


  /**
   * The constructor for the class Layer; visibility will always be true unless the user specifies
   * that it should be false.
   *
   * @param img the image to be loaded into the layer
   */

  public Layer(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.img = img;
    this.visible = true;
  }

  public Layer() {
    this.visible = true;
  }


  // gets the image in layer
  @Override
  public Image getLayerImage() {
    return this.img;
  }

  // gets layers visibility
  @Override
  public boolean getLayerVisibility() {
    return this.visible;
  }

  // gets images fileType
  @Override
  public String getFileType() {
    return this.fileType;
  }

  // sets the images fileType
  @Override
  public void setFileType(String type) {
    if (type == null) {
      throw new IllegalArgumentException();
    }
    this.fileType = type;

  }


  // sets the layers image
  @Override
  public void setLayerImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }

    this.img = image;
  }

  // sets layers visibility
  @Override
  public void setLayerVisibility(boolean visibility) {
    this.visible = visibility;
  }


}

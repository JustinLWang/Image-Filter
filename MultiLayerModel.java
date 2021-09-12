package model;

import java.util.List;

/**
 * Interface that represents the MultiLayerModel. Holds methods regarding the operations needed for
 * this program.
 */
public interface MultiLayerModel extends ReadOnlyModel {

  /**
   * filters image based on operation given.
   *
   * @param img        the image
   * @param multiplier the number of times to filter
   * @param operation  operation to do
   * @return array of pixels
   */
  List<List<Pixel>> filter(Image img, int multiplier, String operation);

  /**
   * transforms image based on operation given.
   *
   * @param img       the image
   * @param operation operation to do
   * @return array of pixels
   */
  List<List<Pixel>> transform(Image img, String operation);

  /**
   * creates a layer in the model.
   */
  Layer createLayer();

  /**
   * A method used to set the given image.
   *
   * @param image the image
   * @param layer the layer
   */
  void setImage(Layer layer, Image image);

  /**
   * A method meant to determine the visibility of the layer, determined by the user; is true unless
   * the user specifies that the layer should be invisible.
   *
   * @param visibility the visibility
   * @param layer      the layer
   */
  void setVisibility(Layer layer, boolean visibility);


  /**
   * Adds a layer to this model.
   *
   * @param whichLayer where to add layer
   * @param layer      the layer
   */

  void addLayer(Layer layer, int whichLayer);


  /**
   * Creates an image from a pixelArray.
   *
   * @param pixelArray the array
   * @return the created Image
   */
  Image createImage(List<List<Pixel>> pixelArray);


  /**
   * Removes a layer from this model.
   *
   * @param whichLayer the layer to remove
   */
  void removeLayer(int whichLayer);


  /**
   * Determines if the layers all have same dimensions.
   *
   * @param layers the layers
   * @return the boolean true or false
   */
  boolean sameDimensions(List<Layer> layers);

  /**
   * Determines if the pixelArray as equal dimensions.
   *
   * @param pixelArray  the pixelArray
   * @return the boolean true or false
   */
  boolean equalPixelArrayDim(List<List<Pixel>> pixelArray);

  /**
   * Gets top most visible layer.
   *
   * @return the top most visible layer
   */
  Layer getTopMostVisible();


}

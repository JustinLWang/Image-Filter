package model;

import java.util.List;

/**
 * Interface that represents the the observer operations of the Model. Holds methods regarding the
 * viewing the Model (only for view).
 */
public interface ReadOnlyModel {

  /**
   * Gets the Image.
   *
   * @param layer the layer to get
   * @return the image in the layer
   */
  Image getImage(Layer layer);


  /**
   * Gets the visibility of the given layer.
   *
   * @param layer the layer to get
   * @return the boolean true or false
   */
  boolean getVisibility(Layer layer);

  /**
   * Gets the layers of the model.
   *
   * @return the boolean true or false
   */
  List<Layer> getLayers();


  /**
   * Gets a specific layer.
   *
   * @param index the layer to get
   * @return the layer
   */
  Layer getSpecificLayer(int index);


  /**
   * Determines number of layers.
   *
   * @return the number of layers
   */
  int numberOfLayers();


}

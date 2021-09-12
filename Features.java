package controller;

/**
 * An interface to represent the features that the controller should have.
 */
public interface Features {

  /**
   * The feature to add a layer to a layered image.
   */
  void addLayer();

  /**
   * The feature to remove a layer from a layered image.
   */
  void removeLayer();

  /**
   * The feature to choose a specific layer from a layered image.
   *
   * @param whichLayer the layer that the user wants to get to
   */
  void currentLayer(int whichLayer);

  /**
   * The feature to load a layer in a layered image.
   */
  void loadLayer();

  /**
   * The feature to load all the layers in a layered image.
   */
  void loadAllLayer();

  /**
   * The feature to blur a layer/image in a layered image.
   */
  void blurLayer();

  /**
   * The feature to sharpen a layer/image in a layered image.
   */
  void sharpenLayer();

  /**
   * The feature to overlay a greyscale filter on a layer/image in a layered image.
   */
  void greyscaleLayer();

  /**
   * The feature to overlay a sepia filter on a layer/image in a layered image.
   */
  void sepiaLayer();

  /**
   * The feature to make a layer of the layered image visible.
   */
  void visible();

  /**
   * The feature to make a layer of the layered image invisible.
   */
  void invisible();

  /**
   * The feature to input a script for the user.
   */
  void script();

  /**
   * The feature to save a layer in a layered image.
   */
  void save();

  /**
   * The feature to save all the layers in a layered image.
   */
  void saveAll();

  /**
   * The feature to exit the program.
   */
  void exitProgram();


}

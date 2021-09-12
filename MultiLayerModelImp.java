package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the MultiLayerModel Implementation. Holds methods regarding the operations
 * needed for this program. Implements these methods.
 */
public class MultiLayerModelImp implements MultiLayerModel {

  private List<Layer> layers;


  /**
   * Creates a Model for this program.
   */
  public MultiLayerModelImp() {
    this.layers = new ArrayList<Layer>();

  }

  /**
   * Creates a Model for this program.
   *
   * @param layers the MultiLayerModel
   */
  public MultiLayerModelImp(List<Layer> layers) {
    if (layers == null) {
      throw new IllegalArgumentException();
    }
    if (sameDimensions(layers)) {
      this.layers = layers;
    } else {
      throw new IllegalArgumentException("layers are not same dimensions.");
    }


  }

  // determines if the layers are the same dimensions
  @Override
  public boolean sameDimensions(List<Layer> layers) {
    if (layers == null) {
      throw new IllegalArgumentException();
    }

    if (layers.size() == 0) {
      return true;
    }
    int height = layers.get(0).getLayerImage().getPixelArray().size();
    int width = layers.get(0).getLayerImage().getPixelArray().get(0).size();
    for (int i = 1; i < layers.size(); i++) {
      int layerHeight = layers.get(i).getLayerImage().getPixelArray().size();
      int layerWidth = layers.get(i).getLayerImage().getPixelArray().get(i).size();
      if (layerHeight != height || layerWidth != width) {
        return false;
      }
    }
    return true;
  }

  // determines if the pixelArray has even dimensions to this model
  @Override
  public boolean equalPixelArrayDim(List<List<Pixel>> pixelArray) {
    if (pixelArray == null) {
      throw new IllegalArgumentException();
    }

    if (this.layers.size() == 0) {
      return true;
    }

    int pixelArrayHeight = pixelArray.size();
    int pixelArrayWidth = pixelArray.get(0).size();
    boolean result = true;
    for (int i = 0; i < layers.size(); i++) {
      Image layerImage = this.getSpecificLayer(i).getLayerImage();
      if (layerImage == null) {
        continue;
      }
      if (pixelArrayHeight != layerImage.getPixelArray().size()
          || pixelArrayWidth != this.getSpecificLayer(i).getLayerImage().getPixelArray().get(0)
          .size()) {
        return false;
      }
    }
    return result;
  }


  // filters the image based on operation given
  @Override
  public List<List<Pixel>> filter(Image img, int multiplier, String operation) {
    if (img == null || multiplier < 0 || operation == null) {
      throw new IllegalArgumentException("invalid paramaters.");
    }
    if (multiplier == 0) {
      return img.getPixelArray();
    }
    // Need some way to get pixels and sepagetrate into 3 different array (red, green, blue)
    List<List<Double>> redArray = AbstractFilter.splitChannels(img, "red");
    List<List<Double>> greenArray = AbstractFilter.splitChannels(img, "green");
    List<List<Double>> blueArray = AbstractFilter.splitChannels(img, "blue");
    // call helper and do math
    List<List<Double>> alteredRed = new ArrayList<List<Double>>();
    List<List<Double>> alteredGreen = new ArrayList<List<Double>>();
    List<List<Double>> alteredBlue = new ArrayList<List<Double>>();
    switch (operation) {
      case ("blur"):
        alteredRed = new Blur().applyFilter(redArray, new Blur().getKernel());
        alteredGreen = new Blur().applyFilter(greenArray, new Blur().getKernel());
        alteredBlue = new Blur().applyFilter(blueArray, new Blur().getKernel());

        for (int i = 1; i < multiplier; i++) {
          alteredRed = new Blur().applyFilter(alteredRed, new Blur().getKernel());
          alteredGreen = new Blur().applyFilter(alteredGreen, new Blur().getKernel());
          alteredBlue = new Blur().applyFilter(alteredBlue, new Blur().getKernel());
        }
        break;
      case ("sharpen"):
        alteredRed = new Sharpen().applyFilter(redArray, new Sharpen().getKernel());
        alteredGreen = new Sharpen().applyFilter(greenArray, new Sharpen().getKernel());
        alteredBlue = new Sharpen().applyFilter(blueArray, new Sharpen().getKernel());

        for (int i = 1; i < multiplier; i++) {
          alteredRed = new Sharpen().applyFilter(alteredRed, new Sharpen().getKernel());
          alteredGreen = new Sharpen().applyFilter(alteredGreen, new Sharpen().getKernel());
          alteredBlue = new Sharpen().applyFilter(alteredBlue, new Sharpen().getKernel());
        }
        break;
      default:
        throw new IllegalArgumentException("invalid operation.");

    }
    // call helper that combines 3 2D arrays into one Array of Pixels
    return filteredImage(alteredRed, alteredGreen, alteredBlue);
  }


  // combines channels into one array
  private List<List<Pixel>> filteredImage(List<List<Double>> red, List<List<Double>> green,
      List<List<Double>> blue) {
    if (red == null || green == null || blue == null) {
      throw new IllegalArgumentException("null parameters.");
    }

    List<List<Pixel>> filteredImage = new ArrayList<List<Pixel>>();
    for (int i = 0; i < red.size(); i++) {
      filteredImage.add(new ArrayList<Pixel>());
    }
    for (int i = 0; i < red.size(); i++) {
      for (int j = 0; j < red.get(i).size(); j++) {
        Pixel alteredPixel = new Pixel(red.get(i).get(j), green.get(i).get(j), blue.get(i).get(j));
        filteredImage.get(i).add(alteredPixel);
      }
    }
    return filteredImage;
  }

  // transforms the image based on given operation
  @Override
  public List<List<Pixel>> transform(Image img, String operation) {
    if (img == null || operation == null) {
      throw new IllegalArgumentException("null parameter.");
    }

    List<List<Pixel>> originalImg = img.getPixelArray();
    List<List<Pixel>> transformedImg = new ArrayList<List<Pixel>>();

    for (int i = 0; i < originalImg.size(); i++) {
      transformedImg.add(new ArrayList<Pixel>());
    }
    Matrix matrix;
    if (operation.equals("greyscale")) {
      matrix = new Greyscale().getMatrix();
    } else if (operation.equals("sepia")) {
      matrix = new Sepia().getMatrix();
    } else {
      throw new IllegalArgumentException("invalid operation.");
    }

    for (int i = 0; i < originalImg.size(); i++) {
      for (int j = 0; j < originalImg.get(i).size(); j++) {

        double newRed = matrix.getValue(0, 0) * originalImg.get(i).get(j).getRed()
            + matrix.getValue(0, 1) * originalImg.get(i).get(j).getGreen()
            + matrix.getValue(0, 2) * originalImg.get(i).get(j).getBlue();
        double newGreen = matrix.getValue(1, 0) * originalImg.get(i).get(j).getRed()
            + matrix.getValue(1, 1) * originalImg.get(i).get(j).getGreen()
            + matrix.getValue(1, 2) * originalImg.get(i).get(j).getBlue();
        double newBlue = matrix.getValue(2, 0) * originalImg.get(i).get(j).getRed()
            + matrix.getValue(2, 1) * originalImg.get(i).get(j).getGreen()
            + matrix.getValue(2, 2) * originalImg.get(i).get(j).getBlue();

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


  // creates a layer
  @Override
  public Layer createLayer() {
    return new Layer();
  }

  // sets a layer's image
  @Override
  public void setImage(Layer layer, Image image) {
    if (layer == null || image == null) {
      throw new IllegalArgumentException();
    }

    layer.setLayerImage(image);

  }

  // sets a layer's visibility
  @Override
  public void setVisibility(Layer layer, boolean visibility) {
    if (layer == null) {
      throw new IllegalArgumentException();
    }

    layer.setLayerVisibility(visibility);

  }

  // adds a layer
  @Override
  public void addLayer(Layer layer, int whichLayer) {
    if (layer == null) {
      throw new IllegalArgumentException();
    }

    this.layers.add(whichLayer, layer);

  }

  // gets an image
  @Override
  public Image getImage(Layer layer) {
    if (layer == null) {
      throw new IllegalArgumentException();
    }

    return layer.getLayerImage();
  }

  // gets a visibility
  @Override
  public boolean getVisibility(Layer layer) {
    if (layer == null) {
      throw new IllegalArgumentException();
    }

    return layer.getLayerVisibility();
  }

  // gets this model's layers
  @Override
  public List<Layer> getLayers() {
    return this.layers;
  }

  // gets a specific layer
  @Override
  public Layer getSpecificLayer(int index) {
    return this.layers.get(index);
  }

  // get the number of layers
  @Override
  public int numberOfLayers() {
    return this.layers.size();
  }

  // creates an image
  @Override
  public Image createImage(List<List<Pixel>> pixelArray) {
    if (pixelArray == null) {
      throw new IllegalArgumentException();
    }
    return new Image(pixelArray);
  }

  // removes a layer
  @Override
  public void removeLayer(int whichLayer) {
    layers.remove(whichLayer);
  }

  @Override
  public Layer getTopMostVisible() {
    for (int i = numberOfLayers() - 1; i >= 0; i--) {
      Layer layer = getSpecificLayer(i);
      if (layer.getLayerVisibility()) {
        return layer;
      }
    }
    return new Layer();
  }
}



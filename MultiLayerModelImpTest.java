import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Image;
import model.Layer;
import model.MultiLayerModelImp;
import model.Pixel;
import org.junit.Test;

/**
 * Tests for the MultiLayerModelImpl class and its methods.
 */
public class MultiLayerModelImpTest {

  // test equialPixelkArrayDim method
  @Test
  public void testEqualPixelArrayDim() {
    Image image1 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer1 = new Layer(image1);
    MultiLayerModelImp model = new MultiLayerModelImp(new ArrayList<Layer>(Arrays.asList(layer1)));

    assertEquals(true, model.equalPixelArrayDim(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2)))))));
  }


  // test non equal dimensions constructor
  @Test(expected = IllegalArgumentException.class)
  public void testNonEqualDimensionsConstructor() {
    Image image1 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer1 = new Layer(image1);
    Image image2 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2),
            new Pixel(3, 3, 3))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer2 = new Layer(image2);
    MultiLayerModelImp model = new MultiLayerModelImp(
        new ArrayList<Layer>(Arrays.asList(layer1, layer2)));
    assertEquals(layer1, model.getSpecificLayer(0));
  }

  // test equal dimensions constrcutor
  @Test
  public void testEqualDimensionsConstructor() {
    Image image1 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer1 = new Layer(image1);
    Image image2 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer2 = new Layer(image2);
    MultiLayerModelImp model = new MultiLayerModelImp(
        new ArrayList<Layer>(Arrays.asList(layer1, layer2)));
    assertEquals(layer1, model.getSpecificLayer(0));
  }

  // test same diemnsions
  @Test
  public void testSameDimensions() {
    Image image1 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer1 = new Layer(image1);
    Image image2 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer2 = new Layer(image2);
    MultiLayerModelImp model = new MultiLayerModelImp(
        new ArrayList<Layer>(Arrays.asList(layer1, layer2)));
    assertEquals(true, model.sameDimensions(model.getLayers()));
  }

  // test creating layer
  @Test
  public void testCreateLayer() {
    Image image1 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer1 = new Layer(image1);
    Image image2 = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    Layer layer2 = new Layer(image2);
    MultiLayerModelImp model = new MultiLayerModelImp(
        new ArrayList<Layer>(Arrays.asList(layer1, layer2)));
    assertEquals(Layer.class, model.createLayer().getClass());
  }

  // test setting image
  @Test
  public void testSetImage() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setImage(layer, image);
    assertEquals(image, layer.getLayerImage());
  }

  // test setting image null
  @Test(expected = IllegalArgumentException.class)
  public void testSetImageNull() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = null;
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setImage(layer, image);
    assertEquals(image, layer.getLayerImage());
  }

  // test set visibility
  @Test(expected = IllegalArgumentException.class)
  public void testSetVisibility() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = null;
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setVisibility(layer, true);
    assertEquals(image, layer.getLayerImage());
  }

  // test set visibility false
  @Test
  public void testSetVisibilityFalse() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setVisibility(layer, false);
    assertEquals(false, layer.getLayerVisibility());
  }

  //test add layer
  @Test
  public void testAddLayer() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.addLayer(layer, 0);
    assertEquals(layer, model.getSpecificLayer(0));
  }

  // test add layer null
  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerNull() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.addLayer(null, 0);
    assertEquals(layer, model.getSpecificLayer(0));
  }

  //test get image
  @Test
  public void testGetImage() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    layer.setLayerImage(image);
    model.getImage(layer);
    assertEquals(image, layer.getLayerImage());
  }

  // test get visibility
  @Test
  public void testGetVisibility() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    layer.setLayerImage(image);
    model.getImage(layer);
    assertEquals(true, layer.getLayerVisibility());
  }

  // test creatimage
  @Test
  public void testCreateImage() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    layer.setLayerImage(image);
    model.getImage(layer);
    Image image2 = model.createImage(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    assertEquals(new ArrayList<List<Pixel>>(
            Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
                new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))),
        image2.getPixelArray());
  }

  // test removing a layer

  @Test
  public void testRemoveLayer() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    Layer layer = new Layer();
    Image image = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))),
            new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    layer.setLayerImage(image);
    model.addLayer(layer, 0);
    assertEquals(1, model.numberOfLayers());
    model.removeLayer(0);

    assertEquals(0, model.numberOfLayers());
  }


}

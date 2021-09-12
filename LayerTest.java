import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Image;
import model.Layer;
import model.Pixel;
import org.junit.Test;

/**
 * Tests for the Layer class and its methods.
 */
public class LayerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testLayerConstructor() {
    new Layer(null);

  }

  // test getting a layers image
  @Test
  public void testGetLayerImage() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer(image);
    assertEquals(image, layer.getLayerImage());
  }

  // teswt getting layer visibility
  @Test
  public void testGetLayerVisibility() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer(image);
    assertEquals(true, layer.getLayerVisibility());
  }

  // test geting file type
  @Test
  public void testGetLayerFileType() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer(image);
    layer.setFileType("ppm");
    assertEquals("ppm", layer.getFileType());
  }

  //test setting file type
  @Test
  public void testsetFileType() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer(image);
    layer.setFileType("ppm");
    assertEquals("ppm", layer.getFileType());
  }

  //test setfile type but null
  @Test(expected = IllegalArgumentException.class)
  public void testsetFileTypeNull() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer(image);
    layer.setFileType(null);
    assertEquals("ppm", layer.getFileType());
  }

  //test setting layer but null
  @Test(expected = IllegalArgumentException.class)
  public void testsetLayerImageNull() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer(image);
    layer.setLayerImage(null);
    assertEquals("ppm", layer.getFileType());
  }

  //test setting layer image
  @Test
  public void testsetLayerImage() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer();
    layer.setLayerImage(image);
    assertEquals(image, layer.getLayerImage());
  }

  // test setting layer viisbility
  @Test
  public void testsetLayerVisibility() {
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>(Arrays.asList(
        new ArrayList<Pixel>(Arrays.asList(new Pixel(3, 3, 3)))));
    Image image = new Image(pixelArray);
    Layer layer = new Layer();
    layer.setLayerVisibility(false);
    assertEquals(false, layer.getLayerVisibility());
  }


}
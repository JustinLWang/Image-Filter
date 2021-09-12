import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import controller.Controller;
import java.io.File;
import java.io.StringReader;
import model.MultiLayerModelImp;
import org.junit.Test;

/**
 * Tests for the Controller and its methods.
 */
public class ControllerTest {

  // tests constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Controller controller = new Controller(null, null, null);
    assertEquals("", controller.equals(""));
  }

  // tests creating layer
  @Test
  public void testCreateLayer() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("createLayer 1"), out);
    controller.goProgram();
    assertEquals(1, model.numberOfLayers());
    assertEquals("layer successfully created.\n", out.toString());

  }

  // tests creating three layers
  @Test
  public void testCreateLayer3() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader("createLayer 1 createLayer 2 createLayer 3"), out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals(
        "layer successfully created.\nlayer successfully created."
            + "\nlayer successfully created.\n",
        out.toString());

  }

  // test creating layr but numnber not given
  @Test
  public void testCreateLayerNumberNotGiven() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("createLayer"), out);
    controller.goProgram();
    assertEquals(0, model.numberOfLayers());
    assertEquals("specific layer must be given.\n", out.toString());

  }

  // test removing layer
  @Test
  public void testRemoveLayer() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("createLayer 1 createLayer "
        + "2 createLayer 3 remove 1 remove 2"), out);
    controller.goProgram();
    assertEquals(1, model.numberOfLayers());
    assertEquals("layer successfully created.\nlayer successfully created.\nlayer "
            + "successfully created.\n"
            + "layer successfully removed.\n"
            + "layer successfully removed.\n"
        , out.toString());

    assertEquals(1, model.numberOfLayers());

  }

  // test removing layer number not given
  @Test
  public void testRemoveLayerNumberNotGiven() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("createLayer 1 createLayer "
        + "2 createLayer 3 remove"), out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("layer successfully created.\nlayer successfully created.\nlayer "
            + "successfully created.\n"
            + "specific layer must be given.\n"
        , out.toString());

    assertEquals(3, model.numberOfLayers());

  }

  // test load and current
  @Test
  public void testLoadAndCurrent() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("createLayer 1 createLayer "
        + "2 createLayer 3 current 1 load res/Koala.ppm"), out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("layer successfully created.\nlayer successfully created.\nlayer "
            + "successfully created.\n"
            + "image successfully loaded.\n"
        , out.toString());

    assertTrue(model.getSpecificLayer(0).getLayerImage() != null);

  }


  // test loading file doesnt exist
  @Test(expected = NullPointerException.class)
  public void testLoadFileDoesntExist() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("createLayer 1 createLayer "
        + "2 createLayer 3 current 1 load jiji/"), out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("layer successfully created.\nlayer successfully created.\nlayer "
            + "successfully created.\n"
            + "no source path for image given.\n"
        , out.toString());

    assertTrue(model.getSpecificLayer(0).getLayerImage() == null);

  }

  // test load layered image
  @Test
  public void testLoadLayered() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader("loadLayered MultiLayeredImages/loadedLayeredImage"), out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("multilayered image loaded successfully.\n"
        , out.toString());

    assertTrue(model.getSpecificLayer(0).getLayerImage() != null);
    assertTrue(model.getSpecificLayer(1).getLayerImage() != null);
    assertTrue(model.getSpecificLayer(2).getLayerImage() != null);

  }

  // test loading multilayered image but no path given
  @Test
  public void testLoadLayeredNoPathGiven() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("loadLayered"), out);
    controller.goProgram();
    assertEquals(0, model.numberOfLayers());
    assertEquals("path to multilayered image must be given.\n"
        , out.toString());

  }

  // test loading multilayered image but file doesnt exist
  @Test
  public void testLoadLayeredFileDoesntExist() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model, new StringReader("loadLayered tjt/"), out);
    controller.goProgram();
    assertEquals(0, model.numberOfLayers());
    assertEquals("folder for multilayered image doesn't exist.\n"
        , out.toString());

  }


  // test save
  @Test
  public void testSave() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader(
            "loadLayered MultiLayeredImages/loadedLayeredImage current 1 save Koala.jpeg jpeg"),
        out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("multilayered image loaded successfully.\n"
            + "image successfully saved.\n"
        , out.toString());
    File file = new File("res/Koala.jpeg");
    assertTrue(file.exists());

  }

  // test export
  @Test
  public void testExport() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader(
            "loadLayered MultiLayeredImages/loadedLayeredImage current 1 "
                + "export RoadBlur.png png"),
        out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("multilayered image loaded successfully.\n"
            + "image successfully exported.\n"
        , out.toString());
    File file = new File("Exports/RoadBlur.png");
    assertTrue(file.exists());

  }

  // test invisible
  @Test
  public void testInvisible() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader("loadLayered "
            + "MultiLayeredImages/loadedLayeredImage current 1 invisible"),
        out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("multilayered image loaded successfully.\n"
            + "layer set to invisible.\n"
        , out.toString());
    assertFalse(model.getSpecificLayer(0).getLayerVisibility());

  }

  // test visible
  @Test
  public void testVisible() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader("loadLayered MultiLayeredImages/loadedLayeredImage current 1 visible"),
        out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("multilayered image loaded successfully.\n"
            + "layer set to visible.\n"
        , out.toString());
    assertTrue(model.getSpecificLayer(0).getLayerVisibility());

  }

  // test saveAll
  @Test
  public void testSaveAll() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader(
            "loadLayered MultiLayeredImages/loadedLayeredImage "
                + "saveAll loadedLayeredImage"), out);
    controller.goProgram();
    assertEquals(3, model.numberOfLayers());
    assertEquals("multilayered image loaded successfully.\n"
            + "Layer 1: image 1 visible\n"
            + "Layer 2: image 2 visible\n"
            + "Layer 3: image 3 visible\n"
        , out.toString());
    File folder = new File("MultiLayeredImages/loadedLayeredImage");
    assertTrue(folder.exists());
    File file1 = new File("MultiLayeredImages/loadedLayeredImage/Layer 1.jpeg");
    assertTrue(file1.exists());
    File file2 = new File("MultiLayeredImages/loadedLayeredImage/Layer 2.png");
    assertTrue(file2.exists());
    File file3 = new File("MultiLayeredImages/loadedLayeredImage/Layer 3.ppm");
    assertTrue(file3.exists());
    File file4 = new File("MultiLayeredImages/loadedLayeredImage/Location.txt");
    assertTrue(file4.exists());

  }

  // test quit
  /*
  @Test
  public void testQuit() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader("quit"), out);
    controller.goProgram();
    assertEquals(0, model.numberOfLayers());
    assertEquals("quit program.\n"
        , out.toString());
  }

   */

  // test invalid command
  @Test
  public void testInvalidCommand() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader("quit343"), out);
    controller.goProgram();
    assertEquals(0, model.numberOfLayers());
    assertEquals("invalid command.\n"
        , out.toString());

  }

  // test empty command
  @Test
  public void testEmptyCommand() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    assertEquals(0, model.numberOfLayers());
    Controller controller = new Controller(model,
        new StringReader(""), out);
    controller.goProgram();
    assertEquals(0, model.numberOfLayers());
    assertEquals(""
        , out.toString());


  }


}
import static org.junit.Assert.assertEquals;

import controller.Features;
import controller.GUIController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Image;
import model.MultiLayerModel;
import model.MultiLayerModelImp;
import model.Pixel;
import org.junit.Test;
import view.IView;
import view.JFrameView;

/**
 * Test class to test the GUI Controller and its operations.
 */
public class GUIControllerTest {

  // tests invalid constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(null, null);
    assertEquals(0, model.numberOfLayers());
  }

  // test adding a layer
  @Test
  public void testAddLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.addLayer();
    controller.addLayer();
    assertEquals(3, model.numberOfLayers());
  }

  // tests removing a alyer
  @Test
  public void testRemoveLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.addLayer();
    controller.addLayer();
    assertEquals(3, model.numberOfLayers());
    controller.removeLayer();
    assertEquals(2, model.numberOfLayers());
    controller.removeLayer();
    controller.removeLayer();
    assertEquals(0, model.numberOfLayers());
  }

  // tests loading a alyer
  @Test
  public void testLoadLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    //controller.loadLayer();
  }

  // test loading multilayered image
  @Test
  public void testLoadAllLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    //controller.loadAllLayer();
  }

  // test blurring layer
  @Test
  public void testBlurLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    Image img = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setImage(model.getSpecificLayer(0), img);
    controller.blurLayer();
    assertEquals(new ArrayList<List<Pixel>>(
            Arrays.asList(new ArrayList<Pixel>(
                Arrays.asList(new Pixel(0.5, 0.5, 0.5))))),
        model.filter(img, 1, "blur"));

  }

  // test sharpening layer
  @Test
  public void testSharpenLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    Image img = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setImage(model.getSpecificLayer(0), img);
    controller.sharpenLayer();
    assertEquals(new ArrayList<List<Pixel>>(
            Arrays.asList(new ArrayList<Pixel>(
                Arrays.asList(new Pixel(2.0, 2.0, 2.0))))),
        model.filter(img, 1, "sharpen"));

  }

  // test greyscaling layer
  @Test
  public void testGreyScaleLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    Image img = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(10, 2, 3))))));
    model.setImage(model.getSpecificLayer(0), img);
    controller.greyscaleLayer();
    assertEquals(new ArrayList<List<Pixel>>(
            Arrays.asList(new ArrayList<Pixel>(
                Arrays.asList(new Pixel(3.773, 3.773, 3.773))))),
        model.transform(img, "greyscale"));

  }

  // test speia layer
  @Test
  public void testSepiaLayer() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    Image img = new Image(new ArrayList<List<Pixel>>(
        Arrays.asList(new ArrayList<Pixel>(Arrays.asList(new Pixel(2, 2, 2))))));
    model.setImage(model.getSpecificLayer(0), img);
    controller.sepiaLayer();
    assertEquals(new ArrayList<List<Pixel>>(
            Arrays.asList(new ArrayList<Pixel>(
                Arrays.asList(new Pixel(2.702, 2.406, 1.874))))),
        model.transform(img, "sepia"));

  }

  // test invisible layer
  @Test
  public void testInvisible() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    controller.invisible();
    assertEquals(false, model.getVisibility(model.getSpecificLayer(0)));

  }

  // test visible layer
  @Test
  public void testVisible() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    controller.invisible();
    assertEquals(false, model.getVisibility(model.getSpecificLayer(0)));
    controller.visible();
    assertEquals(true, model.getVisibility(model.getSpecificLayer(0)));

  }

  // test script
  @Test
  public void testScript() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    //controller.script();
  }

  // test save
  @Test
  public void testSave() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    //controller.save();
  }

  // test save all
  @Test
  public void testSaveAll() {
    MultiLayerModel model = new MultiLayerModelImp();
    IView view = new JFrameView();
    Features controller = new GUIController(model, view);

    assertEquals(0, model.numberOfLayers());
    controller.addLayer();
    assertEquals(1, model.numberOfLayers());
    controller.currentLayer(0);
    //controller.saveAll();
  }

  // test add layer action event
  @Test
  public void testFireAddLayerEventAndViewMethods() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireAddLayerEvent();

    assertEquals("Wriring works for addLayer", c_out.toString());
    assertEquals("Wriring works for setImageIconPath()Wriring works for "
        + "setImageIconBuffImg"
        + "Wriring works for renderMessage", v_out.toString());


  }

  // test remove layer action event
  @Test
  public void testFireRemoveLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireRemoveLayerEvent();

    assertEquals("Wriring works for removeLayer", c_out.toString());


  }

  // test current layer actione vent
  @Test
  public void testFireCurrentLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireCurrentLayerEvent();

    assertEquals("Wriring works for currentLayer", c_out.toString());


  }

  // test load layer event
  @Test
  public void testFireLoadLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireLoadLayerEvent();

    assertEquals("Wriring works for loadLayer", c_out.toString());


  }

  //test load all layer event
  @Test
  public void testFireLoadAllLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireLoadAllLayerEvent();

    assertEquals("Wriring works for loadAllLayer", c_out.toString());


  }

  // test blur layer event
  @Test
  public void testFireBlurLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireBlurLayerEvent();

    assertEquals("Wriring works for blurLayer", c_out.toString());


  }

  //test sharpen layer event
  @Test
  public void testFireSharpenLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireSharpenLayerEvent();

    assertEquals("Wriring works for sharpenLayer", c_out.toString());


  }

  // test greyscale layer event
  @Test
  public void testFireGreyscaleLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireGreyscaleLayerEvent();

    assertEquals("Wriring works for greyscaleLayer", c_out.toString());


  }

  // test sepia layer event
  @Test
  public void testFireSepiaLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireSepiaLayerEvent();

    assertEquals("Wriring works for sepiaLayer", c_out.toString());


  }

  // test visible layer event
  @Test
  public void testFireVisibleEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireVisibleEvent();

    assertEquals("Wriring works for visible", c_out.toString());


  }

  // test invisible event
  @Test
  public void testFireInvisibleEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireInvisibleEvent();

    assertEquals("Wriring works for invisible", c_out.toString());


  }

  // test script event
  @Test
  public void testFireScriptEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireScriptEvent();

    assertEquals("Wriring works for script", c_out.toString());


  }

  // test save event
  @Test
  public void testFireSaveEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireSaveEvent();

    assertEquals("Wriring works for save", c_out.toString());


  }

  // test save all event
  @Test
  public void testFireSaveAllEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireSaveAllEvent();

    assertEquals("Wriring works for saveAll", c_out.toString());


  }

  // test exit event
  @Test
  public void testFireExitEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    MultiLayerModel model = new MultiLayerModelImp();
    MockView mockView = new MockView(v_out);
    MockController mockController = new MockController(mockView, model, c_out);
    mockView.fireExitEvent();

    assertEquals("Wriring works for exitProgram", c_out.toString());


  }

}
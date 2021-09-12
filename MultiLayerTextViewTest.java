import static org.junit.Assert.assertEquals;

import java.io.IOException;
import model.MultiLayerModelImp;
import org.junit.Test;
import view.MultiLayerTextView;
import view.MultiLayerView;

/**
 * Tests for the View and its methods.
 */
public class MultiLayerTextViewTest {

  // tests renderLayeredImage()
  @Test
  public void testRenderLayeredImage() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    MultiLayerView view = new MultiLayerTextView(model, out);

    model.addLayer(model.createLayer(), 0);
    model.addLayer(model.createLayer(), 1);

    view.renderLayeredImage();

    assertEquals("Layer 1: image 1 visible\n"
        + "Layer 2: image 2 visible\n", out.toString());


  }

  // tests renderMessage()
  @Test
  public void testRenderMessage() {
    MultiLayerModelImp model = new MultiLayerModelImp();
    StringBuilder out = new StringBuilder();
    MultiLayerView view = new MultiLayerTextView(model, out);

    try {
      view.renderMessage("Hi");
    } catch (IOException ioException) {
      //s
    }

    assertEquals("Hi", out.toString());


  }
}
import controller.Features;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import model.MultiLayerModel;
import model.MultiLayerModelImp;
import view.IView;

/**
 * Class that represents a MockController for testing.
 */
public class MockController implements Features, ActionListener {

  private IView mockView;
  private MultiLayerModel model;
  private Appendable out;

  /**
   * Creates a MockController for this program.
   *
   * @param model    the MultiLayerModel
   * @param mockView the View
   * @param out      the Appendable
   */
  public MockController(IView mockView, MultiLayerModel model, Appendable out) {
    if (mockView == null || model == null || out == null) {
      throw new IllegalArgumentException();
    }

    this.mockView = mockView;
    this.model = model;
    this.out = out;
    mockView.setListener(this);
  }

  @Override
  public void addLayer() {
    try {
      this.out.append("Wriring works for addLayer");
      mockView.setImageIconPath(null, null);
      mockView.setImageIconBuffImg(null, null);
      mockView.renderMessage("Hi");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }
  }

  @Override
  public void removeLayer() {
    try {
      this.out.append("Wriring works for removeLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void currentLayer(int whichLayer) {
    model = new MultiLayerModelImp();
    try {
      this.out.append("Wriring works for currentLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void loadLayer() {
    model = new MultiLayerModelImp();
    try {
      this.out.append("Wriring works for loadLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void loadAllLayer() {
    try {
      this.out.append("Wriring works for loadAllLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void blurLayer() {
    try {
      this.out.append("Wriring works for blurLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void sharpenLayer() {
    try {
      this.out.append("Wriring works for sharpenLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void greyscaleLayer() {
    try {
      this.out.append("Wriring works for greyscaleLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void sepiaLayer() {
    try {
      this.out.append("Wriring works for sepiaLayer");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void visible() {
    try {
      this.out.append("Wriring works for visible");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void invisible() {
    try {
      this.out.append("Wriring works for invisible");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void script() {
    try {
      this.out.append("Wriring works for script");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void save() {
    try {
      this.out.append("Wriring works for save");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void saveAll() {
    try {
      this.out.append("Wriring works for saveAll");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void exitProgram() {
    try {
      this.out.append("Wriring works for exitProgram");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Not needed for testing
  }
}

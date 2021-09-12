import controller.Features;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import view.IView;

/**
 * Class that represents a MockView for testing.
 */
public class MockView implements IView {

  private Appendable out;
  private Features listener;

  /**
   * Creates a MockView for this program.
   *
   * @param out the Appendable
   */
  public MockView(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException();
    }
    this.out = out;
  }

  @Override
  public void setListener(ActionListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException();
    }
    this.listener = (Features) listener;
  }

  // just for testing
  @Override
  public JTextField getTextField() {
    return null;
  }

  // just for testing
  @Override
  public JLabel getCenterImage() {
    return null;

  }

  // just for testing
  @Override
  public Container getMainPanel() {
    return null;
  }

  // for testing: appends a message
  @Override
  public void setImageIconPath(JLabel imageLabel, String path) {
    try {
      this.out.append("Wriring works for setImageIconPath()");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }


  }

  // for testing: appends a message
  @Override
  public void setImageIconBuffImg(JLabel imageLabel, BufferedImage buffImg) {
    try {
      this.out.append("Wriring works for setImageIconBuffImg");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }


  }

  // for testing: appends a message
  @Override
  public void renderMessage(String message) {
    try {
      this.out.append("Wriring works for renderMessage");
    } catch (IOException ioException) {
      throw new IllegalStateException("Wiring not working properly");
    }


  }

  /**
   * Creates a addLayer event.
   */
  public void fireAddLayerEvent() {
    this.listener.addLayer();
  }

  /**
   * Creates a removeLayer event.
   */
  public void fireRemoveLayerEvent() {
    this.listener.removeLayer();
  }

  /**
   * Creates a currentLayer event.
   */
  public void fireCurrentLayerEvent() {
    this.listener.currentLayer(0);
  }

  /**
   * Creates a loadLayer event.
   */
  public void fireLoadLayerEvent() {
    this.listener.loadLayer();
  }

  /**
   * Creates a loadAllLayer event.
   */
  public void fireLoadAllLayerEvent() {
    this.listener.loadAllLayer();
  }

  /**
   * Creates a blurLayer event.
   */
  public void fireBlurLayerEvent() {
    this.listener.blurLayer();
  }

  /**
   * Creates a sharpenLayer event.
   */
  public void fireSharpenLayerEvent() {
    this.listener.sharpenLayer();
  }

  /**
   * Creates a greyscaleLayer event.
   */
  public void fireGreyscaleLayerEvent() {
    this.listener.greyscaleLayer();
  }

  /**
   * Creates a sepiaLayer event.
   */
  public void fireSepiaLayerEvent() {
    this.listener.sepiaLayer();
  }

  /**
   * Creates a visible event.
   */
  public void fireVisibleEvent() {
    this.listener.visible();
  }

  /**
   * Creates a invisible event.
   */
  public void fireInvisibleEvent() {
    this.listener.invisible();
  }

  /**
   * Creates a script event.
   */
  public void fireScriptEvent() {
    this.listener.script();
  }

  /**
   * Creates a save event.
   */
  public void fireSaveEvent() {
    this.listener.save();
  }

  /**
   * Creates a saveAll event.
   */
  public void fireSaveAllEvent() {
    this.listener.saveAll();
  }

  /**
   * Creates a exitProgram event.
   */
  public void fireExitEvent() {
    this.listener.exitProgram();
  }


}

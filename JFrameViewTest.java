import static org.junit.Assert.assertEquals;

import controller.GUIController;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.MultiLayerModelImp;
import org.junit.Test;
import view.JFrameView;

/**
 * Test class to test the JFrameView and its methods.
 */
public class JFrameViewTest {

  // test getTextField method
  @Test
  public void testGetTextField() {
    JFrameView view = new JFrameView();
    JTextField textField = new JTextField("");
    view.add(textField);

    assertEquals(textField.getText(), view.getTextField().getText());

  }

  // test getCenterImage method
  @Test
  public void testGetCenterImage() {
    JFrameView view = new JFrameView();
    JLabel centerImage = new JLabel("");
    view.add(centerImage);

    assertEquals(centerImage.getText(), view.getCenterImage().getText());

  }

  // test setImageIconPath method
  @Test
  public void testSetImageIconPath() {
    JFrameView view = new JFrameView();
    JLabel centerImage = new JLabel("");
    view.setImageIconPath(centerImage, "res/Koala.ppm");

    assertEquals(centerImage.getIcon().toString(), "res/Koala.ppm");

  }

  // test setImageIconBuffImg method
  @Test
  public void testSetImageIconBuffImg() {
    JFrameView view = new JFrameView();
    JLabel centerImage = new JLabel("");
    GUIController controller = new GUIController(new MultiLayerModelImp(), view);
    BufferedImage buffImg = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
    view.setImageIconBuffImg(centerImage, buffImg);

    assertEquals(centerImage.getIcon().getIconHeight(), 10);
    assertEquals(centerImage.getIcon().getIconWidth(), 10);

  }

}
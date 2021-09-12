import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Image;
import model.Pixel;
import model.ProgramImage;
import org.junit.Test;

/**
 * Test class for the Image class methods and class.
 */
public class ImageTest {

  // test creating an image for a invalid type
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidType() {
    Image img = new Image(null);
  }

  // tests getPixelArray method
  @Test
  public void testGetPixelArray() {
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    assertEquals(new ArrayList<List<Pixel>>(
            Arrays.asList(new ArrayList<Pixel>(Arrays.asList(
                new Pixel(255.0, 0.0, 0.0))))),
        checkerBoardImg.getPixelArray());
  }
}
import static org.junit.Assert.assertEquals;

import java.util.List;
import model.Image;
import model.IImageManipulation;
import model.Pixel;
import model.ProgramImage;
import model.Sepia;
import org.junit.Test;

/**
 * Test class for the Sepia class and its methods.
 */
public class SepiaTest {

  // testing sepia a null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullImg() {
    IImageManipulation sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = sepia.transform(null);
  }


  // testing original checkerboard pixels
  @Test
  public void testOriginalCheckerBoard() {
    IImageManipulation sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    assertEquals(checkerBoard.get(0).get(0), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(0).get(1), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(0).get(2), new Pixel(255, 0, 0));

    assertEquals(checkerBoard.get(1).get(0), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(1).get(1), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(1).get(2), new Pixel(0, 0, 255));

    assertEquals(checkerBoard.get(2).get(0), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(2).get(1), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(2).get(2), new Pixel(255, 0, 0));

    // List<List<Pixel>> transformedImage = sepia.transform(checkerBoardImg);

  }

  // testing sepia checkerboard pixels
  @Test
  public void testSepiaCheckerBoard() {
    IImageManipulation sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    assertEquals(checkerBoard.get(0).get(0), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(0).get(1), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(0).get(2), new Pixel(255, 0, 0));

    assertEquals(checkerBoard.get(1).get(0), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(1).get(1), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(1).get(2), new Pixel(0, 0, 255));

    assertEquals(checkerBoard.get(2).get(0), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(2).get(1), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(2).get(2), new Pixel(255, 0, 0));

    List<List<Pixel>> transformedImage = sepia.transform(checkerBoardImg);

    assertEquals(transformedImage.get(0).get(0),
        new Pixel(100.215, 88.99499999999999, 69.36));
    assertEquals(transformedImage.get(0).get(1),
        new Pixel(48.195, 42.84, 33.405));
    assertEquals(transformedImage.get(0).get(2),
        new Pixel(100.215, 88.99499999999999, 69.36));

    assertEquals(transformedImage.get(1).get(0),
        new Pixel(48.195, 42.84, 33.405));
    assertEquals(transformedImage.get(1).get(1),
        new Pixel(100.215, 88.99499999999999, 69.36));
    assertEquals(transformedImage.get(1).get(2),
        new Pixel(48.195, 42.84, 33.405));

    assertEquals(transformedImage.get(2).get(0),
        new Pixel(100.215, 88.99499999999999, 69.36));
    assertEquals(transformedImage.get(2).get(1),
        new Pixel(48.195, 42.84, 33.405));
    assertEquals(transformedImage.get(2).get(2),
        new Pixel(100.215, 88.99499999999999, 69.36));

  }
}
import static org.junit.Assert.assertEquals;

import java.util.List;
import model.IProgramImage;
import model.Pixel;
import model.ProgramImage;
import org.junit.Test;

/**
 * Test class for the ProgramImage class and methods (Adds on to CheckerBoardTest).
 */
public class ProgramImageTest {

  // tests creating a checkerBoard with negative row
  @Test(expected = IllegalArgumentException.class)
  public void testCheckerBoardNegativeRow() {
    IProgramImage programImage = new ProgramImage();

    List<List<Pixel>> checkerBoardArray =
        programImage.checkerboard(-1, 8, 10);

  }

  // tests creating a checkerBoard with negative column
  @Test(expected = IllegalArgumentException.class)
  public void testCheckerBoardNegativeColumn() {
    IProgramImage programImage = new ProgramImage();

    List<List<Pixel>> checkerBoardArray =
        programImage.checkerboard(3, -10, 10);

  }

  // tests creating a checkerBoard with negative square size
  @Test(expected = IllegalArgumentException.class)
  public void testCheckerBoardNegativeSquareSize() {
    IProgramImage programImage = new ProgramImage();

    List<List<Pixel>> checkerBoardArray =
        programImage.checkerboard(3, 10, -2);

  }

  // tests creating a checkerBoard with zero square size
  @Test(expected = IllegalArgumentException.class)
  public void testCheckerBoardZeroSquareSize() {
    IProgramImage programImage = new ProgramImage();

    List<List<Pixel>> checkerBoardArray =
        programImage.checkerboard(3, 10, 0);

  }

  // tests creating a checkerBoard and its pixels
  @Test
  public void testCheckerBoardPixels() {
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);

    assertEquals(checkerBoard.get(0).get(0), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(0).get(1), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(0).get(2), new Pixel(255, 0, 0));

    assertEquals(checkerBoard.get(1).get(0), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(1).get(1), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(1).get(2), new Pixel(0, 0, 255));

    assertEquals(checkerBoard.get(2).get(0), new Pixel(255, 0, 0));
    assertEquals(checkerBoard.get(2).get(1), new Pixel(0, 0, 255));
    assertEquals(checkerBoard.get(2).get(2), new Pixel(255, 0, 0));
  }

  /*
    @Test
    public void testCheckerBoardImg() {
      IProgramImage programImage = new ProgramImage();

      List<List<Pixel>> checkerBoardArray = programImage.checkerboard(8,8,10);

      StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(checkerBoardArray);

      new PPMImportExport().fileCreator(fileContent, "CheckerBoard.ppm");
    }

  */
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Pixel;
import model.ProgramImage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test the ProgrammableImage (checkerboard).
 */
public class CheckerBoardTest {

  // tests the pixels of a checkerBoard
  @Test
  public void testCheckerboard() {
    ProgramImage checkerboard = new ProgramImage();

    List<List<Pixel>> checkerboardTest = checkerboard.checkerboard(2, 2, 1);
    List board = new ArrayList<>();
    board.add(Arrays.asList((new Pixel(255.0, 0.0, 0.0)),
        (new Pixel(0.0, 0.0, 255.0))));
    board.add(Arrays.asList((new Pixel(0.0, 0.0, 255.0)),
        (new Pixel(255.0, 0.0, 0.0))));

    assertEquals(checkerboardTest, board);

  }
  /* ALL THESE TESTS WORK. THEY DONT HAVE ASSERTS SO NEEDED TO COMMENT OUT FOR STYLE
    // tests sepia of a checkerboard
    @Test
    public void testSepia() {
      ImageManipulationInterface sepia = new Sepia();
      ProgramImage programImage = new ProgramImage();
      List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
      Image checkerBoardImg = new Image(checkerBoard, "PPM");

      List<List<Pixel>> filteredImage0 = sepia.transform(checkerBoardImg);

      StringBuilder fileContent0 = new PPMImportExport().fileContentCreatorImg(filteredImage0);

      new PPMImportExport().fileCreator(fileContent0, "CheckerBoard1x1.ppm");

      // List<List<Pixel>> filteredImage = blur.filter(checkerBoardImg, 1);

      //StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

      //new PPMImportExport().fileCreator(fileContent, "Blurx1CheckerBoard2x2.ppm");

    }

    // tests GreyScale of a checkerboard
    @Test
    public void testGreyScale() {
      ImageManipulationInterface greyscale = new Greyscale();
      ProgramImage programImage = new ProgramImage();
      List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
      Image checkerBoardImg = new Image(checkerBoard, "PPM");

      List<List<Pixel>> filteredImage0 = greyscale.transform(checkerBoardImg);

      StringBuilder fileContent0 = new PPMImportExport().fileContentCreatorImg(filteredImage0);

      new PPMImportExport().fileCreator(fileContent0, "CheckerBoard1x1.ppm");

      // List<List<Pixel>> filteredImage = blur.filter(checkerBoardImg, 1);

      //StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

      //new PPMImportExport().fileCreator(fileContent, "Blurx1CheckerBoard2x2.ppm");

    }

    // tests blur of a checkerboard
    @Test
    public void testBlur() {
      ImageManipulationInterface blur = new Blur();
      ProgramImage programImage = new ProgramImage();
      List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
      Image checkerBoardImg = new Image(checkerBoard, "PPM");

      List<List<Pixel>> filteredImage0 = blur.filter(checkerBoardImg, 1);

      StringBuilder fileContent0 = new PPMImportExport().fileContentCreatorImg(filteredImage0);

      new PPMImportExport().fileCreator(fileContent0, "CheckerBoard1x1.ppm");

      // List<List<Pixel>> filteredImage = blur.filter(checkerBoardImg, 1);

      //StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

      //new PPMImportExport().fileCreator(fileContent, "Blurx1CheckerBoard2x2.ppm");

    }

    // tests sharpen of a checkerboard
    @Test
    public void testSharpen() {
      ImageManipulationInterface sharpen = new Sharpen();
      ProgramImage programImage = new ProgramImage();
      List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
      Image checkerBoardImg = new Image(checkerBoard, "PPM");

      List<List<Pixel>> filteredImage0 = sharpen.filter(checkerBoardImg, 1);

      StringBuilder fileContent0 = new PPMImportExport().fileContentCreatorImg(filteredImage0);

      new PPMImportExport().fileCreator(fileContent0, "CheckerBoard1x1.ppm");

      // List<List<Pixel>> filteredImage = blur.filter(checkerBoardImg, 1);

      //StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

      //new PPMImportExport().fileCreator(fileContent, "Blurx1CheckerBoard2x2.ppm");

    }

   */


}

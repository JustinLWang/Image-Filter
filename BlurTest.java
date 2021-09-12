import java.util.List;
import model.Blur;
import model.IImageManipulation;
import model.Image;
import model.Pixel;
import model.ProgramImage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test the Blur class/operation.
 */
public class BlurTest {

  // tests blurring a null img
  @Test(expected = IllegalArgumentException.class)
  public void testNullImg() {
    IImageManipulation blur = new Blur();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = blur.filter(null, 0);
  }

  // tests blurring a negative multiplier
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeMultiplier() {
    IImageManipulation blur = new Blur();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = blur.filter(checkerBoardImg, -1);
  }


  // tests a 0 mulitplier
  @Test
  public void testMultiplierx0() {
    IImageManipulation blur = new Blur();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = blur.filter(checkerBoardImg, 0);

    assertEquals(filteredImage0.get(0).get(0), new Pixel(255, 0, 0));
    assertEquals(filteredImage0.get(0).get(1), new Pixel(0, 0, 255));
    assertEquals(filteredImage0.get(0).get(2), new Pixel(255, 0, 0));

    assertEquals(filteredImage0.get(1).get(0), new Pixel(0, 0, 255));
    assertEquals(filteredImage0.get(1).get(1), new Pixel(255, 0, 0));
    assertEquals(filteredImage0.get(1).get(2), new Pixel(0, 0, 255));

    assertEquals(filteredImage0.get(2).get(0), new Pixel(255, 0, 0));
    assertEquals(filteredImage0.get(2).get(1), new Pixel(0, 0, 255));
    assertEquals(filteredImage0.get(2).get(2), new Pixel(255, 0, 0));

  }

  // tests a x1 multiplier
  @Test
  public void testMultiplierx1() {
    IImageManipulation blur = new Blur();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImagex0 = blur.filter(checkerBoardImg, 0);

    assertEquals(filteredImagex0.get(0).get(0), new Pixel(255, 0, 0));
    assertEquals(filteredImagex0.get(0).get(1), new Pixel(0, 0, 255));
    assertEquals(filteredImagex0.get(0).get(2), new Pixel(255, 0, 0));

    assertEquals(filteredImagex0.get(1).get(0), new Pixel(0, 0, 255));
    assertEquals(filteredImagex0.get(1).get(1), new Pixel(255, 0, 0));
    assertEquals(filteredImagex0.get(1).get(2), new Pixel(0, 0, 255));

    assertEquals(filteredImagex0.get(2).get(0), new Pixel(255, 0, 0));
    assertEquals(filteredImagex0.get(2).get(1), new Pixel(0, 0, 255));
    assertEquals(filteredImagex0.get(2).get(2), new Pixel(255, 0, 0));

    List<List<Pixel>> filteredImagex1 = blur.filter(checkerBoardImg, 1);

    assertEquals(filteredImagex1.get(0).get(0), new Pixel(79.6875, 0.0, 63.75));
    assertEquals(filteredImagex1.get(0).get(1), new Pixel(95.625, 0.0, 95.625));
    assertEquals(filteredImagex1.get(0).get(2), new Pixel(79.6875, 0.0, 63.75));

    assertEquals(filteredImagex1.get(1).get(0), new Pixel(95.625, 0.0, 95.625));
    assertEquals(filteredImagex1.get(1).get(1), new Pixel(127.5, 0.0, 127.5));
    assertEquals(filteredImagex1.get(1).get(2), new Pixel(95.625, 0.0, 95.625));

    assertEquals(filteredImagex1.get(2).get(0), new Pixel(79.6875, 0.0, 63.75));
    assertEquals(filteredImagex1.get(2).get(1), new Pixel(95.625, 0.0, 95.625));
    assertEquals(filteredImagex1.get(2).get(2), new Pixel(79.6875, 0.0, 63.75));

  }

  // tests a x2 multiplier
  @Test
  public void testMultiplierx2() {
    IImageManipulation blur = new Blur();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImagex0 = blur.filter(checkerBoardImg, 0);

    assertEquals(filteredImagex0.get(0).get(0), new Pixel(255, 0, 0));
    assertEquals(filteredImagex0.get(0).get(1), new Pixel(0, 0, 255));
    assertEquals(filteredImagex0.get(0).get(2), new Pixel(255, 0, 0));

    assertEquals(filteredImagex0.get(1).get(0), new Pixel(0, 0, 255));
    assertEquals(filteredImagex0.get(1).get(1), new Pixel(255, 0, 0));
    assertEquals(filteredImagex0.get(1).get(2), new Pixel(0, 0, 255));

    assertEquals(filteredImagex0.get(2).get(0), new Pixel(255, 0, 0));
    assertEquals(filteredImagex0.get(2).get(1), new Pixel(0, 0, 255));
    assertEquals(filteredImagex0.get(2).get(2), new Pixel(255, 0, 0));

    List<List<Pixel>> filteredImagex1 = blur.filter(checkerBoardImg, 1);

    assertEquals(filteredImagex1.get(0).get(0), new Pixel(79.6875, 0.0, 63.75));
    assertEquals(filteredImagex1.get(0).get(1), new Pixel(95.625, 0.0, 95.625));
    assertEquals(filteredImagex1.get(0).get(2), new Pixel(79.6875, 0.0, 63.75));

    assertEquals(filteredImagex1.get(1).get(0), new Pixel(95.625, 0.0, 95.625));
    assertEquals(filteredImagex1.get(1).get(1), new Pixel(127.5, 0.0, 127.5));
    assertEquals(filteredImagex1.get(1).get(2), new Pixel(95.625, 0.0, 95.625));

    assertEquals(filteredImagex1.get(2).get(0), new Pixel(79.6875, 0.0, 63.75));
    assertEquals(filteredImagex1.get(2).get(1), new Pixel(95.625, 0.0, 95.625));
    assertEquals(filteredImagex1.get(2).get(2), new Pixel(79.6875, 0.0, 63.75));

    List<List<Pixel>> filteredImagex2 = blur.filter(checkerBoardImg, 2);

    assertEquals(filteredImagex2.get(0).get(0), new Pixel(51.796875, 0.0, 47.8125));
    assertEquals(filteredImagex2.get(0).get(1), new Pixel(71.71875, 0.0, 67.734375));
    assertEquals(filteredImagex2.get(0).get(2), new Pixel(51.796875, 0.0, 47.8125));

    assertEquals(filteredImagex2.get(1).get(0), new Pixel(71.71875, 0.0, 67.734375));
    assertEquals(filteredImagex2.get(1).get(1), new Pixel(99.609375, 0.0, 95.625));
    assertEquals(filteredImagex2.get(1).get(2), new Pixel(71.71875, 0.0, 67.734375));

    assertEquals(filteredImagex2.get(2).get(0), new Pixel(51.796875, 0.0, 47.8125));
    assertEquals(filteredImagex2.get(2).get(1), new Pixel(71.71875, 0.0, 67.734375));
    assertEquals(filteredImagex2.get(2).get(2), new Pixel(51.796875, 0.0, 47.8125));

  }

  // tests blurring a checkerBoard

  /*
  @Test
  public void testBlurCheckerBoardImage() {
    IImageManipulation blur = new Blur();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(10, 10, 8);
    Image checkerBoardImg = new Image(checkerBoard, "PPM");

    List<List<Pixel>> filteredImage = blur.filter(checkerBoardImg, 4);

    StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

    new PPMImportExport().fileCreator(fileContent, "Blurx4CheckerBoard.ppm");


  }

   */

  // ALL THESE TESTS WERE USED TO CREATE THE RES IMAGES AND FILES

  /*
  // blur a koala image
  @Test
  public void testBlurKoala() {
    Image img = new Image("src/model/Koala.ppm", "PPM");
    ImageManipulationInterface blur = new Blur();

    List<List<Pixel>> filteredImage = blur.filter(img, 4);
   // List<List<Pixel>> filteredx2Image = blur.filter("Blurry*1koala.ppm");
   // List<List<Pixel>> filteredx3Image = blur.filter("Blurry*2koala.ppm");
   // List<List<Pixel>> filteredx4Image = blur.filter("Blurry*3koala.ppm");


    StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

    new PPMImportExport().fileCreator(fileContent, "Blurx4koala.ppm");
  }

  */

  /*
  // test for blurred shoe image
  @Test
  public void testBlurShoes() {
    Image img = new Image("res/Shoes.ppm", "PPM");
    ImageManipulationInterface blur = new Blur();

    List<List<Pixel>> filteredImage = blur.filter(img, 4);
    // List<List<Pixel>> filteredx2Image = blur.filter("Blurry*1koala.ppm");
    // List<List<Pixel>> filteredx3Image = blur.filter("Blurry*2koala.ppm");
    // List<List<Pixel>> filteredx4Image = blur.filter("Blurry*3koala.ppm");


    StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

    new PPMImportExport().fileCreator(fileContent, "Blurx4Shoes.ppm");
  }

   */

  /*
  // test for a canned pie image
  @Test
  public void testBlurCannedPie() {
    ImportExportInterface fileType = FileType.typeExtractor("PPM");
    List<List<Pixel>> pixelArray = fileType.readFile("res/CannedPie.ppm");
    Image img = new Image(pixelArray);
    IImageManipulation blur = new Blur();

    List<List<Pixel>> filteredImage = blur.filter(img, 4);
    // List<List<Pixel>> filteredx2Image = blur.filter("Blurry*1koala.ppm");
    // List<List<Pixel>> filteredx3Image = blur.filter("Blurry*2koala.ppm");
    // List<List<Pixel>> filteredx4Image = blur.filter("Blurry*3koala.ppm");


    StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

    new PPMImportExport().fileCreator(fileContent, "Blurx4CannedPie.ppm");
  }

   */


}
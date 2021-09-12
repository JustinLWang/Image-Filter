import static org.junit.Assert.assertEquals;

import java.util.List;
import model.Greyscale;
import model.Image;
import model.IImageManipulation;
import model.Pixel;
import model.ProgramImage;
import org.junit.Test;

/**
 * Test class for the GreyScale class and operations.
 */
public class GreyscaleTest {

  // tests a null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullImg() {
    IImageManipulation greyscale = new Greyscale();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(3, 3, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = greyscale.transform(null);
  }

  // tests an original checkerboard pixels
  @Test
  public void testOriginalCheckerBoard() {
    IImageManipulation greyscale = new Greyscale();
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

    // List<List<Pixel>> transformedImage = greyscale.transform(checkerBoardImg);

  }

  // tests a greyscale on a checkerboard pixel values
  @Test
  public void testGreyScaleCheckerBoard() {
    IImageManipulation greyscale = new Greyscale();
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

    List<List<Pixel>> transformedImage = greyscale.transform(checkerBoardImg);

    assertEquals(transformedImage.get(0).get(0), new Pixel(54.213, 54.213, 54.213));
    assertEquals(transformedImage.get(0).get(1), new Pixel(18.411, 18.411, 18.411));
    assertEquals(transformedImage.get(0).get(2), new Pixel(54.213, 54.213, 54.213));

    assertEquals(transformedImage.get(1).get(0), new Pixel(18.411, 18.411, 18.411));
    assertEquals(transformedImage.get(1).get(1), new Pixel(54.213, 54.213, 54.213));
    assertEquals(transformedImage.get(1).get(2), new Pixel(18.411, 18.411, 18.411));

    assertEquals(transformedImage.get(2).get(0), new Pixel(54.213, 54.213, 54.213));
    assertEquals(transformedImage.get(2).get(1), new Pixel(18.411, 18.411, 18.411));
    assertEquals(transformedImage.get(2).get(2), new Pixel(54.213, 54.213, 54.213));

  }

  // grey scale of a shoes image
  /*
  @Test
  public void testGreyScaleShoes() {
    ImportExportInterface fileType = FileType.typeExtractor("PPM");
    List<List<Pixel>> pixelArray = fileType.readFile("res/CannedPie.ppm");
    Image img = new Image(pixelArray);
    IImageManipulation greyscale = new Greyscale();

    List<List<Pixel>> filteredImage = greyscale.transform(img);
    // List<List<Pixel>> filteredx2Image = blur.filter("Blurry*1koala.ppm");
    // List<List<Pixel>> filteredx3Image = blur.filter("Blurry*2koala.ppm");
    // List<List<Pixel>> filteredx4Image = blur.filter("Blurry*3koala.ppm");


    StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

    new PPMImportExport().fileCreator(fileContent, "GreyScaleShoes.ppm");
  }

   */
  /*
  // greyscale of canned pie image
  @Test
  public void testGreyScaleCannedPie() {
    Image img = new Image("res/CannedPie.ppm", "PPM");
    ImageManipulationInterface greyscale = new Greyscale();

    List<List<Pixel>> filteredImage = greyscale.transform(img);
    // List<List<Pixel>> filteredx2Image = blur.filter("Blurry*1koala.ppm");
    // List<List<Pixel>> filteredx3Image = blur.filter("Blurry*2koala.ppm");
    // List<List<Pixel>> filteredx4Image = blur.filter("Blurry*3koala.ppm");


    StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(filteredImage);

    new PPMImportExport().fileCreator(fileContent, "GreyScaleCannedPie.ppm");
  }

   */

}
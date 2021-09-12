import static org.junit.Assert.assertEquals;

import controller.ImportExportInterface;
import controller.PPMImportExport;
import java.util.List;
import model.Image;
import model.IImageManipulation;
import model.Pixel;
import model.ProgramImage;
import model.Sepia;
import org.junit.Test;

/**
 * Test class for the PPMImportExport class methods and class.
 */
public class PPMImportExportTest {

  // tests reading a null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullReadFile() {
    ImportExportInterface ppmio = new PPMImportExport();
    ppmio.readFile(null);
  }


  // tests creating a null file
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileCreator() {
    IImageManipulation sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = sepia.transform(checkerBoardImg);

    ImportExportInterface ppmio = new PPMImportExport();

    ppmio.fileCreator(null, null, null);


  }

  /*
  // tests creating a file
  @Test
  public void FileCreator() {
    ImageManipulationInterface sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
    Image checkerBoardImg = new Image(checkerBoard, "PPM");

    List<List<Pixel>> filteredImage0 = sepia.transform(checkerBoardImg);

    ImportExportInterface ppmio = new PPMImportExport();

    StringBuilder fileContent0 = new PPMImportExport().fileContentCreatorImg(filteredImage0);

    //new PPMImportExport().fileCreator(fileContent0, "CheckerBoard1x1.ppm");

    //assertEquals(true, Files.exists("res/CheckerBoard1x1.ppm"));

  }

   */


  // tests creating file content with null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileContentCreatorImg() {
    IImageManipulation sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = sepia.transform(checkerBoardImg);
    ImportExportInterface ppmio = new PPMImportExport();

    ppmio.fileContentCreatorImg(null);

  }

  // tests creating file content with  a image
  @Test
  public void testFileContentCreatorImg() {
    IImageManipulation sepia = new Sepia();
    ProgramImage programImage = new ProgramImage();
    List<List<Pixel>> checkerBoard = programImage.checkerboard(1, 1, 1);
    Image checkerBoardImg = new Image(checkerBoard);

    List<List<Pixel>> filteredImage0 = sepia.transform(checkerBoardImg);
    ImportExportInterface ppmio = new PPMImportExport();

    assertEquals(0,
        ppmio.fileContentCreatorImg(filteredImage0).compareTo(new StringBuilder("P3\n"
            + "1 1\n"
            + "255\n"
            + "100\n"
            + "88\n"
            + "69\n")));


  }


}
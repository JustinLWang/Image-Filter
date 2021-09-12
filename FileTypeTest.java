import static org.junit.Assert.assertEquals;

import controller.PPMImportExport;
import controller.FileType;
import org.junit.Test;

/**
 * Test class for the FileType methods and class.
 */
public class FileTypeTest {

  // tests for a null type
  @Test(expected = IllegalArgumentException.class)
  public void testNullTypeExtractor() {
    FileType.typeExtractor(null);
  }

  // tests for a invalid type
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTypeExtractor() {
    FileType.typeExtractor("PPOSM");
  }

  // test for a PPM type (typeExtractor)
  @Test
  public void testTypeExtractor() {
    assertEquals(PPMImportExport.class, FileType.typeExtractor("PPM").getClass());
  }

}
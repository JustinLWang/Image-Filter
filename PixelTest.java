import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Pixel;
import org.junit.Test;

/**
 * Test class for pixel: unit tests to ensure that pixels can be made correctly and the methods
 * within the pixel class work properly.
 */
public class PixelTest {

  Pixel red1 = new Pixel(255.0, 0.0, 0.0);
  Pixel red2 = new Pixel(255, 0, 0);
  Pixel green = new Pixel(0, 255, 0);
  Pixel blue = new Pixel(0, 0, 255);
  Pixel violet = new Pixel(238, 130, 238);
  Pixel orchid = new Pixel(218, 112, 214);

  // tests to check that the method toString works correctly
  @Test
  public void testToString() {
    assertEquals(red1.toString(), "255.0 0.0 0.0");
    assertEquals(violet.toString(), "238.0 130.0 238.0");
    assertEquals(green.toString(), "0.0 255.0 0.0");
  }

  // tests to check that the overridden method equals works correctly
  @Test
  public void testEquals() {
    assertTrue(red1.equals(red2));
    assertFalse(red1.equals(green));
    assertFalse(orchid.equals(blue));
    assertFalse(red2.equals("exam1bad"));
  }

  // tests to check that the overridden method hashCode works correctly
  @Test
  public void testHashCode() {
    assertEquals(red1.hashCode(), 255000000);
    assertEquals(blue.hashCode(), 255);
    assertEquals(orchid.hashCode(), 218112214);
  }

  // tests to check the getter method getRed() works correctly
  @Test
  public void testGetRed() {
    assertEquals(this.red1.getRed(), 255.0, 0.1);
    assertEquals(this.orchid.getRed(), 218.0, 0.1);
    assertEquals(this.blue.getRed(), 0.0, 0.1);
  }

  // tests to check the getter method getGreen() works correctly
  @Test
  public void testGetGreen() {
    assertEquals(this.red1.getGreen(), 0.0, 0.1);
    assertEquals(this.orchid.getGreen(), 112.0, 0.1);
    assertEquals(this.green.getGreen(), 255.0, 0.1);
  }

  // tests to check the getter method getBlue() works correctly
  @Test
  public void testGetBlue() {
    assertEquals(this.red1.getBlue(), 0.0, 0.1);
    assertEquals(this.orchid.getBlue(), 214.0, 0.1);
    assertEquals(this.blue.getBlue(), 255.0, 0.1);
  }

  // tests to check the exception with malformed pixels when the red field is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMalformedPixelRedTooLarge() {

    Pixel badPixel = new Pixel(257.0, 3.0, 0.0);
  }

  // tests to check the exception with malformed pixels when the red field is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMalformedPixelRedTooSmall() {
    Pixel badPixel = new Pixel(-3.0, 3.0, 0.0);
  }

  // tests to check the exception with malformed pixels when the green field is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMalformedPixelGreenTooLarge() {

    Pixel badPixel = new Pixel(255.0, 3234.0, 0.0);
  }

  // tests to check the exception with malformed pixels when the green field is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMalformedPixelGreenTooSmall() {

    Pixel badPixel = new Pixel(3.0, -3.0, 0.0);
  }

  // tests to check the exception with malformed pixels when the blue field is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMalformedPixelBlueTooLarge() {

    Pixel badPixel = new Pixel(27.0, 3.0, 13423.0);
  }

  // tests to check the exception with malformed pixels when the blue field is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMalformedPixelBlueTooSmall() {

    Pixel badPixel = new Pixel(3.0, 3.0, -10.0);
  }


}

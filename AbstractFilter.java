package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that holds methods required for filtering a image.
 */
public abstract class AbstractFilter implements IImageManipulation {

  /**
   * Filters the given image by the given multiplier.
   *
   * @param img        Image to filter
   * @param multiplier number of times to filter
   * @return the 2D array of pixels of the filtered image
   */
  public List<List<Pixel>> filter(Image img, int multiplier) {
    if (img == null || multiplier < 0) {
      throw new IllegalArgumentException("invalid paramaters.");
    }
    if (multiplier == 0) {
      return img.getPixelArray();
    }
    // Need some way to get pixels and sepagetrate into 3 different array (red, green, blue)
    List<List<Double>> redArray = splitChannels(img, "red");
    List<List<Double>> greenArray = splitChannels(img, "green");
    List<List<Double>> blueArray = splitChannels(img, "blue");
    // call helper and do math
    List<List<Double>> alteredRed = applyFilter(redArray,
        getKernel()); // applies filter to Array of all the RED pixel values
    List<List<Double>> alteredGreen = applyFilter(greenArray, getKernel());
    List<List<Double>> alteredBlue = applyFilter(blueArray, getKernel());

    for (int i = 1; i < multiplier; i++) {
      alteredRed = applyFilter(alteredRed, getKernel());
      alteredGreen = applyFilter(alteredGreen, getKernel());
      alteredBlue = applyFilter(alteredBlue, getKernel());

    }
    // call helper that combines 3 2D arrays into one Array of Pixels
    return filteredImage(alteredRed, alteredGreen, alteredBlue);
  }

  /**
   * Applies the Filter to the image's channels.
   *
   * @param channelArray channel to apply filter
   * @param kernel       kernel to apply
   * @return the 2D array of Doubles of the filtered channel
   */
  protected abstract List<List<Double>> applyFilter(List<List<Double>> channelArray, Matrix kernel);

  /**
   * Returns the kernel of the class.
   */
  protected abstract Matrix getKernel();

  /**
   * Private helper method to put the 2D lists of colors back into a 2D list of pixels.
   *
   * @param red   the 2d red array
   * @param green the 2d green array
   * @param blue  the 2d blue array
   * @return the 2d pixel array using the given 2d color arrays
   */
  private List<List<Pixel>> filteredImage(List<List<Double>> red, List<List<Double>> green,
      List<List<Double>> blue) {
    if (red == null || green == null || blue == null) {
      throw new IllegalArgumentException("null parameters.");
    }

    List<List<Pixel>> filteredImage = new ArrayList<List<Pixel>>();
    for (int i = 0; i < red.size(); i++) {
      filteredImage.add(new ArrayList<Pixel>());
    }
    for (int i = 0; i < red.size(); i++) {
      for (int j = 0; j < red.get(i).size(); j++) {
        Pixel alteredPixel = new Pixel(red.get(i).get(j), green.get(i).get(j), blue.get(i).get(j));
        filteredImage.get(i).add(alteredPixel);
      }
    }
    return filteredImage;
  }

  /**
   * A method meant to split an image's 2d pixel array into separate channels depending on the color
   * wanted.
   *
   * @param img     the image's 2d pixel array meant to be split
   * @param channel the color channel wanted
   * @return the 2d array of the color channel wanted
   */
  public static List<List<Double>> splitChannels(Image img, String channel) {
    if (img == null || channel == null) {
      throw new IllegalArgumentException("null parameters.");
    }
    List<List<Double>> channelArray = new ArrayList<List<Double>>();
    for (int i = 0; i < img.getPixelArray().size(); i++) {
      channelArray.add(new ArrayList<Double>());
      for (int j = 0; j < img.getPixelArray().get(i).size(); j++) {
        switch (channel) {
          case "red":
            channelArray.get(i).add(img.getPixelArray().get(i).get(j).getRed());
            break;
          case "green":
            channelArray.get(i).add(img.getPixelArray().get(i).get(j).getGreen());
            break;
          case "blue":
            channelArray.get(i).add(img.getPixelArray().get(i).get(j).getBlue());
            break;
          default:
            throw new IllegalArgumentException("invalid channel.");
        }
      }
    }
    return channelArray;
  }

  @Override
  public List<List<Pixel>> transform(Image img) {
    return null;
  }
}

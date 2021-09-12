package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the blur operation. Holds methods and implements methods needed to Blur a image
 * properly.
 */
public class Blur extends AbstractFilter {

  private final Matrix kernel;

  /**
   * Constructor for the Blur class; creates the matrix for the blur operation.
   */
  public Blur() {
    List blurKernel = new ArrayList<>();
    blurKernel.add(Arrays.asList(0.0625, 0.125, 0.0625));
    blurKernel.add(Arrays.asList(0.125, 0.25, 0.125));
    blurKernel.add(Arrays.asList(0.0625, 0.125, 0.0625));

    this.kernel = new Matrix(blurKernel);
  }

  /**
   * Applies the Blur Filter to the image's channels.
   *
   * @param channelArray channel to apply filter
   * @param kernel       kernel to apply
   * @return the 2D Array of Double of the filtered channel
   */

  protected List<List<Double>> applyFilter(List<List<Double>> channelArray, Matrix kernel) {
    if (channelArray == null || kernel == null) {
      throw new IllegalArgumentException("null parameter.");
    }

    List<List<Double>> newChannelArray = new ArrayList<List<Double>>();
    for (int i = 0; i < channelArray.size(); i++) {
      newChannelArray.add(new ArrayList<Double>());
    }
    for (int i = 0; i < channelArray.size(); i++) {
      for (int j = 0; j < channelArray.get(i).size(); j++) {
        double right = 0;
        double left = 0;
        double top = 0;
        double topRight = 0;
        double topLeft = 0;
        double bottom = 0;
        double bottomRight = 0;
        double bottomLeft = 0;

        double valueChanging = channelArray.get(i).get(j) * kernel.getValue(1, 1);

        //Middle Row (based on kernel not Img)
        try {
          right = channelArray.get(i).get(j + 1) * kernel.getValue(1, 2);
        } catch (IndexOutOfBoundsException e) {
          // Want to ignore this case (don't throw an exception)
        }
        try {
          left = channelArray.get(i).get(j - 1) * kernel.getValue(1, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        //Top Row
        try {
          top = channelArray.get(i - 1).get(j) * kernel.getValue(0, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          topRight = channelArray.get(i - 1).get(j + 1) * kernel.getValue(0, 2);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          topLeft = channelArray.get(i - 1).get(j - 1) * kernel.getValue(0, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        //Bottom Row
        try {
          bottom = channelArray.get(i + 1).get(j) * kernel.getValue(2, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottomRight = channelArray.get(i + 1).get(j + 1) * kernel.getValue(2, 2);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottomLeft = channelArray.get(i + 1).get(j - 1) * kernel.getValue(2, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }

        double newValue =
            valueChanging + right + left + top + topRight + topLeft + bottom + bottomRight
                + bottomLeft;

        if (newValue < 0) {
          newValue = 0;
        }

        if (newValue > 255) {
          newValue = 255;
        }

        newChannelArray.get(i).add(newValue);
      }
    }
    return newChannelArray;
  }

  // gets the blur kernel
  protected Matrix getKernel() {
    return this.kernel;
  }


}

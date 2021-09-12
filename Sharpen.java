package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the sharpen operation.
 */
public class Sharpen extends AbstractFilter {

  private final Matrix kernel;

  /**
   * Constructor for the Sharpen class; creates the matrix for the sharpen operation.
   */
  public Sharpen() {
    List sharpenKernel = new ArrayList<>();
    sharpenKernel.add(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125));
    sharpenKernel.add(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125));
    sharpenKernel.add(Arrays.asList(-0.125, 0.25, 1.0, 0.25, -0.125));
    sharpenKernel.add(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125));
    sharpenKernel.add(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125));

    this.kernel = new Matrix(sharpenKernel);
  }

  /**
   * Applies the Sharpen Filter to the image's channels.
   *
   * @param channelArray channel to apply filter
   * @param kernel       kernel to apply
   * @return the 2D array of Double of the filtered channel
   */
  protected List<List<Double>> applyFilter(List<List<Double>> channelArray, Matrix kernel) {
    if (channelArray == null || kernel == null) {
      throw new IllegalArgumentException("null parameters.");
    }

    List<List<Double>> newChannelArray = new ArrayList<List<Double>>();
    for (int i = 0; i < channelArray.size(); i++) {
      newChannelArray.add(new ArrayList<Double>());
    }
    for (int i = 0; i < channelArray.size(); i++) {
      for (int j = 0; j < channelArray.get(i).size(); j++) {

        double right = 0;
        double right2 = 0;
        double left = 0;
        double left2 = 0;

        double top = 0;
        double topRight = 0;
        double topRight2 = 0;
        double topLeft = 0;
        double topLeft2 = 0;

        double top2 = 0;
        double top2Right = 0;
        double top2Right2 = 0;
        double top2Left = 0;
        double top2Left2 = 0;

        double bottom = 0;
        double bottomRight = 0;
        double bottomRight2 = 0;
        double bottomLeft = 0;
        double bottomLeft2 = 0;

        double bottom2 = 0;
        double bottom2Right = 0;
        double bottom2Right2 = 0;
        double bottom2Left = 0;
        double bottom2Left2 = 0;

        double valueChanging = channelArray.get(i).get(j) * kernel.getValue(2, 2);

        //Middle Row (based on kernel not Img)
        try {
          right = channelArray.get(i).get(j + 1) * kernel.getValue(2, 3);
        } catch (IndexOutOfBoundsException e) {
          // Want to ignore this case (don't throw an exception)
        }
        try {
          right2 = channelArray.get(i).get(j + 2) * kernel.getValue(2, 4);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          left = channelArray.get(i).get(j - 1) * kernel.getValue(2, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          left2 = channelArray.get(i).get(j - 2) * kernel.getValue(2, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        //Up 1 Row
        try {
          top = channelArray.get(i - 1).get(j) * kernel.getValue(1, 2);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          topRight = channelArray.get(i - 1).get(j + 1) * kernel.getValue(1, 3);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          topRight2 = channelArray.get(i - 1).get(j + 2) * kernel.getValue(1, 4);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          topLeft = channelArray.get(i - 1).get(j - 1) * kernel.getValue(1, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          topLeft2 = channelArray.get(i - 1).get(j - 2) * kernel.getValue(1, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        //Top Row
        try {
          top2 = channelArray.get(i - 2).get(j) * kernel.getValue(0, 2);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          top2Right = channelArray.get(i - 2).get(j + 1) * kernel.getValue(0, 3);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          top2Right2 = channelArray.get(i - 2).get(j + 2) * kernel.getValue(0, 4);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          top2Left = channelArray.get(i - 2).get(j - 1) * kernel.getValue(0, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          top2Left2 = channelArray.get(i - 2).get(j - 2) * kernel.getValue(0, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        //Down 1 Row
        try {
          bottom = channelArray.get(i + 1).get(j) * kernel.getValue(3, 2);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottomRight = channelArray.get(i + 1).get(j + 1) * kernel.getValue(3, 3);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottomRight2 = channelArray.get(i + 1).get(j + 2) * kernel.getValue(3, 4);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottomLeft = channelArray.get(i + 1).get(j - 1) * kernel.getValue(3, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottomLeft2 = channelArray.get(i + 1).get(j - 2) * kernel.getValue(3, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        //Bottom Row
        try {
          bottom2 = channelArray.get(i + 2).get(j) * kernel.getValue(4, 2);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottom2Right = channelArray.get(i + 2).get(j + 1) * kernel.getValue(4, 3);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottom2Right2 = channelArray.get(i + 2).get(j + 2) * kernel.getValue(4, 4);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottom2Left = channelArray.get(i + 2).get(j - 1) * kernel.getValue(4, 1);
        } catch (IndexOutOfBoundsException e) {
          //
        }
        try {
          bottom2Left2 = channelArray.get(i + 2).get(j - 2) * kernel.getValue(4, 0);
        } catch (IndexOutOfBoundsException e) {
          //
        }

        double newValue = valueChanging + right + right2 + left + left2
            + top + topRight + topRight2 + topLeft + topLeft2
            + top2 + top2Right + top2Right2 + top2Left + top2Left2
            + bottom + bottomRight + bottomRight2 + bottomLeft + bottomLeft2
            + bottom2 + bottom2Right + bottom2Right2 + bottom2Left + bottom2Left2;

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

  // returns this kernel
  protected Matrix getKernel() {
    return this.kernel;
  }


}

package view;

import java.io.IOException;

/**
 * Represents the interface for the view. Displays the textual view to the user.
 */
public interface MultiLayerView {

  /**
   * Renders the layered image.
   */
  void renderLayeredImage();

  /**
   * Renders the layered image.
   *
   * @paqram message   the message to render
   */
  void renderMessage(String message) throws IOException;

}

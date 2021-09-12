package view;

import java.io.IOException;
import model.Layer;
import model.MultiLayerModel;
import model.ReadOnlyModel;

/**
 * Represents the class for the view. Displays the textual view to the user.
 */
public class MultiLayerTextView implements MultiLayerView {

  private ReadOnlyModel modelState;
  private Appendable destination;

  /**
   * The constructor for the class MultiLayerTextView.
   *
   * @param modelState  the model
   * @param destination the appendable
   */
  public MultiLayerTextView(MultiLayerModel modelState, Appendable destination) {
    this.modelState = modelState;
    this.destination = destination;
  }

  /**
   * The constructor for the class MultiLayerTextView.
   */
  public MultiLayerTextView(MultiLayerModel modelState) {
    this.modelState = modelState;
  }


  // renders the layered image
  @Override
  public void renderLayeredImage() {
    StringBuilder renderedLayers = new StringBuilder();
    String visibility;
    for (int i = 0; i < modelState.numberOfLayers(); i++) {
      Layer layer = modelState.getSpecificLayer(i);
      if (modelState.getVisibility(layer)) {
        visibility = "visible";
      } else {
        visibility = "invisible";
      }
      renderedLayers.append("Layer " + (i + 1) + ": " + "image " + (i + 1) + " " + visibility
          + "\n");
    }
    try {
      this.destination.append(renderedLayers);
    } catch (IOException ioException) {
      //s
    }
  }


  // renders the given message
  @Override
  public void renderMessage(String message) throws IOException {
    if (this.destination == null) {
      System.out.print(message);
    }

    this.destination.append(message);

  }
}

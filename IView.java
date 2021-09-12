package view;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The interface for an IView.
 */
public interface IView {

  /**
   * Sets the listener to send to the view.
   *
   * @param listener  the ActionListener
   */
  void setListener(ActionListener listener);

  /**
   * Gets the appropriate/current text field.
   *
   * @return a new TextField
   */
  JTextField getTextField();

  /**
   * Gets the appropriate/current center image.
   *
   * @return the center image
   */
  JLabel getCenterImage();

  /**
   * Gets the main panel.
   *
   * @return the main panel
   */
  Container getMainPanel();

  /**
   * Sets the image so it is aligned correctly. Sets the icon.
   *
   * @param imageLabel the image label
   * @param path       the path of the image
   */
  void setImageIconPath(JLabel imageLabel, String path);

  /**
   * Sets the image so it is aligned correctly. Sets the icon.
   *
   * @param imageLabel the image label
   * @param buffImg    the buffered image
   */
  void setImageIconBuffImg(JLabel imageLabel, BufferedImage buffImg);

  /**
   * Renders the message given.
   *
   * @param message the message
   */
  void renderMessage(String message);


}

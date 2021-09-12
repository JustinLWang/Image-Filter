package controller;

import java.util.List;
import model.Pixel;

/**
 * Interface that represents the Importing and Exporting aspects of the program. Falls under the
 * controller aspect of MVC.
 */

public interface ImportExportInterface {


  /**
   * Reads the given image.
   *
   * @param filename the sourcepath of the image
   * @return the 2D Array of Pixel of the image
   */
  List<List<Pixel>> readFile(String filename);


  /**
   * Creates a file given the content and name of the file.
   *
   * @param fileContent the content of the file
   * @param fileName    name of file
   */
  void fileCreator(StringBuilder fileContent, String fileName, String folderName);


  /**
   * Creates the file content of the given image.
   *
   * @param img image to produce the content for
   * @return img content as a StringBuilder
   */

  StringBuilder fileContentCreatorImg(List<List<Pixel>> img);

  void fileCreatorSaveExport(StringBuilder fileContent, String fileName, String folderName);

  void fileCreator2(StringBuilder fileContent, String parentPath, String path);


}

package controller;

/**
 * Class that holds a factory method for creating different file types. Allows program to handle any
 * file type.
 */
public class FileType implements FileTypeInterface {

  /**
   * Creates the given file type.
   *
   * @param type the file type
   */
  public static ImportExportInterface typeExtractor(String type) {
    if (type == null) {
      throw new IllegalArgumentException("null parameter.");
    }

    switch (type) {
      case "PPM":
        return new PPMImportExport();
      case "":
        throw new IllegalArgumentException("no file type give.");
      default:
        throw new IllegalArgumentException("invalid file type.");
    }
  }

}

package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Pixel;

/**
 * Class that represents the Importing and Exporting aspects of the program. Implements the methods
 * needed for exporting and importing PPM files. Falls under the controller aspect of MVC.
 */
public class PPMImportExport implements ImportExportInterface {

  // reads the given file and produces its pixels
  @Override
  public List<List<Pixel>> readFile(String filename) {
    if (filename == null) {
      throw new IllegalArgumentException("null parameter.");
    }

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return new ArrayList<>();
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    //System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    // System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    List<List<Pixel>> image = new ArrayList<List<Pixel>>();
    for (int i = 0; i < height; i++) {
      image.add(new ArrayList<Pixel>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        //Add the pixel to an 2D array
        image.get(i).add(new Pixel(r, g, b));
        //  System.out.println("("+j+","+i+"): "+ r+","+g+","+b);
      }
    }

    return image;


  }


  // creates a file
  @Override
  public void fileCreator(StringBuilder fileContent, String fileName, String folderName) {
    if (fileContent == null || fileName == null) {
      throw new IllegalArgumentException("null paramater.");
    }

    File temp = new File("MultiLayeredImages/" + folderName,
        fileName); // file needs to have .ppm at end
    try {
      FileWriter testWriter = new FileWriter(temp);
      testWriter.write(fileContent.toString());
      testWriter.close();
      temp.createNewFile();
    } catch (Exception e) {
      throw new IllegalArgumentException("Problem with generating file.");
    }
  }

  // creates a file
  @Override
  public void fileCreatorSaveExport(StringBuilder fileContent, String fileName, String folderName) {
    if (fileContent == null || fileName == null) {
      throw new IllegalArgumentException("null paramater.");
    }

    File temp = new File(folderName,
        fileName); // file needs to have .ppm at end
    try {
      FileWriter testWriter = new FileWriter(temp);
      testWriter.write(fileContent.toString());
      testWriter.close();
      temp.createNewFile();
    } catch (Exception e) {
      throw new IllegalArgumentException("Problem with generating file.");
    }
  }

  // creates a file
  @Override
  public void fileCreator2(StringBuilder fileContent, String parentPath, String path) {
    if (fileContent == null || path == null) {
      throw new IllegalArgumentException("null paramater.");
    }

    File temp = new File(parentPath, path);
    try {
      FileWriter testWriter = new FileWriter(temp);
      testWriter.write(fileContent.toString());
      testWriter.close();
      temp.createNewFile();
    } catch (Exception e) {
      throw new IllegalArgumentException("Problem with generating file.");
    }
  }


  // creates the content of the file given a image
  @Override
  public StringBuilder fileContentCreatorImg(List<List<Pixel>> img) {
    if (img == null) {
      throw new IllegalArgumentException("null paramater.");
    }

    Scanner sc;

    StringBuilder builder = new StringBuilder();

    builder.append("P3\n");
    builder.append(img.get(0).size() + " ");
    builder.append(img.size() + "\n");
    builder.append(255 + "\n");

    for (int i = 0; i < img.size(); i++) {
      for (int j = 0; j < img.get(i).size(); j++) {
        int r = (int) img.get(i).get(j)
            .getRed(); // cast to int because ppm files can't read doubles
        int g = (int) img.get(i).get(j).getGreen();
        int b = (int) img.get(i).get(j).getBlue();
        builder.append(r + "\n");
        builder.append(g + "\n");
        builder.append(b + "\n");

      }

    }
    return builder;
  }

}

package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.Image;
import model.Layer;
import model.MultiLayerModel;
import model.Pixel;
import view.MultiLayerTextView;
import view.MultiLayerView;


/**
 * Class that represents the Controller. Communicates between the Model and the View. Holds methods
 * regarding the Controller.
 */
public class Controller implements IController {

  private MultiLayerModel model;
  private Readable rd;
  private Appendable ap;
  private MultiLayerView view;

  /**
   * Creates a Controller for this program.
   *
   * @param model the MultiLayerModel
   * @param rd    the Readable
   * @param ap    the Appendable
   */

  public Controller(MultiLayerModel model, Readable rd, Appendable ap) {
    if (model == null || rd == null) {
      throw new IllegalArgumentException("null parameters.");
    }

    this.model = model;
    this.rd = rd;
    this.ap = ap;
    this.view = new MultiLayerTextView(model, ap);

  }

  /**
   * Creates a Controller for this program.
   *
   * @param model the MultiLayerModel
   * @param rd    the Readable
   */
  public Controller(MultiLayerModel model, Readable rd) {
    if (model == null || rd == null) {
      throw new IllegalArgumentException("null parameters.");
    }

    this.model = model;
    this.rd = rd;
    this.ap = new StringBuilder();
    this.view = new MultiLayerTextView(model, ap);

  }

  // starts the program
  @Override
  public void goProgram() {
    Scanner sc = new Scanner(rd);
    Layer currentLayer = new Layer();
    String sourcePath = "";

    while (sc.hasNext()) {
      String input = sc.next();

      switch (input) {
        case "script":
          if (sc.hasNext()) {
            String scriptPath = sc.next();
            try {
              sc = new Scanner(new File(scriptPath));
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
          break;
        case "createLayer":
          Layer layer = model.createLayer();
          if (sc.hasNext()) {
            int whichLayer = Integer.parseInt(sc.next()) - 1;
            model.addLayer(layer, whichLayer);
            try {
              view.renderMessage("layer successfully created.\n");
            } catch (IOException ioException) {
              //s
            }
          } else {
            try {
              view.renderMessage("specific layer must be given.\n");
            } catch (IOException ioException) {
              //s
            }
          }
          break;
        case "remove":
          if (sc.hasNext()) {
            int whichLayer = Integer.parseInt(sc.next()) - 1;
            model.removeLayer(whichLayer);
            try {
              view.renderMessage("layer successfully removed.\n");
            } catch (IOException ioException) {
              //s
            }
          } else {
            try {
              view.renderMessage("specific layer must be given.\n");
            } catch (IOException ioException) {
              //ss
            }
          }
          break;
        case "current":
          if (sc.hasNext()) {
            int whichLayer = Integer.parseInt(sc.next()) - 1;
            currentLayer = model.getSpecificLayer(whichLayer);
          }
          break;
        case "load":
          List<List<Pixel>> pixelArray;
          if (sc.hasNext()) {
            sourcePath = sc.next();
            if (sourcePath.substring(sourcePath.lastIndexOf(".") + 1).equals("ppm")) {
              pixelArray = FileType.typeExtractor("PPM").readFile(sourcePath);
              if (model.equalPixelArrayDim(pixelArray)) {
                model.setImage(currentLayer, model.createImage(pixelArray));
                currentLayer.setFileType("ppm");

                try {
                  view.renderMessage("image successfully loaded.\n");
                } catch (IOException ioException) {
                  //ss
                }

              } else {
                try {
                  view.renderMessage("image is not the same dimension as other layers.\n");
                } catch (IOException ioException) {
                  //s
                }
              }
            } else {
              String fileType = sourcePath.substring(sourcePath.lastIndexOf(".") + 1);
              pixelArray = convertPixelArray(sourcePath);
              if (model.equalPixelArrayDim(pixelArray)) {
                model.setImage(currentLayer, model.createImage(pixelArray));
                currentLayer.setFileType(fileType);

                try {
                  view.renderMessage("image successfully loaded.\n");
                } catch (IOException ioException) {
                  //ss
                }

              } else {
                try {
                  view.renderMessage("image is not the same dimension as other layers.\n");
                } catch (IOException ioException) {
                  //ss
                }
              }
            }
          } else {
            try {
              view.renderMessage("no source path for image given.\n");
            } catch (IOException ioException) {
              //ss
            }
          }
          break;
        case "loadLayered":
          if (sc.hasNext()) {
            String folderPath = sc.next(); // the folder that holds the multilayered image
            File imageFolder = new File(folderPath);
            if (imageFolder.exists()) {
              String[] files = listFilesInFolder(folderPath);
              for (int i = 0; i < files.length; i++) {
                if (files[i].substring(files[i].lastIndexOf(".") + 1).equals("txt")) {
                  continue;
                }
                Layer newLayer = model.createLayer();
                model.addLayer(newLayer, i);
                if (files[i].substring(files[i].lastIndexOf(".") + 1).equals("ppm")) {
                  pixelArray = FileType.typeExtractor("PPM").readFile(folderPath + "/"
                      + files[i]);
                  model.setImage(model.getSpecificLayer(i), model.createImage(pixelArray));
                  model.getSpecificLayer(i).setFileType("ppm");
                } else {
                  String fileType = files[i].substring(files[i].lastIndexOf(".") + 1);
                  pixelArray = convertPixelArray(folderPath + "/" + files[i]);
                  model.setImage(model.getSpecificLayer(i), model.createImage(pixelArray));
                  model.getSpecificLayer(i).setFileType(fileType);
                }
              }
              try {
                view.renderMessage("multilayered image loaded successfully.\n");
              } catch (IOException ioException) {
                //s
              }

            } else {
              try {
                view.renderMessage("folder for multilayered image doesn't exist.\n");
              } catch (IOException ioException) {
                //s
              }
            }
          } else {
            try {
              view.renderMessage("path to multilayered image must be given.\n");
            } catch (IOException ioException) {
              //ss
            }
          }
          break;
        case "blur":
          if (sc.hasNext()) {
            int multiplier = Integer.parseInt(sc.next());
            List<List<Pixel>> blurredArray = model.filter(currentLayer.getLayerImage(),
                multiplier, "blur");
            Image blurredImage = model.createImage(blurredArray);
            model.setImage(currentLayer, blurredImage);
            try {
              view.renderMessage("blur successfully applied.\n");
            } catch (IOException ioException) {
              //ss
            }
          }
          break;
        case "sharpen":
          if (sc.hasNext()) {
            int multiplier = Integer.parseInt(sc.next());
            List<List<Pixel>> sharpenedArray = model.filter(currentLayer.getLayerImage(),
                multiplier, "sharpen");
            Image sharpenedImage = model.createImage(sharpenedArray);
            model.setImage(currentLayer, sharpenedImage);
            try {
              view.renderMessage("sharpen successfully applied.\n");
            } catch (IOException ioException) {
              //s
            }
          }
          break;
        case "greyscale":
          List<List<Pixel>> greyscaleArray = model.transform(currentLayer.getLayerImage(),
              "greyscale");
          Image greyscaleImage = model.createImage(greyscaleArray);
          model.setImage(currentLayer, greyscaleImage);
          try {
            view.renderMessage("greyscale successfully applied.\n");
          } catch (IOException ioException) {
            //s
          }
          break;
        case "sepia":
          List<List<Pixel>> sepiaArray = model.transform(currentLayer.getLayerImage(),
              "sepia");
          Image sepiaImage = model.createImage(sepiaArray);
          model.setImage(currentLayer, sepiaImage);
          try {
            view.renderMessage("sepia successfully applied.\n");
          } catch (IOException ioException) {
            //s
          }
          break;
        case "save":
          if (sc.hasNext()) {
            String fileName = sc.next();
            if (sc.hasNext()) {
              String fileType = sc.next();
              if (fileType.equals("ppm")) {
                StringBuilder fileContent = FileType.typeExtractor("PPM")
                    .fileContentCreatorImg(currentLayer.getLayerImage().getPixelArray());
                FileType.typeExtractor("PPM").fileCreatorSaveExport(fileContent, fileName, "res");
                currentLayer.setFileType(fileType);
                try {
                  view.renderMessage("image successfully saved.\n");
                } catch (IOException ioException) {
                  ioException.printStackTrace();
                }
              } else {
                try {
                  BufferedImage buffImg =
                      createBufferedImage(currentLayer.getLayerImage().getPixelArray());
                  ImageIO.write(buffImg, fileType, new File("res/" + fileName));
                  currentLayer.setFileType(fileType);
                  view.renderMessage("image successfully saved.\n");
                } catch (IOException ioException) {
                  //s
                }
              }
            }
          }
          break;
        case "export":
          if (sc.hasNext()) {
            String fileNamee = sc.next();
            if (sc.hasNext()) {
              String fileType = sc.next();
              Layer topMostVisible = model.getTopMostVisible();
              if (fileType.equals("ppm")) {
                StringBuilder fileContent = FileType.typeExtractor("PPM")
                    .fileContentCreatorImg(topMostVisible.getLayerImage().getPixelArray());
                FileType.typeExtractor("PPM")
                    .fileCreatorSaveExport(fileContent, fileNamee, "Exports");
                topMostVisible.setFileType(fileType);
                try {
                  view.renderMessage("image successfully Exported.\n");
                } catch (IOException ioException) {
                  ioException.printStackTrace();
                }
              } else {
                try {
                  BufferedImage buffImg = createBufferedImage(
                      topMostVisible.getLayerImage().getPixelArray());
                  ImageIO.write(buffImg, fileType, new File("Exports/" + fileNamee));
                  view.renderMessage("image successfully exported.\n");
                } catch (IOException ioException) {
                  //ss
                }
                break;
              }
            }
          }
          break;
        case "invisible":
          model.setVisibility(currentLayer, false);
          try {
            view.renderMessage("layer set to invisible.\n");
          } catch (IOException ioException) {
            //ss
          }

          break;
        case "visible":
          model.setVisibility(currentLayer, true);
          try {
            view.renderMessage("layer set to visible.\n");
          } catch (IOException ioException) {
            //ss
          }

          break;
        case "saveAll":
          view.renderLayeredImage();
          if (sc.hasNext()) {
            String folderName = sc.next();
            File folder = new File("MultiLayeredImages", folderName);
            folder.mkdir();
            new File("MultiLayeredImages/" + folderName, "Location.txt");

            for (int i = model.numberOfLayers() - 1; i >= 0; i--) {
              Layer layerr = model.getSpecificLayer(i);
              if (layerr.getLayerImage() == null) {
                model.removeLayer(i);
                continue;
              }
              List<List<Pixel>> array = layerr.getLayerImage().getPixelArray();
              String fileType = layerr.getFileType();
              BufferedImage buffImage = createBufferedImage(array);
              if (layerr.getFileType().equals("ppm")) {
                StringBuilder fileContent =
                    FileType.typeExtractor("PPM").fileContentCreatorImg(array);
                FileType.typeExtractor("PPM").fileCreator(fileContent,
                    "Layer " + (i + 1) + ".ppm", folderName);
              } else {
                try {
                  ImageIO.write(buffImage, fileType,
                      new File("MultiLayeredImages/" + folderName,
                          "Layer " + (i + 1) + "." + fileType));
                } catch (IOException ioException) {
                  //ss
                }
              }
            }
            writeToTextFile("MultiLayeredImages/" + folderName + "/" + "Location.txt",
                folderName);
            return;
          } else {
            try {
              view.renderMessage("folder name required.");
            } catch (IOException ioException) {
              //ss
            }
          }
          break;
        case "quit":
          try {
            view.renderMessage("quit program.\n");
            System.exit(0);
          } catch (IOException ioException) {
            ioException.printStackTrace();
            //ss
          }
          return;
        default:
          try {
            view.renderMessage("invalid command.\n");
          } catch (IOException ioException) {
            //ss
          }
      }
    }
  }

  // converts the given sourcePath into a pixelArray
  private List<List<Pixel>> convertPixelArray(String sourcePath) {
    BufferedImage buffImage = null;
    List<List<Pixel>> pixelArray = new ArrayList<List<Pixel>>();
    try {
      File file = new File(sourcePath);
      buffImage = ImageIO.read(file);
    } catch (IOException ioException) {
      //ss
    }
    for (int i = 0; i < buffImage.getHeight(); i++) {
      pixelArray.add(new ArrayList<Pixel>());
      for (int j = 0; j < buffImage.getWidth(); j++) {
        Color color = new Color(buffImage.getRGB(j, i));
        pixelArray.get(i).add(new Pixel(color.getRed(), color.getGreen(), color.getBlue()));
      }
    }
    return pixelArray;
  }


  // creates a bufferedImage out of a pixelArray
  private BufferedImage createBufferedImage(List<List<Pixel>> pixelArray) {
    BufferedImage buffImage = new BufferedImage(pixelArray.get(0).size(), pixelArray.size(),
        BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < pixelArray.size(); i++) {
      for (int j = 0; j < pixelArray.get(i).size(); j++) {
        double red = pixelArray.get(i).get(j).getRed();
        double green = pixelArray.get(i).get(j).getGreen();
        double blue = pixelArray.get(i).get(j).getBlue();

        Color color = new Color((int) red, (int) green, (int) blue);
        buffImage.setRGB(j, i, color.getRGB());
      }
    }
    return buffImage;
  }

  // writes to the given fileName in the given folderName
  private void writeToTextFile(String fileName, String folderName) {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(fileName, "UTF-8");
    } catch (FileNotFoundException e) {
      //ss
    } catch (UnsupportedEncodingException e) {
      //ss
    }
    for (int i = 0; i < model.numberOfLayers(); i++) {
      String fileType = model.getSpecificLayer(i).getFileType();
      writer.println(
          "Layer " + (i + 1) + ": " + "MultiLayeredImages/" + folderName + "/" + "Layer " + (i + 1)
              + "." + fileType);
    }
    writer.close();

  }

  // lists the files in a folder (given folderPath)
  private String[] listFilesInFolder(String folderPath) {
    // Creates an array in which we will store the names of files and directories
    String[] pathnames;

    // Creates a new File instance by converting the given pathname string
    // into an abstract pathname
    File f = new File(folderPath);

    // Populates the array with names of files and directories
    pathnames = f.list();

    Arrays.sort(pathnames, new Comparator<String>() {
      // override the compare method
      @Override
      public int compare(String o1, String o2) {
        if (o1.contains(".txt") || o2.contains(".txt")) {
          return 0;
        }
        int firstNum = Integer.parseInt(o1.substring(o1.lastIndexOf(".") - 1,
            o1.lastIndexOf(".")));
        int secondNum = Integer.parseInt(o2.substring(o2.lastIndexOf(".") - 1,
            o2.lastIndexOf(".")));
        if (firstNum < secondNum) {
          return -1;
        }
        if (firstNum == secondNum) {
          return 0;
        }
        if (firstNum > secondNum) {
          return 1;
        }
        return 0;
      }
    });

    return pathnames;
  }


}

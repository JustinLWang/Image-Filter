package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import model.Image;
import model.Layer;
import model.MultiLayerModel;
import model.Pixel;
import view.IView;

/**
 * Class to represent the GUI Controller.
 */
public class GUIController implements Features, ActionListener {

  private MultiLayerModel model;
  // change back to IVIEW
  private IView view;
  private int currentLayer;

  /**
   * The constructor for GUI controller.
   *
   * @param model  the model
   * @param view   the view
   */
  public GUIController(MultiLayerModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("null parameters.");
    }
    this.model = model;
    this.view = view;
    this.currentLayer = 0;
    view.setListener(this);
  }

  @Override
  public void addLayer() {
    Layer layer = model.createLayer();
    model.getLayers().add(layer);

  }

  @Override
  public void removeLayer() {
    model.removeLayer(currentLayer);


  }

  @Override
  public void currentLayer(int whichLayer) {
    this.currentLayer = whichLayer; // indexed starting at 0
  }

  @Override
  public void loadLayer() {
    List<List<Pixel>> pixelArray;
    String path = "";
    Layer currentLayerr = model.getSpecificLayer(this.currentLayer);
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      path = selectedFile.getAbsolutePath();
      view.renderMessage("Selected file: " + path);
    }

    if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
      pixelArray = FileType.typeExtractor("PPM").readFile(path);
      if (model.equalPixelArrayDim(pixelArray)) {
        model.setImage(currentLayerr, model.createImage(pixelArray));
        currentLayerr.setFileType("ppm");
      }
    } else {
      String fileType = path.substring(path.lastIndexOf(".") + 1);
      pixelArray = convertPixelArray(path);
      if (model.equalPixelArrayDim(pixelArray)) {
        model.setImage(currentLayerr, model.createImage(pixelArray));
        currentLayerr.setFileType(fileType);
      }
    }

  }

  @Override
  public void loadAllLayer() {
    List<List<Pixel>> pixelArray;
    String path = "";
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      path = selectedFile.getAbsolutePath();
      view.renderMessage("Selected folder: " + path);
    }

    String[] files = listFilesInFolder(path);
    for (int i = 0; i < files.length; i++) {
      if (files[i].substring(files[i].lastIndexOf(".") + 1).equals("txt")) {
        continue;
      }
      Layer newLayer = model.createLayer();
      model.addLayer(newLayer, i);
      if (files[i].substring(files[i].lastIndexOf(".") + 1).equals("ppm")) {
        pixelArray = FileType.typeExtractor("PPM").readFile(path + "/"
            + files[i]);
        model.setImage(model.getSpecificLayer(i), model.createImage(pixelArray));
        model.getSpecificLayer(i).setFileType("ppm");
      } else {
        String fileType = files[i].substring(files[i].lastIndexOf(".") + 1);
        pixelArray = convertPixelArray(path + "/" + files[i]);
        model.setImage(model.getSpecificLayer(i), model.createImage(pixelArray));
        model.getSpecificLayer(i).setFileType(fileType);
      }
    }
  }


  @Override
  public void blurLayer() {
    Layer currentLayerr = model.getSpecificLayer(currentLayer);
    List<List<Pixel>> blurredArray = model.filter(currentLayerr.getLayerImage(),
        1, "blur");
    Image blurredImage = model.createImage(blurredArray);
    model.setImage(currentLayerr, blurredImage);

  }

  @Override
  public void sharpenLayer() {
    Layer currentLayerr = model.getSpecificLayer(currentLayer);
    List<List<Pixel>> sharpenedArray = model.filter(currentLayerr.getLayerImage(),
        1, "sharpen");
    Image sharpenedImage = model.createImage(sharpenedArray);
    model.setImage(currentLayerr, sharpenedImage);

  }

  @Override
  public void greyscaleLayer() {
    Layer currentLayerr = model.getSpecificLayer(currentLayer);
    List<List<Pixel>> greyscaleArray = model.transform(currentLayerr.getLayerImage(),
        "greyscale");
    Image greyscaleImage = model.createImage(greyscaleArray);
    model.setImage(currentLayerr, greyscaleImage);

  }

  @Override
  public void sepiaLayer() {
    Layer currentLayerr = model.getSpecificLayer(currentLayer);
    List<List<Pixel>> sepiaArray = model.transform(currentLayerr.getLayerImage(),
        "sepia");
    Image sepiaImage = model.createImage(sepiaArray);
    model.setImage(currentLayerr, sepiaImage);

  }

  @Override
  public void visible() {
    Layer currentLayerr = model.getSpecificLayer(currentLayer);
    model.setVisibility(currentLayerr, true);

  }

  @Override
  public void invisible() {
    Layer currentLayerr = model.getSpecificLayer(currentLayer);
    model.setVisibility(currentLayerr, false);

  }

  @Override
  public void script() {
    String scriptPath = "";
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      scriptPath = selectedFile.getAbsolutePath();
      view.renderMessage("Selected Script: " + scriptPath);
      System.out.println("Selected Script: " + scriptPath);
      Appendable out = System.out;
      new Controller(model, new StringReader("script " + scriptPath), out).goProgram();
      loadTopMostVisibleImage();

    }
  }

  @Override
  public void save() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");

    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String path = fileToSave.getAbsolutePath();
      String parentPath = path.substring(0, path.lastIndexOf("/") + 1);
      String childPath = path.substring(path.lastIndexOf("/") + 1);
      String fileType = path.substring(path.lastIndexOf(".") + 1);
      Layer topVisibleLayer = model.getTopMostVisible();
      List<List<Pixel>> pixelArray = topVisibleLayer.getLayerImage().getPixelArray();
      if (fileType.equals("ppm")) {
        StringBuilder fileContent = new PPMImportExport().fileContentCreatorImg(pixelArray);
        new PPMImportExport().fileCreator2(fileContent, parentPath, childPath);
        topVisibleLayer.setFileType(fileType);
        view.renderMessage("Image saved as " + fileToSave.getAbsolutePath());
      } else {
        try {
          BufferedImage buffImg =
              createBufferedImage(topVisibleLayer.getLayerImage().getPixelArray());
          ImageIO.write(buffImg, fileType, new File(path));
        } catch (IOException ioException) {
          ioException.printStackTrace();
        } catch (NullPointerException e) {
          view.renderMessage("Top most visible layer is blank!");
        }
        topVisibleLayer.setFileType(fileType);
        view.renderMessage("Image saved as " + fileToSave.getAbsolutePath());
      }
    }


  }


  @Override
  public void saveAll() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");

    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String path = fileToSave.getAbsolutePath();
      String parentPath = path.substring(0, path.lastIndexOf("/") + 1);
      System.out.println(parentPath);
      String childPath = path.substring(path.lastIndexOf("/") + 1);
      System.out.println(childPath);
      File folder = new File(parentPath, childPath);
      folder.mkdir();
      File locationFile = new File(path, "Location.txt");
      try {
        locationFile.createNewFile();
      } catch (IOException ioException) {
        //s
      }

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
          FileType.typeExtractor("PPM")
              .fileCreator2(fileContent, path, "Layer " + (i + 1) + "." + fileType);
          layerr.setFileType(fileType);

        } else {
          try {
            ImageIO.write(buffImage, fileType,
                new File(path,
                    "Layer " + (i + 1) + "." + fileType));
          } catch (IOException ioException) {
            //ss
          }
        }
      }
      writeToTextFile(path + "/" + "Location.txt",
          path);
    }
  }

  @Override
  public void exitProgram() {
    System.exit(0);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      // UI Buttons
      case "Add Button":
        addLayer();
        view.renderMessage("layer added.");
        loadTopMostVisibleImage();
        break;
      case "Current Text Field":
        int whichLayer = Integer.parseInt(view.getTextField().getText()) - 1;
        currentLayer(whichLayer);
        view.renderMessage("current layer: " + (currentLayer + 1));
        break;
      case "Remove Button":
        removeLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " removed");
        loadTopMostVisibleImage();
        break;
      case "Load Button":
        loadLayer();
        view.renderMessage("image loaded in layer " + (currentLayer + 1));
        loadTopMostVisibleImage();
        break;
      case "Load All Button":
        loadAllLayer();
        view.renderMessage("multi layered image loaded.");
        loadTopMostVisibleImage();
        break;
      case "Blur Button":
        blurLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " blurred");
        loadTopMostVisibleImage();
        break;
      case "Sharpen Button":
        sharpenLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " sharpened");
        loadTopMostVisibleImage();
        break;
      case "Greyscale Button":
        greyscaleLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " greyscaled");
        loadTopMostVisibleImage();
        break;
      case "Sepia Button":
        sepiaLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " sepia");
        loadTopMostVisibleImage();
        break;
      case "Visible Button":
        visible();
        view.renderMessage("layer " + (currentLayer + 1) + " set to visible");
        loadTopMostVisibleImage();
        break;
      case "Invisible Button":
        invisible();
        view.renderMessage("layer " + (currentLayer + 1) + " set to invisible");
        loadTopMostVisibleImage();
        break;
      case "Save Button":
        save();
        break;
      case "Save All Button":
        saveAll();
        break;

      // Menu Buttons
      case "Menu Load":
        loadLayer();
        break;
      case "Menu Load All":
        loadAllLayer();
        view.renderMessage("multi layered image loaded.");
        loadTopMostVisibleImage();
        break;
      case "Menu Save":
        save();
        break;
      case "Menu Save All":
        saveAll();
        break;
      case "Menu Exit":
        exitProgram();
        break;
      case "Menu Blur":
        blurLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " blurred");
        loadTopMostVisibleImage();
        break;
      case "Menu Sharpen":
        sharpenLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " sharpened");
        loadTopMostVisibleImage();
        break;
      case "Menu Greyscale":
        greyscaleLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " greyscaled");
        loadTopMostVisibleImage();
        break;
      case "Menu Sepia":
        sepiaLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " sepia");
        loadTopMostVisibleImage();
        break;
      case "Menu Visible":
        visible();
        view.renderMessage("layer " + (currentLayer + 1) + " set to visible");
        loadTopMostVisibleImage();
        break;
      case "Menu Invisible":
        invisible();
        view.renderMessage("layer " + (currentLayer + 1) + " set to invisible");
        loadTopMostVisibleImage();
        break;
      case "Menu Script":
        script();
        break;
      case "Menu Add Layer":
        addLayer();
        view.renderMessage("layer added.");
        loadTopMostVisibleImage();
        break;
      case "Menu Remove Layer":
        removeLayer();
        view.renderMessage("layer " + (currentLayer + 1) + " removed");
        loadTopMostVisibleImage();
        break;
      default:
        view.renderMessage("invalid event called.");


    }
  }

  private void loadTopMostVisibleImage() {
    Layer topVisibleLayer = model.getTopMostVisible();
    int topVisibleIndex = model.getLayers().indexOf(topVisibleLayer);
    try {
      List<List<Pixel>> pixelArray = topVisibleLayer.getLayerImage().getPixelArray();
      view.getCenterImage().removeAll();
      BufferedImage buffImg = createBufferedImage(pixelArray);
      view.setImageIconBuffImg(view.getCenterImage(), buffImg);
      view.getCenterImage().revalidate();
      view.getCenterImage().repaint();
      view.renderMessage("image updated. layer " + (topVisibleIndex + 1) + " is displaying.");
    } catch (NullPointerException e) {
      view.getCenterImage().setIcon(null);
      view.getCenterImage().revalidate();
      view.getCenterImage().repaint();
      view.renderMessage("image updated. layer " + (topVisibleIndex + 1) + " is displaying.");
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
  private void writeToTextFile(String fileName, String path) {
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
          "Layer " + (i + 1) + ": " + path + "/" + "Layer " + (i + 1)
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

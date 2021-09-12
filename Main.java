import controller.Controller;
import controller.GUIController;
import controller.IController;
import java.io.InputStreamReader;
import java.io.StringReader;
import javax.swing.JFrame;
import model.MultiLayerModel;
import model.MultiLayerModelImp;
import view.JFrameView;


/**
 * This class contains utility methods the main method.
 */
public class Main {

  /**
   * Runs the program (demo).
   *
   * @param args the array of Strings
   */
  public static void main(String[] args) {
    String input1 = "";
    try {
      input1 = args[0];
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("main() must be given at least one String argument.");
    }
    switch (input1) {
      case "-text":
        MultiLayerModel multiLayerModel = new MultiLayerModelImp();
        Readable textIn = new InputStreamReader(System.in);
        Appendable out = System.out;
        IController textViewController = new Controller(multiLayerModel,
            new InputStreamReader(System.in), out);
        textViewController.goProgram();
        break;
      case "-script":
        try {
          String scriptPath = args[1];
          MultiLayerModelImp multiLayerModel2 = new MultiLayerModelImp();
          StringBuilder out2 = new StringBuilder();
          Controller textViewController2 = new Controller(multiLayerModel2,
              new StringReader("script " + scriptPath), out2);
          textViewController2.goProgram();
          System.exit(0);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("script path required.");
        }
        break;
      case "-interactive":
        JFrameView.setDefaultLookAndFeelDecorated(false);
        MultiLayerModel model = new MultiLayerModelImp();
        JFrameView view = new JFrameView();
        GUIController controller = new GUIController(model, view);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
        break;
      default:
        System.out.println("invalid command.\nValid Commands:\n"
            + "-text\n-script scriptPath\n-interactive");
        System.exit(0);

    }
  }
}


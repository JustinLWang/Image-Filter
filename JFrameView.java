package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * A class to represent an interactive view of the program.
 */
public class JFrameView extends JFrame implements IView {

  private JPanel mainPanell;

  private JMenuItem loadScriptItem;
  private JMenuItem loadItem;
  private JMenuItem saveItem;
  private JMenuItem saveAllItem;
  private JMenuItem exitItem;
  private JMenuItem blurItem;
  private JMenuItem sharpenItem;
  private JMenuItem greyscaleItem;
  private JMenuItem sepiaItem;
  private JMenuItem visibleItem;
  private JMenuItem invisibleItem;
  private JMenuItem addLayerItem;
  private JMenuItem removeLayerItem;
  private JMenuItem loadAllItem;


  private JLabel centerImage;




  private JButton loadButton;
  private JButton loadAllButton;
  private JButton saveButton;
  private JButton saveAllButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton greyscaleButton;
  private JButton sepiaButton;
  private JButton visibleButton;
  private JButton invisibleButton;
  private JButton addButton;
  private JButton removeButton;

  private JTextField currentTextField;

  private JTextField messageTextField;

  /**
   * The constructor for the class JFrameView.
   */
  public JFrameView() {
    super();
    setTitle("Image Manipulation Program");
    setSize(1300, 1100);
    setLocation(100, 100);

    // Main Panel
    Container mainPanel = this.getContentPane();
    mainPanel.setLayout(new BorderLayout(8, 6));
    mainPanel.setBackground(Color.DARK_GRAY);
    this.getRootPane().setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.DARK_GRAY));

    // MENU

    // menu bar
    JMenuBar menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    // menus
    JMenuItem fileMenu = new JMenu("File");
    fileMenu.setOpaque(true);
    menuBar.add(fileMenu);
    // menu items
    loadItem = new JMenuItem("Load");
    loadItem.setActionCommand("Menu Load");
    fileMenu.add(loadItem);

    loadAllItem = new JMenuItem("Load MultiLayered Image");
    loadAllItem.setActionCommand("Menu Load All");
    fileMenu.add(loadAllItem);

    saveItem = new JMenuItem("Save");
    saveItem.setActionCommand("Menu Save");
    fileMenu.add(saveItem);

    saveAllItem = new JMenuItem("Save Multilayered Image");
    saveAllItem.setActionCommand("Menu Save All");
    fileMenu.add(saveAllItem);

    exitItem = new JMenuItem("Exit");
    exitItem.setActionCommand("Menu Exit");
    fileMenu.add(exitItem);

    JMenu operationMenu = new JMenu("Operations");
    menuBar.add(operationMenu);

    blurItem = new JMenuItem("Blur");
    blurItem.setActionCommand("Menu Blur");
    operationMenu.add(blurItem);

    sharpenItem = new JMenuItem("Sharpen");
    sharpenItem.setActionCommand("Menu Sharpen");
    operationMenu.add(sharpenItem);

    greyscaleItem = new JMenuItem("Greyscale");
    greyscaleItem.setActionCommand("Menu Greyscale");
    operationMenu.add(greyscaleItem);

    sepiaItem = new JMenuItem("Sepia");
    sepiaItem.setActionCommand("Menu Sepia");
    operationMenu.add(sepiaItem);

    JMenu visibilityMenu = new JMenu("Visibility");
    menuBar.add(visibilityMenu);

    visibleItem = new JMenuItem("Visible");
    visibleItem.setActionCommand("Menu Visible");
    visibilityMenu.add(visibleItem);

    invisibleItem = new JMenuItem("Invisible");
    invisibleItem.setActionCommand("Menu Invisible");
    visibilityMenu.add(invisibleItem);

    JMenu scriptMenu = new JMenu("Script");
    menuBar.add(scriptMenu);
    loadScriptItem = new JMenuItem("Load Script");
    loadScriptItem.setActionCommand("Menu Script");
    scriptMenu.add(loadScriptItem);

    JMenu layerMenu = new JMenu("Layers");
    menuBar.add(layerMenu);
    addLayerItem = new JMenuItem("Add Layer");
    addLayerItem.setActionCommand("Menu Add Layer");
    removeLayerItem = new JMenuItem("Remove Layer");
    removeLayerItem.setActionCommand("Menu Remove Layer");
    layerMenu.add(addLayerItem);
    layerMenu.add(removeLayerItem);

    // Buttons
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");
    this.buttonDesigner(loadButton);

    loadAllButton = new JButton("Load MultiLayered Image");
    loadAllButton.setActionCommand("Load All Button");
    this.buttonDesigner(loadAllButton);

    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    this.buttonDesigner(saveButton);

    saveAllButton = new JButton("Save Entire Image");
    saveAllButton.setActionCommand("Save All Button");
    this.buttonDesigner(saveAllButton);

    JLabel messageLabel = new JLabel("Message Center: ");
    messageTextField = new JTextField("", 50);
    messageLabel.setForeground(new Color(255, 186, 210));
    messageLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
    messageTextField.setBackground(new Color(255, 255, 242));
    messageTextField.setForeground(Color.BLACK);
    messageTextField.setFont(new Font("Monospaced", Font.BOLD, 13));
    JScrollPane messageScrollPane = new JScrollPane();
    messageScrollPane.setPreferredSize(new Dimension(200, 200));
    messageScrollPane.setViewportView(messageTextField);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur Button");
    this.buttonDesigner(blurButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen Button");
    this.buttonDesigner(sharpenButton);

    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale Button");
    this.buttonDesigner(greyscaleButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia Button");
    this.buttonDesigner(sepiaButton);

    visibleButton = new JButton("Visible");
    visibleButton.setActionCommand("Visible Button");
    this.buttonDesigner(visibleButton);

    invisibleButton = new JButton("Invisible");
    invisibleButton.setActionCommand("Invisible Button");
    this.buttonDesigner(invisibleButton);

    addButton = new JButton("Add Layer");
    addButton.setActionCommand("Add Button");
    this.buttonDesigner(addButton);

    removeButton = new JButton("Remove Layer");
    removeButton.setActionCommand("Remove Button");
    this.buttonDesigner(removeButton);

    currentTextField = new JTextField("", 1);
    currentTextField.setActionCommand("Current Text Field");
    currentTextField.setBackground(new Color(255, 255, 242));
    currentTextField.setForeground(Color.BLACK);
    JLabel currentLabel = new JLabel("Current Layer:");
    currentLabel.setForeground(new Color(255, 186, 210));
    currentLabel.setFont(new Font("Monospaced", Font.BOLD, 13));

    // Input Output Panel
    JPanel ioPanel = new JPanel();
    ioPanel.setBackground(Color.DARK_GRAY);
    ioPanel.setLayout(new FlowLayout(3));
    ioPanel.setBorder(new LineBorder(Color.BLACK, 5));
    ioPanel.add(loadButton);
    ioPanel.add(loadAllButton);
    ioPanel.add(saveButton);
    ioPanel.add(saveAllButton);

    // Operations Panel
    JPanel operationPanel = new JPanel();
    operationPanel.setBackground(Color.DARK_GRAY);
    operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.PAGE_AXIS));
    operationPanel.setBorder(new LineBorder(Color.BLACK, 3));

    // Visibility Panel
    JPanel visibilityPanel = new JPanel();
    visibilityPanel.setBackground(Color.DARK_GRAY);
    visibilityPanel.setLayout(new BoxLayout(visibilityPanel, BoxLayout.PAGE_AXIS));
    visibilityPanel.setBorder(new LineBorder(Color.BLACK, 3));

    // Left Panel
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(4, 1, 5, 5));
    leftPanel.setBackground(Color.DARK_GRAY);
    leftPanel.setBorder(new LineBorder(Color.BLACK, 2));
    leftPanel.add(blurButton);
    leftPanel.add(sharpenButton);
    leftPanel.add(greyscaleButton);
    leftPanel.add(sepiaButton);

    // Right Panel
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(2, 1, 5, 5));
    rightPanel.setBackground(Color.DARK_GRAY);
    rightPanel.setBorder(new LineBorder(Color.BLACK, 2));
    rightPanel.add(visibleButton);
    rightPanel.add(invisibleButton);

    // Center Image
    centerImage = new JLabel();
    centerImage.setOpaque(true);
    centerImage.setBackground(new Color(255, 255, 242));
    centerImage.setBorder(new LineBorder(Color.BLACK, 5));

    centerImage.setPreferredSize(new Dimension(700, 500));
    JScrollPane imageScrollPane = new JScrollPane();
    imageScrollPane.setPreferredSize(new Dimension(200, 200));
    imageScrollPane.setViewportView(centerImage);

    // Low Panel
    JPanel lowPanel = new JPanel();
    lowPanel.setBorder(new LineBorder(Color.BLACK, 5));
    lowPanel.setBackground(Color.DARK_GRAY);
    lowPanel.setLayout(new FlowLayout(3));
    lowPanel.add(addButton);
    lowPanel.add(removeButton);
    lowPanel.add(currentLabel);
    lowPanel.add(currentTextField);
    lowPanel.add(messageLabel);
    lowPanel.add(messageTextField);

    operationPanel.add(leftPanel);
    visibilityPanel.add(rightPanel);

    mainPanel.add(ioPanel, BorderLayout.NORTH);
    mainPanel.add(operationPanel, BorderLayout.WEST);
    mainPanel.add(visibilityPanel, BorderLayout.EAST);
    mainPanel.add(imageScrollPane, BorderLayout.CENTER);
    mainPanel.add(lowPanel, BorderLayout.SOUTH);


  }

  private void buttonDesigner(JButton button) {
    button.setForeground(new Color(255, 186, 210));
    button.setBackground(Color.GRAY);
    button.setOpaque(true);
    Border line = new LineBorder(Color.LIGHT_GRAY);
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    button.setBorder(compound);
    button.setFont(new Font("Monospaced", Font.BOLD, 13));
  }

  @Override
  public void setListener(ActionListener listener) {
    // Buttons
    loadButton.addActionListener(listener);
    loadAllButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    saveAllButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    visibleButton.addActionListener(listener);
    invisibleButton.addActionListener(listener);
    addButton.addActionListener(listener);
    removeButton.addActionListener(listener);
    currentTextField.addActionListener(listener);

    // Menus
    loadItem.addActionListener(listener);
    saveItem.addActionListener(listener);
    saveAllItem.addActionListener(listener);
    exitItem.addActionListener(listener);
    blurItem.addActionListener(listener);
    sharpenItem.addActionListener(listener);
    greyscaleItem.addActionListener(listener);
    sepiaItem.addActionListener(listener);
    visibleItem.addActionListener(listener);
    invisibleItem.addActionListener(listener);
    loadScriptItem.addActionListener(listener);
    addLayerItem.addActionListener(listener);
    removeLayerItem.addActionListener(listener);
    loadAllItem.addActionListener(listener);

    mainPanell = new JPanel();
    JScrollPane mainlScrollPane = new JScrollPane();
  }

  @Override
  public JTextField getTextField() {
    return this.currentTextField;
  }

  @Override
  public JLabel getCenterImage() {
    return this.centerImage;
  }

  @Override
  public Container getMainPanel() {
    return this.mainPanell;
  }

  @Override
  public void setImageIconPath(JLabel imageLabel, String path) {
    imageLabel.setIcon(new ImageIcon(path));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);

  }

  @Override
  public void setImageIconBuffImg(JLabel imageLabel, BufferedImage buffImg) {
    imageLabel.setIcon(new ImageIcon(buffImg));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
  }

  // renders the given message
  @Override
  public void renderMessage(String message) {
    messageTextField.setText("");
    messageTextField.setText(message);

  }


}

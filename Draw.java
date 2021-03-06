import controlP5.*;
import controlP5.ControlP5.*;
import processing.core.PApplet;
import java.util.ArrayList;

public class Draw {
  public static final int BACKGROUND = 255,
          ID_BTN_NS_MINUS = 1,
          ID_BTN_NS_PLUS = 2,
          ID_BTN_NEW_STAIR = 3,
          ID_BTN_CLEAR_STAIRS = 4,
          ID_BTN_CLEAR_DRAWINGS = 5;
  
  private PApplet app;
  private ArrayList<Stair> stairs = new ArrayList<Stair>();
  private ArrayList<Line> lines = new ArrayList<Line>();
  // ControlP5
  ControlP5 cp5;
  private Textfield nsTextField;
  private Button nsMinus, nsPlus, newStair;
  private RadioButton alignRB;
  
  public Draw(PApplet app) {
    this.app = app;
    cp5 = new ControlP5(app);

    // Set up ControlP5
    final int SM_BUTTON_WIDTH = 30,
            SM_BUTTON_HEIGHT = 25;
    // Draw interface
    int cx = 1;
    nsMinus = cp5.addButton("-")
            .setId(ID_BTN_NS_MINUS)
            .setPosition(cx, 1)
            .setSize(SM_BUTTON_WIDTH, SM_BUTTON_HEIGHT);
    cx += SM_BUTTON_WIDTH * Conf.iScale() + 3;
    nsTextField = cp5.addTextfield("nsSize")
            .setText("10")
            .setPosition(cx, 1)
            .setSize(SM_BUTTON_WIDTH, SM_BUTTON_HEIGHT);
    cx += SM_BUTTON_WIDTH * Conf.iScale() + 3;
    nsPlus = cp5.addButton("+")
            .setId(ID_BTN_NS_PLUS)
            .setPosition(cx, 1)
            .setSize(SM_BUTTON_WIDTH, SM_BUTTON_HEIGHT);
    cx += SM_BUTTON_WIDTH * Conf.iScale() + 5;
    newStair = cp5.addButton("ADD")
            .setId(ID_BTN_NEW_STAIR)
            .setPosition(cx, 1)
            .setSize((int)(2 * SM_BUTTON_WIDTH * Conf.iScale()), SM_BUTTON_HEIGHT);
    cx += 2 * SM_BUTTON_WIDTH * Conf.iScale() + 30;
    cp5.addButton("CL. STAIRS")
            .setId(ID_BTN_CLEAR_STAIRS)
            .setPosition(cx, 1)
            .setSize((int)(2 * SM_BUTTON_WIDTH * Conf.iScale()), SM_BUTTON_HEIGHT);
    cx += 2 * SM_BUTTON_WIDTH * Conf.iScale() + 5;
    cp5.addButton("CL. DRAW-s")
            .setId(ID_BTN_CLEAR_DRAWINGS)
            .setPosition(cx, 1)
            .setSize((int)(2 * SM_BUTTON_WIDTH * Conf.iScale()), SM_BUTTON_HEIGHT);
    // RIGHT SIDE
    alignRB = cp5.addRadioButton("alignRB")
            .setPosition(app.width - SM_BUTTON_WIDTH * Conf.iScale() - 1 -50, 1)
            .setSize(SM_BUTTON_WIDTH, SM_BUTTON_HEIGHT)
            .setSpacingRow(3)
            .setColorLabel(0xFF000000)
            .addItem("LT", 0)
            .addItem("RB", 1)
            .addItem("", -1)
            .addItem("LB", 2)
            .addItem("RT", 3);
    for(Toggle t:alignRB.getItems()) {
//      t.getCaptionLabel().setColorBackground(0xFF000000);
//      t.getCaptionLabel().getStyle().moveMargin(-7,0,0,-3);
//      t.getCaptionLabel().getStyle().movePadding(7,0,0,3);
      t.getCaptionLabel().getStyle().backgroundWidth = 30;
      t.getCaptionLabel().getStyle().backgroundHeight = SM_BUTTON_HEIGHT;
    }
  }

  public void controlEvent(ControlEvent event) {
    if (event.isController()) {
      // app.println("Text: " + nsTextField.getText());
      switch (event.getController().getId()) {
        case (ID_BTN_NS_MINUS):
          nsTextField.setText(String.valueOf(Integer.parseInt(nsTextField.getText().trim()) - 1));
          break;
        case (ID_BTN_NS_PLUS):
          nsTextField.setText(String.valueOf(Integer.parseInt(nsTextField.getText().trim()) + 1));
          break;
        case (ID_BTN_CLEAR_STAIRS):
          stairs.clear();
          break;
        case (ID_BTN_CLEAR_DRAWINGS):
          lines.clear();
          break;
        case (ID_BTN_NEW_STAIR):
          String nsSizeS = nsTextField.getText().trim();
          int nsSize = Integer.parseInt(nsSizeS);
          int alignSetInt = (int)alignRB.getValue();
          Stair.Align alignSet = Stair.Align.LT;
          switch (alignSetInt) {
            case (0):
              alignSet = Stair.Align.LT;
              break;
            case (1):
              alignSet = Stair.Align.RB;
              break;
            case (2):
              alignSet = Stair.Align.LB;
              break;
            case (3):
              alignSet = Stair.Align.RT;
              break;
          }
          addStair(50, 70, nsSize, alignSet);
          break;
      }
    }
  }
  
  public void addStair(float x, float y, int size, Stair.Align align) {
    Stair st = new Stair(app, x, y, size, align);
    stairs.add(st);
//    return st;
  }

  public void addLine(float px, float py, float nx, float ny) {
    lines.add(new Line(app, px, py, nx, ny));
    app.println(lines.size());
  }
  
  public ObjUnder objUnder(float qx, float qy) {
    for (int i = 0; i < stairs.size(); i++) {
      Stair st = stairs.get(i);
      if (st.isUnder(qx, qy)) {
        return new ObjUnder(ObjUnder.Type.STAIR, st);
      }
    }
    return new ObjUnder();
  }
  
  public void draw() {
    // Clear screen
    app.background(BACKGROUND);

    // Draw lines
    for (int i = 0; i < stairs.size(); i++) {
      stairs.get(i).draw();
    }

    // Draw stairs
    for (int i = 0; i < lines.size(); i++) {
      lines.get(i).draw();
    }
  }

}

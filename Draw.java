import processing.core.PApplet;
import java.util.ArrayList;

public class Draw {
  public static final int BACKGROUND = 255;
  
  private PApplet app;
  private ArrayList<Stair> stairs = new ArrayList<Stair>();
  
  public Draw(PApplet app) {
    this.app = app;
  }
  
  public Stair addStair(float x, float y, int size, Stair.Align align) {
    Stair st = new Stair(app, x, y, size, align);
    stairs.add(st);
    return st;
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
    app.background(BACKGROUND);
    for (int i = 0; i < stairs.size(); i++) {
      stairs.get(i).draw();
    }
  }

}

import controlP5.*;
Draw dr;

void setup() {
  size(1200, 800);
  //background(12);
  background(Draw.BACKGROUND);
  
  dr = new Draw(this);
  //dr.addStair(100, 100, 10, Stair.Align.LT);
}

void draw() {
  dr.draw();
}

Drawing holdStair;
ObjUnder obj = new ObjUnder();

void mousePressed() {
  obj = dr.objUnder(mouseX, mouseY);
  
  if (obj.getType() == ObjUnder.Type.STAIR) {
    holdStair = obj.getObj();
  }
}

void mouseReleased() {
  obj = new ObjUnder();
}

void mouseDragged() {
  if (obj.getType() == ObjUnder.Type.STAIR) {
    holdStair.move(mouseX - pmouseX, mouseY - pmouseY);
  } else if (obj.getType() == ObjUnder.Type.NONE) {
    dr.addLine(pmouseX, pmouseY, mouseX, mouseY);
  }
}

void controlEvent(ControlEvent event) {
  dr.controlEvent(event);
}

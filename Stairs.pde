
Draw dr;

void setup() {
  size(1200, 800);
  //background(12);
  background(Draw.BACKGROUND);
  
  dr = new Draw(this);
  dr.addStair(100, 100, 10, Stair.Align.LT);
}

void draw() {
  dr.draw();
}

Drawing holdStair;

void mousePressed() {
  ObjUnder obj = dr.objUnder(mouseX, mouseY);
  
  if (obj.getType() == ObjUnder.Type.STAIR) {
    holdStair = obj.getObj();
  }
}

void mouseReleased() {
  holdStair = null;
}

void mouseDragged() {
  if (holdStair != null) {
    holdStair.move(mouseX - pmouseX, mouseY - pmouseY);
  }
}

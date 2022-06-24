import processing.core.PApplet;

public class Stair implements Drawing {
    public static final float BOX_SIZE = 30;
    
    private PApplet app;
    private float x, y;
    private int size;
    private Align align;
    
    public enum Align {RT, RB, LT, LB}
    
    public float getX() {return x;}
    public float getY() {return y;}
    public void setX(float qx) {x = qx;}
    public void setY(float qy) {y = qy;}
    public void setXY(float qx, float qy) {x = qx; y = qy;}
    
    public void move(float vx, float vy) {
      x += vx;
      y += vy;
    }
    
    public Stair(PApplet app, float x, float y, int size, Align align) {
      this.app = app;
      this.x = x;
      this.y = y;
      this.size = size;
      this.align = align;
    }
    
    public boolean isUnder(float qx, float qy) {
      float x_shift = 0;
      float y_shift = 0;
  
      if (align == Align.RT || align == Align.RB) {
        x_shift = (size - 1) * BOX_SIZE * Conf.scale();
      }
      if (align == Align.LB || align == Align.RB) {
        y_shift = (size -1) * BOX_SIZE * Conf.scale();
      }
  
      if ((qx > x + x_shift) && (qx < x + BOX_SIZE * Conf.scale() + x_shift)) {
        if ((qy > y + y_shift) && (qy < y + BOX_SIZE * Conf.scale() + y_shift)) {
          return true;
        }
      }
      
      return false;
    }
    
    public void draw() {
      for (int row = 0; row < size; row++) {
        float col_size;
        if (align == Align.RT || align == Align.LT) {
          col_size = size - row;
        }
        else {
          col_size = row + 1;
        }
        
        for (int col = 0; col < col_size; col++) {
          float x_shift = 0;
          if (align == Align.RT) {
            x_shift = row * BOX_SIZE * Conf.scale();
          } else if (align == Align.RB) {
            x_shift = (size - row - 1) * BOX_SIZE * Conf.scale();
          }
          app.fill(Conf.color(row%10));
          app.stroke(Conf.color(Conf.COLOR_BORDER));
          app.square(x + col*BOX_SIZE*Conf.scale() + x_shift,
            y + row*BOX_SIZE*Conf.scale(), 
            BOX_SIZE*Conf.scale());
        }
      }
    }
  }

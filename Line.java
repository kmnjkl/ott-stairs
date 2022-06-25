import processing.core.PApplet;

public class Line implements Drawing{
    private PApplet app;
    private float x1, y1, x2, y2;

    public Line(PApplet app, float x1, float y1, float x2, float y2) {
        this.app = app;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public boolean isUnder(float qx, float qy) {
        return false;
    }

    @Override
    public void move(float vx, float vy) {

    }

    public void draw() {
        app.stroke(Conf.color(Conf.COLOR_DRAW));
        app.line(x1, y1, x2, y2);
    }
}

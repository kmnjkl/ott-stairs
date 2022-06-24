public class Conf {
  private static float scale = 1, iScale = 1;
  public static final int COLOR_BORDER = 10;
  private static int[] colors = {0xFFFFB700, 0xFFFFFF00, 0xFFFF0026, 0xFF00FF10, 
                       0xFF00FFFF, 0xFF001BFF, 0xFFFA00FF, 0xFFB2FF00,
                       0xFF5000FF, 0xFF0092FF, 0xFF888888};
  
  public static float scale() {
    return scale;
  }
  
  public static float iScale() {
    return iScale;
  }
  
  public static int color(int i) {
    return colors[i];
  }
}

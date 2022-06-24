public class ObjUnder {
    private Type type = Type.NONE;
    private Drawing object;
    
    public enum Type {NONE, STAIR, LINE};
    
    public ObjUnder() {}
    
    public ObjUnder(Type t, Drawing obj) {
      this.type = t;
      this.object = obj;
    }
    
    public Type getType() {
      return type;
    }
    
    public Drawing getObj() {
      return object;
    }
  }

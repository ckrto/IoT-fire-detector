package test;

public class SensorData {
   private int x;
   private int y;

   public SensorData(int x, int y) {
      this.setX(x);
      this.setY(y);
   }
   
   public int getX() {
      return x;
   }
   public void setX(int x) {
      this.x = x;
   }
   public int getY() {
      return y;
   }
   public void setY(int y) {
      this.y = y;
   }
}
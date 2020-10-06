package sample;

public class Square {
    private int tier;
    private int x;
    private int y;

    public Square(int tier, int x, int y){
        this.tier = tier;
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty(){
        return tier == 0;
    }

    public void tierUp(){
        this.tier++;
    }

    public int getTier(){
        return this.tier;
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

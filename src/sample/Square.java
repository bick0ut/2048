package sample;

public class Square {
    private int tier;
    private int x;
    private int y;
    private boolean immune;

    public Square(int tier, int x, int y){
        this.tier = tier;
        this.x = x;
        this.y = y;
        this.immune = false;
    }

    public boolean isEmpty(){
        return tier == 0;
    }

    public int tierUp(){
        this.tier++;
        this.immune = true;
        return (int)Math.pow(2, this.tier);
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

    public void setImmune(boolean immune){
        this.immune = immune;
    }

    public boolean isImmune(){
        return this.immune;
    }
}

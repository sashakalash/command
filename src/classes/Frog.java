package classes;

public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() { position = 5; }

    public boolean jump(int steps) {
        // сделаем прыжок, если прыжок слишком большой
        // для поля, то не прыгнем и вернём false
        if (position + steps <= MAX_POSITION && position + steps >= MIN_POSITION) {
            position += steps;
            return true;
        }
        System.out.println("Невозможно прыгнуть за рамки поля! Повторите ввод");
        return false;
    }

    public int getPosition() {
        return position;
    }
}
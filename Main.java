public class Main {
    public static void main(String[] args) {
        Stock gamestop = new Stock("Gamestop", 50, .5);
        for (int i=0; i<50; i++)
        {
            gamestop.advanceDay();
        }
    }
}

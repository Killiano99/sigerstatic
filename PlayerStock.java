public class PlayerStock {
    static int counter = 0;
    Stock stock;
    double quantity;
    Player player;
    int id;
    public PlayerStock(Stock stock, double quantity, Player player) {
        this.stock = stock;
        this.quantity = quantity;
        this.player = player;
        id = counter;
        counter++;
    }
    
    public Stock getStock() {
        return stock;
    }
    public double getQuantity() {
        return quantity;
    }
    public boolean changeQuantity(double change) {
        if(change + quantity < 0) {
            return false;
        }
        quantity += change;
        return true;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public double getPrice() {
        return stock.getPrice() * quantity;
    }

    public int getId() {
        return id;
    }


    


}

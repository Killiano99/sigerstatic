import java.util.*;;
public class Player {
    double money;
    ArrayList<PlayerStock> ownedStocks = new ArrayList<PlayerStock>();
    //implement job later
    //implement other stuff too
    public Player() {
        money = 0;
    }
    public double getMoney() {
        return money;
    }
    public void changeMoney(double change) {
        money += change;
    }
    public ArrayList<PlayerStock> getOwnedStocks() {
        return ownedStocks;
    }
    public boolean buyStock(Stock s, double qty) {
        PlayerStock p = new PlayerStock(s, qty, this);
        if(money < p.getPrice()) {
            return false;
        }
        changeMoney(-1 * p.getPrice());
        addStock(p);
        return true;
    }
    public boolean sellStock(PlayerStock pStock) {
        boolean sold = removeStock(pStock);
        if(sold) {
            changeMoney(pStock.getPrice());
        }
        return sold;
        
    }
    private boolean removeStock(PlayerStock pStock) {
        boolean removed = false;
        for(int i = 0; i < ownedStocks.size(); i++) {
            if(ownedStocks.get(i).getId() == pStock.getId()) {
                ownedStocks.remove(i);
                i--;
                removed = true;
            }
        }
        return removed;
    }
    public void addStock(PlayerStock p) {
        ownedStocks.add(p);
    }
}

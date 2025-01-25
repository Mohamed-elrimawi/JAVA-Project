import java.util.Comparator;

public class SortByPrice implements Comparator<StockItem> {
    @Override
    public int compare(StockItem o1, StockItem o2) {
        return Double.compare(o2.getPrice() , o1.getPrice()); // sort array list according to the price in descending order

    }
}

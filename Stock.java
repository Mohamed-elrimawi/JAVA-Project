

import java.util.*;
public class Stock {

   private  String name;
   private  Location location;
   private  ArrayList<StockItem> list = new ArrayList<>();

    public Stock() {
    }

    public Stock(String name, Location location) {
        this.name = name;
        this.location = location;

    }


  public ArrayList<StockItem> getItems() {

        return list;
  }

  public void setItems(ArrayList<StockItem> list) {

        this.list = list;
  }

    public int countGrocery(){

int count = 0;
    for(int i=0;i<list.size();i++){

        if(list.get(i) instanceof  Grocery){
            count++;
        }
    }
        return count;
    }


    public int countElectronic (){

        int count = 0;
        for(int i=0;i<list.size();i++){

            if(list.get(i) instanceof  Electronic){
                count++;
            }
        }
        return count;
    }




    public void addStockItem(StockItem item){

    list.add(item);
    }





    public ArrayList<StockItem> getList() {
        return list;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setList(ArrayList<StockItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", list=" + list +
                '}';
    }
}


import java.util.*;
public class Grocery extends StockItem implements Cloneable  {

    private String name;
    private  Calendar expiryDate;
    private float weight;


    public Grocery() {
    }


    @Override
    public double calculateDiscount() {
        return price -(price*(getDiscount()/100));
    } // this method calculate discount

    public Grocery( String name , String brand, double discount, double price, Calendar expiryDate, float weight) {
        super(brand, discount, price);
        this.name = name;
        this.expiryDate = expiryDate;


            this.weight = weight;

    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double calculateTax () {  // this method calculate tax (5.75%)

        return ( (price - (price * getDiscount()) ) * 0.0575) + (price * getDiscount());
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }








    @Override
    public String toString() {
        int year = expiryDate.get(Calendar.YEAR);
        int month = expiryDate.get(Calendar.MONTH) + 1;
        int day = expiryDate.get(Calendar.DAY_OF_MONTH);

        if(discount>0){
            return brand + " " + name + "(" + price + ") has a discount : " + discount + "%," + "expiry date " + day+"/"+month+"/"+year + " (" + weight + ") ,after discount price is "+calculateDiscount()+",after tax included ("+calculateTax()+").";

        }
        else {
            return brand + " " + name + "(" + price + ") , expiry date " + day+"/"+month+"/"+year + " (" + weight + ") ,after discount price is "+calculateDiscount()+",after tax included ("+calculateTax()+").";

        }
    }


    @Override
    public Object clone() {
        Grocery clone = (Grocery) super.clone(); // copy the attributes of Grocery except calendar object
        clone.setExpiryDate((Calendar)expiryDate.clone()); // copy calendar object

        return clone;
    }
}




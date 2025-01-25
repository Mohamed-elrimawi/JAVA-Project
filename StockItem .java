public abstract class StockItem implements Comparable<StockItem> ,Cloneable{
    protected String brand;
    protected double discount;
    protected double price;


    public StockItem() {
    }

    @Override
    public int compareTo(StockItem o) {
        return o.getBrand().compareTo(this.getBrand()); //this compare between this object o and the object who invoked the method if object o greater than the object who invoked return 1 and put the object who invoked before the object o
    }

    public StockItem(String brand, double discount, double price) {
        this.brand = brand;

            this.discount = discount;


            this.price = price;


    }




public abstract double calculateDiscount();

    public boolean equals(StockItem s) {
        if(this.getBrand().equals(s.getBrand()) && this.getDiscount() == s.getDiscount() && this.getPrice() == s.getPrice()) {
            return true;
        }
return false;

    }


    public abstract double calculateTax();


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {

if(discount >0) {
    return brand + "(" + price + ")" + "has a discount of " + discount;
}
else {
    return brand + "(" + price + ")";
}
    }






    @Override
    public Object clone() {
        try {
            StockItem clone = (StockItem) super.clone();  // copy the attributes of Stock item

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


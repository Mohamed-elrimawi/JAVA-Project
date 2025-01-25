public class Electronic extends  StockItem implements Cloneable {

    String version;
    String type;
    double power;




    public Electronic() {
    }

    @Override
    public double calculateDiscount() { //this method calculate discount

        return price -(price*(getDiscount()/100));
    }

    public Electronic(String version,String brand ,double discount,double price,  String type, double power) {

       super(brand,discount,price);
        this.version = version;
        this.type = type;
        this.power = power;
    }



    public double calculateTax () { //this method calculate tax (1.65)%

        return ( (price - (price *discount/100) ) * 0.165) + (price - (price *discount/100));

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }



    @Override
    public String toString() {

        if (discount > 0) {
            return brand + "(" + price + ")" + "with a discount of " + discount + "%(" + type + " )" + power + "After discount price is " + calculateDiscount() + ",after tax included(" + calculateTax() +")";
        }
        else{
            return brand + "(" + price + ")(" + type + " )" + power + ",after tax included(" + calculateTax()+ ")";
        }
    }

    @Override
    public Electronic clone() {
        Electronic clone = (Electronic) super.clone(); // copy the attributes of Electronic

        return clone;
    }
}

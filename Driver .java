
import java.util.*;
import java.io.*;
public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File file = new File("Data.txt");


        Grocery g1 = new Grocery();

        Stock stock = new Stock("Al-Mashhadawi", new Location("Lacasa Mall", "Arehan", "Ramallah"));


        boolean flag = true;

        while (flag) {

            System.out.println("1. Read from file ");
            System.out.println("2. upload items ");
            System.out.println("3. Print all expired Grocery items ");
            System.out.println("4. Print all stock Electronic items that have discounts ");
            System.out.println("5. Print all stock items sorted by brand ");
            System.out.println("6. Print all stock items sorted by price");
            System.out.println("7. Print all stock items that have discounts");
            System.out.println("8. Clone the ArrayList ");
            System.out.println("9. Exit Program ");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    readFile(file, stock.getItems() ,stock);

                    break;
                case 2:
                    System.out.println("Enter the number of items you want to add:");
                    int numOfItems = input.nextInt();
                    int j = 0;

                    while (j < numOfItems) {
                        System.out.println("Enter the type of item Grocery or Electronic:");
                        String type = input.next();

                        if (type.equalsIgnoreCase("Grocery")) {
                            System.out.println("Enter Item : ");
                        } else if (type.equalsIgnoreCase("Electronic")) {
                            System.out.println("Enter Item : ");
                        } else {
                            System.out.println("Invalid item type, please try again.");
                            continue;
                        }
                        input.nextLine();
                        String item =input.nextLine();
                        String[] parts = item.split(",");



                        try {
                            if (type.equalsIgnoreCase("Grocery")) {

                                String[] date = parts[4].split("/");
                                Calendar c = new GregorianCalendar(Integer.parseInt(date[2]),Integer.parseInt(date[1])-1,Integer.parseInt(date[0]));
                                boolean exit =false;
                                while (exit == false) {


                                    if(!(isValidPrice(Double.parseDouble(parts[3])))){ // if user enter not valid price  ask the user to enter price again
                                        System.out.println("not valid price, try again");
                                        double price = input.nextDouble();
                                        parts[3]= String.valueOf(price);
                                        continue;
                                    }
                                    if (!(isValidWeight(Float.parseFloat(parts[5])))) {   // if user enter not valid weight  ask the user to enter weight  again
                                        System.out.println("not valid weight, try again");
                                       float  weigth = input.nextFloat();
                                       parts[5]= String.valueOf(weigth);
                                        continue;
                                    }
                                    if (!(isValidDis(Double.parseDouble(parts[2])))) { // if user enter not valid discount  ask the user to enter discount again
                                        System.out.println("not valid discount, try again");
                                       double  dis = input.nextDouble();
                                        parts[2]= String.valueOf(dis);
                                    }
                                    if(!isValidDate(c)){ // if user enter not valid Date  ask the user to enter Date again
                                        System.out.println("not valid date, try again");

                                        String ss = input.nextLine(); //read the line that user provided
                                        String[] parts2 = ss.split("/"); // split the lines into parts and put this parts int array of String
                                        Calendar c1 = new GregorianCalendar(Integer.parseInt(parts2[2]),Integer.parseInt(parts2[1])-1,Integer.parseInt(parts2[0]));

                                    }

                                   else{
                                       exit =true; // after check that all data is valid exit the loop
                                    }


                                }
                                Grocery g = new Grocery(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), c, Float.parseFloat(parts[5]));
                               stock.getItems().add(g);



                            } else if (type.equalsIgnoreCase("Electronic")) {
                                boolean exit = false;
                                while (exit == false) {


                                    if(!(isValidPrice(Double.parseDouble(parts[3])))){ // if user enter not valid price  ask the user to enter price again
                                        System.out.println("not valid price, try again");
                                        double price = input.nextDouble();
                                        parts[3]= String.valueOf(price);
                                        continue;
                                    }

                                    if (!(isValidDis(Double.parseDouble(parts[2])))) { // if user enter not valid discount  ask the user to enter discount again
                                        System.out.println("not valid discount, try again");
                                        double  dis = input.nextDouble();
                                        parts[2]= String.valueOf(dis);
                                    }
                                    if(!isValidPower(Double.parseDouble(parts[5]))){
                                        System.out.println("not valid power, try again");
                                        double power = input.nextDouble();
                                        parts[5]= String.valueOf(power);
                                    }


                                    else{
                                        exit =true; // after check that all data is valid exit the loop
                                    }


                                }


                                Electronic e = new Electronic(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[4], Float.parseFloat(parts[5]));

                                stock.getItems().add(e);
                            }
                        } catch (Exception e) {
                            System.out.println("Error adding item: " + e.getMessage());

                        }
                        j++;
                    }


                    writeFile(file,stock.getItems() );

                    break;
                case 3:
                    printExpiredGroceryItems(stock.getItems());
                    break;

                case 4:
                    getElectronicHasDis( stock.getItems());
                    break;
                case 5:
                    printSortBrand( stock.getItems());
                    break;
                case 6:
                    printSortPrice( stock.getItems());
                    break;
                case 7:
                    System.out.println("Enter discount :");
                    double dis = input.nextDouble();
                    printAllItemsHasDis(stock.getItems() ,dis);
                    break;
                case 8:
                   ArrayList <StockItem> cloned= clone1( stock.getItems());
                    System.out.println("Done");

                    break;
                case 9:
                    flag = false;
                    System.out.println("Thank you for using the program");
                    break;

            }

        }
    }


    public static void readFile(File file, ArrayList<StockItem> list , Stock stock) {  // this method read from file and put the data in the Array List
        list.clear();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine(); // here we read the  line and put this line in String value
                String[] parts = line.split(","); // here we split the line into parts
                if (parts[0].equals("Grocery")) {   // if the first part of the line equal Grocery put the data that in this line in the Grocery object
                    Calendar c = Calendar.getInstance();
                    String[] date = parts[5].split("/");   // the part 5 of line is the expiry date we spilt this part into 3 parts day  and month and year to make set for calendar and put this object in grocery object

                    c.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));

                    if(!isValidWeight(Float.parseFloat(parts[6])) ){ // if the weight  not valid don't read the line
                        continue;
                    }
                    if(!isValidDate(c)){   //if the date is not valid don't  read the line
                        continue;
                    }
                    if(!isValidDis(Double.parseDouble(parts[3]))){
                        continue;
                    }
                    if(!isValidPrice(Double.parseDouble(parts[4]))){
                        continue;
                    }

                    Grocery g = new Grocery(parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), c, Float.parseFloat(parts[6]));
                    list.add(g); // here we add grocery object named  g to the array  list

                } else if(parts[0].equals("Electronics")) { // if the first part of the line equal Electronics put the data that in this line in the Electronic object

                    if(!isValidDis(Double.parseDouble(parts[3]))){
                        continue;
                    }
                    if(!isValidPrice(Double.parseDouble(parts[4]))){
                        continue;
                    }

                    if(!isValidPower(Double.parseDouble(parts[6]))){
                        continue;
                    }

                    Electronic e = new Electronic(parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), parts[5], Float.parseFloat(parts[6]));
                    list.add(e);
                }
                stock.setItems(list);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void writeFile(File file, ArrayList<StockItem> list ) {  //this method write at file


        try ( PrintWriter printWriter = new PrintWriter(file);) {


           for(int i=0; i<list.size(); i++) {


               if(list.get(i) instanceof Grocery) {// if the StockItem object is instance of Grocery print the toString that implement in Grocery class at file
                   Calendar c = ((Grocery)list.get(i)).getExpiryDate();
                  printWriter.println("Grocery," + ((Grocery) list.get(i)).getName() + "," + list.get(i).getBrand() + "," + list.get(i).getDiscount() + "," + list.get(i).getPrice() + "," +printCalendar(c) +"," + ((Grocery) list.get(i)).getWeight());

               }
               else if(list.get(i) instanceof Electronic ) { // if the StockItem object is instance of Electronic print the toString that implement in Electronic class at file
                   printWriter.println("Electronics,"+((Electronic) list.get(i)).getVersion()+","+list.get(i).getBrand()+","+list.get(i).getDiscount()+","+list.get(i).getPrice()+","+((Electronic) list.get(i)).getType()+","+((Electronic) list.get(i)).getPower());
               }
           }
            System.out.println("Done");
            printWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public static void printExpiredGroceryItems(ArrayList<StockItem> list) { // this method print the Expired Grocery Items


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Grocery) {


                Calendar c = Calendar.getInstance(); // current date

                if (c.compareTo(((Grocery) list.get(i)).getExpiryDate()) > 0) { // if the current date greater than expiry date return 1 and print the item
                    System.out.println(list.get(i).toString());
                }
            }
        }

    }

    public static void getElectronicHasDis(ArrayList<StockItem> list) { // this method print all the  Electronic items that  have the discount

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Electronic) { // if the stock item object
                if (list.get(i).getDiscount() > 0) {
                    System.out.println(list.get(i).getBrand() +" "+ ((Electronic) list.get(i)).getType() +" "+((Electronic) list.get(i)).calculateDiscount() );
                }
            }
        }

    }

    public static void printSortBrand(ArrayList<StockItem> list) {

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    public static void printSortPrice(ArrayList<StockItem> list) { //this method print all the items that in the array list sorted  by price

        Collections.sort(list, new SortByPrice()); // sorting according to the price

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    public static void printAllItemsHasDis(ArrayList<StockItem> list , double discount ) { // this method print all the items that have same the discount that user provided


        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getDiscount() <= discount) {
                if (list.get(i).getDiscount() > 0) {
                    System.out.println(list.get(i).toString());
                }
            }

        }
    }

    public static ArrayList<StockItem> clone1 (ArrayList < StockItem > list) { //this method make copy for each object that in the array list and put it in new array list named copy
                ArrayList<StockItem> copy = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {

                    copy.add((StockItem) list.get(i).clone());
                }
                return copy;

            }


            public static boolean isValidPower ( double power){ //this method check if power is valid or not
                return power > 0;

            }
    public static boolean isValidDate(Calendar expiryDate) { // this method check if the date is valid or not

        try {

            int day = expiryDate.get(Calendar.DAY_OF_MONTH); // this get the day and put it in integer day
            int month = expiryDate.get(Calendar.MONTH);  // this get the month and put it in integer month
            int year = expiryDate.get(Calendar.YEAR);  // this get the year and put it in integer year


            if( month<1 || day < 1 || day>31|| year < 1){
                return false;
            }
            if(expiryDate.getActualMaximum(Calendar.DAY_OF_MONTH) < day){
                return false;
            }

            if(month >12){
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }



    public static boolean isValidWeight(double weight) {
        return weight > 0;
    }



    public static boolean isValidPrice(double price) {
        return price >= 0;
    }

    public  static boolean isValidDis(double discount) {

        return discount >= 0 && discount <= 100 ;

    }

    public static void printList(ArrayList<StockItem> list ){  // this method print all the objects that in the array list
        try{
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i).toString());
            }
        }catch(IllegalArgumentException e){
            System.out.println("The item : 3 , has "+e.getMessage());
        }

    }


    public static String  printCalendar (Calendar c){

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return day+"/"+month+"/"+year;
    }


}




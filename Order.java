import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Order {
    public static void main(String[] args) {
        String fileName = "OrderDetails.txt";
        String line;

        ArrayList<Integer> quantityList = new ArrayList<>();
        ArrayList<Double> unitPriceList = new ArrayList<>();
        ArrayList<Integer> orderIDList = new ArrayList<>();
        ArrayList<Integer> productIDList = new ArrayList<>();
        ArrayList<Double> discountList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                quantityList.add(Integer.parseInt(values[0].trim()));     // Quantity
                unitPriceList.add(Double.parseDouble(values[1].trim()));  // UnitPrice
                orderIDList.add(Integer.parseInt(values[2].trim()));       // OrderID
                productIDList.add(Integer.parseInt(values[3].trim()));     // ProductID
                discountList.add(Double.parseDouble(values[4].trim()));    // Discount
            }

            ArrayList<Double> priceList = new ArrayList<>();

            try (FileWriter writer = new FileWriter("Prices.txt")) {
                for (int x = 0; x < orderIDList.size(); x++) {
                    double totalPrice = (unitPriceList.get(x) * quantityList.get(x)) - (discountList.get(x) * quantityList.get(x) * unitPriceList.get(x));
                    priceList.add(totalPrice);

                    writer.write(String.format("%d, $%.2f\n", orderIDList.get(x), priceList.get(x)));
                }
            } catch (Exception e) {
                System.out.println("Error.");
            }

        } catch (Exception e) {
            System.out.println("Error.");
        }
    }
}
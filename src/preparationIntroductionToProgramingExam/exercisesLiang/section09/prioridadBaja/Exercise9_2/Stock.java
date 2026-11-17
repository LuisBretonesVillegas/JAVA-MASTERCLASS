package preparationIntroductionToProgramingExam.exercisesLiang.section09.prioridadBaja.Exercise9_2;

public class Stock {
    public static void main(String[] args) {
        Stock stock = new Stock("ORCL", "Oracle Corporation");
        stock.previousClosingPrice = 34.5;
        stock.currentPrice = 34.35;
        System.out.println("The change percent of the stock is: " + stock.getChangePercent() + "%");
    }

    private String symbol, name;
    private double previousClosingPrice, currentPrice;

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public double getChangePercent() {
        return (previousClosingPrice - currentPrice) / previousClosingPrice * 100;
    }
}

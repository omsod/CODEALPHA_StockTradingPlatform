import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        balance -= cost;
        System.out.println("Bought " + quantity + " shares of " + symbol);
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (!holdings.containsKey(symbol) || holdings.get(symbol) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(symbol, holdings.get(symbol) - quantity);
        balance += quantity * price;
        System.out.println("Sold " + quantity + " shares of " + symbol);
    }

    public void displayPortfolio() {
        System.out.println("\nPortfolio Summary:");
        System.out.println("Balance: " + balance);
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Stock> market = new HashMap<>();
        market.put("APPLE", new Stock("APPLE", 150.0));
        market.put("GOOGLE", new Stock("GOOGLE", 2800.0));
        market.put("TESLA", new Stock("TESLA", 700.0));
        market.put("TATA", new Stock("TATA", 500.0));
        market.put("JIO", new Stock("JIO", 400.0));
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        Portfolio portfolio = new Portfolio(balance);

        while (true) {
            System.out.println("\n1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nMarket Data:");
                    for (Stock stock : market.values()) {
                        System.out.println(stock.symbol + " - $" + stock.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next().toUpperCase();
                    if (!market.containsKey(buySymbol)) {
                        System.out.println("Invalid stock symbol!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();
                    portfolio.buyStock(buySymbol, buyQty, market.get(buySymbol).price);
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    if (!market.containsKey(sellSymbol)) {
                        System.out.println("Invalid stock symbol!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();
                    portfolio.sellStock(sellSymbol, sellQty, market.get(sellSymbol).price);
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}

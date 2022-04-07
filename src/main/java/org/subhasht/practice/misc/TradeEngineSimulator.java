package org.subhasht.practice.misc;

import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TradeEngineSimulator {
    public static void main(String[] args) {
        TradeEngine tradeEngine = new TradeEngine();
        Company c1 = new Company("C1");
        Company c2 = new Company("C2");
        Company c3 = new Company("C3");
        Company c4 = new Company("C4");
        Company c5 = new Company("C5");

        User u1 = new User("U1");
        User u2 = new User("U2");
        User u3 = new User("U3");
        User u4 = new User("U4");
        User u5 = new User("U5");

        long id = 1;
        tradeEngine.addTrade(new Trade(id++, "SYB-001", c1, u1, LocalDateTime.now(), 100, new BigDecimal(10), 'B'));
        Assertions.assertEquals(0, tradeEngine.getMatchedTradeQuantity());

        tradeEngine.addTrade(new Trade(id++, "SYB-001", c1, u1, LocalDateTime.now(), 60, new BigDecimal(10), 'S'));
        Assertions.assertEquals(0, tradeEngine.getMatchedTradeQuantity());//same company

        tradeEngine.addTrade(new Trade(id++, "SYB-001", c2, u2, LocalDateTime.now(), 60, new BigDecimal(10), 'S'));
        Assertions.assertEquals(120, tradeEngine.getMatchedTradeQuantity());
    }
}


class TradeEngine implements TradeEngineInterface {

    @Override
    public void addTrade(Trade trade) {

    }

    @Override
    public List<Trade> getAllTrades() {
        return null;
    }

    @Override
    public void cancelTradesByUser(User user) {

    }

    @Override
    public void cancelTradesWithQuantityLessThan(int threshold) {

    }

    @Override
    public int getMatchedTradeQuantity() {
        return 0;
    }

    @Override
    public List<UnmatchedOrder> getUnmatchedOrders() {
        return null;
    }
}

interface TradeEngineInterface {
    void addTrade(Trade trade);
    List<Trade> getAllTrades();
    void cancelTradesByUser(User user);
    void cancelTradesWithQuantityLessThan(int threshold);
    int getMatchedTradeQuantity();
    List<UnmatchedOrder> getUnmatchedOrders();
}

class UnmatchedOrder {
    Company company;
    String symbol;
    int quantity;
    char buyOrSale;

    public UnmatchedOrder(Company company, String symbol, int quantity, char buyOrSale) {
        this.company = company;
        this.symbol = symbol;
        this.quantity = quantity;
        this.buyOrSale = buyOrSale;
    }

    public Company getCompany() {
        return company;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public char getBuyOrSale() {
        return buyOrSale;
    }
}

class Trade {
    long tradeId;
    String symbol;
    Company company;
    User user;//unique to company
    LocalDateTime issueDateTime;//ignore
    int quantity;
    BigDecimal amountPerUnit;//ignore
    char buyOrSale;

    public Trade(long tradeId, String symbol, Company company, User user, LocalDateTime issueDateTime, int quantity, BigDecimal amountPerUnit, char buyOrSale) {
        this.tradeId = tradeId;
        this.symbol = symbol;
        this.company = company;
        this.user = user;
        this.issueDateTime = issueDateTime;
        this.quantity = quantity;
        this.amountPerUnit = amountPerUnit;
        this.buyOrSale = buyOrSale;
    }

    public long getTradeId() {
        return tradeId;
    }

    public String getSymbol() {
        return symbol;
    }

    public Company getCompany() {
        return company;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAmountPerUnit() {
        return amountPerUnit;
    }

    public char getBuyOrSale() {
        return buyOrSale;
    }
}

class Company {
    String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
import java.util.HashMap;

public class Bank {
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    public Account createAccount(String name, double balance) {
        Account acc = new Account(nextAccountNumber++, name, balance);
        accounts.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public Account getAccount(int accNo) {
        return accounts.get(accNo);
    }

    
}

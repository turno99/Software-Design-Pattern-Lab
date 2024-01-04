import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//driver class
public class VendingMachine05_07
{
    public static void main(String[] args)
    {
        VendingMachine vd = new VendingMachine();
        int y = 0;
        while(true)
        {
            vd.idleMenu();
            vd.cashInMenu();
            System.out.println("Vending machine ready for new transaction. press 1 to quit the program\nPress any other number to continue");
            y = vd.scanner.nextInt();
            if(y == 1)break;
        }
        
    }
}

//enumerator for three types of coin
enum Coin{
    TEN(10),TWENTY(20), FIFTY(50);
    private int val;
    private Coin(int val)
    {
        this.val = val;
    }
    public int getVal()
    {
        return val;
    }
}

//enumerator for two types of beverage
enum Beverage{
    COFFEE(120),CAPPUCCINO(150);
    private int val;
    private Beverage(int val)
    {
        this.val = val;
    }
    public int getVal()
    {
        return val;
    }
}

//The abstract class that will force concrete methods to implement its abstract methods
//We imagine that there are two layers of coin storage in the vending machine
//The first one is the buffer, holding the coins participating in current transaction
//The second one is the inventory, containing coins from past transactions which
//can be used to provide changes in future transactions
abstract class State{
    protected VendingMachine vendingMachine;
    // user inserts a coin
    public abstract  void insertCoin(Coin coin);
    // user chooses a beverage
    public abstract void selectBeverage(Beverage beverage);
    // the coins in the buffer of the vending machine is ejected
    public abstract void ejectBufferCoins();
    // user chooses to cancel current transaction
    public abstract void cancelTransaction();
    // the machine dispenses change to the user
    public abstract void dispenseChange();
    // the machine serves the beverage of the user's choice
    public abstract void serveBeverage(Beverage beverage);
}

class IdleState extends State{

    // the buffer of the machine is cleared upon transition to idle state
    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.vendingMachine.clearBuffer();
        
    }

    //transition to cash-in state
    @Override
    public void insertCoin(Coin coin) {
        // inserted coin is added to the buffer
        this.vendingMachine.addCoinToBuffer(coin);
        this.vendingMachine.setState(new CashInState(this.vendingMachine));
    }

    @Override
    public void selectBeverage(Beverage beverage) {
        // invalid state transition
        
    }

    @Override
    public void ejectBufferCoins() {
        // invalid state transition
        
    }


    @Override
    public void cancelTransaction() {
        // invalid state transition
        
    }

    @Override
    public void dispenseChange() {
        // invalid state transition
        
    }

    @Override
    public void serveBeverage(Beverage beverage) {
        // invalid state transition
        
    }
}
// user collects a refund or change
class RefundState extends State{
    // the coins in the buffer are ejected and the user collects the change
    public RefundState(VendingMachine vendingMachine) {
        System.out.println("Please collect refund: ");
        this.vendingMachine = vendingMachine;
        ejectBufferCoins();        
    }

    @Override
    public void insertCoin(Coin coin) {
        // invalid state transition
        
    }

    @Override
    public void selectBeverage(Beverage beverage) {
        // invalid state transition
        
    }

    @Override
    public void ejectBufferCoins() {
        // the buffer is cleared upon coin ejection        
        System.out.println(this.vendingMachine.getBufferSum() + " cents");
        this.vendingMachine.clearBuffer();
    }

   

    @Override
    public void cancelTransaction() {
        // invalid state transition
        
    }

    @Override
    public void dispenseChange() {
        // invalid state transition
        
    }

    @Override
    public void serveBeverage(Beverage beverage) {
        // invalid state transition
        
    }
}
// the machine receives user input (coin)
class CashInState extends State{

    public CashInState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(Coin coin) {
        // the inserted coin is added to the buffer
        this.vendingMachine.addCoinToBuffer(coin);
    }

    @Override
    public void selectBeverage(Beverage beverage) {
        if(this.vendingMachine.getBufferSum() < beverage.getVal() || this.vendingMachine.getBeverageCount(beverage)==0)
        {
            // sum of entered coins is less than the price of the beverage
            if(this.vendingMachine.getBufferSum() < beverage.getVal() )System.out.println("Not enough money entered");
            // drink is out of stock
            else System.out.println(beverage.name() + " out of stock");
            // transition to refund state and then to idle state
            this.vendingMachine.setState(new RefundState(this.vendingMachine));
            this.vendingMachine.setState(new IdleState(this.vendingMachine));
        }
        // sufficient funds
        else if( this.vendingMachine.hasChange(this.vendingMachine.getBufferSum() - beverage.getVal()) )
        {
            // provide change to user
            this.dispenseChange();
            // serve the requested beverage
            this.vendingMachine.setState(new ServeState(this.vendingMachine));
            this.vendingMachine.getState().serveBeverage(beverage);
        }
        else 
        {
            // the coins in the inventory and the buffer cannot make 
            // the amount of change needed
            System.out.println("Cannot provide change");
            this.vendingMachine.setState(new RefundState(this.vendingMachine));
            this.vendingMachine.setState(new IdleState(this.vendingMachine));
        }
        
    }

    @Override
    public void ejectBufferCoins() {
        // invalid state transition
        
    }


    @Override
    public void cancelTransaction() {
        // upon cancellation, transition to refund state and then to idle state
        this.vendingMachine.setState(new RefundState(this.vendingMachine));
        this.vendingMachine.setState(new IdleState(this.vendingMachine));
    }

    @Override
    public void dispenseChange() {
        // print the amount of change to be collected
        this.vendingMachine.printChange();
    }

    @Override
    public void serveBeverage(Beverage beverage) {
        // invalid state transition
        
    }

}
class ServeState extends State{

    public ServeState(VendingMachine vendingMachine) {
        // the tray contains a cup of the beverage
        this.vendingMachine = vendingMachine;
        System.out.println("Your drink is in the tray");
    }
    @Override
    public void insertCoin(Coin coin) {
        // invalid state transition
        
    }

    @Override
    public void selectBeverage(Beverage beverage) {
        // invalid state transition
        
    }

    @Override
    public void ejectBufferCoins() {
        // invalid state transition
        
    }


    @Override
    public void cancelTransaction() {
        // invalid state transition
        
    }

    @Override
    public void dispenseChange() {
        // invalid state transition
        
    }

    @Override
    public void serveBeverage(Beverage beverage) {
        // the beverage is served in a cup placed in a tray
        this.vendingMachine.placeBeverage();
        while(this.vendingMachine.beverageInTray()==true)
        {
            int x = 0;
            // no new requests until the cup is taken from the tray
            while(x == 0)
            {
                System.out.println("Press 1 to take beverage");
                x = this.vendingMachine.scanner.nextInt();
                if(x == 1)this.vendingMachine.takeBeverage();
                else
                {
                    System.out.println("Please select proper number");
                    x = 0;
                }
            }
        }
        // the number of available cups of current beverage is reduced by one
        this.vendingMachine.removeBeverage(beverage);
        // transition to idle state
        this.vendingMachine.setState(new IdleState(this.vendingMachine));
    }
}

class VendingMachine{
    // current state of the machine
    private State state;
    // list of coins in the inventory
    private ArrayList<Coin> inventoryCoins;
    // list of coins in the buffer
    private ArrayList<Coin>bufferCoins;
    // list of available beverages
    private ArrayList<Beverage>beverages;
    // list of coins to be provided as change
    private ArrayList<Coin> change;
    // boolean for checking if a cup is in the tray
    private boolean cupInTray;
    // scanner for handling user input
    public Scanner scanner;
    public VendingMachine()
    {
        inventoryCoins = new ArrayList<Coin>();
        bufferCoins = new ArrayList<Coin>();
        beverages = new ArrayList<Beverage>();
        change = new ArrayList<Coin>();
        cupInTray = false;
        scanner = new Scanner(System.in);
        //We assume there are 20 coins of each type in the machine initially
        for(Coin coin : Coin.values())
        {
            for(int i=0;i<20;i++)inventoryCoins.add(coin);
        }
        //We assume there are 5 cups of each type of beverage in the machine initially
        for(Beverage beverage: Beverage.values())
        {
            for(int i=0;i<5;i++)beverages.add(beverage);
        }
        
        state = new IdleState(this);
    }
    public State getState()
    {
        return this.state;
    }
    // method for adding a coin to the buffer
    public void addCoinToBuffer(Coin coin)
    {
        bufferCoins.add(coin);
    }
    // method for removing one cup of beverage from the storage
    public void removeBeverage(Beverage beverage)
    {
        beverages.remove(beverage);
    }
    // user receives the cup from the tray
    public void takeBeverage()
    {
        cupInTray = false;
    }
    // the machine places the cup in the tray
    public void placeBeverage()
    {
        cupInTray = true;
    }
    // checking if the tray currently holds a cup
    public boolean beverageInTray()
    {
        return cupInTray;
    }
    // method for adding a coin to the inventory
    public void addCoinToInventory(Coin coin)
    {
        inventoryCoins.add(coin);
    }
    // method for counting the number of cups of a beverage left
    public int getBeverageCount(Beverage beverage)
    {
        return Collections.frequency(beverages, beverage);
    }
    // method for clearing the buffer
    public void clearBuffer()
    {
        bufferCoins.clear();
    }
    // method for counting the sum of the coins in the buffer
    public int getBufferSum()
    {
        int sum = 0;
        for(Coin coin : bufferCoins)sum+=coin.getVal();
        return sum;
    }
    // method for printing the amount of change to be provided
    public void printChange()
    {
        int x = 0;
        for(Coin coin: change)
        {
            x += coin.getVal();
        }
        System.out.println("Your change " + x);
        change.clear();
    }
    // method for checking if the required amount of change can be made
    public boolean hasChange(int changeToGive)
    {
        change.clear();
        ArrayList<Coin> bufferChange = new ArrayList<Coin>();
        ArrayList<Coin> inventoryChange = new ArrayList<Coin>();
        for(Coin coin : Coin.values())
        {
            while(changeToGive > 0)
            {
                if(Collections.frequency(bufferCoins, coin)>0 && changeToGive >=coin.getVal())
                {
                    change.add(coin);
                    changeToGive -= coin.getVal();
                    bufferCoins.remove(coin);
                    bufferChange.add(coin);
                }
                else break;
            }
            
        }
        for(Coin coin : Coin.values())
        {
            while(changeToGive > 0)
            {
                if(Collections.frequency(inventoryCoins, coin)>0 && changeToGive >=coin.getVal())
                {
                    change.add(coin);
                    changeToGive -= coin.getVal();
                    inventoryCoins.remove(coin);
                    inventoryChange.add(coin);
                }
                else break;
            }
            
        }
        if(changeToGive == 0)
        {
            for(Coin coin : bufferCoins)
            {
                inventoryCoins.add(coin);
            }
            bufferCoins.clear();
            return true;
        }
        else 
        {
            for(Coin coin : bufferChange)
            {
                bufferCoins.add(coin);
            }
            for(Coin coin : inventoryChange)
            {
                inventoryCoins.add(coin);
            }
            return false;
        }
    }
    public void setState(State state)
    {
        this.state = state;
    }
    
    // following methods are for handling user interaction
    public void idleMenu()
    {
        int x = 0;
        while(x == 0)
        {
            System.out.println("Please enter coin to start vending machine\nAcceptable: 10, 20, 50");
            x = scanner.nextInt();
            if(x!=10 && x!= 20 && x!= 50)
            {
                System.out.println("Please insert proper coin. " + x  + " has been ejected\n");
                x = 0;
            }
        }
        if(x == 10)this.getState().insertCoin(Coin.TEN);
        else if(x == 20)this.getState().insertCoin(Coin.TWENTY);
        else this.getState().insertCoin(Coin.FIFTY);
    }
    public void beverageSelectMenu()
    {
        System.out.println("1) Coffee: 1.20\n2) Cappuccino: 1.50");
        int x = 0;
        while(x == 0)
        {
            x = scanner.nextInt();
            if(x != 1 && x != 2)
            {
                System.out.println("Please select an appropriate option\n");
                x = 0;
            }
            else
            {
                if(x == 1)
                {
                    this.getState().selectBeverage(Beverage.COFFEE);
                }
                else
                {
                    this.getState().selectBeverage(Beverage.CAPPUCCINO);
                }
            }
        }

    }
    public void cashInMenu()
    {
        int x = 0;
        while(x == 0)
        {
            System.out.println("1) Insert Coin\n2) Select Beverage\n3) Cancel Transaction");
            x = scanner.nextInt();
            if(x != 1 && x != 2 && x != 3)
            {
                System.out.println("Select proper option");
                x = 0;
            }
            else if(x == 1)
            {
                idleMenu();
                x = 0;
            }
            else if(x == 2)
            {
                beverageSelectMenu();
            }
            else{
                this.getState().cancelTransaction();
            }
        }
    }

}
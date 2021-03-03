package abstractFactory;

public class ICICI implements Bank{
    private final String BankName;
    ICICI(){
        BankName = "ICICI Bank";
    }

    @Override
    public String getBankName(){
        return BankName;
    }
}

import builder.BankAccount;

public class Main {
    public static void main(String[] args){
        BankAccount bankAccount = new BankAccount(1234, "Bart", 100.00);

        //Thanks to our new and improved account handling process, we get some new clients.
        BankAccount account = new BankAccount(456L, "Marge", "Springfield", 100.00, 2.5);
        BankAccount anotherAccount = new BankAccount(789L, "Homer", null, 2.5, 100.00);  //Oops!
        // Our compiler, which should be our safety net, thinks that this code is fine.
        // The practical implication, however, is that Homer's money will double every month.
        // order of the parameters passed to the constructor.

        /*
        * If we have multiple consecutive arguments of the same type, it's easy to accidentally swap them around.
        * Since the compiler doesn't pick it up as an error, it can manifest as an issue somewhere
        * down the line at runtime – and that can turn into a tricky debugging exercise.
        * In addition, adding more constructor parameters results in code that becomes harder to read*/

        /*
        * If we had 10 different parameters, it would become very difficult to identify what's
        * what in the constructor at a single glance.*/

        /*The builder will contain all of the fields that exist on the BankAccount class itself.*/

        /*At the same time, we'll remove the public constructor from the BankAccount class and replace
        it with a private constructor so that accounts can only be created via the builder.*/

        //We can now create new accounts as follows.
        BankAccount newAccount = new BankAccount.Builder(1234L)
                                                .withOwner("Marge")
                                                .atBranch("Springfield")
                                                .openingBalance(100)
                                                .atRate(2.5)
                                                .build();

        BankAccount anotherNewAccount = new BankAccount.Builder(4567L)
                                                        .withOwner("Homer")
                                                        .atBranch("Springfield")
                                                        .openingBalance(100)
                                                        .atRate(2.5)
                                                        .build();
        /*
        * Is this code more verbose? Yes.
        * Is it clearer? Yes.
        * Is it better?
        * Since a large chunk of our time is spent reading code rather than writing it,
        * I'm pretty sure it is, yes.*/
    }
}

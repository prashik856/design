package builder;

//Create an abstract class NonVegPizza that will extend to the abstract class Pizza.
public abstract class NonVegPizza extends Pizza{
    @Override
    public abstract float price();

    @Override
    public abstract String name();

    @Override
    public abstract String size();
}

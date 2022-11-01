package builder;

//Now, similarly create concrete sub-classes SmallOnionPizza, MediumOnionPizza, LargeOnionPizza, ExtraLargeOnionPizza that will extend to the abstract class VegPizza.
public class SmallOnionPizza extends VegPizza{
    @Override
    public float price() {
        return 120.0f;
    }
    @Override
    public String name() {
        return  "Onion Pizza";
    }
    @Override
    public String size() {
        return  "Small Size";
    }
}

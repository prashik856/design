package factory;

abstract class Plan {
    protected double rate;
    abstract void getRate();
    public void caculateBill(int units){
        System.out.println(units*rate);
    }
}

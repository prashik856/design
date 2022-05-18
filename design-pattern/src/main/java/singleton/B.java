package singleton;

//Understanding lazy Instantiation of Singleton Pattern
//In such case, we create the instance of the class in synchronized method or synchronized block, so instance of the class is created when required.
public class B {
    private static B obj;
    private B(){}

    public static B getB(){
        if(obj == null){
            synchronized (B.class){
                if(obj==null){
                    obj = new B();
                }
            }
        }
        return obj;
    }

    public void doSomething(){

    }
}

package singleton;

//Understanding early Instantiation of Singleton Pattern
//In such case, we create the instance of the class at the time of declaring the static data member, so instance of the class is created at the time of classloading.

public class A {
    private A(){}
    private static A obj = new A();
    //Early, instance will be created at load time

    public static A getA(){
        return obj;
    }

    public  void doSomething(){
        // Write some code here.
    }
}

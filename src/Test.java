
/**
 * @author LvShengyI
 * @description
 */
public class Test {
    static C cInstance = new C();
    static Filler  filler = DefaultFiller.build();

    public static void main(String[] args) {
        cInstance.a = new A();
        cInstance.a.aInteger = 99;
        cInstance.a.aString = "new string";

        filler.fill(cInstance);

        System.out.println(cInstance.a.aInteger);
        System.out.println(cInstance.b.a.aString);
    }
}

class A{
    public Integer aInteger = 100;
    public String aString = "i am aString";
}

class B{
    public A a;
    public Double bDouble;
}

class C{
    public B b;
    public A a;
    public Float cFloat;
}


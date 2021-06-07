package javahighconcurrent.personstream;

public class Person {

    int age;
    String name;
    Country country;
    public Person(int age, String name, Country country) {
        this.age = age;
        this.name = name;
        this.country = country;
    }

    static enum Country {
        BeiJing, ShangHai, HangZhou
    }
}

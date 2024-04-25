package annaspringcat.annaspringcat;

import java.util.List;

public class Cat {
    int id;
    String name;
    String color;
    double age;

    public Cat(int id, String name, String color, double age) throws Exception {
        this.id = id;
        this.name = name;
        this.color = color;
        if ((age > 25) || (age<0)) throw new Exception("неправильно введён возраст");
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getAge() {
        return age;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public Cat findCat(String name, List<Cat> cats) {
        for (Cat с : cats) {
            if (с.getName().equals(name)) {
                return с;
            }
        }
        return null;}
    @Override
    public String toString() {
        return "annaspringcat.annaspringcat.Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}

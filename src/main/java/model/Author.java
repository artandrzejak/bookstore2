package model;

public class Author {
    private int id;
    private String name;
    private int age;

    public Author(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                        "\tname: " + name +
                        "\tage: " + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
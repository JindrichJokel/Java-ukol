
package package1;

class User {
    private String name;
    private String surname;
    private long id;

    public User(String name, String surname, long id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setRc(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "jméno='" + name + '\'' +
                ", příjmení='" + surname + '\'' +
                ", rodné číslo=" + id +
                '}';
    }
}
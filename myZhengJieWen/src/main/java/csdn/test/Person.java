package csdn.test;

public class Person {
    private String name;//名称
    private String city;//城市
    private String cellPhone;//手机号码

    public Person(){

    }
    public Person(String name, String city, String cellPhone) {
        this.name = name;
        this.city = city;
        this.cellPhone = cellPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                '}';
    }
}

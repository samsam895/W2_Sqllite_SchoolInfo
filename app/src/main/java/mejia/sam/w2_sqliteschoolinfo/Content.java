package mejia.sam.w2_sqliteschoolinfo;

/**
 * Created by User on 11/22/2016.
 */

public class Content {
    int id;
    String name;
    String adress;
    String phone;

    // Empty constructor

    public Content(){

    }

    //Constructor
    public Content (int id, String name, String adress, String phone){
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
    }
    //Constructor
    public Content (int id){
        this.id = id;
    }

    //contructor
    public Content(String name, String adress,String phone){
        this.name = name;
        this.adress = adress;
        this.phone = phone;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

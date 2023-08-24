package ra.model;

public class Billing {
    private int id;
    private String  receiver;
    private String phone;
    private String address;
    private String ortherInfo;
    private int user_id;

    public Billing() {
    }

    public Billing(int id, String receiver, String phone, String address, String ortherInfo, int user_id) {
        this.id = id;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.ortherInfo = ortherInfo;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrtherInfo() {
        return ortherInfo;
    }

    public void setOrtherInfo(String ortherInfo) {
        this.ortherInfo = ortherInfo;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

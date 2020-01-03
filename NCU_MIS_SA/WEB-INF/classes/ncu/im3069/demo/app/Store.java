package ncu.im3069.demo.app;
import org.json.*;

public class Store {
	private int id;
	private String name;
	private String address;
	private String city;
	private String district;
	private String phone;
	
	public Store(int id) {
		this.id = id;
	}
	public Store(String name, String address, String city, String district, String phone) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.district = district;
		this.phone = phone;
	}
	public Store(int id, String name, String address, String city, String district, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.district = district;
		this.phone = phone;
	}
	
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public String getCity() {
		return this.city;
	}
	public String getDistrict() {
		return this.district;
	}
	public String getPhone() {
		return this.phone;
	}
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("address", getAddress());
        jso.put("city", getCity());
        jso.put("district", getDistrict());
        jso.put("phone", getPhone());
        return jso;
    }
}

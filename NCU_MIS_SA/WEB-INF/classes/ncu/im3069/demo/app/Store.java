package ncu.im3069.demo.app;
import org.json.*;

public class Store {
	private int id;
	private String name;
	private String address;
	private String city;
	private String district;
	
	public Store(int id) {
		this.id = id;
	}
	public Store(String name, String address, String city, String district) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.district = district;
	}
	public Store(int id, String name, String address, String city, String district) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.district = district;
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
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("address", getAddress());
        jso.put("city", getCity());
        jso.put("district", getDistrict());
        return jso;
    }
}

package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

//
// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Member
 * Member類別（class）具有會員所需要之屬性與方法，並且儲存與會員相關之商業判斷邏輯<br>
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class Member {
    
	
    /** id，會員編號 */
    private int id;
  
    /** IDNumber，身分證字號 */
    private String IDNumber;
    
    /** password，會員密碼 */
    private String password;
    
    /** name，會員姓名 */
    private String name;
    
    /** birth，會員生日 */
    private String birth;
    
    /** mobilephone，行動電話 */
    private String mobilephone;
    
    /** address，會員住址*/
    private String address;
    
    /** license，會員駕照管轄編號*/
    private String license;
    
    /** email，會員電子郵件信箱 */
    private String email;
    
    /** login_times，更新時間的分鐘數 */
    private int login_times;
    
    /** status，會員之組別 */
    private String status;
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private MemberHelper mh =  MemberHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     */
    
    //IDNumber, password, name, birth, mobilephone, address, license, email
    public Member(String IDNumber, String password, String name, String birth, String mobilephone, String address, String license, String email) {
    	this.IDNumber = IDNumber;
    	this.password = password;
    	this.name = name;
    	this.birth = birth;
    	this.mobilephone = mobilephone;
    	this.address = address;
    	this.license = license;
    	this.email = email;
        update();
    }

    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新會員資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(int id, String IDNumber, String password, String name, String birth, String mobilephone, String address, String license, String email) {
        this.id = id;
    	this.IDNumber = IDNumber;
    	this.password = password;
    	this.name = name;
    	this.birth = birth;
    	this.mobilephone = mobilephone;
    	this.address = address;
    	this.license = license;
    	this.email = email;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 */
        getLoginTimesStatus();
        /** 計算會員之組別 */
        calcAccName();
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，將每一筆資料新增為一個會員物件
     *
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     * @param login_times 更新時間的分鐘數
     * @param status the 會員之組別
     */
    public Member(int id, String IDNumber, String password, String name, String birth, String mobilephone, String address, String license, String email, int login_times, String status) {
        this.id = id;
    	this.IDNumber = IDNumber;
    	this.password = password;
    	this.name = name;
    	this.birth = birth;
    	this.mobilephone = mobilephone;
    	this.address = address;
    	this.license = license;
    	this.email = email;
        this.login_times = login_times;
        this.status = status;
    }
    
    /**
     * 取得會員之編號
     *
     * @return the id 回傳會員編號
     */
    public int getID() {
        return this.id;
    }
    
    public String getIDNumber() {
        return this.IDNumber;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getBirth() {
        return this.birth;
    }
    
    public String getMobilephone() {
        return this.mobilephone;
    }
    
    public String getAddress() {
        return this.address;
    }

    public String getLicense() {
        return this.license;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    
    /**
     * 取得更新資料時間之分鐘數
     *
     * @return the login times 回傳更新資料時間之分鐘數
     */
    public int getLoginTimes() {
        return this.login_times;
    }
    
    /**
     * 取得會員資料之會員組別
     *
     * @return the status 回傳會員組別
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * 更新會員資料
     *
     * @return the JSON object 回傳SQL更新之結果與相關封裝之資料
     */
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        /** 取得更新資料時間（即現在之時間）之分鐘數 */
        Calendar calendar = Calendar.getInstance();
        this.login_times = calendar.get(Calendar.MINUTE);
        /** 計算帳戶所屬之組別 */
        calcAccName();
        
        /** 檢查該名會員是否已經在資料庫 */
        if(this.id != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 */
            mh.updateLoginTimes(this);
            /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
            data = mh.update(this);
        }
        
        return data;
    }
    
    /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("IDNumber", getIDNumber());
        jso.put("password", getPassword());
        jso.put("name", getName());
        jso.put("birth", getBirth());
        jso.put("mobilephone", getMobilephone());
        jso.put("address", getAddress());
        jso.put("license", getLicense());
        jso.put("email", getEmail());
        jso.put("login_times", getLoginTimes());
        jso.put("status", getStatus());
        
        return jso;
    }
    //IDNumber, password, name, birth, mobilephone, address, license, email
    /**
     * 取得資料庫內之更新資料時間分鐘數與會員組別
     *
     */
    private void getLoginTimesStatus() {
        /** 透過MemberHelper物件，取得儲存於資料庫的更新時間分鐘數與會員組別 */
        JSONObject data = mh.getLoginTimesStatus(this);
        /** 將資料庫所儲存該名會員之相關資料指派至Member物件之屬性 */
        this.login_times = data.getInt("login_times");
        this.status = data.getString("status");
    }
    
    /**
     * 計算會員之組別<br>
     * 若為偶數則為「偶數會員」，若為奇數則為「奇數會員」
     */
    private void calcAccName() {
        /** 計算目前分鐘數為偶數或奇數 */
        String curr_status = (this.login_times % 2 == 0) ? "偶數會員" : "奇數會員";
        /** 將新的會員組別指派至Member之屬性 */
        this.status = curr_status;
        /** 檢查該名會員是否已經在資料庫，若有則透過MemberHelper物件，更新目前之組別狀態 */
        if(this.id != 0) mh.updateStatus(this, curr_status);
    }
}
package c.testfcm.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wildcoder on 01/08/16.
 */
public class Version implements Constants.JSONKeys {
    private int versionCode;
    private String title = "New Update Available";
    private String message = "Hey new update is ready to install, click yes to enjoy crash free app";
    private boolean isMandatory;


    /**
     * @param jsonObject {version:1,isMandatory:false, title:"", message:""}
     */
    public Version(String jsonObject) throws JSONException {
        this(new JSONObject(jsonObject));
    }

    /**
     * @param jsonObject
     */
    public Version(JSONObject jsonObject) throws JSONException {
        setVersionCode(jsonObject.getInt(VERSION_CODE));
        setTitle(jsonObject.optString(TITLE, title));
        setMessage(jsonObject.optString(MESSAGE, message));
        setMandatory(jsonObject.getBoolean(IS_MANDATORY));
    }

    /**
     * @return
     */
    public int getVersionCode() {
        return versionCode;
    }

    /**
     * @param versionCode
     */

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     */
    public boolean isMandatory() {
        return isMandatory;
    }

    /**
     * @param mandatory
     */
    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }
}

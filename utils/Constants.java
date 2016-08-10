package c.testfcm.utils;

/**
 * Created by wildcoder on 09/08/16.
 */
public class Constants {

    public interface Extras {
        String ACTION = "action";
        String MESSAGE = "message";
        String TITLE = "title";
        String P_NAME = "p_name";
        String PAGE_ID = "page_id";
        String UPDATE = "update";
        String ACCOUNT_NAME = "ac_name";
    }

    public interface Actions {
        String SHARE_APP = "0";
        String RATE_APP = "1";
        String TRY_OUR_MORE_APP = "2";
        String TRY_OUR_OTHER_APP = "3";
        String FOLLOW_FB_PAGE = "4";
        String UPDATE_APP = "5";
    }

    public interface JSONKeys {
        String TITLE = "title";
        String MESSAGE = "message";
        String IS_MANDATORY = "isMandatory";
        String VERSION_CODE = "version";
    }
}

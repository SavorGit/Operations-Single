package com.savor.operations.single.core;

import android.content.Context;
import android.text.TextUtils;

import com.common.api.utils.AppUtils;

import java.io.File;
import java.util.HashMap;

public class AppApi {
    public static final String APK_DOWNLOAD_FILENAME = "NewApp.apk";

    /**云平台php接口*/
//    public static final String CLOUND_PLATFORM_PHP_URL = "http://devp.mobile.littlehotspot.com/";
  public static final String CLOUND_PLATFORM_PHP_URL = "http://mobile.littlehotspot.com/";

    /**
     * 常用的一些key值 ,签名、时间戳、token、params
     */
    public static final String SIGN = "sign";
    public static final String TIME = "time";
    public static final String TOKEN = "token";
    public static final String PARAMS = "params";


    /**这是一个临时值，以请求时传入的值为准*/
    public static String tvBoxUrl;
    public static int hotelid;
    public static int roomid;

    /**
     * Action-自定义行为 注意：自定义后缀必须为以下结束 _FORM:该请求是Form表单请求方式 _JSON:该请求是Json字符串
     * _XML:该请求是XML请求描述文件 _GOODS_DESCRIPTION:图文详情 __NOSIGN:参数不需要进行加密
     */
    public static enum Action {
        TEST_POST_JSON,
        TEST_GET_JSON,
        /**搜索酒楼*/
        POST_SEARCH_HOTEL_JSON,
        POST_UPGRADE_JSON,
        TEST_DOWNLOAD_JSON,
        /**获取酒楼损坏配置表*/
        POST_DAMAGE_CONFIG_JSON,
        /**维修记录*/
        POST_POSITION_LIST_JSON,
        /**获取酒店版位详细信息（mac地址等）*/
        POST_HOTEL_MACINFO_JSON,
        /**维修或者签到*/
        POST_SUBMIT_DAMAGE_JSON,
        /**登录*/
        POST_LOGIN_JSON,

    }

    /**
     * API_URLS:(URL集合)
     */
    public static final HashMap<Action, String> API_URLS = new HashMap<Action, String>() {
        private static final long serialVersionUID = -8469661978245513712L;

        {
            put(Action.TEST_GET_JSON, "https://www.baidu.com/");
            put(Action.TEST_GET_JSON, "https://www.baidu.com/");
            put(Action.POST_SEARCH_HOTEL_JSON, formatPhpUrl("Tasksubcontract/hotel/searchHotel"));
            put(Action.POST_UPGRADE_JSON, formatPhpUrl("Tasksubcontract/Version/index"));
            put(Action.POST_DAMAGE_CONFIG_JSON, formatPhpUrl("Tasksubcontract/Box/getHotelBoxDamageConfig"));
            put(Action.POST_POSITION_LIST_JSON, formatPhpUrl("Tasksubcontract/Hotel/getHotelVersionById"));
            put(Action.POST_HOTEL_MACINFO_JSON, formatPhpUrl("Tasksubcontract/Hotel/getHotelMacInfoById"));
            put(Action.POST_SUBMIT_DAMAGE_JSON, formatPhpUrl("Tasksubcontract/Box/InsertBoxDamage"));
            put(Action.POST_LOGIN_JSON, formatPhpUrl("Opclient/login/doLogin"));
        }
    };


    /**
     * php后台接口
     * @param url
     * @return
     */
    private static String formatPhpUrl(String url) {
        return CLOUND_PLATFORM_PHP_URL +url;
    }

    public static void testPost(Context context, String orderNo, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("loginfield", "15901559579");
        params.put("password", "123456");
        params.put("dr_rg_cd", "86");
        params.put("version_code", 19 + "");
        new AppServiceOk(context, Action.TEST_POST_JSON, handler, params).post(false, false, true, true);

    }

    /**升级*/
    public static void Upgrade(Context context, ApiRequestListener handler, int versionCode) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("versionCode", versionCode);
        params.put("deviceType", 3);
        new AppServiceOk(context, Action.POST_UPGRADE_JSON,handler,params).post();
    }

    public static void downApp(Context context, String url, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        String target = AppUtils.getPath(context, AppUtils.StorageFile.file);

        String targetApk = target + APK_DOWNLOAD_FILENAME;
        File tarFile = new File(targetApk);
        if (tarFile.exists()) {
            tarFile.delete();
        }
        new AppServiceOk(context, Action.TEST_DOWNLOAD_JSON, handler, params).downLoad(url, targetApk);

    }

    /**
     * 获取运维端首页信息
     * @param context 上下文
     * @param handler 接口回调
     */
    public static void searchHotel(Context context, String hotel_name, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("hotel_name",hotel_name);
        new AppServiceOk(context, Action.POST_SEARCH_HOTEL_JSON, handler, params).post();
    }

    /**
     * 提交保修记录
     * @param context 上下文
     * @param handler 接口回调
     */
    public static void submitDamage(Context context, String bid, String hotel_id,
                                    String remark, String repair_img,String repair_type,String srtype,String state,
                                    String userid, String current_location,ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("bid",bid);
        params.put("hotel_id",hotel_id);
        params.put("remark",remark);
        params.put("repair_img",repair_img);
        params.put("repair_type",repair_type);
        if(!TextUtils.isEmpty(current_location)) {
            params.put("current_location",current_location);
        }
        params.put("srtype",srtype);
        params.put("userid",userid);
        new AppServiceOk(context, Action.POST_SUBMIT_DAMAGE_JSON, handler, params).post();
    }

    /**
     * 酒店损坏配置表
     * @param context 上下文
     * @param handler 接口回调
     */
    public static void getDamageConfig(Context context, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<>();
        new AppServiceOk(context, Action.POST_DAMAGE_CONFIG_JSON, handler, params).post();
    }

    /**
     * 获取版位信息
     * @param context 上下文
     * @param handler 接口回调
     * @param hotelId 酒店id
     */
    public static void getPositionList(Context context, String hotelId,ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("hotel_id",hotelId);
        new AppServiceOk(context, Action.POST_POSITION_LIST_JSON, handler, params).post();
    }

    public static void getHotelMacInfo(Context context,String hotel_id, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("hotel_id",hotel_id);
        new AppServiceOk(context, Action.POST_HOTEL_MACINFO_JSON, handler, params).post();
    }

    /**
     * 登录
     * @param context 上下文
     * @param username 用户名
     * @param password 密码
     * @param handler 接口回调
     */
    public static void login(Context context, String username,String password, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", password);
        new AppServiceOk(context, Action.POST_LOGIN_JSON, handler, params).post();
    }

    // 超时（网络）异常
    public static final String ERROR_TIMEOUT = "3001";
    // 业务异常
    public static final String ERROR_BUSSINESS = "3002";
    // 网络断开
    public static final String ERROR_NETWORK_FAILED = "3003";

    public static final String RESPONSE_CACHE = "3004";

    /**
     * 从这里定义业务的错误码
     */
    public static final int CMS_RESPONSE_STATE_SUCCESS = 1001;
    public static final int CLOUND_RESPONSE_STATE_SUCCESS = 10000;

    /**机顶盒返回响应码*/
    public static final int TVBOX_RESPONSE_STATE_SUCCESS = 0;
    public static final int TVBOX_RESPONSE_STATE_ERROR = -1;
    public static final int TVBOX_RESPONSE_STATE_FORCE = 4;
    /**大小图不匹配失败*/
    public static final int TVBOX_RESPONSE_NOT_MATCH = 10002;
    public static final int TVBOX_RESPONSE_VIDEO_COMPLETE = 10003;

    /**
     * 数据返回错误
     */
    public static final int HTTP_RESPONSE_STATE_ERROR = 101;
    /**没有更多数据响应码*/
    public static final int HTTP_RESPONSE_CODE_NO_DATA = 10060;
}

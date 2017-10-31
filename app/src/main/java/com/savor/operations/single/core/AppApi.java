package com.savor.operations.single.core;

import android.content.Context;

import com.common.api.utils.AppUtils;
import com.savor.operations.single.utils.STIDUtil;

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
        TEST_DOWNLOAD_JSON,
        /**下载启动图或视频*/
        DOWNLOAD_START_UP_JSON,
        /**热点上拉（底部加載）*/
        POST_GETVODLIST_JSON,
        /**热点下拉（頂部刷新）*/
        POST_GETVODLIST_LAST_JSON,
        GET_CATEGORY_JSON,
        POST_FEEDBACK_JSON,
        /**分类上拉*/
        POST_TOPICLIST_JSON,
        /**分类下拉*/
        POST_LAST_TOPICLIST_JSON,
        /**点对点呼码*/
        GET_CALL_CODE_BY_BOXIP_JSON,
        /**点对点校验三位数字*/
        GET_VERIFY_CODE_BY_BOXIP_JSON,
        /**检查ap是否可连接*/
        POST_CHECKAP_CONNECTION_JSON,
        /**行为统计*/
        POST_TEMP_STATISTICS_JSON,
        /**请求机顶盒播放进度*/
        GET_QUERY_SEEK_JSON,
        /**通知机顶盒更新进度*/
        POST_NOTIFY_TVBOX_SEEK_JSON,
        /**通知机顶盒暂停*/
        GET_NOTIFY_PAUSE_JSON,
        /**通知机顶盒继续播放*/
        POST_NOTIFY_REPLAY_JSON,
        /**房间统计*/
        POST_ROOM_STATISTICS_JSON,
        /**退出行为统计*/
        POST_EXIT_STATISTICS_JSON,
        /**停止播放*/
        POST_NOTIFY_TVBOX_STOP_JSON,
        /**通知机顶盒旋转*/
        POST_PHOTO_ROTATE_JSON,
        /**获取小平台地址*/
        GET_SAMLL_PLATFORMURL_JSON,
        /**小平台呼码*/
        GET_CALL_QRCODE_JSON,
        /**改变进度*/
        POST_SEEK_CHANGE_JSON,
        /**记录用户首次使用*/
        POST_STATICS_FIRSTUSE_JSON,
        /**点播下拉刷新*/
        POST_LAST_HOTEL_VOD_JSON,
        /**上拉刷新*/
        POST_BOTTOM_HOTEL_VOD_JSON,
        /**升级*/
        POST_UPGRADE_JSON,

        POST_NOTIFY_VOL_UP_JSON,
        POST_NOTIFY_VOL_DOWN_JSON,
        POST_NOTIFY_VOL_ON_JSON,
        POST_NOTIFY_VOL_OFF_JSON,
        /**图片投屏*/
        POST_IMAGE_PROJECTION_JSON,
        /**点播头品*/
        GET_VOD_PRO_JSON,
        /**本地视频投屏*/
        POST_LOCAL_VIDEO_PRO_JSON,
        /**投蛋*/
        GET_EGG_JSON,
        /**砸蛋*/
        GET_HIT_EGG_JSON,
        /**获取机顶盒信息通过数字*/
        POST_BOX_INFO_JSON,
        /**启动*/
        GET_CLIENTSTART_JSON,
        /**中奖上报*/
        GET_AWARD_JSON,
        /**服务员推广统计接口**/
        POST_WAITER_EXTENSION_JSON,

        /**客户端得到所有的投屏酒楼距离**/
        GET_ALL_DISTANCE_JSON,
        /**获取最近酒楼（目前是三家)*/
        GET_NEARLY_HOTEL_JSON,
        /**收藏列表下拉20条*/
        POST_LAST_COLLECTION_JSON,

        /**收藏列表上拉20条*/
        POST_UP_COLLECTION_JSON,

        /**添加到收藏列表**/
        GET_ADD_MY_COLLECTION_JSON,
        /**检查文章是否被收藏**/
        GET_IS_COLLECTION_JSON,
        /**获取砸蛋活动配置**/
        POST_SMASH_EGG_JSON,
        /**创富生活接口**/
        POST_WEALTH_LIFE_LIST_JSON,
        /**判断文章是否在线*/
        POST_CONTENT_IS_ONLINE_JSON,
        /**图集接口**/
        POST_PICTURE_SET_JSON,
        /**获取专题列表**/
        POST_RECOMMEND_LIST_JSON,
        /**获取投屏点播列表*/
        POST_DEMAND_LIST_JSON,
        /**获取砸蛋记录url*/
        POST_AWARD_RECORD_JSON,
        /**获取专题名称*/
        POST_SPECIAL_NAME_JSON,
        /**电视推荐*/
        POST_TV_RECOMMEND_JSON,
        /**专题组详情*/
        POST_SPECIAL_DETAIL_JSON,
        /**专题组列表*/
        POST_SPECIAL_LIST_JSON,
    }

    /**
     * API_URLS:(URL集合)
     */
    public static final HashMap<Action, String> API_URLS = new HashMap<Action, String>() {
        private static final long serialVersionUID = -8469661978245513712L;

        {
//            put(Action.TEST_POST_JSON, CMS_PLATFORM_BASE_URL + "12");
            put(Action.TEST_GET_JSON, "https://www.baidu.com/");

            //热点
            put(Action.POST_GETVODLIST_JSON, formatPhpUrl("content/Home/getVodList"));
            put(Action.POST_GETVODLIST_LAST_JSON, formatPhpUrl("content/Home/getLastVodList"));

            //分类
            put(Action.GET_CATEGORY_JSON, formatPhpUrl("basedata/category/getCategoryList"));
            //意见反馈
          //  put(Action.POST_FEEDBACK_JSON, formatPhpUrl("mobile/api/feedback"));
         //   http://mobile.rerdian.com/
            put(Action.POST_FEEDBACK_JSON, formatPhpUrl("feed/Feedback/feedInsert"));

            //分类视频-上拉加载更多
            put(Action.POST_TOPICLIST_JSON, formatPhpUrl("catvideo/catvideo/getTopList"));
            //分类视频-下拉刷新20条
            put(Action.POST_LAST_TOPICLIST_JSON, formatPhpUrl("catvideo/catvideo/getLastTopList"));
            // 点播下啦
            put(Action.POST_LAST_HOTEL_VOD_JSON, formatPhpUrl("content/home/getLastHotelList"));
            // 点播上啦
            put(Action.POST_BOTTOM_HOTEL_VOD_JSON, formatPhpUrl("content/home/getHotelList"));
            //升级
            put(Action.POST_UPGRADE_JSON, formatPhpUrl("version/Upgrade/index"));
            put(Action.POST_NOTIFY_VOL_UP_JSON, tvBoxUrl);
            put(Action.POST_NOTIFY_VOL_DOWN_JSON, tvBoxUrl);
            put(Action.POST_NOTIFY_VOL_ON_JSON, tvBoxUrl);
            put(Action.POST_NOTIFY_VOL_OFF_JSON, tvBoxUrl);
            put(Action.POST_CHECKAP_CONNECTION_JSON, tvBoxUrl);
            put(Action.GET_QUERY_SEEK_JSON, tvBoxUrl);
            put(Action.POST_NOTIFY_TVBOX_SEEK_JSON, tvBoxUrl);
            put(Action.GET_NOTIFY_PAUSE_JSON, tvBoxUrl);
            put(Action.POST_NOTIFY_REPLAY_JSON, tvBoxUrl);
            put(Action.POST_EXIT_STATISTICS_JSON, tvBoxUrl);
            put(Action.POST_NOTIFY_TVBOX_STOP_JSON, tvBoxUrl);
            put(Action.POST_PHOTO_ROTATE_JSON, tvBoxUrl);
            put(Action.GET_SAMLL_PLATFORMURL_JSON, formatPhpUrl("basedata/Ip/getIp"));
            put(Action.GET_CALL_QRCODE_JSON,tvBoxUrl);
            put(Action.POST_SEEK_CHANGE_JSON,tvBoxUrl);
            put(Action.POST_STATICS_FIRSTUSE_JSON, formatPhpUrl("basedata/Firstuse/pushData"));
            put(Action.POST_IMAGE_PROJECTION_JSON,tvBoxUrl);
            put(Action.GET_VOD_PRO_JSON,tvBoxUrl);
            put(Action.POST_BOX_INFO_JSON,tvBoxUrl);
            put(Action.GET_CLIENTSTART_JSON,formatPhpUrl("clientstart/clientstart/getInfo"));
            put(Action.POST_WAITER_EXTENSION_JSON,formatPhpUrl("download/DownloadCount/recordCount"));
            put(Action.GET_AWARD_JSON, formatPhpUrl("Award/Award/recordAwardLog"));
            put(Action.GET_ALL_DISTANCE_JSON, formatPhpUrl("Screendistance/distance/getAllDistance"));
            put(Action.GET_NEARLY_HOTEL_JSON,formatPhpUrl("Screendistance/distance/getHotelDistance"));
            put(Action.POST_LAST_COLLECTION_JSON,formatPhpUrl("APP3/UserCollection/getLastCollectoinList"));
            put(Action.POST_UP_COLLECTION_JSON,formatPhpUrl("APP3/UserCollection/getUpCollectoinList"));
            put(Action.GET_ADD_MY_COLLECTION_JSON,formatPhpUrl("APP3/UserCollection/addMyCollection"));
            put(Action.GET_IS_COLLECTION_JSON,formatPhpUrl("APP3/UserCollection/getCollectoinState"));
            put(Action.POST_SMASH_EGG_JSON,formatPhpUrl("APP3/Activity/smashEgg"));
            put(Action.POST_WEALTH_LIFE_LIST_JSON,formatPhpUrl("APP3/Content/getLastCategoryList"));
            put(Action.POST_CONTENT_IS_ONLINE_JSON,formatPhpUrl("APP3/Content/isOnlie"));
            put(Action.POST_PICTURE_SET_JSON,formatPhpUrl("APP3/Content/picDetail"));
            put(Action.POST_RECOMMEND_LIST_JSON,formatPhpUrl("APP3/Recommend/getRecommendInfo"));
            put(Action.POST_DEMAND_LIST_JSON,formatPhpUrl("APP3/Content/demandList"));
            put(Action.POST_AWARD_RECORD_JSON,formatPhpUrl("APP3/Activity/geteggAwardRecord"));
            put(Action.POST_SPECIAL_NAME_JSON,formatPhpUrl("APP3/Special/getSpecialName"));
            put(Action.POST_TV_RECOMMEND_JSON,formatPhpUrl("APP3/Recommend/getTvPlayRecommend"));
            put(Action.POST_SPECIAL_DETAIL_JSON,formatPhpUrl("APP3/Special/specialGroupDetail"));
            put(Action.POST_SPECIAL_LIST_JSON,formatPhpUrl("APP3/Special/specialGroupList"));
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

    public static void testGet(Context context, ApiRequestListener handler) {
//        SmallPlatInfoBySSDP smallPlatInfoBySSDP = Session.get(context).getSmallPlatInfoBySSDP();
//        API_URLS.put(Action.TEST_GET_JSON,"http://"+ smallPlatInfoBySSDP.getServerIp()+":"+ smallPlatInfoBySSDP.getCommandPort()+"/small-platform-1.0.0.0.1-SNAPSHOT/com/execute/call-tdc");
//        final HashMap<String, Object> params = new HashMap<String, Object>();
//        new AppServiceOk(context, Action.TEST_GET_JSON, handler, params).get();

    }

    public static void testDownload(Context context, String url, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        String target = AppUtils.getPath(context, AppUtils.StorageFile.file);

        String targetApk = target + "123.apk";
        File tarFile = new File(targetApk);
        if (tarFile.exists()) {
            tarFile.delete();
        }
        new AppServiceOk(context, Action.TEST_DOWNLOAD_JSON, handler, params).downLoad(url, targetApk);
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

    public static void donloadStartUpFile(Context context,String targetFile,String url,ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        File file = new File(targetFile);
        if(file.exists()) {
            file.delete();
        }
        new AppServiceOk(context, Action.DOWNLOAD_START_UP_JSON,handler,params).downLoad(url,targetFile);
    }

    /**热点底部加載*/
    public static void getVodList(Context context,ApiRequestListener listener,Long createTime) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("createTime", createTime);
        new AppServiceOk(context, Action.POST_GETVODLIST_JSON,listener,params).post();
    }

    /**热点头部加載*/
    public static void getLastVodList(Context context,ApiRequestListener listener,String hotelId,String flag) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("hotelId", hotelId);
        params.put("flag", flag);

        new AppServiceOk(context, Action.POST_GETVODLIST_LAST_JSON,listener,params).post();
    }
    /**获取分类列表数据*/
    public static void getCategoryList(Context context,ApiRequestListener listener) {
        new AppServiceOk(context, Action.GET_CATEGORY_JSON,listener,null).post();
    }

    public static void submitFeedback(Context context, String deviceId, String suggestion,String contactWay,ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("deviceId", STIDUtil.getDeviceId(context));
        params.put("suggestion", suggestion);
        params.put("contactWay", contactWay);
        new AppServiceOk(context, Action.POST_FEEDBACK_JSON, handler, params).post();

    }

    /***
     * 分类上拉加载
     */
    public static void getCategoryBottomList(Context context, int categoryId, long createTime, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("categoryId", categoryId);
        params.put("createTime", createTime);
        new AppServiceOk(context, Action.POST_TOPICLIST_JSON, handler, params).post();

    }

    //下拉刷新
    public static void getCategoryTopicList(Context context, int categoryId, String flag, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("categoryId", categoryId);
        params.put("flag", flag);
        new AppServiceOk(context, Action.POST_LAST_TOPICLIST_JSON, handler, params).post();

    }



    /**上报中奖数据*/
    public static void recordAwardLog(Context context, String prizeid,String time,String mac,ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("prizeid", prizeid);
        params.put("award_time", time);
        params.put("mac", mac);
        params.put("deviceid",  STIDUtil.getDeviceId(context));

        new AppServiceOk(context, Action.GET_AWARD_JSON,handler,params).post();
    }


    /**统计首次在酒店使用*/
    public static void staticsFirstUseInHotel(Context context, String hotelId, ApiRequestListener handler) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("hotelId", hotelId);
        new AppServiceOk(context, Action.POST_STATICS_FIRSTUSE_JSON,handler,params).post();
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

package com.ls.brand.dingSdk;


/**
 * 企业应用接入时的常量定义
 */
public class Env {

    /**
     * 企业应用接入秘钥相关
     */
	//以下属性值待定
    public static final String CORP_ID = "dingd670b4f1fa12f21835c2f4657eb6378f";
    public static final String CORP_SECRET = "KYWyAvi7LU3XTHuVbfuFT5sm3v8SdjgPcaNC79_YDw5UNieBk6Ka6GC4WiRBDAP5";
    public static final String SSO_Secret = "SJo7hTXrQCgxyFE0tr5T24erlduS6ALxMB_6dU3RzWXKyp11AF1uWsT7ikTv9FWN";

    public static final String AGENT_ID = "174601524";
    
    /**
     * DING API地址
     */
	public static final String OAPI_HOST = "https://oapi.dingtalk.com";
    /**
     * 企业应用后台地址，用户管理后台免登使用
     */
	public static final String OA_BACKGROUND_URL = "http://localhost:8888/brand/v1";


    /**
     * 企业通讯回调加密Token，注册事件回调接口时需要传递给钉钉服务器
     */
	public static final String TOKEN = "";
	public static final String ENCODING_AES_KEY = "";
	
}

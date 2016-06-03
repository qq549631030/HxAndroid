package cn.hx.hxand.net;


/**
 * Created by huangx on 2016/6/2.
 */
public interface CallBack {
    void onSuccess(BaseResponse response);

    void onFail(BaseThrowable error);
}

package razerdp.friendcircle.api.network.request;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import razerdp.friendcircle.api.FriendCircleApp;
import razerdp.friendcircle.api.data.entity.HostInfo;
import razerdp.friendcircle.api.data.entity.MomentsInfo;
import razerdp.friendcircle.api.network.base.BaseHttpRequestClient;
import razerdp.friendcircle.api.network.base.BaseResponse;
import razerdp.friendcircle.utils.JSONUtil;

/**
 * Created by 大灯泡 on 2016/2/25.
 */
public class FriendCircleRequest extends BaseHttpRequestClient {
    private int start;
    private int count;
    private long userId;
    public HostInfo hostInfo;

    public FriendCircleRequest(long userId, int start, int count) {
        this.userId = userId;
        this.start = start;
        this.count = count;
    }

    @Override
    public String setUrl() {
        return String.format(Locale.getDefault(),
                FriendCircleApp.getRootUrl() + "/momentsList?userid=%d" + "&start=%d" + "&count=%d", userId, start,
                count);
    }

    @Override
    public void parseResponse(BaseResponse response, JSONObject json, int start, boolean hasMore) throws JSONException {
        hostInfo= JSONUtil.toObject(json.optString("hostInfo"),HostInfo.class);
        List<MomentsInfo> momentsInfos=JSONUtil.toList(json.optString("moments"),new TypeToken<ArrayList<MomentsInfo>>(){}
                .getType());
        response.setData(momentsInfos);
        setStart(start);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
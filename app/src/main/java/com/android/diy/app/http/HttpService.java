package com.android.diy.app.http;

import com.android.diy.app.bean.NodeBean;
import com.android.diy.app.bean.SiteBean;
import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.bean.TopicReplyBean;
import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cheng on 2017/2/14.
 */
public interface HttpService {

    /**
     * 获取话题列表
     * @param type
     * @param nodeId 如果需要查看某个节点的，传此参数
     * @param offset
     * @param limit 范围1...150，默认20条
     * @return
     */
    @GET("topics.json")
    Observable<List<TopicBean>> getTopics(@Query("type") String type,
                                          @Query("node_id") Integer nodeId, @Query("offset") Integer offset,
                                          @Query("limit") Integer limit);

    /**
     * 获取新闻列表
     * @param nodeId
     * @param offset
     * @param limit
     * @return
     */
    @GET("news.json")
    Observable<List<TopicBean>> getNews(@Query("node_id") Integer nodeId,
                                  @Query("offset") Integer offset, @Query("limit") Integer limit);

    /**
     * 获取酷站信息
     */
    @GET("sites.json")
    Observable<List<SiteBean>> getSites();

    /**
     * 获取节点列表
     * @return
     */
    @GET("nodes.json")
    Observable<ArrayList<NodeBean>> getNode();

    /**
     * 获取话题详情
     * @param id
     * @return
     */
    @GET("topics/{id}.json") Observable<TopicDetailBean> getTopic(@Path("id") int id);

    /**
     * 创建话题
     * @param token
     * @param title 标题
     * @param body 内容
     * @param nodeId 节点编号
     * @return
     */
    @POST("topics.json")
    @FormUrlEncoded
    Observable<TopicDetailBean> createTopic(@Header(Constant.KEY_TOKEN) String token,
                                            @Field("title") String title,
                                            @Field("body") String body, @Field("node_id") int nodeId);

    /**
     * 获取话题评论
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    @GET("topics/{id}/replies.json")
    Observable<List<TopicReplyBean>> getReply(@Path("id") int id, @Query("offset") Integer offset, @Query("limit") Integer limit);

    /**
     * 获取用户创建的话题列表
     *
     * @param loginName 用户的登录名
     * @param offset 默认 0，从第 21 条开始就传 20
     * @param limit 默认 20 范围 [1..150]
     */
    @GET("users/{login}/topics.json")
    Observable<List<TopicBean>> getUserCreateTopics(
            @Path("login") String loginName, @Query("offset") Integer offset,
            @Query("limit") Integer limit);

    /**
     * 获取用户收藏的话题列表
     *
     * @param loginName 用户的登录名
     * @param offset 默认 0，从第 21 条开始就传 20
     * @param limit 默认 20 范围 [1..150]
     */
    @GET("users/{login}/favorites.json")
    Observable<List<TopicBean>> getUserFavoriteTopics(
            @Path("login") String loginName, @Query("offset") Integer offset,
            @Query("limit") Integer limit);

    /**
     * 获取用户登录token
     * @param client_id
     * @param client_secret
     * @param grant_type
     * @param username
     * @param password
     * @return
     */
    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    Observable<TokenBean> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                   @Field("grant_type") String grant_type, @Field("username") String username,
                                   @Field("password") String password);


    /**
     * 获取当然登录者的资料
     *
     * @param token 当然登录者的 Token
     */
    @GET("users/me.json")
    Observable<UserDetailBean> getMe(@Header(Constant.KEY_TOKEN) String token);

    /**
     * 获取用户详细资料
     *
     * @param loginName 用户登录名
     */
    @GET("users/{loginName}.json")
    Observable<UserDetailBean> getUser(
            @Header(Constant.KEY_TOKEN) String token, @Path("loginName") String loginName);


}

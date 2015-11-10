package com.morse.gank.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：Morse on 2015/11/9 11:41
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ResultBean {
    /**
     * iOS : [{"who":"Andrew Liu","publishedAt":"2015-10-26T03:52:58.742Z","desc":"iOS 开发者旅途中的指南针 - LLDB 调试技术","type":"iOS","url":"http://swiftcafe.io/2015/09/05/lldb-debug/?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io","used":true,"objectId":"562b04f300b0d1db627f4a6a","createdAt":"2015-10-24T04:11:31.720Z","updatedAt":"2015-10-26T03:52:59.544Z"},{"who":"CallMeWhy","publishedAt":"2015-10-26T03:52:58.732Z","desc":"不可变对象的魅力\n","type":"iOS","url":"http://limboy.me/ios/2015/10/18/the-power-of-immutable-objects.html","used":true,"objectId":"56285b4760b2804577da7690","createdAt":"2015-10-22T03:43:03.047Z","updatedAt":"2015-10-26T03:52:59.856Z"},{"who":"__weak_Point","publishedAt":"2015-10-26T03:52:58.738Z","desc":"一个可重用的iOS富文本编辑器组件。","type":"iOS","url":"https://github.com/wordpress-mobile/WordPress-Editor-iOS","used":true,"objectId":"5629d154ddb2084a8a058663","createdAt":"2015-10-23T06:19:00.872Z","updatedAt":"2015-10-26T03:52:59.860Z"},{"who":"Dear宅学长","publishedAt":"2015-10-26T03:52:58.755Z","desc":"类似微信的 webView","type":"iOS","url":"https://github.com/Roxasora/RxWebViewController","used":true,"objectId":"562d8ed360b28045784b3f80","createdAt":"2015-10-26T02:24:19.435Z","updatedAt":"2015-10-26T03:52:59.884Z"},{"who":"Andrew Liu","publishedAt":"2015-10-26T03:52:58.745Z","desc":"知乎日报iOS客户端","type":"iOS","url":"https://github.com/zpz1237/NirZhihuDaily2.0","used":true,"objectId":"562c3e3100b07d36233a49da","createdAt":"2015-10-25T02:28:01.834Z","updatedAt":"2015-10-26T03:53:00.418Z"},{"who":"Andrew Liu","publishedAt":"2015-10-26T03:52:58.743Z","desc":"纯Swift2.0工程CocoaChina+从0到1遇到的坑和解决方案","type":"iOS","url":"http://zixun.github.io/blog/2015/10/25/chun-swift2-dot-0gong-cheng-cocoachina-plus-cong-0dao-1yu-dao-de-keng-he-jie-jue-fang-an/","used":true,"objectId":"562c3e1600b07a1836769b4c","createdAt":"2015-10-25T02:27:34.104Z","updatedAt":"2015-10-26T03:53:00.424Z"}]
     * App : [{"who":"咕咚","publishedAt":"2015-10-26T03:52:58.749Z","desc":"根据 知乎日报-API-分析 提供的API制作的知乎日报iOS客户端","type":"App","url":"https://github.com/zpz1237/NirZhihuDaily2.0/","used":true,"objectId":"562d86b560b25974b2cbbc0a","createdAt":"2015-10-26T01:49:41.166Z","updatedAt":"2015-10-26T03:52:59.544Z"}]
     * Android : [{"who":"有时放纵","publishedAt":"2015-10-26T03:52:58.753Z","desc":"一个\u201c基础登陆\u201d的框架用facebook，G+或者自己的应用登陆","type":"Android","url":"https://github.com/andrebts/login-basics","used":true,"objectId":"562d8c3960b2ed3669089158","createdAt":"2015-10-26T02:13:13.747Z","updatedAt":"2015-10-26T03:53:20.340Z"},{"who":"有时放纵","publishedAt":"2015-10-26T03:52:58.752Z","desc":"一个容易\u201c删除\u201d的RecyclerView","type":"Android","url":"https://github.com/CodeFalling/RecyclerViewSwipeDismiss","used":true,"objectId":"562d8abb60b2ed3669087868","createdAt":"2015-10-26T02:06:51.384Z","updatedAt":"2015-10-26T03:52:59.553Z"},{"who":"有时放纵","publishedAt":"2015-10-26T03:52:58.750Z","desc":"一个 material designed风格的颜色选择器","type":"Android","url":"https://github.com/LarsWerkman/Lobsterpicker","used":true,"objectId":"562d883960b28da5ca0c07b8","createdAt":"2015-10-26T01:56:09.243Z","updatedAt":"2015-10-26T03:52:59.857Z"},{"who":"Dear宅学长","publishedAt":"2015-10-26T03:52:58.757Z","desc":" 调试UI利器（简直跨时代！！！）","type":"Android","url":"https://github.com/xfumihiro/ViewInspector","used":true,"objectId":"562d97ac00b0a6792c5addba","createdAt":"2015-10-26T03:02:04.768Z","updatedAt":"2015-10-26T03:53:00.478Z"},{"who":"Jason","publishedAt":"2015-10-26T03:52:58.756Z","desc":"直接播放帧动画的ImageView","type":"Android","url":"https://github.com/skyfe79/FAImageView","used":true,"objectId":"562d90d4e4b09d077e6fb3ff","createdAt":"2015-10-26T02:32:52.768Z","updatedAt":"2015-10-26T03:53:00.467Z"}]
     * 休息视频 : [{"who":"lxxself","publishedAt":"2015-10-26T03:52:58.739Z","desc":"温情电影片段剪辑","type":"休息视频","url":"http://video.weibo.com/show?fid=1034:cde1021523cffc55442376a2a359f526","used":true,"objectId":"562ae02d00b0d1db627b9fcd","createdAt":"2015-10-24T01:34:37.924Z","updatedAt":"2015-10-26T03:52:59.811Z"}]
     * 拓展资源 : [{"who":"lxxself","publishedAt":"2015-10-26T03:52:58.741Z","desc":"material design 设计网站","type":"拓展资源","url":"http://www.materialup.com/","used":true,"objectId":"562ae68000b0bf3701411f61","createdAt":"2015-10-24T02:01:36.294Z","updatedAt":"2015-10-26T03:52:59.758Z"}]
     * 福利 : [{"who":"张涵宇","publishedAt":"2015-10-26T03:52:58.746Z","desc":"10.26","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1exe9ssy2gsj20qo0hndjr.jpg","used":true,"objectId":"562d7f0160b2d97d2d9e24e5","createdAt":"2015-10-26T01:16:49.787Z","updatedAt":"2015-10-26T03:53:01.675Z"}]
     */

    @SerializedName("iOS")
    private List<Bean> iOS;
    @SerializedName("App")
    private List<Bean> App;
    @SerializedName("Android")
    private List<Bean> Android;
    @SerializedName("休息视频")
    private List<Bean> 休息视频;
    @SerializedName("拓展资源")
    private List<Bean> 拓展资源;
    @SerializedName("福利")
    private List<Bean> 福利;
    @SerializedName("瞎推荐")
    private List<Bean> 瞎推荐;
    @SerializedName("前端")
    private List<Bean> 前端;

    public List<Bean> getIOS() {
        return iOS;
    }

    public void setIOS(List<Bean> iOS) {
        this.iOS = iOS;
    }

    public List<Bean> getApp() {
        return App;
    }

    public void setApp(List<Bean> App) {
        this.App = App;
    }

    public List<Bean> getAndroid() {
        return Android;
    }

    public void setAndroid(List<Bean> Android) {
        this.Android = Android;
    }

    public List<Bean> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<Bean> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<Bean> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<Bean> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<Bean> get福利() {
        return 福利;
    }

    public void set福利(List<Bean> 福利) {
        this.福利 = 福利;
    }

    public List<Bean> get瞎推荐() {
        return 瞎推荐;
    }

    public void set前端(List<Bean> 前端) {
        this.前端 = 前端;
    }
    public List<Bean> get前端() {
        return 前端;
    }

    public void set瞎推荐(List<Bean> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "iOS=" + iOS +
                ", App=" + App +
                ", Android=" + Android +
                ", 休息视频=" + 休息视频 +
                ", 拓展资源=" + 拓展资源 +
                ", 福利=" + 福利 +
                ", 瞎推荐=" + 瞎推荐 +
                ", 前端=" + 前端 +
                '}';
    }
}

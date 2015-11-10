package com.morse.gank.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：Morse on 2015/11/9 14:41
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ProgramBean {

    /**
     * error : false
     * results : [{"who":"LHF","publishedAt":"2015-11-02T04:16:06.418Z","desc":"工作在 Bash shell 中，熟记以下快捷键，将极大的提高你的命令行操作效率。","type":"瞎推荐","url":"http://junnan.org/wiki/2011/11/13/base-shell-command.html","used":true,"objectId":"5635f1deddb2084ad6fcf4cc","createdAt":"2015-11-01T11:05:02.943Z","updatedAt":"2015-11-02T04:16:10.482Z"},{"who":"lxxself","publishedAt":"2015-10-30T03:50:54.400Z","desc":"美 + 整理 + 分享 + 稍后读 = 下一代书签 Raindrop.io","type":"瞎推荐","url":"http://sspai.com/31652#rd?sukey=66d4519b2d3854cd051d2e111ee949282b415c703181bec7b978418db5859344f26d29792e4f714ab8c4893af42fb1d1","used":true,"objectId":"5632db16ddb2084ad6c18bce","createdAt":"2015-10-30T02:51:02.048Z","updatedAt":"2015-10-30T03:50:55.865Z"},{"who":"AllenJuns","publishedAt":"2015-10-30T03:50:54.398Z","desc":"如何成为一个好的技术领导者","type":"瞎推荐","url":"http://codecloud.net/how-to-be-technology-leader-6362.html","used":true,"objectId":"5632d2da60b294e7f5b77e79","createdAt":"2015-10-30T02:15:54.880Z","updatedAt":"2015-10-30T03:50:54.883Z"},{"who":"lxxself","publishedAt":"2015-10-23T04:01:16.216Z","desc":"很赞的全平台修图工具\u2014\u2014泼辣修图","type":"瞎推荐","url":"http://www.polaxiong.com/","used":true,"objectId":"56279bf660b2d0be41d909ce","createdAt":"2015-10-21T14:06:46.154Z","updatedAt":"2015-10-23T04:01:19.361Z"},{"who":"__weak_Point","publishedAt":"2015-10-20T03:45:36.973Z","desc":"打造最美HTML5 3D机房（第三季新增资产管理、动环监控）","type":"瞎推荐","url":"http://segmentfault.com/a/1190000003863028?utm_source=Weibo&utm_medium=shareLink&utm_campaign=socialShare","used":true,"objectId":"5620c88200b08664c5442a64","createdAt":"2015-10-16T09:50:58.797Z","updatedAt":"2015-10-20T03:45:37.471Z"}]
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("results")
    private List<Bean> beans;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }
}

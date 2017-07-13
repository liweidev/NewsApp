package liwei.com.newsapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liwei on 2017/7/12.
 */
//精确查询实体类
public class QueryEntity extends ParentEntity {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * title : ﻿足球赌博判刑:NBA-太阳6人上双遭逆转 四巨头103分勇士四连胜
         * content : 李牧羊无视那些议论攻击他的路人们,猛然转身看向木鼎一,说道:"不过,我又能理解你。毕竟,你的儿子--亲生儿子,应当是亲生的吧?"   中国警察网官方微信:扫一扫,免费订阅! 最权威、最及时、最全面的公安新闻发布平台。 精彩的警察故事,靓丽的警花警草,靠谱的预警知识…实乃广大...
         * img_width :
         * full_title : ﻿足球赌博判刑:NBA-太阳6人上双遭逆转 四巨头103分勇士四连胜
         * pdate : 18分钟前
         * src : 中国警察网
         * img_length :
         * img :
         * url : http://minsheng.cpd.com.cn/n1448490/c1666699/content_1115834.html
         * pdate_src : 2017-07-12 09:29:29
         */

        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_width() {
            return img_width;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public String getFull_title() {
            return full_title;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public String getPdate() {
            return pdate;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getImg_length() {
            return img_length;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPdate_src() {
            return pdate_src;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }
    }
}

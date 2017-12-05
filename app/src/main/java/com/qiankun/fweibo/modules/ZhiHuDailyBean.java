package com.qiankun.fweibo.modules;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */

public class ZhiHuDailyBean {

    /**
     * date : 20170828
     * stories : [{"images":["https://pic4.zhimg.com/v2-62d709bed8c2cebe8e1bbd4d96fbf18b.jpg"],"type":0,"id":9590958,"ga_prefix":"082811","title":"恋童是一种「疾病」还是一种「性取向」？"},{"images":["https://pic2.zhimg.com/v2-01ed7a85aacab35addefe3bc1b5e4871.jpg"],"type":0,"id":9590593,"ga_prefix":"082810","title":"「劣币驱逐良币」被用得越来越多，其实背离了本来含义"},{"images":["https://pic2.zhimg.com/v2-20e6fce9bc257ad8cbbdc85dfe710f29.jpg"],"type":0,"id":9586200,"ga_prefix":"082809","title":"你该找个什么样的人才算「般配」？经济学家是这么说的"},{"images":["https://pic1.zhimg.com/v2-3cab3162de4a47bda60a7b5caab95e10.jpg"],"type":0,"id":9590226,"ga_prefix":"082808","title":"媒体对恐怖主义的密集报道，可能并不是一件好事"},{"images":["https://pic4.zhimg.com/v2-a5ca75337639ce15f2d3413a6307bc13.jpg"],"type":0,"id":9590589,"ga_prefix":"082807","title":"男朋友一直晚睡这个问题，我们不妨开一个脑洞"},{"title":"天空可能同时出现两道彩虹吗？","ga_prefix":"082807","images":["https://pic4.zhimg.com/v2-0bf97b77af6e39a34937744e92750413.jpg"],"multipic":true,"type":0,"id":9590555},{"images":["https://pic4.zhimg.com/v2-4c8d52a596607f380902ee35e477e093.jpg"],"type":0,"id":9586627,"ga_prefix":"082807","title":"恋人之间应该「知根知底」吗？我建议不要"},{"images":["https://pic3.zhimg.com/v2-3b6d6665e5b57ee0b42373bb326a67be.jpg"],"type":0,"id":9587856,"ga_prefix":"082806","title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic1.zhimg.com/v2-da65215d2439da7aa0e3412f94b965ec.jpg"],"type":0,"id":9584763,"ga_prefix":"082806","title":"这里是广告 · 跨过时空的大桥，大山深处体验极客生活"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-668a762511a547d8627b7864475e0d99.jpg","type":0,"id":9590958,"ga_prefix":"082811","title":"恋童是一种「疾病」还是一种「性取向」？"},{"image":"https://pic1.zhimg.com/v2-66ffee4a7905b8f73f410c9709f6255c.jpg","type":0,"id":9586200,"ga_prefix":"082809","title":"你该找个什么样的人才算「般配」？经济学家是这么说的"},{"image":"https://pic1.zhimg.com/v2-c9a8dc09022a428d8a980958489a3840.jpg","type":0,"id":9590555,"ga_prefix":"082807","title":"天空可能同时出现两道彩虹吗？"},{"image":"https://pic4.zhimg.com/v2-04502e05ee51129d10444d8d4582ef4b.jpg","type":0,"id":9588025,"ga_prefix":"082715","title":"特别版瞎扯 · 你看，他们又要过七夕节了"},{"image":"https://pic1.zhimg.com/v2-f969a29b5a79bbb0f4b136c53fab0740.jpg","type":0,"id":9586188,"ga_prefix":"082506","title":"这里是广告 · 为什么 RAP\r\n 要唱那么快？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-62d709bed8c2cebe8e1bbd4d96fbf18b.jpg"]
         * type : 0
         * id : 9590958
         * ga_prefix : 082811
         * title : 恋童是一种「疾病」还是一种「性取向」？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getImages() {
            return images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-668a762511a547d8627b7864475e0d99.jpg
         * type : 0
         * id : 9590958
         * ga_prefix : 082811
         * title : 恋童是一种「疾病」还是一种「性取向」？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public void setImage(String image) {
            this.image = image;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }
    }
}

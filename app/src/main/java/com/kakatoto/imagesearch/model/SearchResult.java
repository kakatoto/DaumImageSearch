package com.kakatoto.imagesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchResult {

    @SerializedName("channel")
    private ChannelBean channel;

    public ChannelBean getChannel() {
        return channel;
    }

    public void setChannel(ChannelBean channel) {
        this.channel = channel;
    }

    public static class ChannelBean {
        @SerializedName("totalCount")
        private String totalCount;

        @SerializedName("item")
        private List<ItemBean> item;

        public String getTotalCount() {
            return totalCount;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public static class ItemBean {
            @SerializedName("pubDate")
            private String pubDate;
            @SerializedName("title")
            private String title;
            @SerializedName("thumbnail")
            private String thumbnail;
            @SerializedName("cp")
            private String cp;
            @SerializedName("width")
            private String width;
            @SerializedName("height")
            private String height;
            @SerializedName("link")
            private String link;
            @SerializedName("image")
            private String image;
            @SerializedName("cpname")
            private String cpname;

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCpname() {
                return cpname;
            }

            public void setCpname(String cpname) {
                this.cpname = cpname;
            }
        }
    }
}

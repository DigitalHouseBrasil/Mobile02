package br.com.digitalhouse.json.model;

import java.util.List;

public class NewsResponse {

    private List<News> news;

    public NewsResponse() {
    }

    public NewsResponse(List<News> news) {
        this.news = news;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}

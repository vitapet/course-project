package com.gmail.vitaliapetsenak.shop.repository.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_news_file")
public class NewsFile implements Serializable {

    private static final long serialVersionUID = 4088064051243911585L;

    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "news"))
    @Id
    @GeneratedValue(generator = "gen")
    @Column(name = "F_NEWS_ID")
    private Long newsId;
    @Column(name = "F_NAME")
    private String name;
    @Column(name = "F_LOCATION")
    private String location;

    @OneToOne
    @PrimaryKeyJoinColumn
    private News news;

    public NewsFile() {
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsFile)) return false;
        NewsFile newsFile = (NewsFile) o;
        return Objects.equals(getNewsId(), newsFile.getNewsId()) &&
                Objects.equals(getName(), newsFile.getName()) &&
                Objects.equals(getLocation(), newsFile.getLocation()) &&
                Objects.equals(getNews(), newsFile.getNews());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNewsId(), getName(), getLocation(), getNews());
    }

    @Override
    public String toString() {
        return "NewsFile{" +
                "newsId=" + newsId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", news=" + news +
                '}';
    }
}

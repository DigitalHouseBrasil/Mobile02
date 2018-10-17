package br.com.digitalhouse.digitalhouseapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private String imageUrl;
    private String title;
    private String date;
    private String description;
    private String author;

    public Post() {
    }

    public Post(String imageUrl, String title, String date, String description, String author) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.date = date;
        this.description = description;
        this.author = author;
    }

    protected Post(Parcel in) {
        imageUrl = in.readString();
        title = in.readString();
        date = in.readString();
        description = in.readString();
        author = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeString(author);
    }
}

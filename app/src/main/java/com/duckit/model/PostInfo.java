
package com.duckit.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostInfo implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("upvotes")
    @Expose
    private int upvotes;
    @SerializedName("author")
    @Expose
    private String author;
    public final static Creator<PostInfo> CREATOR = new Creator<PostInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PostInfo createFromParcel(android.os.Parcel in) {
            return new PostInfo(in);
        }

        public PostInfo[] newArray(int size) {
            return (new PostInfo[size]);
        }

    }
    ;

    protected PostInfo(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.headline = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.upvotes = ((int) in.readValue((int.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PostInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("headline");
        sb.append('=');
        sb.append(((this.headline == null)?"<null>":this.headline));
        sb.append(',');
        sb.append("image");
        sb.append('=');
        sb.append(((this.image == null)?"<null>":this.image));
        sb.append(',');
        sb.append("upvotes");
        sb.append('=');
        sb.append(this.upvotes);
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.image == null)? 0 :this.image.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+ this.upvotes);
        result = ((result* 31)+((this.headline == null)? 0 :this.headline.hashCode()));
        result = ((result* 31)+((this.author == null)? 0 :this.author.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostInfo) == false) {
            return false;
        }
        PostInfo rhs = ((PostInfo) other);
        return ((((((this.image == rhs.image)||((this.image!= null)&&this.image.equals(rhs.image)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&(this.upvotes == rhs.upvotes))&&((this.headline == rhs.headline)||((this.headline!= null)&&this.headline.equals(rhs.headline))))&&((this.author == rhs.author)||((this.author!= null)&&this.author.equals(rhs.author))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(headline);
        dest.writeValue(image);
        dest.writeValue(upvotes);
        dest.writeValue(author);
    }

    public int describeContents() {
        return  0;
    }

}

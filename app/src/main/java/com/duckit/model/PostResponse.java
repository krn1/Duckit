
package com.duckit.model;

import java.util.List;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponse implements Parcelable
{

    @SerializedName("Posts")
    @Expose
    private List<PostInfo> posts = null;
    public final static Creator<PostResponse> CREATOR = new Creator<PostResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PostResponse createFromParcel(android.os.Parcel in) {
            return new PostResponse(in);
        }

        public PostResponse[] newArray(int size) {
            return (new PostResponse[size]);
        }

    }
    ;

    protected PostResponse(android.os.Parcel in) {
        in.readList(this.posts, (PostInfo.class.getClassLoader()));
    }

    public PostResponse() {
    }

    public List<PostInfo> getPosts() {
        return posts;
    }

    public void setPosts(List<PostInfo> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("posts");
        sb.append('=');
        sb.append(((this.posts == null)?"<null>":this.posts));
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
        result = ((result* 31)+((this.posts == null)? 0 :this.posts.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostResponse) == false) {
            return false;
        }
        PostResponse rhs = ((PostResponse) other);
        return ((this.posts == rhs.posts)||((this.posts!= null)&&this.posts.equals(rhs.posts)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(posts);
    }

    public int describeContents() {
        return  0;
    }

}

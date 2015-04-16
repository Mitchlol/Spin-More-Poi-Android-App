package com.mitchelllustig.spinmorepoi;

import java.util.ArrayList;

/**
 * Created by Mitch on 4/16/15.
 */
public class Posts {
    String status;
    int count;
    int count_total;
    int pages;
    ArrayList<Post> posts;

    public static class Post{
        int id;
        String title;
        String url;
        CustomFields custom_fields;

        public static class CustomFields{
            ArrayList<String> dp_video_url;
        }
    }
}

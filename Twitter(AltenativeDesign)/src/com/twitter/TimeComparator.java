package com.twitter;

import java.util.Comparator;

public class TimeComparator implements Comparator<Tweet> {
    public int compare(Tweet t1, Tweet t2) {
        return (-t1.getPublishDate().compareTo(t2.getPublishDate()));
    }
}

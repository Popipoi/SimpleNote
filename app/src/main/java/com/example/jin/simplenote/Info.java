package com.example.jin.simplenote;

/**
 * Created by Jin on 2014-12-21.
 */
/**
 * Created by Jin on 2014-12-21.
 */
public class Info {
    private int id;
    private String meeting;
    private String time;
    private String rate;
    public Info(int id, String meeting, String time, String rate){
        this.id = id;
        this.meeting = meeting;
        this.time = time;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public String getMeeting() {
        return meeting;
    }

    public String getTime() {
        return time;
    }

    public String getRate() {return rate; }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRate(String rate) {this.rate = rate; }

    @Override
    public String toString() {
        return
                time + '\n' +
                "Rating: " + rate + '\n' +
                meeting + '\n' ;

    }
}

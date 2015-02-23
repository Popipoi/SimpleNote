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
    public Info(int id, String meeting, String time){
        this.id = id;
        this.meeting = meeting;
        this.time = time;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return
                time + '\n' +
                meeting + '\n';
    }
}

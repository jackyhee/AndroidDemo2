package com.shgbit.android.demo.bean;

public class Res_MeetingSchedule extends BaseResponse {
    private Meeting[] meetings;

    public Meeting[] getMeetings() {
        return meetings;
    }

    public void setMeetings(Meeting[] meetings) {
        this.meetings = meetings;
    }
}

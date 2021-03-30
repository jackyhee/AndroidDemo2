package com.shgbit.android.demo.bean;


public class Meeting {
	private String meetingId;
	private String meetingName;
	private User createdUser;
	private User[] users;
	private User[] listeners;
//	private Record record;
	private String status;
	private String type;
	private String password;
//	private YunDesktop yunDesktop;
	private String startTime;//"2016-10-20 15:00"
	private String endTime;
	private String actualStartTime;
	private String actualEndTime;
	private int duration;//second
	private int autoRecord;
	private int isLive;//1直播，0非直播
	private int open;//1公开，0非公开
	private String tag; //普通会议""，主会"main"，分会"sub"
	private String main;
	private String groupmode;//tag=main时
	private String invitationCode;
	private String liveLink;
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public User getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}

	public User[] getListeners() {
		return listeners;
	}

	public void setListeners(User[] listeners) {
		this.listeners = listeners;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public YunDesktop getYunDesktop() {
//		return yunDesktop;
//	}
//	public void setYunDesktop(YunDesktop yunDesktop) {
//		this.yunDesktop = yunDesktop;
//	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getActualStartTime() {
		return actualStartTime;
	}
	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public String getActualEndTime() {
		return actualEndTime;
	}
	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

//	public Record getRecord() {
//		return record;
//	}

//	public void setRecord(Record record) {
//		this.record = record;
//	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAutoRecord() {
		return autoRecord;
	}

	public void setAutoRecord(int autoRecord) {
		this.autoRecord = autoRecord;
	}

	public int getIsLive() {
		return isLive;
	}

	public void setIsLive(int isLive) {
		this.isLive = isLive;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getLiveLink() {
		return liveLink;
	}

	public void setLiveLink(String liveLink) {
		this.liveLink = liveLink;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getGroupmode() {
		return groupmode;
	}

	public void setGroupmode(String groupmode) {
		this.groupmode = groupmode;
	}
}

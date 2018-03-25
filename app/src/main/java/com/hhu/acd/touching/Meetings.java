package com.hhu.acd.touching;

import java.util.Date;

public class Meetings {
    private Integer idmeetings;

    private String tname;

    private String owner;

    private String tid;

    private Date start;

    private Date end;

    private String place;
    
    private String[] members;
    
    private  String intro;

    public Integer getIdmeetings() {
        return idmeetings;
    }

    public void setIdmeetings(Integer idmeetings) {
        this.idmeetings = idmeetings;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members==null ? okhttpurl.members:members;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro==null?"":intro;
	}
}
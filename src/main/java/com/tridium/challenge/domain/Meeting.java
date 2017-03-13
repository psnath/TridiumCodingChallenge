package com.tridium.challenge.domain;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Meeting.
 */
@Entity
@Table(name = "meeting")
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private String date;
    
    @NotNull
    @Column(name = "time", nullable = false)
    private String time;
    
	@NotNull
    @Column(name = "subject", nullable = false)
    private String subject;

    @NotNull
    @Column(name = "attendees", nullable = false)
    private String attendees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() throws ParseException {
    	Calendar calendar = Calendar.getInstance();
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date dateObject = formatter.parse(date);
    	DateFormat formatter1 = new SimpleDateFormat("MMMM d, yyyy");
    	return formatter1.format(dateObject);
    	
    }

    public Meeting date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;       
    }
    
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

    public String getSubject() {
        return subject;
    }

    public Meeting subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttendees() {
        return attendees;
    }

    public Meeting attendees(String attendees) {
        this.attendees = attendees;
        return this;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Meeting meeting = (Meeting) o;
        if (meeting.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + id +
            ", dateTime='" + date + "'" +
            ", time='" + time + "'" +
            ", subject='" + subject + "'" +
            ", attendees='" + attendees + "'" +
            '}';
    }
}

package com.tridium.challenge.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tridium.challenge.domain.Meeting;
import com.tridium.challenge.service.MeetingService;
import com.tridium.challenge.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Meeting.
 */
@RestController
@RequestMapping("/api")
public class MeetingResource {

    private final Logger log = LoggerFactory.getLogger(MeetingResource.class);

    private static final String ENTITY_NAME = "meeting";
        
    private final MeetingService meetingService;

    public MeetingResource(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * POST  /meetings : Create a new meeting.
     *
     * @param meeting the meeting to create
     * @return the ResponseEntity with status 201 (Created) and with body the new meeting, or with status 400 (Bad Request) if the meeting has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/meetings")
    
    public ResponseEntity<Meeting> createMeeting(@Valid @RequestBody Meeting meeting) throws URISyntaxException {
        log.debug("REST request to save Meeting : {}", meeting);
        if (meeting.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new meeting cannot already have an ID")).body(null);
        }
        Meeting result = meetingService.save(meeting);
        return ResponseEntity.created(new URI("/api/meetings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /meetings : Updates an existing meeting.
     *
     * @param meeting the meeting to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated meeting,
     * or with status 400 (Bad Request) if the meeting is not valid,
     * or with status 500 (Internal Server Error) if the meeting couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/meetings")
    
    public ResponseEntity<Meeting> updateMeeting(@Valid @RequestBody Meeting meeting) throws URISyntaxException {
        log.debug("REST request to update Meeting : {}", meeting);
        if (meeting.getId() == null) {
            return createMeeting(meeting);
        }
        Meeting result = meetingService.save(meeting);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, meeting.getId().toString()))
            .body(result);
    }

    /**
     * GET  /meetings : get all the meetings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of meetings in body
     */
    @GetMapping("/meetings")
    
    public List<Meeting> getAllMeetings() {
        log.debug("REST request to get all Meetings");
        return meetingService.findAll();
    }
    
    /**
     * GET  /meetings : get all the meetings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of meetings in body
     */
    @GetMapping("/meetings/count")
    
    public int getCountOfAllMeetings() {
        log.debug("REST request to get the count of all Meetings");
        return meetingService.findAll().size();
    }

    /**
     * GET  /meetings/:id : get the "id" meeting.
     *
     * @param id the id of the meeting to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the meeting, or with status 404 (Not Found)
     */
    @GetMapping("/meetings/{id}")
    
    public Meeting getMeeting(@PathVariable Long id) {
        log.debug("REST request to get Meeting : {}", id);
        Meeting meeting = meetingService.findOne(id);
        return meeting;
    }

    /**
     * DELETE  /meetings/:id : delete the "id" meeting.
     *
     * @param id the id of the meeting to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/meetings/{id}")
    
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        log.debug("REST request to delete Meeting : {}", id);
        meetingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

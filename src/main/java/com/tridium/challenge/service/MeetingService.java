package com.tridium.challenge.service;

import com.tridium.challenge.domain.Meeting;
import java.util.List;

/**
 * Service Interface for managing Meeting.
 */
public interface MeetingService {

    /**
     * Save a meeting.
     *
     * @param meeting the entity to save
     * @return the persisted entity
     */
    Meeting save(Meeting meeting);

    /**
     *  Get all the meetings.
     *  
     *  @return the list of entities
     */
    List<Meeting> findAll();

    /**
     *  Get the "id" meeting.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Meeting findOne(Long id);

    /**
     *  Delete the "id" meeting.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

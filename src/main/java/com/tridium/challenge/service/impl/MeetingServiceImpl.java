package com.tridium.challenge.service.impl;

import com.tridium.challenge.service.MeetingService;
import com.tridium.challenge.domain.Meeting;
import com.tridium.challenge.repository.MeetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Meeting.
 */
@Service
@Transactional
public class MeetingServiceImpl implements MeetingService{

    private final Logger log = LoggerFactory.getLogger(MeetingServiceImpl.class);
    
    private final MeetingRepository meetingRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    /**
     * Save a meeting.
     *
     * @param meeting the entity to save
     * @return the persisted entity
     */
    @Override
    public Meeting save(Meeting meeting) {
        log.debug("Request to save Meeting : {}", meeting);
        Meeting result = meetingRepository.save(meeting);
        return result;
    }

    /**
     *  Get all the meetings.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Meeting> findAll() {
        log.debug("Request to get all Meetings");
        List<Meeting> result = meetingRepository.findAll();

        return result;
    }

    /**
     *  Get one meeting by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Meeting findOne(Long id) {
        log.debug("Request to get Meeting : {}", id);
        Meeting meeting = meetingRepository.findOne(id);
        return meeting;
    }

    /**
     *  Delete the  meeting by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Meeting : {}", id);
        meetingRepository.delete(id);
    }
}

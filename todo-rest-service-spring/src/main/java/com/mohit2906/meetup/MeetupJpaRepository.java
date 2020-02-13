package com.mohit2906.meetup;

import java.util.List;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupJpaRepository extends JpaRepository<MeetUpModel, Long>
{	
}

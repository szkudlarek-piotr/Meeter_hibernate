package org.example.meeter.interactions.meeting;

import org.example.meeter.people.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingsRepository extends JpaRepository<Meeting, Long> {

    @Query("select m from Meeting m where ?1 member of m.meetingMembers")
    public List<Meeting> findMeetingsByHuman(Human human);

    @Query("select m from Meeting m where ?1 member of m.meetingMembers and ?2 member of m.meetingMembers")
    public List<Meeting> getCommonMeetings(Human human1, Human human2);
}

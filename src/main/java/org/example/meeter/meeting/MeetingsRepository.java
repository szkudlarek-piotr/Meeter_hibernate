package org.example.meeter.meeting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingsRepository extends JpaRepository<Meeting, Long> {
}

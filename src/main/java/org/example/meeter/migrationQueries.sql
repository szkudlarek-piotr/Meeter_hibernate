
--Migracja miejsc
INSERT INTO java_meeter_rebuild.places (id, latitude, longitude, name, category)
SELECT id, latitude, longitude, place_name, category
FROM real_meeter.places
WHERE id NOT IN (
    SELECT id FROM java_meeter_rebuild.places
);


###

--migracja trip_place
INSERT INTO java_meeter_rebuild.trip_place(trip_id, place_id)
SELECT trip_id, place_id
FROM real_meeter.trip_place;


###

--migracja spotkań
INSERT INTO java_meeter_rebuild.meetings (id, short_desc, long_desc, date, place_string, place_id, uuid, created_by_id)
SELECT id, short_description, long_desc, meeting_date, place, place_id, uuid(), (here was the ID of the user associated with me)
FROM real_meeter.meetings;

###

--migracja meeting_human
INSERT INTO java_meeter_rebuild.meeting_human (meeting_id, human_id)
SELECT meeting_id, human_id
FROM real_meeter.meeting_human;

###



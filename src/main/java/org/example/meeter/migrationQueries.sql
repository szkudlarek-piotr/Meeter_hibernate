
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


package org.example.meeter.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {
    public final PlaceRepository placeRepository;
}

package com.nussknacker.cinemareservation.room;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/room", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoomController {

    @GetMapping
    public void get(@RequestParam Long screeningId){

    }

}

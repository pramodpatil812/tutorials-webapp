package com.example.tutorials.controllers;


import com.example.tutorials.models.ApiResponse;
import com.example.tutorials.models.Tutorial;
import com.example.tutorials.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @RequestMapping(value="/tutorials", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tutorial> tutorials;
            if (Objects.isNull(title) || title.isEmpty()) {
                tutorials = tutorialRepository.findAll();
            }
            else {
                tutorials = tutorialRepository.findByTitleContaining(title);
            }

            if (tutorials.isEmpty()) {
                //return new ResponseEntity<>(ApiResponse.success(),HttpStatus.NO_CONTENT);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ApiResponse.success(tutorials), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/tutorials", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> createTutorial(@RequestBody Tutorial reqTutorial) {
        try {
            Tutorial tutorial = tutorialRepository
                    .save(new Tutorial(reqTutorial.getTitle(), reqTutorial.getDescription(), false));
            return new ResponseEntity<>(ApiResponse.success(tutorial), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

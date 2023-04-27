package com.example.TextGrabber.controllers;

import com.example.TextGrabber.model.TextData;
import com.example.TextGrabber.repository.TextDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/text")
public class TextDataController {

    @Autowired
    private TextDataRepository textDataRepository;

    @GetMapping("/")
    public ResponseEntity<List<TextData>> getAllTextData() {
        try {
            List<TextData> textDataList = textDataRepository.findAll();
            if (textDataList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(textDataList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextData> getTextDataById(@PathVariable("id") Long id) {
        Optional<TextData> textData = textDataRepository.findById(id);

        if (textData.isPresent()) {
            return new ResponseEntity<>(textData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/text")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TextData>> getTextData() {
        List<TextData> textDataList = textDataRepository.findAll();
        return ResponseEntity.ok(textDataList);
    }

    @PostMapping("/")
    public ResponseEntity<TextData> createTextData(@RequestBody TextData textData) {
        try {
            TextData _textData = textDataRepository.save(new TextData(textData.getText()));
            return new ResponseEntity<>(_textData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TextData> updateTextData(@PathVariable("id") Long id, @RequestBody TextData textData) {
        Optional<TextData> textDataOptional = textDataRepository.findById(id);

        if (textDataOptional.isPresent()) {
            TextData _textData = textDataOptional.get();
            _textData.setText(textData.getText());
            return new ResponseEntity<>(textDataRepository.save(_textData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTextData(@PathVariable("id") Long id) {
        try {
            textDataRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

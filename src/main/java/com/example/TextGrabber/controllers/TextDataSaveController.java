package com.example.TextGrabber.controllers;

import com.example.TextGrabber.DTO.TextDataDTO;
import com.example.TextGrabber.model.TextData;
import com.example.TextGrabber.services.TextDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save")
public class TextDataSaveController {

    private final TextDataService textDataService;

    public TextDataSaveController(TextDataService textDataService) {
        this.textDataService = textDataService;
    }

    @PostMapping
    public ResponseEntity<Void> saveText(@RequestBody TextDataDTO textDataDTO) {
        TextData textData = new TextData();
        textData.setText(textDataDTO.getText());
        textDataService.saveTextData(textData.toString());
        return ResponseEntity.ok().build();
    }
}

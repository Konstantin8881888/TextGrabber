package com.example.TextGrabber.services;

import com.example.TextGrabber.DTO.TextDataDTO;
import com.example.TextGrabber.model.TextData;
import com.example.TextGrabber.repository.TextDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TextDataService {

    private final TextDataRepository textDataRepository;

    @Autowired
    public TextDataService(TextDataRepository textDataRepository) {
        this.textDataRepository = textDataRepository;
    }

    public List<TextDataDTO> getAllTextData() {
        List<TextData> textDataList = textDataRepository.findAll();
        return textDataList.stream()
                .map(textData -> new TextDataDTO(textData.getId(), textData.getText()))
                .collect(Collectors.toList());
    }

    public TextDataDTO saveTextData(String text) {
        TextData textData = new TextData(text);
        textDataRepository.save(textData);
        return new TextDataDTO(textData.getId(), textData.getText());
    }
}


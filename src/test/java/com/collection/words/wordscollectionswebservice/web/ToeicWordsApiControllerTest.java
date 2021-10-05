package com.collection.words.wordscollectionswebservice.web;


import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWordsRepository;
import com.collection.words.wordscollectionswebservice.domain.user.Role;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import com.collection.words.wordscollectionswebservice.domain.user.UserRepository;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationSaveRequestDto;
import com.collection.words.wordscollectionswebservice.web.dto.ToeicWordsSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToeicWordsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ToeicWordsRepository toeicWordsRepository;

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @AfterEach
    public void tearDown() throws Exception{
        toeicWordsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void Toeic_등록() throws Exception{
        String fileName = "file";
        String contentType = "xlsx";
        String filePath = "src/test/resources/excel/wordExcelForm.xlsx";
        MockMultipartFile mFile = getMockMultipartFile(fileName, contentType, filePath);;
        //when
        mvc.perform(multipart("/api/words/postsExcel").file(mFile)).andExpect(status().isOk());

    }

}

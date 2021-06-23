package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.posts.PostsService;
import com.collection.words.wordscollectionswebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/words/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PostMapping("/api/words/postsExcel")
    public void excelsave(@RequestParam("file") MultipartFile file) throws IOException{
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(!extension.equals("xlsx") && !extension.equals("xls")){
            throw new IOException("only excel file upload please");
        }

        Workbook workbook = null;

        if(extension.equals("xlsx")){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if(extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for(int i=1; i< worksheet.getPhysicalNumberOfRows();i++){
            Row row = worksheet.getRow(i);

            PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                    .word(row.getCell(0).getStringCellValue())
                    .meaning(row.getCell(1).getStringCellValue())
                    .category(row.getCell(2).getStringCellValue())
                    .build();
            postsService.save(requestDto);
        }
    }
}

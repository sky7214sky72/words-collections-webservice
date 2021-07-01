package com.collection.words.wordscollectionswebservice.service.posts;

import com.collection.words.wordscollectionswebservice.domain.posts.PostsRepository;
import com.collection.words.wordscollectionswebservice.web.dto.PostsListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void excelSave(MultipartFile file) throws IOException{
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
            save(requestDto);
        }
    }
}

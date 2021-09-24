package com.collection.words.wordscollectionswebservice.service.posts;

import com.collection.words.wordscollectionswebservice.domain.posts.PostsRepository;
import com.collection.words.wordscollectionswebservice.web.dto.PostsListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Page<PostsListResponseDto> findAll(Pageable pageable){
        List<PostsListResponseDto> wordList = postsRepository.findAll(pageable).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(wordList,pageable,postsRepository.findAll(pageable).getTotalElements());
    }

    @Transactional
    public int pageCount(Pageable pageable){
        return postsRepository.findAll(pageable).getTotalPages();
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
                    .build();
            save(requestDto);
        }
    }

}

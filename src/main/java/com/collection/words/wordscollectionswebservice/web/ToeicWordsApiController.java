package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.toeic.ToeicWordsService;
import com.collection.words.wordscollectionswebservice.web.dto.ToeicWordsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ToeicWordsApiController {
    private final ToeicWordsService toeicWordsService;

    @PostMapping("/api/words/posts")
    public Long save(@RequestBody ToeicWordsSaveRequestDto requestDto){
        return toeicWordsService.save(requestDto);
    }

    @PostMapping("/api/words/postsExcel")
    public void excelsave(@RequestParam("file") MultipartFile file) throws IOException {
        toeicWordsService.excelSave(file);
    }

    @PostMapping("/api/words/excelDown")
    public void exceldown(HttpServletResponse response) throws IOException {
//        Workbook wb = new HSSFWorkbook(); xls 확장자
        Workbook wb = new XSSFWorkbook(); // xlsx 확장자
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("단어");
        cell = row.createCell(1);
        cell.setCellValue("의미");

        // Body
        for (int i=0; i<3; i++) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("영단어입력");
            cell = row.createCell(1);
            cell.setCellValue("의미입력");
        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=wordExcelForm.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}

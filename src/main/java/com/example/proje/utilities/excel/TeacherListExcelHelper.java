package com.example.proje.utilities.excel;

import java.util.List;

import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.proje.model.entity.Teacher;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class TeacherListExcelHelper {

    static String[] HEADERs = {"Lesson Name", "Section Name", "School Name", "Mail", "Phone Number", "Password"};

    static String SHEET = "Teachers";

    private Workbook workbook;

    private Sheet sheet;

    private List<Teacher> teachers;

    public TeacherListExcelHelper(List<Teacher> teachers) {
        this.teachers = teachers;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(SHEET);
    }

    public void export(HttpServletResponse response) throws IOException {

        Row headerRow = sheet.createRow(0);

        CellStyle headerCellStyle = workbook.createCellStyle();
        XSSFFont headerFontStyle = (XSSFFont) workbook.createFont();
        headerFontStyle.setBold(true);
        headerFontStyle.setFontHeight(16);
        headerFontStyle.setColor(Font.COLOR_RED);
        headerCellStyle.setFont(headerFontStyle);

        for (int col = 0; col < HEADERs.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(HEADERs[col]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowIdx = 1;
        CellStyle rowCellStyle = workbook.createCellStyle();
        XSSFFont rowFontStyle = (XSSFFont) workbook.createFont();
        rowFontStyle.setFontHeight(13);
        rowFontStyle.setColor(IndexedColors.BLUE.getIndex());
        rowCellStyle.setFont(rowFontStyle);

        for (Teacher teacher : teachers) {
            Row dataRow = sheet.createRow(rowIdx++);

            Cell cell = dataRow.createCell(0);
            cell.setCellValue(teacher.getLesson().getLessonName());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(1);
            cell.setCellValue(teacher.getLesson().getSectionName());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(2);
            cell.setCellValue(teacher.getSchoolName());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(3);
            cell.setCellValue(teacher.getMail());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(4);
            cell.setCellValue(teacher.getPhoneNo());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(5);
            cell.setCellValue(teacher.getPassword());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(rowCellStyle);

        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}

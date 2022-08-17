package com.example.proje.utilities.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.proje.model.entity.Lesson;
import com.example.proje.model.entity.Student;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentListExcelHelper {

    static String[] HEADERs = {"Lesson Name", "LSection Name", "Identification Number", "Registration Year", "E - Mail", "Password"};

    static String SHEET = "student";

    private Workbook workbook;

    private Sheet sheet;

    private List<Student> student;

    private List<Lesson> lesson;

    public StudentListExcelHelper(List<Student> student, List<Lesson> lesson) {
        this.student = student;
        this.lesson = lesson;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(SHEET);
    }

    public void export(HttpServletResponse response) throws IOException {

        Row headerRow = sheet.createRow(0);

        CellStyle headerCellStyle = workbook.createCellStyle();
        XSSFFont headerFontStyle = (XSSFFont) workbook.createFont();
        headerFontStyle.setBold(true);
        headerFontStyle.setFontHeight(15);
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
        rowCellStyle.setFont(rowFontStyle);
        rowFontStyle.setColor(IndexedColors.DARK_YELLOW.getIndex());

        for (Student student : student) {
            Row dataRow = sheet.createRow(rowIdx++);

            Cell cell = dataRow.createCell(0);
            //  for(Lesson lessons : lesson) {
            //if(lessons.getId()==student.getLessonid())
            //	cell.setCellValue(student.getEmail());
            //   }

            sheet.autoSizeColumn(0);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(1);
            //cell.setCellValue(student.getLesson().getSectionName());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(2);
            cell.setCellValue(student.getIdentificationNo());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(3);
            cell.setCellValue(student.getRegistrationYear());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(4);
            cell.setCellValue(student.getEmail());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(rowCellStyle);

            cell = dataRow.createCell(5);
            cell.setCellValue(student.getPassword());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(rowCellStyle);
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}

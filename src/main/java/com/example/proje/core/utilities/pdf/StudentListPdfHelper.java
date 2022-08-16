package com.example.proje.core.utilities.pdf;

import com.example.proje.entities.concretes.Lesson;
import com.example.proje.entities.concretes.Student;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentListPdfHelper {

    static String[] HEADER1 = {"Lesson Name", "Section Name", "Identification Number", "Registration Year", "e - Mail", "Password"};

    static String[] HEADER2 = {"e - Mail", "Password"};

    private List<Student> StudentList;
    private List<Lesson> LessonList;

    public StudentListPdfHelper(List<Student> studentList, List<Lesson> lessonList) {
        this.StudentList = studentList;
        this.LessonList = lessonList;
    }


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table1 = new PdfPTable(6);
        table1.setWidthPercentage(105);
        table1.setWidths(new float[]{1f, 1f, 1.8f, 2f, 3f, 2f});
        table1.setSpacingBefore(15);

        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(105);
        table2.setWidths(new float[]{3f, 3f});
        table2.setSpacingBefore(15);


        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PatternColor.WHITE);
        cell.setPadding(5);


        Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.RED);

        Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLUE);


        for (int col = 0; col < HEADER1.length; col++) {
            cell.setPhrase(new Phrase(HEADER1[col], font1));
            table1.addCell(cell);
        }

        for (int col = 0; col < HEADER2.length; col++) {
            cell.setPhrase(new Phrase(HEADER2[col], font2));
            table2.addCell(cell);
        }

        String lessonNames = "";
        String sectionNames = "";
        for (Student student : StudentList) {
            for (Lesson lesson : LessonList) {
                if (lesson.getLessonId() == student.getStudentId()) {
                    lessonNames += lesson.getLessonName().toString() + " - ";
                    sectionNames += lesson.getSectionName() + " - ";
                }
            }
            table1.addCell(lessonNames);
            table1.addCell(sectionNames);
            table1.addCell(student.getIdentificationNo());
            table1.addCell(student.getRegistrationYear());
            table1.addCell(student.getEmail());
            table1.addCell(student.getPassword());
            table2.addCell(student.getEmail());
            table2.addCell(student.getPassword());
        }


        document.add(table1);
        document.addTitle("Öğrenci PDF işlemleri");

        document.newPage();

        document.add(table2);

        document.close();
    }
}

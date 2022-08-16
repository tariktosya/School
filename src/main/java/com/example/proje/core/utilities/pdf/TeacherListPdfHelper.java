package com.example.proje.core.utilities.pdf;

import com.example.proje.entities.concretes.Teacher;

import com.itextpdf.text.*;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeacherListPdfHelper {

    static String[] HEADERs = {"Lesson Name", "Section Name", "School Name", "Mail", "Phone Number", "Password"};

    private List<Teacher> teacherList;

    public TeacherListPdfHelper(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        /*Image image = Image.getInstance((new ClassPathResource("static/image/user-icon.jpg").getFile()).getPath());
        image.setAlignment(Image.ALIGN_CENTER);
        document.add(image);*/

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(105);
        table.setWidths(new float[]{1.8f, 1.8f, 2f, 2.3f, 3.8f, 2.8f, 1.3f, 2f});
        table.setSpacingBefore(15);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PatternColor.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.RED);

        for (int col = 0; col < HEADERs.length; col++) {
            cell.setPhrase(new Phrase(HEADERs[col], font));
            table.addCell(cell);
        }

        for (Teacher teacher : teacherList) {
            table.addCell(teacher.getLesson().getLessonName());
            table.addCell(teacher.getLesson().getSectionName());
            table.addCell(teacher.getSchoolName());
            table.addCell(teacher.getMail());
            table.addCell(teacher.getPhoneNo());
            table.addCell(teacher.getPassword());
        }

        document.add(table);
        document.addTitle("Öğretmen PDF işlemleri");

        document.close();
    }
}

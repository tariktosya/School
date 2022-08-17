package com.example.proje.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.proje.repository.WebMailRepository;
import com.example.proje.model.dtos.teacher.TeacherGetDto;
import com.example.proje.utilities.excel.TeacherListExcelHelper;
import com.example.proje.utilities.pdf.TeacherListPdfHelper;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.ErrorDataResult;
import com.example.proje.utilities.results.ErrorResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.utilities.results.SuccessDataResult;
import com.example.proje.utilities.results.SuccessResult;
import com.example.proje.repository.TeacherRepository;
import com.example.proje.repository.LessonRepository;
import com.example.proje.model.entity.Teacher;
import com.example.proje.model.entity.Lesson;
import com.example.proje.model.dtos.teacher.TeacherDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService  {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private LessonRepository LessonRepository;

    @Autowired
    private WebMailRepository webMailRepository;

    private TeacherGetDto convertEntityToDto(Teacher teacher) {
        TeacherGetDto newteacherGetDto = new TeacherGetDto();
        newteacherGetDto.setId(teacher.getId());
        newteacherGetDto.setPassword(teacher.getPassword());
        newteacherGetDto.setPhoneNo(teacher.getPhoneNo());
        newteacherGetDto.setMail(teacher.getMail());
        newteacherGetDto.setSchoolName(teacher.getSchoolName());
        
        
        /*newteacherGetDto.setFirstName(teacher.getLesson().getLessonName());
        newteacherGetDto.setLastName(teacher.getLesson().getSectionName());*/

        return newteacherGetDto;
    }

    public DataResult<List<TeacherGetDto>> getAllTeacher() {
        return new SuccessDataResult<List<TeacherGetDto>>(teacherRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }

    public DataResult<List<TeacherGetDto>> getAllPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of((pageNo - 1), pageSize);

        if (teacherRepository.findAll(pageable).getContent().size() == 0) {
            return new ErrorDataResult<List<TeacherGetDto>>("Kullanıcı bulunamadı.");
        } else {
            return new SuccessDataResult<List<TeacherGetDto>>(teacherRepository.findAll(pageable).getContent()
                    .stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList()), "Bilgiler sayfa numarası ve sırasına göre getiriliyor.");
        }
    }

    public Result addTeacher(TeacherDto teacherDto) {
        if (webMailRepository.existsByMail(teacherDto.getMail()) || teacherRepository.existsByPhoneNo(teacherDto.getPhoneNo())) {
            return new ErrorResult("Mail adresinizi veya telefon numaranızı kontrol edin!");
        } else {
            Teacher newteacher = new Teacher();
            Lesson newLesson = new Lesson();

            newLesson.setLessonName(teacherDto.getLessonName());
            newLesson.setSectionName(teacherDto.getSectionName());

            newteacher.setSchoolName(teacherDto.getSchoolName());
            newteacher.setPassword(teacherDto.getPassword());
            newteacher.setPhoneNo(teacherDto.getPhoneNo());
            newteacher.setMail(teacherDto.getMail());

            newteacher.setLesson(newLesson);

            LessonRepository.save(newteacher.getLesson());
            teacherRepository.save(newteacher);
            return new SuccessResult("Kişi listeye eklendi.");
        }
    }

    public Result deleteTeacher(int id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return new SuccessResult("Kişi listeden silindi.");
        } else {
            return new ErrorResult(id + " id numarasına ait kullanıcı bulunamadı.");
        }
    }


    public DataResult<List<Teacher>> getBySchoolNameContains(String companyName) {
        if (teacherRepository.existsBySchoolName(companyName)) {
            return new ErrorDataResult<List<Teacher>>("Kullanıcı bulunamadı.");
        } else {
            return new SuccessDataResult<List<Teacher>>(teacherRepository.getBySchoolNameContains(companyName), "Data listelendi.");
        }
    }


    public DataResult<List<Teacher>> getByMailContains(String webMail) {
        if (teacherRepository.existsByMail(webMail)) {
            return new ErrorDataResult<List<Teacher>>("Kullanıcı bulunamadı.");
        } else {
            return new SuccessDataResult<List<Teacher>>(teacherRepository.getByMailContains(webMail), "Data listelendi.");
        }
    }


    public Result updatePhoneNo(int id, String phoneNo) {
        if (teacherRepository.existsById(id)) {
            if (teacherRepository.existsByPhoneNo(phoneNo)) {
                return new ErrorResult("Bu telefon numarası bir başkasına aittir.");
            } else if (phoneNo.length() == 11) {
                Teacher newteacher = teacherRepository.findById(id);
                newteacher.setPhoneNo(phoneNo);
                teacherRepository.save(newteacher);
                return new SuccessResult("Kişinin telefon numarası güncellendi.");
            } else {
                return new ErrorResult("Lütfen telefon numaranızın karakter sayısına dikkat ediniz.");
            }
        } else {
            return new ErrorResult(id + " id numarasına ait bir kullanıcı bulunamadı.");
        }

    }


    public Result updatePassword(int id, String password) {
        if (teacherRepository.existsById(id)) {
            Teacher newteacher = teacherRepository.findById(id);
            newteacher.setPassword(password);
            teacherRepository.save(newteacher);
            return new SuccessResult("Kişinin şifresi güncellendi.");
        } else {
            return new ErrorResult(id + " id numarasına ait bir kullanıcı bulunamadı.");
        }
    }


    public Result exportToExcelTeacher(HttpServletResponse response) {
        try {
            String fileName = "teacher-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

            TeacherListExcelHelper teacherListExcelHelper = new TeacherListExcelHelper(teacherRepository.findAll());
            teacherListExcelHelper.export(response);
            return new SuccessResult(getAllTeacher().toString());
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


    public Result exportToPdfTeacher(HttpServletResponse response) {
        try {
            String fileName = "Teachers";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");

            TeacherListPdfHelper teacherListPdfHelper = new TeacherListPdfHelper(teacherRepository.findAll());
            teacherListPdfHelper.export(response);
            return new SuccessResult(getAllTeacher().toString());
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }
}

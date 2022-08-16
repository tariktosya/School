package com.example.proje.business.concretes;

import com.example.proje.core.entities.dtos.StudentGetWithLessonDto;
import com.example.proje.core.utilities.converters.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.proje.business.abstracts.StudentService;
import com.example.proje.core.dataAccess.IdentificationNoEmailDao;
import com.example.proje.core.entities.dtos.StudentGetDto;
import com.example.proje.core.utilities.excel.StudentListExcelHelper;
import com.example.proje.core.utilities.pdf.StudentListPdfHelper;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.ErrorDataResult;
import com.example.proje.core.utilities.results.ErrorResult;
import com.example.proje.core.utilities.results.Result;
import com.example.proje.core.utilities.results.SuccessDataResult;
import com.example.proje.core.utilities.results.SuccessResult;
import com.example.proje.dataAccess.StudentDao;
import com.example.proje.dataAccess.LessonDao;
import com.example.proje.entities.concretes.Lesson;
import com.example.proje.entities.concretes.Student;
import com.example.proje.entities.dtos.StudentDto;


import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentManager implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private IdentificationNoEmailDao identificationNoEmailDao;

    @Autowired
    private LessonDao lessonDao;


    private StudentGetDto convertEntityToDto(Student student) {
        StudentGetDto newStudentGetDto = new StudentGetDto();

        newStudentGetDto.setStudentId(student.getStudentId());
        newStudentGetDto.setRegistrationYear(student.getRegistrationYear());
        newStudentGetDto.setEmail(student.getEmail());
        newStudentGetDto.setPassword(student.getPassword());
        newStudentGetDto.setIdentificationNo(student.getIdentificationNo());

        newStudentGetDto.setLessons(student.getLessons());//*****

        System.out.println(student.getLessons());
        return newStudentGetDto;
    }

    private StudentGetWithLessonDto convertStudentLessonDto(Student student) {
        StudentGetWithLessonDto news = new StudentGetWithLessonDto();

        news.setId(student.getStudentId());
        news.setEmail(student.getEmail());
        news.setIdentificationNo(student.getIdentificationNo());
        news.setRegistrationYear(student.getRegistrationYear());

        news.setLessonName(lessonDao.getByStudentIdLessonName(studentDao.getByStudentLessons(student.getStudentId())));
        news.setSectionName(lessonDao.getByStudentIdSectionName(studentDao.getByStudentLessons(student.getStudentId())));
        return news;
    }

    @Override
    public DataResult<List<StudentGetWithLessonDto>> getByStudentLessons() {
        return new SuccessDataResult<List<StudentGetWithLessonDto>>(studentDao.findAll()
                .stream()
                .map(this::convertStudentLessonDto)
                .collect(Collectors.toList()), "Öğrenci Bilgileri Dersler ile listelendi.");
    }

    /*@Override
    public List<Integer> getByStudentLessons(int studentId) {
        //System.out.println("Burasi -"+studentDao.getByStudentLessons(studentId));
        System.out.println("Nasi tak diye burdayim -" + studentDao.getByStudentLessons(studentId));
        List<String> studentLessonName = lessonDao.getByStudentIdLessonName(studentDao.getByStudentLessons(studentId));
        List<String> studentSectionName = lessonDao.getByStudentIdSectionName(studentDao.getByStudentLessons(studentId));
        System.out.println("lessonlar" +studentLessonName);
        System.out.println("Sectionlar" +studentSectionName);
        //return new SuccessDataResult<List<Student>>(studentDao.getByStudentLessons(), "Data listelendi");
        return null;

    }*/


    private EntityDtoConverter<StudentGetDto, Student> studentAddDtoToStudentGetDtoConverter = new EntityDtoConverter(Student.class);

    @Override
    public DataResult<List<StudentGetDto>> getAllStudent() {
        return new SuccessDataResult<List<StudentGetDto>>(studentDao.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()), "Öğrenci Bilgileri listelendi.");
        //return new SuccessDataResult<List<StudentGetDto>>(studentDao.getAllStudentGetDto());
    }

    @Override
    public DataResult<List<StudentGetDto>> getAllPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of((pageNo - 1), pageSize);

        if (studentDao.findAll(pageable).getContent().size() == 0) {
            return new ErrorDataResult<List<StudentGetDto>>("Kullanıcı bulunamadı.");
        } else {
            return new SuccessDataResult<List<StudentGetDto>>(studentDao.findAll(pageable).getContent()
                    .stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList()), "Bilgiler sayfa numarası ve sırasına göre getiriliyor.");
        }
    }

    @Override
    public Result addStudent(StudentDto studentDto) {
        if (identificationNoEmailDao.existsByIdentificationNo(studentDto.getIdentificationNo()) || identificationNoEmailDao.existsByEmail(studentDto.getEmail())) {
            return new ErrorResult("Kişinin e-mail veya TC kimlik numarasını tekrardan kontrol ediniz. Kişi listeye eklenemedi.");
        } else {
            Student newStudent = new Student();
            newStudent.setEmail(studentDto.getEmail());
            newStudent.setRegistrationYear(studentDto.getRegistrationYear());
            newStudent.setIdentificationNo(studentDto.getIdentificationNo());
            newStudent.setPassword(studentDto.getPassword());

            List<Lesson> lessonss = lessonDao.getAllByLessonIdInTarik(studentDto.getLessonId());
            newStudent.setLessons(lessonss);
            //System.out.println(studentDto.getLessonId()+"\n");
            studentDao.save(newStudent);
            return new SuccessResult("Öğrenci listeye eklendi.");
        }
    }

    @Override
    public Result deleteStudent(int id) {
        if (studentDao.existsById(id)) {
            studentDao.deleteById(id);
            return new SuccessResult("Öğrenci listeden silindi.");
        } else {
            return new ErrorResult(id + " id numarasına ait öğrenci bulunamadı.");
        }
    }

    @Override
    public Result updatePassword(int id, String password) {
        if (studentDao.existsById(id)) {
            Student newStudent = studentDao.findById(id);
            newStudent.setPassword(password);
            studentDao.save(newStudent);
            return new SuccessResult("Öğrencinin şifresi güncellendi.");
        } else {
            return new ErrorResult(id + " id numarasına ait bir öğrenci bulunamadı.");
        }
    }

    @Override
    public DataResult<List<Student>> getByIdentificationNoContains(String identificationNo) {
        if (studentDao.existsByIdentificationNoContains(identificationNo)) {
            return new SuccessDataResult<List<Student>>(studentDao.getByIdentificationNoContains(identificationNo), "Data listelendi.");
        } else {
            return new ErrorDataResult<List<Student>>("Kullanıcı bulunamadı.");
        }
    }


    @Override
    public Result exportToExcelStudent(HttpServletResponse response) {
        try {
            String fileName = "Student-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

            StudentListExcelHelper StudentListExcelHelper = new StudentListExcelHelper(studentDao.findAll(), lessonDao.findAll());
            StudentListExcelHelper.export(response);
            return new SuccessResult(getAllStudent().toString());
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }

    @Override
    public Result exportToPdfStudent(HttpServletResponse response) {
        try {
            /*LessonManager lessonManager = new LessonManager();*/
            String fileName = "Student-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");

            StudentListPdfHelper studentListPdfHelper = new StudentListPdfHelper(studentDao.findAll(), lessonDao.findAll());
            studentListPdfHelper.export(response);
            return new SuccessResult(getAllStudent().toString());
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


}

package com.example.proje.service;

import com.example.proje.model.dtos.student.StudentGetWithLessonDto;
import com.example.proje.utilities.converters.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.proje.repository.IdentificationNoEmailRepository;
import com.example.proje.model.dtos.student.StudentGetDto;
import com.example.proje.utilities.excel.StudentListExcelHelper;
import com.example.proje.utilities.pdf.StudentListPdfHelper;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.ErrorDataResult;
import com.example.proje.utilities.results.ErrorResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.utilities.results.SuccessDataResult;
import com.example.proje.utilities.results.SuccessResult;
import com.example.proje.repository.StudentRepository;
import com.example.proje.repository.LessonRepository;
import com.example.proje.model.entity.Lesson;
import com.example.proje.model.entity.Student;
import com.example.proje.model.dtos.student.StudentDto;


import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService  {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IdentificationNoEmailRepository identificationNoEmailRepository;

    @Autowired
    private LessonRepository lessonRepository;


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

        news.setLessonName(lessonRepository.getByStudentIdLessonName(studentRepository.getByStudentLessons(student.getStudentId())));
        news.setSectionName(lessonRepository.getByStudentIdSectionName(studentRepository.getByStudentLessons(student.getStudentId())));
        return news;
    }


    public DataResult<List<StudentGetWithLessonDto>> getByStudentLessons() {
        return new SuccessDataResult<List<StudentGetWithLessonDto>>(studentRepository.findAll()
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


    public DataResult<List<StudentGetDto>> getAllStudent() {
        return new SuccessDataResult<List<StudentGetDto>>(studentRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()), "Öğrenci Bilgileri listelendi.");
        //return new SuccessDataResult<List<StudentGetDto>>(studentDao.getAllStudentGetDto());
    }


    public DataResult<List<StudentGetDto>> getAllPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of((pageNo - 1), pageSize);

        if (studentRepository.findAll(pageable).getContent().size() == 0) {
            return new ErrorDataResult<List<StudentGetDto>>("Kullanıcı bulunamadı.");
        } else {
            return new SuccessDataResult<List<StudentGetDto>>(studentRepository.findAll(pageable).getContent()
                    .stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList()), "Bilgiler sayfa numarası ve sırasına göre getiriliyor.");
        }
    }


    public Result addStudent(StudentDto studentDto) {
        if (identificationNoEmailRepository.existsByIdentificationNo(studentDto.getIdentificationNo()) || identificationNoEmailRepository.existsByEmail(studentDto.getEmail())) {
            return new ErrorResult("Kişinin e-mail veya TC kimlik numarasını tekrardan kontrol ediniz. Kişi listeye eklenemedi.");
        } else {
            Student newStudent = new Student();
            newStudent.setEmail(studentDto.getEmail());
            newStudent.setRegistrationYear(studentDto.getRegistrationYear());
            newStudent.setIdentificationNo(studentDto.getIdentificationNo());
            newStudent.setPassword(studentDto.getPassword());

            List<Lesson> lessonss = lessonRepository.getAllByLessonIdInTarik(studentDto.getLessonId());
            newStudent.setLessons(lessonss);
            //System.out.println(studentDto.getLessonId()+"\n");
            studentRepository.save(newStudent);
            return new SuccessResult("Öğrenci listeye eklendi.");
        }
    }


    public Result deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return new SuccessResult("Öğrenci listeden silindi.");
        } else {
            return new ErrorResult(id + " id numarasına ait öğrenci bulunamadı.");
        }
    }


    public Result updatePassword(int id, String password) {
        if (studentRepository.existsById(id)) {
            Student newStudent = studentRepository.findById(id);
            newStudent.setPassword(password);
            studentRepository.save(newStudent);
            return new SuccessResult("Öğrencinin şifresi güncellendi.");
        } else {
            return new ErrorResult(id + " id numarasına ait bir öğrenci bulunamadı.");
        }
    }


    public DataResult<List<Student>> getByIdentificationNoContains(String identificationNo) {
        if (studentRepository.existsByIdentificationNoContains(identificationNo)) {
            return new SuccessDataResult<List<Student>>(studentRepository.getByIdentificationNoContains(identificationNo), "Data listelendi.");
        } else {
            return new ErrorDataResult<List<Student>>("Kullanıcı bulunamadı.");
        }
    }



    public Result exportToExcelStudent(HttpServletResponse response) {
        try {
            String fileName = "Student-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

            StudentListExcelHelper StudentListExcelHelper = new StudentListExcelHelper(studentRepository.findAll(), lessonRepository.findAll());
            StudentListExcelHelper.export(response);
            return new SuccessResult(getAllStudent().toString());
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


    public Result exportToPdfStudent(HttpServletResponse response) {
        try {
            /*LessonManager lessonManager = new LessonManager();*/
            String fileName = "Student-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");

            StudentListPdfHelper studentListPdfHelper = new StudentListPdfHelper(studentRepository.findAll(), lessonRepository.findAll());
            studentListPdfHelper.export(response);
            return new SuccessResult(getAllStudent().toString());
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


}

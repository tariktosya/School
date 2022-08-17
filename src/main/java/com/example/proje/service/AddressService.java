package com.example.proje.service;

import com.example.proje.utilities.results.Result;
import com.example.proje.utilities.results.SuccessResult;
import com.example.proje.repository.AddressRepository;
import com.example.proje.repository.CityRepository;
import com.example.proje.repository.StudentRepository;
import com.example.proje.model.entity.*;
import com.example.proje.model.dtos.address.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CityRepository cityRepository;


    private AddressDto convertDto(List<City> city, List<Distrik> distrik){
        AddressDto dto = new AddressDto();
        return dto;
    }



    public List<String> getByCityNameToCityDistrik(String cityName) {
        return addressRepository.getByCityDistrik(cityName);

    }


    public Result addAddress(AddressDto addressDto) {
        Address newAdress = new Address();
        newAdress.setAddressId(addressDto.getAddressId());
        newAdress.setAddressCityName(addressDto.getAddressCityName());
        newAdress.setAddressDistrikName(addressDto.getAddressDistrikName());
        newAdress.setAddressDescription(addressDto.getAddressDescription());
        newAdress.setStudentId(studentRepository.findById(addressDto.getStudentIds()));
        this.addressRepository.save(newAdress);
        return new SuccessResult("Adres eklendi");
    }

//    @Override
//    public Result getDistrik(StudentDto studentDto) {
//
//            Student newStudent = new Student();
//            newStudent.setEmail(studentDto.getEmail());
//            newStudent.setRegistrationYear(studentDto.getRegistrationYear());
//            newStudent.setIdentificationNo(studentDto.getIdentificationNo());
//            newStudent.setPassword(studentDto.getPassword());
//
//            List<Lesson> lessonss = lessonDao.getAllByLessonIdInTarik(studentDto.getLessonId());
//            newStudent.setLessons(lessonss);
//            //System.out.println(studentDto.getLessonId()+"\n");
//            studentDao.save(newStudent);
//            return new SuccessResult("Öğrenci listeye eklendi.");
//
//    }
}

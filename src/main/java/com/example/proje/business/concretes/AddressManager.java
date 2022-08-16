package com.example.proje.business.concretes;

import com.example.proje.business.abstracts.AddressService;
import com.example.proje.core.utilities.results.Result;
import com.example.proje.core.utilities.results.SuccessResult;
import com.example.proje.dataAccess.AddressDao;
import com.example.proje.dataAccess.CityDao;
import com.example.proje.dataAccess.StudentDao;
import com.example.proje.entities.concretes.*;
import com.example.proje.entities.dtos.AddressDto;
import com.example.proje.entities.dtos.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressManager implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CityDao cityDao;


    private AddressDto convertDto(List<City> city, List<Distrik> distrik){
        AddressDto dto = new AddressDto();
        return dto;
    }


    @Override
    public List<String> getByCityNameToCityDistrik(String cityName) {
        return addressDao.getByCityDistrik(cityName);

    }

    @Override
    public Result addAddress(AddressDto addressDto) {
        Address newAdress = new Address();
        newAdress.setAddressId(addressDto.getAddressId());
        newAdress.setAddressCityName(addressDto.getAddressCityName());
        newAdress.setAddressDistrikName(addressDto.getAddressDistrikName());
        newAdress.setAddressDescription(addressDto.getAddressDescription());
        newAdress.setStudentId(studentDao.findById(addressDto.getStudentIds()));
        this.addressDao.save(newAdress);
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

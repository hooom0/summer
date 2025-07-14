package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.List;

public interface PersonService {
    // 创建人员完整档案
    void createPersonWithDetails(Person person, PersonDetail detail, List<FaceData> faceDataList);

    // 获取完整人员信息（包含详情和人脸数据）
    Person getPersonById(Long id);

    // 更新基础信息
    void updatePerson(Person person);

    // 级联删除
    void deletePerson(Long id);

    // 分页查询
    List<Person> listPersons(int page, int size);
}
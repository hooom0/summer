package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "创建人员完整档案")
    @PostMapping
    public ResponseEntity<String> createPerson(
            @RequestBody Person person,
            @RequestParam(required = false) PersonDetail detail,
            @RequestParam(required = false) List<FaceData> faceDataList) {
        personService.createPersonWithDetails(person, detail, faceDataList);
        return ResponseEntity.ok("人员档案创建成功");
    }

    @Operation(summary = "获取人员完整信息")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonFullInfo(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @Operation(summary = "更新基础信息")
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(
            @PathVariable Long id,
            @RequestBody Person person) {
        person.setId(id);
        personService.updatePerson(person);
        return ResponseEntity.ok("基础信息更新成功");
    }

    @Operation(summary = "删除人员（级联删除）")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("人员档案已删除");
    }

    @Operation(summary = "分页查询人员列表")
    @GetMapping
    public ResponseEntity<List<Person>> listPersons(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(personService.listPersons(page, size));
    }
}
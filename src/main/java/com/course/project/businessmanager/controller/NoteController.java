package com.course.project.businessmanager.controller;


import com.course.project.businessmanager.dto.NoteDTO;
import com.course.project.businessmanager.entity.Note;
import com.course.project.businessmanager.mapper.NoteMapper;
import com.course.project.businessmanager.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "Note API")
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @Autowired
    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping
    @ApiOperation(value = "Get the list of all notes")
    public ResponseEntity<List<NoteDTO>> list() {
        log.info("Enter into list of NoteController");
        return ResponseEntity.ok().body(noteMapper.convertToDtoList(noteService.getAll()));
    }


    @PostMapping
    @ApiOperation(value = "Create new note")
    public ResponseEntity<NoteDTO> save(@Valid @RequestBody NoteDTO noteDTO) {
        log.info("Enter into save of NoteController with noteDTO: {}", noteDTO);
        Note note = noteService.save(noteMapper.convertToEntity(noteDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.convertToDto(note));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get note info by id")
    public ResponseEntity<NoteDTO> get(@PathVariable("id") UUID id){
        log.info("In get(id = [{}])", id);
        Note note = noteService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(noteMapper.convertToDto(note));
    }

    @PutMapping
    @ApiOperation(value = "Update existing note by id")
    public ResponseEntity<NoteDTO> update(@Valid @RequestBody NoteDTO noteDTO) {
        log.info("In update (noteDTO = [{}])", noteDTO);
        Note note = noteService.update(noteMapper.convertToEntity(noteDTO));
        return ResponseEntity.status(HttpStatus.OK).body(noteMapper.convertToDto(note));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete note by id")
    public ResponseEntity delete(@PathVariable("id") UUID id){
        log.info("In delete (id =[{}]", id);
        Note note = noteService.getById(id);
        noteService.delete(note);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

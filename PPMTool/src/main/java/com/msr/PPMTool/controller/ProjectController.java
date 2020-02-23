package com.msr.PPMTool.controller;

import com.msr.PPMTool.domain.Project;
import com.msr.PPMTool.service.MapValidationErrorService;
import com.msr.PPMTool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return mapValidationErrorService.mapValidationService(theBindingResult);
        }
        Project project1 = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProject(@PathVariable String projectId){
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable String projectId){
        String response = projectService.deleteProjectByProjectId(projectId);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
}

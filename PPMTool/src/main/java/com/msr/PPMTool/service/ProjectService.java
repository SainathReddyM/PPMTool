package com.msr.PPMTool.service;

import com.msr.PPMTool.Exceptions.ProjectIDException;
import com.msr.PPMTool.domain.Project;
import com.msr.PPMTool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIDException("Project ID'"+project.getProjectIdentifier()+"' already exists");
        }

        return project;
    }
}

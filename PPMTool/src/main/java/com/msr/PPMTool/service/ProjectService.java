package com.msr.PPMTool.service;


import com.msr.PPMTool.domain.Project;
import com.msr.PPMTool.exception.ProjectIdException;
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
            throw new ProjectIdException("Project ID'"+project.getProjectIdentifier()+"' already exists");
        }

        return project;
    }

    public Project getProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project==null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public String deleteProjectByProjectId(String projectId){
        Project project=projectRepository.findByProjectIdentifier(projectId);
        if(project==null){
            throw new ProjectIdException("No project exists with id '"+projectId+"'");
        }
        projectRepository.delete(project);
        return "project with id '"+projectId+"' is deleted!";
    }
}

package diyproject.service;

import diyproject.dao.DIYProjectDao;
import diyproject.entity.Project;


public class ProjectService {
	private DIYProjectDao diyProjectDao = new DIYProjectDao();
	 

	public Project addProject(Project project) {
		
		return diyProjectDao.insertProject(project);
	}
	

		
	}



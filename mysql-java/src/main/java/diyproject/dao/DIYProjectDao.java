package diyproject.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import diyproject.entity.Project;
import diyproject.exception.DbException;
import provided.util.DaoBase;

public class DIYProjectDao extends DaoBase {

	
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY = "project_category";
	private static final String STEP_TABLE = "step";
	
	public Project insertProject(Project project) {
		
		 // @formatter:off
		  String sql = ""
		      + "INSERT INTO " + PROJECT_TABLE + " "
		      + "(project_name, notes, difficulty, estimated_hours, actual_hours) "
		      + "VALUES "
		      + "(?, ?, ?, ?, ?)";
		  // @formatter:on

		  try (Connection conn = DbConnection.getConnection()) {
		    startTransaction(conn);

		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		      setParameter(stmt, 1, project.getProjectName(), String.class);
		      setParameter(stmt, 2, project.getNotes(), String.class);
		      setParameter(stmt, 3, project.getDifficulty(), Integer.class);
		      setParameter(stmt, 4, project.getEstimatedHours(), BigDecimal.class);
		      setParameter(stmt, 5, project.getActualHours(), BigDecimal.class);
		       
		      
		      stmt.executeUpdate();
		      
		      Integer projectId = getLastInsertId(conn, PROJECT_TABLE);

		      commitTransaction(conn);
		      
		      project.setProjectId(projectId);
		      return project;
		    } 
		    catch (Exception e) {
		      rollbackTransaction(conn);
		      throw new DbException(e);
		    }
		  } catch (SQLException e) {
		    throw new DbException(e);
		  }
		  
	}
}
	



	
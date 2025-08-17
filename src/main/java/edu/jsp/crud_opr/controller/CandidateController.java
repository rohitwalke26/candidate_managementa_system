package edu.jsp.crud_opr.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.jsp.crud_opr.model.Candidate;
import edu.jsp.crud_opr.utility.JDBC;

public class CandidateController {
	static JDBC jdbc = JDBC.jdbcUtilityInit();
	private static int candidateId = initialId();
	
	
	public static int insertCandidate(Candidate candidate) {
		try {
			JDBC.preparedStatementInsertCandidate.setInt(1, candidateId);
			JDBC.preparedStatementInsertCandidate.setString(2, candidate.getName());
			JDBC.preparedStatementInsertCandidate.setDouble(3, candidate.getFee());
			
			int executeUpdate = JDBC.preparedStatementInsertCandidate.executeUpdate();
			candidateId++;
			return executeUpdate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int initialId() {
		int existingId = -1;
		try {
			ResultSet resultSet = JDBC.statement.executeQuery("SELECT MAX(id) FROM candidate;");
			while(resultSet.next()) {
				existingId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existingId+1;
		
	}
	
	public static Candidate ReadCandidateById(int id) {
		Candidate c=null;
		try {
			JDBC.preparedStatementToReadCandidateById.setInt(1, id);
			ResultSet resultSet = JDBC.preparedStatementToReadCandidateById.executeQuery();
			while(resultSet.next()) {
				c= new Candidate();
				c.setId(resultSet.getInt(1));
				c.setName(resultSet.getString(2));
				c.setFee(resultSet.getDouble(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}
	
	public static ArrayList<Candidate> fetchCandidatedetails() {
		
		ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
		try {
			ResultSet resultSetToFetchData = JDBC.statement.executeQuery("SELECT * FROM candidate;");
			while (resultSetToFetchData.next()) {
				Candidate c = new Candidate();
				c.setId(resultSetToFetchData.getInt(1));
				c.setName(resultSetToFetchData.getString(2));
				c.setFee(resultSetToFetchData.getDouble(3));
				candidateList.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateList;
	}
	
	
	public static void updateCandidate(byte updateChoice, Object updateElement, int id) {
		if(updateChoice ==1) {
		
				try {
					JDBC.preparedStatementUpdateCandidateName.setString(1, (String)updateElement);
					JDBC.preparedStatementUpdateCandidateName.setInt(2, id);
					JDBC.preparedStatementUpdateCandidateName.executeUpdate();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		}else {
			try {
				JDBC.preparedStatementUpdateCandidateFee.setDouble(1, (double) updateElement);
				JDBC.preparedStatementUpdateCandidateFee.setInt(2, id);
				JDBC.preparedStatementUpdateCandidateFee.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static int deleteCandidateById(int id) {
		try {
			JDBC.preparedStatementDeleteCandidateById.setInt(1, id);
			int row = JDBC.preparedStatementDeleteCandidateById.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}

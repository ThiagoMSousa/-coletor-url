package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ColetaURL;
import to.ColetaUrlTO;
import to.ListaDeUrls;
import factory.ConnectionFactory;

public class ColetaUrlDAO {
	public void incluir(ColetaUrlTO to) {
		String sqlInsert = "INSERT INTO url (endereco) VALUES (?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, to.getUrl());
			stm.execute();
			String sqlSelect = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm1 = conn.prepareStatement(sqlSelect); ResultSet rs = stm1.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(ColetaUrlTO to) {
		String sqlUpdate = "UPDATE url SET endereco=? WHERE id=?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getUrl());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(ColetaUrlTO to) {
		String sqlDelete = "DELETE FROM url WHERE id=?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ColetaUrlTO carregar(int id) {
		ColetaUrlTO to = new ColetaUrlTO();
		to.setId(id);
		String sqlSelect = "SELECT endereco FROM url WHERE url.id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setUrl(rs.getString("endereco"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return to;
	}

}

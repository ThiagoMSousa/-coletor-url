package dao;

public interface DAO {
	public void saveUrl(String url) throws Exception;
	public boolean exists(String url) throws Exception;
}

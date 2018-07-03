package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl {

	public BaseDaoImpl() {

	}

	protected SqlSession getSession() {
		return MyBatisUtils.getSqlSessionFactory().openSession();
	}
}

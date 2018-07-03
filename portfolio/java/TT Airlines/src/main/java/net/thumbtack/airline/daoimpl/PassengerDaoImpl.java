package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.PassengerDao;
import net.thumbtack.airline.mappers.PassengersMapper;
import net.thumbtack.airline.model.Order;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PassengerDaoImpl extends BaseDaoImpl implements PassengerDao {

	private final Logger logger = LoggerFactory.getLogger(PassengerDaoImpl.class);

	protected PassengersMapper getPassengerMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PassengersMapper.class);
	}

	@Override
	public void isContainEnoughPlaces(int orderId, Order order) throws Exception {
		try(SqlSession sqlSession = getSession()) {
			getPassengerMapper(sqlSession).getPassengersByOrderId(orderId);
		}

	}
}

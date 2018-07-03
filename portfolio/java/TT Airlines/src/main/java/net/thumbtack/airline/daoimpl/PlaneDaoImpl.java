package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.PlaneDao;
import net.thumbtack.airline.errors.types.runtime.BadPlaneNameError;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.mappers.PlaneMapper;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.PlaneInfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaneDaoImpl extends BaseDaoImpl implements PlaneDao {

	private final Logger logger = LoggerFactory.getLogger(PlaneDaoImpl.class);

	public PlaneDaoImpl() {
	}

	protected PlaneMapper getPlaneMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PlaneMapper.class);
	}

	@Override
	public List<PlaneInfo> getPlanes() {
		try (SqlSession sqlSession = getSession()) {
			return getPlaneMapper(sqlSession).getInfoPlanes();
		}
	}

	@Override
	public int getPlaneIdByName(String typePlaneName) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			Integer planeId = getPlaneMapper(sqlSession).getPlaneIdByName(typePlaneName);
			if(planeId == null) {
				BadPlaneNameError badPlaneNameError = new BadPlaneNameError();
				logger.info(badPlaneNameError.toString());
				throw new DataNotFoundException(badPlaneNameError);
			}
			return planeId;
		}
	}

	@Override
	public int getPlaneIdByName(Flight flight) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			Integer planeId = getPlaneMapper(sqlSession).getPlaneIdByNameFromFlight(flight);
			if(planeId == null) {
				BadPlaneNameError badPlaneNameError = new BadPlaneNameError();
				logger.info(badPlaneNameError.toString());
				throw new DataNotFoundException(badPlaneNameError);
			}
			return planeId;
		}
	}

	@Override
	public PlaneInfo getPlanes(Flight flight) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			PlaneInfo planeInfo = getPlaneMapper(sqlSession).getPlaneInfoByFlight(flight);
			if(planeInfo == null) {
				BadPlaneNameError badPlaneNameError = new BadPlaneNameError();
				logger.info(badPlaneNameError.toString());
				throw new DataNotFoundException(badPlaneNameError);
			}
			return planeInfo;
		}
	}

	@Override
	public void dropTable() {
		try (SqlSession sqlSession = getSession()) {
			try {
				getPlaneMapper(sqlSession).dropPlanesTable();
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
		}
	}

	@Override
	public PlaneInfo getPlaneInfoByPlaneName(String planeName) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			PlaneInfo planeInfo = getPlaneMapper(sqlSession).getPlaneInfo(planeName);
			if(planeInfo == null) {
				BadPlaneNameError badPlaneNameError = new BadPlaneNameError();
				logger.info(badPlaneNameError.toString());
				throw new DataNotFoundException(badPlaneNameError);
			}
			return planeInfo;
		}
	}
}

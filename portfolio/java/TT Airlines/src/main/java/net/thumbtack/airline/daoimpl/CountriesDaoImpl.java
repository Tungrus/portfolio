package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.CountriesDao;
import net.thumbtack.airline.errors.types.runtime.DataNotFoundError;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.mappers.CountriesMapper;
import net.thumbtack.airline.model.Countries;
import net.thumbtack.airline.model.Nationality;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CountriesDaoImpl extends BaseDaoImpl implements CountriesDao {

	private final Logger logger = LoggerFactory.getLogger(CountriesDaoImpl.class);

	protected CountriesMapper getCountriesMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(CountriesMapper.class);
	}

	@Override
	public Countries getCountries() {
		try(SqlSession sqlSession = getSession()) {
			return new Countries(getCountriesMapper(sqlSession).getSetOfCountries());
		} catch (Exception e) {
			logger.error(e.toString());
			throw e;
		}
	}

	@Override
	public Nationality getCountryName(Nationality nationality) throws DataNotFoundException {
		try(SqlSession sqlSession = getSession()) {
			Nationality result = getCountriesMapper(sqlSession).getNationalityInfoByIso(nationality.getIso3166());
			if(result == null) {
				DataNotFoundError dataNotFoundError = new DataNotFoundError("iso3166");
				logger.info(dataNotFoundError.toString());
				throw new DataNotFoundException(dataNotFoundError);
			}
			return result;
		}
	}
}

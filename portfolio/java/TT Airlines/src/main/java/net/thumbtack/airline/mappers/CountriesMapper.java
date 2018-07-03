package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Country;
import net.thumbtack.airline.model.Nationality;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface CountriesMapper {

	@Select("SELECT id, name, iso3166 FROM countries")
	public Set<Country> getSetOfCountries();

	@Select("SELECT id, name, iso3166 FROM countries WHERE iso3166=#{iso3166}")
	public Nationality getNationalityInfoByIso(@Param("iso3166") String iso3166);

	@Select("SELECT id, name, iso3166 FROM countries WHERE countries.id=#{nationalityId}")
	public Nationality getNationality(@Param("nationalityId") int nationalityId);

}

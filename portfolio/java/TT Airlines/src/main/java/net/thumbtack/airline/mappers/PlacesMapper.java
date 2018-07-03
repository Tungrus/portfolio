package net.thumbtack.airline.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface PlacesMapper {

	@Insert("INSERT INTO freePlaces (departureId, businessPlaces, economyPlaces ) " +
			"VALUES ( #{departureId}, #{businessPlaces}, #{economyPlaces} )")
	public int insert(@Param("departureId") Integer departureId,
					  @Param("businessPlaces") int businessPlaces,
					  @Param("economyPlaces") int economyPlaces);

	@Update("UPDATE freePlaces " +
			"SET businessPlaces=businessPlaces - #{business}, " +
			"economyPlaces = economyPlaces - #{economy} " +
			"WHERE businessPlaces - #{business} >= 0 AND " +
			"economyPlaces - #{economy} >= 0 AND " +
			"#{departureId} = departureId")
	public int updatePlaces(@Param("business") int business, @Param("economy") int economy,
							@Param("departureId") int departureId);
}

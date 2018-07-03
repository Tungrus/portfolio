package net.thumbtack.airline.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import net.thumbtack.airline.model.PlaneInfo;

public class PlaneInfoDto {
	private String typePlaneName;
	private int bussinesRows;
	private int economyRows;
	private int placesInBusinessRow;
	private int placesInEconomyRow;

	public PlaneInfoDto(String typePlaneName, int bussinesRows, int economyRows, int placesInBusinessRow,
						int placesInEconomyRow) {
		this.typePlaneName = typePlaneName;
		this.bussinesRows = bussinesRows;
		this.economyRows = economyRows;
		this.placesInBusinessRow = placesInBusinessRow;
		this.placesInEconomyRow = placesInEconomyRow;
	}

	public PlaneInfoDto() {
	}

	@JsonGetter("typePlaneName")
	public String getTypePlaneName() {
		return typePlaneName;
	}

	@JsonSetter("typePlaneName")
	public void setTypePlaneName(String typePlaneName) {
		this.typePlaneName = typePlaneName;
	}

	@JsonGetter("bussinesRows")
	public int getBussinesRows() {
		return bussinesRows;
	}

	@JsonSetter("bussinesRows")
	public void setBussinesRows(int bussinesRows) {
		this.bussinesRows = bussinesRows;
	}

	@JsonGetter("economyRows")
	public int getEconomyRows() {
		return economyRows;
	}

	@JsonSetter("economyRows")
	public void setEconomyRows(int economyRows) {
		this.economyRows = economyRows;
	}

	@JsonGetter("placesInBusinessRow")
	public int getPlacesInBusinessRow() {
		return placesInBusinessRow;
	}

	@JsonSetter("placesInBusinessRow")
	public void setPlacesInBusinessRow(int placesInBusinessRow) {
		this.placesInBusinessRow = placesInBusinessRow;
	}

	@JsonGetter("placesInEconomyRow")
	public int getPlacesInEconomyRow() {
		return placesInEconomyRow;
	}

	@JsonSetter("placesInEconomyRow")
	public void setPlacesInEconomyRow(int placesInEconomyRow) {
		this.placesInEconomyRow = placesInEconomyRow;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlaneInfo)) return false;

		PlaneInfo planeInfo = (PlaneInfo) o;

		if (getBussinesRows() != planeInfo.getBussinesRows()) return false;
		if (getEconomyRows() != planeInfo.getEconomyRows()) return false;
		if (getPlacesInBusinessRow() != planeInfo.getPlacesInBusinessRow()) return false;
		if (getPlacesInEconomyRow() != planeInfo.getPlacesInEconomyRow()) return false;
		return getTypePlaneName().equals(planeInfo.getPlaneNameType());
	}

	@Override
	public int hashCode() {
		int result = getTypePlaneName().hashCode();
		result = 31 * result + getBussinesRows();
		result = 31 * result + getEconomyRows();
		result = 31 * result + getPlacesInBusinessRow();
		result = 31 * result + getPlacesInEconomyRow();
		return result;
	}
}

package net.thumbtack.airline.model;

public class PlaneInfo {
	private int id;
	private String planeNameType;
	private int bussinesRows;
	private int economyRows;
	private int placesInBusinessRow;
	private int placesInEconomyRow;


	public PlaneInfo(int id, String planeNameType, int bussinesRows, int economyRows, int placesInBusinessRow,
					 int placesInEconomyRow) {
		this.id = id;
		this.planeNameType = planeNameType;
		this.bussinesRows = bussinesRows;
		this.economyRows = economyRows;
		this.placesInBusinessRow = placesInBusinessRow;
		this.placesInEconomyRow = placesInEconomyRow;
	}

	public PlaneInfo(String planeNameType) {
		this.planeNameType = planeNameType;
		this.id = 0;
		this.bussinesRows = 0;
		this.economyRows = 0;
		this.placesInBusinessRow = 0;
		this.placesInEconomyRow = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaneNameType() {
		return planeNameType;
	}

	public void setPlaneNameType(String planeNameType) {
		this.planeNameType = planeNameType;
	}

	public int getBussinesRows() {
		return bussinesRows;
	}

	public void setBussinesRows(int bussinesRows) {
		this.bussinesRows = bussinesRows;
	}

	public int getEconomyRows() {
		return economyRows;
	}

	public void setEconomyRows(int economyRows) {
		this.economyRows = economyRows;
	}

	public int getPlacesInBusinessRow() {
		return placesInBusinessRow;
	}

	public void setPlacesInBusinessRow(int placesInBusinessRow) {
		this.placesInBusinessRow = placesInBusinessRow;
	}

	public int getPlacesInEconomyRow() {
		return placesInEconomyRow;
	}

	public void setPlacesInEconomyRow(int placesInEconomyRow) {
		this.placesInEconomyRow = placesInEconomyRow;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlaneInfo)) return false;

		PlaneInfo planeInfo = (PlaneInfo) o;

		if (getId() != planeInfo.getId()) return false;
		if (getBussinesRows() != planeInfo.getBussinesRows()) return false;
		if (getEconomyRows() != planeInfo.getEconomyRows()) return false;
		if (getPlacesInBusinessRow() != planeInfo.getPlacesInBusinessRow()) return false;
		if (getPlacesInEconomyRow() != planeInfo.getPlacesInEconomyRow()) return false;
		return getPlaneNameType().equals(planeInfo.getPlaneNameType());
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + getPlaneNameType().hashCode();
		result = 31 * result + getBussinesRows();
		result = 31 * result + getEconomyRows();
		result = 31 * result + getPlacesInBusinessRow();
		result = 31 * result + getPlacesInEconomyRow();
		return result;
	}

	@Override
	public String toString() {
		return "PlaneInfo{" +
				"id=" + id +
				", planeNameType='" + planeNameType + '\'' +
				", bussinesRows=" + bussinesRows +
				", economyRows=" + economyRows +
				", placesInBusinessRow=" + placesInBusinessRow +
				", placesInEconomyRow=" + placesInEconomyRow +
				'}';
	}

	public boolean checkPlace(int placeNumber, int rowNumber, String placeType ) {
		if(placeType.equals("BUSINESS")) {
			if(rowNumber > bussinesRows) return false;
			if(placeNumber > placesInBusinessRow) return false;
		} else {
			if(bussinesRows == 0) {
				if(rowNumber  > ( bussinesRows + 1)) return false;
			}
			if(rowNumber  > (bussinesRows + economyRows)) return false;
			if(placeNumber > placesInEconomyRow ) return false;
		}
		return true;
	}
}
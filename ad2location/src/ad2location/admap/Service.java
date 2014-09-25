package ad2location.admap;

public class Service {
	public String name;
	public String xmlContentMenu;
	public int latitude;
	public int longitude;
	public String type;
	
	public Service(){
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXmlContentMenu() {
		return xmlContentMenu;
	}

	public void setXmlContentMenu(String xmlContentMenu) {
		this.xmlContentMenu = xmlContentMenu;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + latitude;
		result = prime * result + longitude;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((xmlContentMenu == null) ? 0 : xmlContentMenu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Service other = (Service) obj;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (xmlContentMenu == null) {
			if (other.xmlContentMenu != null)
				return false;
		} else if (!xmlContentMenu.equals(other.xmlContentMenu))
			return false;
		return true;
	}
	
	
}

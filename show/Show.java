package show;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "show")
@XmlType(propOrder = { "name", "seasonNumber", "episodeNumber", "description" })
public class Show {

	private String name;
	private int seasonNumber;
	private int episodeNumber;
	private String description;
	
	public Show() {
		this.name = "";
		this.seasonNumber = 0;
		this.episodeNumber = 0;
		this.description = "";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String showName) {
		this.name = showName;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}
	
	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String showDescription) {
		this.description = showDescription;
	}

}
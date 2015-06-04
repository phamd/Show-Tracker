package show;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "shows")
public class ShowList {
	
	@XmlElement(name = "show")
	private ArrayList<Show> showList;
	private JAXBContext context;
	private static final String SHOWLIST_XML = "./showlist.xml";
	
	public ShowList() {
		showList = new ArrayList<Show>();
		try {
			context = JAXBContext.newInstance(ShowList.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Show> getShowList() {
		return showList;
	}
	
	public void addShow(Show show) {
		showList.add(show);
	}
	
	public void removeShow(Show show) {
		showList.remove(show);
	}

	public void clear() {
		showList.clear();
	}
	
	public void save() {
		try { // http://www.vogella.com/tutorials/JAXB/article.html
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    m.marshal(this, System.out); // debug
	    
	    // Write to file
	    m.marshal(this, new File(SHOWLIST_XML));
	    
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}

	public void load() {
		try {
			Unmarshaller um = context.createUnmarshaller();
			ShowList showList = (ShowList) um.unmarshal(new FileReader(SHOWLIST_XML));
			this.showList = showList.getShowList();
		} catch (Exception e) {
			System.out.println("No save file found.");
		}
	}
}

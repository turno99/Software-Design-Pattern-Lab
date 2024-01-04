
import java.util.Collection;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//This Class overrides the DefaultHandler needed by the SaxParser,.
//Since we have only XML elements with no nested elements in it, we can just override the first method 

public class XMLParser5_7 extends DefaultHandler{

    private final Collection<ConfigItem5_7> itemList;

    public XMLParser5_7(Collection<ConfigItem5_7> itemList)
    {
        this.itemList = itemList;
    }
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		ConfigItem5_7 item = new ConfigItem5_7(qName);
        int len = attributes.getLength();
        for(int i=0;i <len;i++)
        {
            String key = attributes.getQName(i);
            String val = attributes.getValue(i);
            item.addProperty(key,val);
        }
        itemList.add(item);
	}

    
}

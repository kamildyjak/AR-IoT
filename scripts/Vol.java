package project;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Vol extends VolThing {

    public void addInformation(String key, String val) {
        information.put(key, val);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void display() {
        Set set = information.entrySet();
        Iterator iterator = set.iterator();
        System.out.println("Vol id = " + id);
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("\t" + mentry.getKey() + " : " + mentry.getValue());
        }
    }

}

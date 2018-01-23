package project;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Raid extends RaidThing {

    public void addInformation(String key, String val) {
        information.put(key, val);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void display() {
        Set set = information.entrySet();
        Iterator iterator = set.iterator();
        System.out.println("Raid id = " + id);
        while (iterator.hasNext()) {
            Map.Entry info = (Map.Entry) iterator.next();
            System.out.println("\t" + info.getKey() + " : " + info.getValue());
        }
    }

}

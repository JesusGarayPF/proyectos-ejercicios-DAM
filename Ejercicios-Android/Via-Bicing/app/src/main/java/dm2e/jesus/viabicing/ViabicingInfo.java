package dm2e.jesus.viabicing;

import java.util.ArrayList;
import java.util.List;

public class ViabicingInfo {
    public void addStation(Station s) {
        stations.add(s);
    }
    List<Station> stations = new ArrayList<Station>();
    int timestamp = -1;
}

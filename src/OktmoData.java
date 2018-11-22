import java.util.*;

public class OktmoData {
    private HashSet<String> allStatuses = new HashSet<String>();

    Map<Long, OKTMOGroup> oktmoGroupMap = new HashMap<>();
    Map<Long,Place> npMap = new TreeMap<Long, Place>();

    public void addParentGroup (OKTMOGroup gr1) {
        try {
            oktmoGroupMap.get(gr1.parentCode).arrGroup.add(gr1);
        }
        catch (NullPointerException ex) {
            //System.err.println(ex.getMessage());
        }
    }

    public Map<Long, OKTMOGroup> getOktmoGroupMap() {
        return oktmoGroupMap;
    }


    public void addGroup (long code, String name) {
        OKTMOGroup oktomogroup1 = new OKTMOGroup(name,code);
        oktmoGroupMap.put((Long)code,oktomogroup1);
        addParentGroup(oktomogroup1);
    }


}
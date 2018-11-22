import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OKTMO_test_lab3 {
    @Test
    public void test() {
        OktmoReader ok3 = new OktmoReader();
        OktmoData dat3 = new OktmoData();
        long t0 = System.currentTimeMillis();
        ok3.readPlaces_lab3("data-201710.csv", dat3, 10);
        double countGr  =
                dat3.oktmoGroupMap.entrySet()
                        .stream()
                        .filter(p -> p.getValue().getParentCode()==99630425000L)
                        .collect(Collectors.counting());
        //System.out.println(averageAge);
        double sumGr  =
                dat3.oktmoGroupMap.entrySet()
                        .stream()
                        .filter(p -> p.getKey()==99630425000L)
                        .collect(Collectors.summingDouble(p->p.getValue().arrGroup.size()));
        //System.out.println(averageAge2);
        assertEquals(countGr,sumGr,0);
        // List<String> strList  =
        dat3.oktmoGroupMap.entrySet()
                .stream()
                .filter(p -> p.getKey()==99630425000L)
                //.collect(Collectors.(p->p.getValue().arrGroup.size()));

                .forEach(x -> {
                    assertTrue(x.getValue().getArrGroup().contains("Даниловка"));
//                    System.out.println( "; code:" + x.getValue().getCode() + "; Name:" + x.getValue().getName() + ";  " +
//                            "Status:" + x.getValue().getLevel() + " parent: " + x.getValue().getParentCode() +
//                            "; Вложенные группы [" + x.getValue().getArrGroup() + " ]");
                });
        //.collect(Collectors.summingDouble(p->p.getValue().arrGroup.size()));
        //System.out.println(averageAge);
    }
}

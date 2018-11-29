import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OktmoAnalyzer {

    public static List<OKTMOGroup> findAllPlacesInGroup1(OKTMOGroup group, OktmoData data3) {
        SortedMap<Long, OKTMOGroup> tmpTreemap = new TreeMap<Long, OKTMOGroup>();
        tmpTreemap = data3.oktmoGroupMap.subMap(group.getCode(), group.endCode());
        List<OKTMOGroup> lst= //= new ArrayList<OKTMOGroup>();
        tmpTreemap.values()
                .stream()
                .collect(Collectors.toList());
        return lst;
    }

    static class Counter implements Comparable<Counter>{
        int count=1;
        void add() {count++;}

        @Override
        public int compareTo(Counter o) {
            return Integer.compare(count,o.count);
        }

        @Override
        public String toString() {
            return String.valueOf(count);
        }
    }

    public static void findMostPopularPlaceName1(OKTMOGroup group, OktmoData data3) {
        List<OKTMOGroup> lst = findAllPlacesInGroup1(group,data3);
        Map<String, Counter> np = new TreeMap<String, Counter>();
        lst.stream()
                .map(x-> x.getName())
                .forEach(item->{
                    Counter value = np.get(item);
                    if (value == null)
                        np.put(item, new Counter());
                    else
                        value.add();
                });
        np.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                // np.entrySet().stream().sorted((a,b)->a.getValue().compareTo(b.getValue()))
                .forEach(System.out::println);
    }

    public static void printStatusTableForRegion(String region,OktmoData data){


        data.oktmoGroupMap.entrySet().stream()
                .filter(p->p.getValue().getLevel()== OKTMOGroup.OKTMOLevel.REGION)
                .filter(p->p.getValue().getName().contains(region))
             //   .forEach(p-> System.out.println(p.getValue().toString()));
                .peek(p-> System.out.println(p.getValue().getCode() + " " + p.getValue().getName()/* + " " + p.getValue().getLevel()  + " "+ p.getValue().endCode() */))
                .map(x->findAllPlacesInGroup1(x.getValue(),data))

                .forEach(x->{
                    x.stream()

                                    .collect(Collectors.groupingBy(e -> e.getLevel(), Collectors.counting()))
                                    .entrySet().stream()
                                        .sorted(Comparator.comparing(Map.Entry::getValue))
                                        .forEach(g-> System.out.println( g.getKey() + " " + g.getValue()));

                });
    }
}



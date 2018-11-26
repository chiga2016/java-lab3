import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OktmoAnalyzer {

    public static List<String> findAllPlacesInGroup(OKTMOGroup group, OktmoData data3) {
        SortedMap<Long, OKTMOGroup> tmpTreemap = new TreeMap<Long, OKTMOGroup>();
        tmpTreemap = data3.oktmoGroupMap.subMap(group.getCode(), group.endCode());
        List<String> lst = new ArrayList<String>();
        tmpTreemap.values()
                .stream()
                .forEach(s -> {
                   lst.add(s.getName());
                   //  System.out.println(s.getName() + " " + s.getLevel() +" " + s.getCode());
                });
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

    public static void findMostPopularPlaceName(OKTMOGroup group, OktmoData data3) {
        List<String> lst = findAllPlacesInGroup(group,data3);
       // Map<String, Integer> countMap = new HashMap<>();
//        Map<String,Long> np = new TreeMap<>();
//                lst.stream()
//        .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
//        .forEach({p -> System.out.println(p)
//        });
//
        //map.entrySet().stream()
        //.sorted(Comparator.comparing(p -> p.getValue()))

        Map<String, Counter> np = new TreeMap<String, Counter>();
        lst.stream()
        .forEach(item->{
                Counter value = np.get(item);
                if (value == null)
                    np.put(item, new Counter());
                else
                    value.add();
        });
//Comparator.
        np.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
       // np.entrySet().stream().sorted((a,b)->a.getValue().compareTo(b.getValue()))
                .forEach(System.out::println);
        }

    public static void printStatusTableForRegion(String region,OktmoData data){
        List<OKTMOGroup> printList = new ArrayList<>();
        printList=
        data.oktmoGroupMap.entrySet().stream()
                .filter(p->p.getValue().getName().contains(region))
                .flatMap(
                        x-> (findAllPlacesInGroup(x.getValue(),data))
                )
                .
                //.map(a->findAllPlacesInGroup(a.getValue(),data))
                //.forEach(p->{
                   // findAllPlacesInGroup(p.getValue(),data).stream()
                  //  .collect(Collectors.groupingBy(f->f,Collectors.counting()))
                         //   .forEach(c-> {System.out.println(c.toString()); });
                   // System.out.println(p.toString());
                     //    .forEach(s-> System.out.println(s));
                    //findAllPlacesInGroup(p.getValue());
                //});

                //.forEach(p-> System.out.println(p.getValue().getName()));


    }


}



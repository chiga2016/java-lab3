import java.time.YearMonth;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class OktmoMain {

    public static void main(String[] args) {
        OktmoReader okt3 = new OktmoReader();
        OktmoData data3  =new OktmoData();
        long t0 = System.currentTimeMillis();
        okt3.readPlaces_lab3("data-201710.csv",data3,10);
        long t1 = System.currentTimeMillis();
        AtomicInteger k= new AtomicInteger();
//int k=0;
//
//        ////// Places по 3 лабе
//     for (Map.Entry<Long,Place> pair:data3.npMap.entrySet())
//     {
//         k++;
//         System.out.println(k + " Code: " +pair.getKey()+"; Name: " + pair.getValue().getName()+"; Status: "  + pair.getValue().getStatus());
//     }

        //// OKTMOGroup по 3 лабе
//     for (Map.Entry<Long,OKTMOGroup> pair:data3.oktmoGroupMap.entrySet())
//     {
//         k++;
//         System.out.println(k + "; code:" +pair.getKey()+"; Name:" + pair.getValue().getName()+";  Level:"  + pair.getValue().getLevel());
//     }

//        data3.oktmoGroupMap.values().stream().forEach( x-> {
//            k.getAndIncrement();
//         System.out.println(k + "; code:" +x.getCode()+"; Name:" + x.getName()+";  Level:"  + x.getLevel());
//        } );
       // Map<Long,OKTMOGroup> map =
                data3.npMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(new PlaceComparator()))
                //.filter(s -> s.getValue().getName().startsWith("Иль"))

                .collect(Collectors.groupingBy(e -> e.getKey(), Collectors.counting()));

                .forEach(x -> {
                    k.getAndIncrement();
                    System.out.println(k + "; code:" + x. + "; Name:" + x.getValue().getName() + ";  Status:" + x.getValue().getStatus());
                });
     //   map.forEach((k,v)-> v.forEach((a,b)-> System.out.println(k + " " +  a + " " + b)));
        //   Map<Long, OKTMOGroup> parentGroup =

        /// Вложенные группы
//        data3.oktmoGroupMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey())
//                // .limit(100)
//                // .filter(s -> s.getValue().getName().startsWith("Иль"))
//                //.collect(Collectors.groupingBy(p -> pgetValue().getParentCode()));
//                .forEach(x -> {
//                    k.getAndIncrement();
//                    System.out.println(k + "; code:" + x.getValue().getCode() + "; Name:" + x.getValue().getName() + ";  " +
//                            "Status:" + x.getValue().getLevel() + " parent: " + x.getValue().getParentCode() +
//                            "; Вложенные группы [" + x.getValue().getArrGroup() + " ]");
//                });


    }
}

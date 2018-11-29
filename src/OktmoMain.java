import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OktmoMain {

    public static void main(String[] args) {
        OktmoReader okt3 = new OktmoReader();
        OktmoData data3  =new OktmoData();
        long t0 = System.currentTimeMillis();
        okt3.readPlaces_lab3("data-201710.csv",data3);
        long t1 = System.currentTimeMillis();
        AtomicInteger k= new AtomicInteger();

        System.out.println();

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
// ИЛИ

//       // Вложенные группы
//        data3.oktmoGroupMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEach(x -> {
//                    k.getAndIncrement();
//                    System.out.println(k + "; code:" + x.getValue().getCode() + "; Name:" + x.getValue().getName() + ";  " +
//                            "Status:" + x.getValue().getLevel() + " parent: " + x.getValue().getParentCode() +
//                            "; Вложенные группы [" + x.getValue().getArrGroup() + " ]");
//                });


////findAllPlacesInGroup
//        OKTMOGroup okt2 = new OKTMOGroup("Башкортостан",80600000000L);
//        OktmoAnalyzer.findAllPlacesInGroup1(okt2,data3).stream()
//        .forEach(s-> {
//            k.getAndIncrement();
//            System.out.println(k + " "+ s.getName() + " " + s.getCode() + " ");
//
//        });
//// findMostPopularPlaceName
//        OKTMOGroup okt2 = new OKTMOGroup("Башкортостан",80600000000L);
//        OktmoAnalyzer.findMostPopularPlaceName1(okt2,data3);

////printStatusTableForRegion
        //Муниципальные районы Республики Башкортостан
        OktmoAnalyzer.printStatusTableForRegion("Краснодар",data3);
     //.forEach(s-> System.out.println(s.getKey()+" "+s.getValue().getParentCode() +" " + s.getValue().getName() + " " + s.getValue().getLevel() ));


    }




}

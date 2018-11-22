import java.util.*;
import java.util.stream.Collectors;

public class OKTMOGroup {
    private String name;
    private long code;
    long parentCode;
    private enum OKTMOLevel {REGION, RAYON_CITY, SEL_MO, NP}
    List<OKTMOGroup> arrGroup = new ArrayList<OKTMOGroup>() ;
    //KRAI,OBLAST,AVTO_OBLAST, AVTO_OKRUG,REPUBLIC,, CITY_AREAL,CITY_OKRUG, PGT, SEL_P
    protected OKTMOLevel level ;

    public OKTMOGroup(String name,long code) {
        this.name = name;
        this.code = code;
        this.level = defineLevel(code);
        this.parentCode = parentCode(code);
    }



    public void setParentCode(long parentCode) {
        this.parentCode = parentCode;
    }

    public long getParentCode() {
        return parentCode;
    }

    public long parentCode (long code){
        if (defineLevel(code) == OKTMOLevel.SEL_MO) {
            return (long) ((int) (code / 1000000)) * 1000000;
        }
        if (defineLevel(code) == OKTMOLevel.RAYON_CITY) {
            return (long) ((int) (code / 1000000000)) * 1000000000;
        }
        if (defineLevel(code) == OKTMOLevel.NP) {
            return (long) ((int) (code / 1000)) * 1000;
        }

        return 0;
    }
    public String getArrGroup() {
        String tostring = arrGroup
                .stream().map(p-> p.getName()).collect(Collectors.joining(";"));
        return tostring;
    }

    public static int countZero (long code) {
        int countZero=0;
        for(int i=0;i<12;i++) {
            if (code % 10 == 0) {
                countZero++;
                code = code / 10;
            } else {
                break;
            }
        }
        return countZero;
    }

    public static OKTMOLevel defineLevel (long code) {
        OKTMOLevel level = null;
        if (countZero(code)<3 ) {
            level = OKTMOLevel.NP;
        }

        if (countZero(code)>=3 && countZero(code)<6) {
            level = OKTMOLevel.SEL_MO;
        }
        else if (countZero(code)>=6 && countZero(code)<9 ) {
            level = OKTMOLevel.RAYON_CITY;
        }
        else if (countZero(code)==9) {
            level = OKTMOLevel.REGION;
        }
        return level;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setLevel(OKTMOLevel level) {
        this.level = level;
    }

    public OKTMOLevel getLevel() {
        return level;
    }
}

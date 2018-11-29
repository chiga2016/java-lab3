import java.util.*;
import java.util.stream.Collectors;

public class OKTMOGroup {
    private String name;
    private long code;
    private long parentCode;
     enum OKTMOLevel {REGION, RAYON_CITY, SEL_MO, NP}
    protected List<OKTMOGroup> arrGroup = new ArrayList<OKTMOGroup>() ;
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
//80 643 455 111
        final OKTMOLevel oktmoLevel = defineLevel(code);
        if (oktmoLevel == OKTMOLevel.REGION) {
            return 0L;
        }

        if (oktmoLevel == OKTMOLevel.RAYON_CITY) {
            return (long) ( code / 1000000000) * 1000000000;
        }
        if (oktmoLevel == OKTMOLevel.SEL_MO) {
            return (long) ( code / 1000000 ) * 1000000;
        }
        if (oktmoLevel == OKTMOLevel.NP) {
            return (long) (code / 1000) * 1000;
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
        int zeros = countZero(code);
        if (zeros <3 ) {
            level = OKTMOLevel.NP;
        }

        if (zeros >=3 && zeros <6) {
            level = OKTMOLevel.SEL_MO;
        }
        else if (zeros >=6 && zeros <9 ) {
            level = OKTMOLevel.RAYON_CITY;
        }
        else if (zeros >=9) {
            level = OKTMOLevel.REGION;
        }
        return level;
    }

    public long endCode() {
        long endCode=0L;
        if (this.getLevel().equals(OKTMOLevel.REGION))
        { endCode = this.getCode() + 1000000000;}
        if (this.getLevel()== OKTMOLevel.RAYON_CITY)
        { endCode = this.getCode() + 1000000;}
        if (this.getLevel()== OKTMOLevel.SEL_MO)
        {endCode = this.getCode() + 1000;}
//        int countZero = countZero(this.getCode());
//        String nineString = "";
//        long nineLong = 0;
//        long endCode;
//        for (int i = 0; i < countZero; i++) {
//            nineString = nineString + 9;
//        }
//        nineLong = Long.parseLong(nineString);
//        endCode = this.getCode() + nineLong;
        return endCode;
    }

    @Override public String  toString() {
        return (getCode() + " " + getName() + " " + getLevel());
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

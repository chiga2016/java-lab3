import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoReader {
    protected long code;
    protected String status;
    protected String name;
    //private static final //,Pattern.UNICODE_CASE+Pattern.CASE_INSENSITIVE); //(.+)(\1)$
    int maxStr = 0;


    public void readPlaces_lab3(String fileName, OktmoData data, int maxStr) {
        int lineCount = 0;
        Pattern codePatch = Pattern.compile("(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"([^;]*)\";\"([^;]*)\";\"");
        Pattern statusPattern = Pattern.compile("(.[^А-Я]+)");

        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(fileName)
                                , "cp1251")
                        // читаем файл из двоичного потока
                        // в виде текста с нужной кодировкой
                )
        ) {
            String s;
            while ((s = br.readLine()) != null) { // пока readLine() возвращает не null
                status = null;
                name = null;
                code = 0;

                Matcher m = codePatch.matcher(s);
                m.find();
                //System.out.println(m.groupCount());
                code = Long.parseLong(m.group(1) + m.group(2) + m.group(3) + m.group(4));
                //String name = (m.group(8) + " " + m.group(7)).trim();
                name = m.group(7);

                if (!(name.startsWith("Населенные пункты, входящие в состав") || name.startsWith("Городские поселения") || name.startsWith("Межселенные территории"))) {
                    lineCount++;
                    if (OKTMOGroup.countZero(code) < 3) {
                        if (name != null) {
                            Matcher mStatus = statusPattern.matcher(name);
                            mStatus.find();
                            status = mStatus.group(1);
                        }
                        if (status != null) {
                            name = name.substring(status.length());
                        } else {
                            name = name;
                        }

                        Place place = new Place(code, name, status);
                        data.npMap.put(code, place);
                        data.addGroup(code, name);
                    } else data.addGroup(code, name);
                }
                // if (lineCount==maxStr) break; // пример частичного чтения первых 20 строк
            }
        } catch (IOException ex) {
            System.out.println("Reading error in line " + lineCount);
            ex.printStackTrace();
        }

    }

}

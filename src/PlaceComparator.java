import java.util.Comparator;

public class PlaceComparator implements Comparator<Place> {
    @Override
    public int compare(Place obj1, Place obj2) {

        return obj1.getName().toLowerCase().compareTo(obj2.getName().toLowerCase());
    }
}

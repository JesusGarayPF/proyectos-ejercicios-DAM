package dm2e.jesus.viabicing;

public class Station {
    public int id;
    public String street;
    public String streetNumber; // Cadena; a veces no hay ("")
    public String slots;
    public String bikes;
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(": ");
        sb.append(street).append(", no ").append(streetNumber);
        sb.append("; ").append(slots).append(" slots; ");
        sb.append(bikes).append(" bikes");
        return sb.toString();
    }
}

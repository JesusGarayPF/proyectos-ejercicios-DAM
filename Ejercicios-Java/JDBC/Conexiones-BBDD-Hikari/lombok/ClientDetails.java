package tema_02.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class ClientDetails {

    private final int customerId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final boolean active;
    private final Timestamp lastUpdate;
    private final String address;
    private final String address2;
    private final String district;
    private final String city;
    private final String country;

    public String getFullName(){
        return String.format("%s %s",firstName,lastName);
    }
    public String getFullAdrress (){
        return  String.format("%s %s",address,address2);
    }

    @Override
    public String toString() {
      return String.format("""
    +-----------------------------------------------------------------------------------------------------------------------------------------------------------+
    | Nombre y apellido: %-15s | Email: %-25s | Activo: %-5b | Ultima Actualización: %-21s |
    +-----------------------------------------------------------------------------------------------------------------------------------------------------------+
    | Dirección: %-15s | Pais: %-10s | Ciudad: %-10s | Distrito: %-10s |
    +---------------------------------------------------------------------------------------- ------------------------------------------------------------------+
    """,getFullName(), getEmail(), active, getLastUpdate().toString(), getFullAdrress(),getCountry(), getDistrict(), getCity());
    }
}

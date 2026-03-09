package conexion;

import entities.Category;
import entities.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class pruebaConexion {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("sakila");
        EntityManager manager = factory.createEntityManager()){
            Category category = manager.find(Category.class, 1L);
            if(category != null){
                System.out.println(category);
            }else{
                System.out.println("No se puede obtener la categoria con id 1l");
            }
            City city = manager.find(City.class, 1L);
            System.out.println(city.getName());
            System.out.println(city.getCountry().getName());

            City city2 = manager.find(City.class, 1L);
            System.out.println(city2.getName());
        }
    }
}

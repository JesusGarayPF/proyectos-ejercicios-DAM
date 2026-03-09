package program;

import application.CustomerTests;
import application.EmployeeTests;
import application.OrderTests;
import application.ProductTests;
import dataaccess.CustomerDataAccessImpl;
import dataaccess.EmployeeDataAccessImpl;
import dataaccess.OrderDataAccessImpl;
import dataaccess.ProductDataAccessImpl;
import dto.CreateOrderDto;
import dto.OrderDetailDto;
import entities.Customer;
import entities.Employee;
import entities.Order;
import entities.Product;
import services.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Main con la lógica principal del programa en el que mediante un flag
     * se controla el bucle de la selección elegida por el usuario y una vez
     * que el usuario ha elegido una opción en el primer menú, no se volverá
     * a utilizar el primer menú hasta que el usuario introduzca el valor 7.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    public static void main(String[] args) throws SQLException {
        boolean continuar = false;
        while (!continuar) {
            try {
                int entidad = mostrarMenu();

                if (entidad != 5) {
                    if(entidad<1 || entidad>5){
                        throw new Error("Numero fuera del rango");
                    }
                    int metodo = mostrarSegundoMenu(entidad);
                    while (metodo != 7) {
                        metodo = mostrarSegundoMenu(entidad);
                    }
                } else {
                    continuar = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Por favor, introduzca un número.");
            } catch (InputMismatchException e) {
                System.out.println("Error: El tipo de dato introducido no es válido.");
            } catch (Exception | Error e) {
               System.out.println("Se ha producido un error: " + e.getMessage());
            }
        }
    }

    /**
     * Método que muestra el menú al usuario para que elija con que entidad de la tabla desea trabajar
     *
     * @return la entidad elegida por el usuario
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    private static int mostrarMenu() throws SQLException {
        System.out.println("""
                Elija la entidad a probar:
                1-Customer
                2-Employee
                3-Order
                4-Product
                5-Salir
                """);

        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método que muestra un segundo menú al usuario para realizar la operación que desee con
     * la operación anteriormente elegida
     *
     * @param entidad la entidad que ha elegido antes el usuario para trabajar con ella
     * @return una de las opciones que quiere realizar
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    private static int mostrarSegundoMenu(int entidad) throws SQLException {
        System.out.println("""
                Elija la operación a realizar:
                1-Contar filas
                2-Comprobar existencia (por id)
                3-Buscar por id
                4-Ver tabla
                5-Guardar/Crear
                6-Borrar
                7-Salir
                """);
        int metodo = Integer.parseInt(sc.nextLine());

        if (entidad == 1) {
            CustomerTests customerTests = new CustomerTests(new CustomerServiceImpl(new CustomerDataAccessImpl()));
            int customerNumber;
            switch (metodo) {
                case 1:
                    long numClientes = customerTests.count();
                    System.out.println("Hay " + numClientes + " clientes");
                    break;
                case 2:
                    System.out.println("Introduce el id");
                    customerNumber = Integer.parseInt(sc.nextLine());
                    if (customerTests.existById(customerNumber)) {
                        System.out.println("El cliente con id " + customerNumber + " existe");
                    } else {
                        System.out.println("El cliente con id " + customerNumber + " no existe");
                    }
                    break;
                case 3:
                    System.out.println("Introduzca el id del cliente que quiere ver");
                    customerNumber = Integer.parseInt(sc.nextLine());
                    if (customerTests.existById(customerNumber)) {
                        System.out.println(customerTests.findById(customerNumber));
                    } else {
                        System.out.println("El cliente con id " + customerNumber + " no existe");
                    }
                    break;
                case 4:
                    System.out.println("Todos los clientes");
                    List<Customer> listaClientes = customerTests.findAll();
                    for (Customer listaCliente : listaClientes) {
                        System.out.println(listaCliente);
                    }
                    break;
                case 5:
                    customerTests.testSaveCustomer(crearCliente());
                    break;
                case 6:
                    System.out.println("Introduzca el id del cliente que quiere borrar");
                    customerNumber = Integer.parseInt(sc.nextLine());
                    customerTests.deleteById(customerNumber);
                    break;

            }
        } else if (entidad == 2) {
            EmployeeTests employeeTests = new EmployeeTests(new EmployeeServiceImpl(new EmployeeDataAccessImpl()));
            int employeeNumber;
            switch (metodo) {
                case 1:
                    System.out.printf("Hay %d empleados\n", employeeTests.count());
                    break;
                case 2:
                    System.out.println("Introduce el id");
                    employeeNumber = Integer.parseInt(sc.nextLine());
                    if (employeeTests.existById(employeeNumber)) {
                        System.out.println("El cliente con id " + employeeNumber + " existe");
                    } else {
                        System.out.println("El cliente con id " + employeeNumber + " no existe");
                    }
                    break;
                case 3:
                    System.out.println("Introduce el id");
                    employeeNumber = Integer.parseInt(sc.nextLine());
                    if (employeeTests.findById(employeeNumber).equals(Optional.empty())) {
                        System.out.println("El empleado con id " + employeeNumber + " no existe");
                    } else {
                        System.out.println(employeeTests.findById(employeeNumber));
                    }
                    break;
                case 4:
                    List<Employee> listaEmpleados = employeeTests.findAll();
                    for (Employee empleado : listaEmpleados) {
                        System.out.println(empleado);
                    }
                    break;
                case 5:
                    employeeTests.testSaveEmployee(crearEmpleado());
                    break;
                case 6:
                    System.out.println("Introduce el id del cliente que quiere borrar");
                    employeeNumber = Integer.parseInt(sc.nextLine());
                    employeeTests.deleteById(employeeNumber);
                    break;


            }
        } else if (entidad == 3) {
            OrderTests orderTests = new OrderTests(new OrderServiceImpl(new OrderDataAccessImpl(new ProductDataAccessImpl())));
            switch (metodo) {
                case 1:
                    System.out.printf("Hay %s ordenes\n", orderTests.count());
                    break;

                case 2:
                    System.out.println("Introduce el id");
                    String existsId = sc.nextLine();
                    if (orderTests.existById(Integer.parseInt(existsId))) {
                        System.out.println("La orden con id " + existsId + " existe");
                    } else {
                        System.out.println("La orden con id " + existsId + " no existe");
                    }
                    break;

                case 3:
                    System.out.println("Introduce el id");
                    String findId = sc.nextLine();
                    if (orderTests.findById(Integer.parseInt(findId)).equals(Optional.empty())) {
                        System.out.println("El producto con id " + findId + " no existe");
                    } else {
                        System.out.println(orderTests.findById(Integer.parseInt(findId)));
                    }
                    break;

                case 4:
                    List<Order> orderList = orderTests.findAll();
                    for (Order order : orderList) {
                        System.out.println(order);
                    }
                    break;

                case 5:
                    orderTests.testCreateOrder(crearOrden());
                    break;

                case 6:
                    System.out.println("Introduce el id");
                    String deleteId = sc.nextLine();
                    if (!orderTests.existById(Integer.parseInt(deleteId))) {
                        System.out.println("El producto con id " + deleteId + " no existe");
                    } else {
                        orderTests.deleteById(Integer.parseInt(deleteId));
                    }
                    break;
            }
        } else if (entidad == 4) {
            ProductTests productTests = new ProductTests(new ProductServiceImpl(new ProductDataAccessImpl()));
            switch (metodo) {
                case 1:
                    System.out.printf("Hay %s productos\n", productTests.count());
                    break;

                case 2:
                    System.out.println("Introduce el id");
                    String existsId = sc.nextLine();
                    if (productTests.existsById(existsId)) {
                        System.out.println("El producto con id " + existsId + " existe");
                    } else {
                        System.out.println("El producto con id " + existsId + " no existe");
                    }
                    break;

                case 3:
                    System.out.println("Introduce el id");
                    String findId = sc.nextLine();
                    if (productTests.findById(findId).equals(Optional.empty())) {
                        System.out.println("El producto con id " + findId + " no existe");
                    } else {
                        System.out.println(productTests.findById(findId));
                    }
                    break;

                case 4:
                    List<Product> productList = productTests.findAll();
                    for (Product product : productList) {
                        System.out.println(product);
                    }
                    break;
                case 5:
                    productTests.save(crearProducto());
                    break;

                case 6:
                    System.out.println("Introduce el id");
                    String deleteId = sc.nextLine();
                    if (!productTests.existsById(deleteId)) {
                        System.out.println("El producto con id " + deleteId + " no existe");
                    } else {
                        productTests.deleteById(deleteId);
                    }
                    break;
            }
        }
        return metodo;
    }

    /**
     * Método para crear un objeto empleado por medio de información que se pedirá al usuario
     *
     * @return un objeto de tipo {@link Employee}
     */
    private static Employee crearEmpleado() {
        System.out.println("Introduce el employeeNumber del empleado");
        int employeeNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el lastName del empleado");
        String lastName = sc.nextLine();
        System.out.println("Introduce la firstName del empleado");
        String firstName = sc.nextLine();
        System.out.println("Introduce la extension del empleado");
        String extension = sc.nextLine();
        System.out.println("Introduce el email del empleado");
        String email = sc.nextLine();
        System.out.println("Introduce el officeCode del empleado");
        String officeCode = sc.nextLine();
        System.out.println("Introduce el reportsTo del empleado");
        int reportsTo = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el jobTitle del empleado");
        String jobTittle = sc.nextLine();

        return new Employee(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTittle);
    }

    /**
     * Método para crear un objeto producto por medio de información que se pedirá al usuario
     *
     * @return un objeto de tipo {@link Product}
     */
    private static Product crearProducto() {
        System.out.println("Introduce el productCode del producto");
        String productCode = sc.nextLine();
        System.out.println("Introduce el productName del producto");
        String productName = sc.nextLine();
        System.out.println("Introduce la productLine del producto");
        String productLine = sc.nextLine();
        System.out.println("Introduce el productScale del producto");
        String productScale = sc.nextLine();
        System.out.println("Introduce el productVendor del producto");
        String productVendor = sc.nextLine();
        System.out.println("Introduce el productDescription del producto");
        String productDescription = sc.nextLine();
        System.out.println("Introduce el quantityInStock del producto");
        int quantityInStock = sc.nextInt();
        System.out.println("Introduce el buyPrice del producto");
        double buyPrice = sc.nextDouble();
        System.out.println("Introduce el mSRP del producto");
        double mSRP = sc.nextDouble();
        return new Product(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, mSRP);
    }

    /**
     * Método para crear un objeto orden por medio de información que se pedirá al usuario
     *
     * @return un objeto de tipo {@link CreateOrderDto}
     */
    private static CreateOrderDto crearOrden() {
        System.out.println("Introduce la requiredDate");
        LocalDate requiredDate = LocalDate.parse(sc.nextLine());
        System.out.println("Introduce los comments");
        String comments = sc.nextLine();
        System.out.println("Introduce el customerNumber");
        int customerNumber = Integer.parseInt(sc.nextLine());
        boolean orderDetailTerminado = true;
        System.out.println("Para generar la lista de productos de tu orden introduce los siguientes datos ");
        List<OrderDetailDto> pedido = new ArrayList<>();
        while (orderDetailTerminado) {
            System.out.println("Introduce el productCode");
            String productCode = sc.nextLine();
            System.out.println("Introduce la quantityOrdered");
            int quantityOrdered = Integer.parseInt(sc.nextLine());
            pedido.add(new OrderDetailDto(productCode, quantityOrdered));
            System.out.println("1-Añadir otro\n2-Terminar pedido");
            if (sc.nextInt() == 2) orderDetailTerminado = false;
        }
        return new CreateOrderDto(requiredDate, comments, customerNumber, pedido);
    }

    /**
     * Método para crear un objeto cliente por medio de información que se pedirá al usuario
     *
     * @return un objeto de tipo {@link Customer}
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    private static Customer crearCliente() throws SQLException {
        System.out.println("Introduzca customerNumber ");
        int customNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Introduzca customerName");
        String customerName = sc.nextLine();
        System.out.println("Introduzca contactLastName");
        String contactLastName = sc.nextLine();
        System.out.println("Introduzca contactFirstName");
        String contactFirstName = sc.nextLine();
        System.out.println("Introduzca phone");
        String phone = sc.nextLine();
        System.out.println("Introduzca addressLine1");
        String addressLine1 = sc.nextLine();
        System.out.println("Introduzca addressLine2");
        String addressLine2 = sc.nextLine();
        System.out.println("Introduzca city");
        String city = sc.nextLine();
        System.out.println("Introduzca state ");
        String state = sc.nextLine();
        System.out.println("Introduzca postalCode");
        String postalCode = sc.nextLine();
        System.out.println("Introduzca country");
        String country = sc.nextLine();
        System.out.println("Introduzca salesRepEmployeeNumber");
        int salesRepEmployeeNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Introduzca el creditLimit");
        float creditLimit = Float.parseFloat(sc.nextLine());

        return new Customer(customNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit);
    }
}

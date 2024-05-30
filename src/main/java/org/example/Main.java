package org.example;

import org.example.Util.UtilEntity;
import org.example.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManager em = UtilEntity.getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e from Employee e",Employee.class).getResultList();
        System.out.println("----------Listar todos----------");
        employees.forEach(System.out::println);

        System.out.println("-----Buscar uno-------");
        int employeeId = 3;
        Employee employee = em.find(Employee.class, employeeId);
        System.out.println("Empleado encontrado" + employee);

        System.out.println("---Crear uno------");
        Employee newEmployee = new Employee();
        newEmployee.setFirstName("Diego");
        newEmployee.setPaSurname("Lechuga");
        newEmployee.setMaSurname("Pimentel");
        newEmployee.setEmail("diego@example.com");
        newEmployee.setSalary(890000F);

        em.getTransaction().begin();
        em.persist(newEmployee);
        em.getTransaction().commit();

        System.out.println("Nuevo empleado creado");

        System.out.println("-----Actualizar-----");
        int employeeToUpdateId = 2 ;
        Employee employeeToUpdate = em.find(Employee.class,employeeToUpdateId);
        System.out.println("Empleado a modificar" + employeeToUpdate);

        employeeToUpdate.setFirstName("Irving");
        employeeToUpdate.setPaSurname("Juarez");
        employeeToUpdate.setSalary(97000F);

        em.getTransaction().begin();
        em.merge(employeeToUpdate);
        em.getTransaction().commit();

        System.out.println("Empleado actualizado" + employeeToUpdate);

        System.out.println("------Eliminar-----");
        int employeeToDeleteId = 1;
        Employee employeeToDelete = em.find(Employee.class,employeeToDeleteId);
        System.out.println("Empleado a eliminar" + employeeToDelete);

        em.getTransaction().begin();
        em.remove(employeeToDelete);
        em.getTransaction().commit();
        em.close();


    }
}
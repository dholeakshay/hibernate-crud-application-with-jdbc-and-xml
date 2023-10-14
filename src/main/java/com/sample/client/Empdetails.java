package com.sample.client;

import com.sample.entities.Employee;
import com.sample.util.session_factory;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Date;

public class Empdetails {
    public static void main(String[] args) {

        try (Session session = session_factory.getSessionFactory().openSession()) {
            createEmployeeInDB(session);

            int employeeId = 3;
            double newsal = 85000;
            String newempname = "akash";

            //reademployeeById(session,employeeId);

            //updateemployeedetailsById(session,newsal,newempname,employeeId);

            //deleteemployeeById(session,employeeId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session_factory.getSessionFactory().close();
        }

    }

    //delete employee details
    private static void deleteemployeeById(Session session, int employeeId) {
        Employee employee = session.get(Employee.class, employeeId);

        if (employee != null) {
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        } else {
            System.out.println("employee does not exist with id " + employeeId);
        }
    }

    //update employee details

    private static void updateemployeedetailsById(Session session, double newsal, String newempname, int employeeId) {
        Employee employee = session.get(Employee.class, employeeId);
        employee.setSalary(newsal);
        employee.setEmployeeName(newempname);

        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
    }

    //Read employee details
    private static void reademployeeById(Session session, int employeeId) {
        Employee employee = session.get(Employee.class, employeeId);
        System.out.println(employee);
    }

    //create employee table in database
    private static void createEmployeeInDB(Session session) {
        Employee employee = createEmployee();
        session.beginTransaction();

        Serializable serializable = session.save(employee);
        Integer id = (Integer) serializable;

        session.getTransaction().commit();
        System.out.println("Employee is created with Id" + id);
    }


    //employee details
    private static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Akshay");
        employee.setEmail("Akshay@gmail.com");
        employee.setDoj(new Date());
        employee.setSalary(7000);
        return employee;
    }

}

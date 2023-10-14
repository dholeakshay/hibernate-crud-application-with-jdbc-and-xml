package com.sample.util;

import com.sample.entities.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class session_factory {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {
        try {
            if (sessionFactory == null) {

                //  for application.properties this method is used
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
                standardServiceRegistryBuilder.loadProperties("Application.properties");
                standardServiceRegistry = standardServiceRegistryBuilder.build();

                //For hibernate.cfg.xml this build method is used
                //create standardServiceRegistry
                //standardServiceRegistry =new StandardServiceRegistryBuilder().configure().build();

//                Hibernate setting is equivalent to hibernate.cfg.xml properties
//                Map<String,String> dbsetting = new HashMap<>();
//                dbsetting.put(Environment.URL,"jdbc:mysql://localhost:3306/test");
//                dbsetting.put(Environment.USER,"root");
//                dbsetting.put(Environment.PASS,"12345678");
//                dbsetting.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
//                dbsetting.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
//                dbsetting.put(Environment.HBM2DDL_AUTO,"update");
//                dbsetting.put(Environment.FORMAT_SQL,"true");
//                dbsetting.put(Environment.SHOW_SQL,"true");
//               standardServiceRegistryBuilder.applySettings(dbsetting);


                //Create MetadataSources
                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                //for application.properties this annotation class added
                metadataSources.addAnnotatedClass(Employee.class);

                //Create Metadata
                Metadata metadata = metadataSources.getMetadataBuilder().build();

                //create session factory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (standardServiceRegistry != null) {
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }
        }
    }

    // utility method return to session factory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

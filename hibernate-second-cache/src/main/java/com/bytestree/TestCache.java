package com.bytestree;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bytestree.dao.util.HibernateUtils;
import com.bytestree.model.Department;

public class TestCache {

    private static void getData() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            System.out.println("\nRetrieving data....");
            
            Department department = session.load(Department.class, new Long(1));
            System.out.println("Department retrieved: " + department);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	tx.commit();
            session.close();
        }
    }

    public static void main(String[] args) {
        getData();
        System.out.println("\nCalling getData() second time....");
        getData();
        
        HibernateUtils.getSessionFactory().close();

    }

}

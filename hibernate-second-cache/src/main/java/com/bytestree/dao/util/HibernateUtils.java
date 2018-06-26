package com.bytestree.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.bytestree.model.Department;

public class HibernateUtils {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {

				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				// When properties file name is different than
				// hibernate.prpoerties use >
				// registryBuilder.loadProperties("hibernateConfig.properties");

				registry = registryBuilder.build();
				Metadata metadata = getMetadataSources().getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	private static MetadataSources getMetadataSources() {
		MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Department.class);
		return sources;
	}
}

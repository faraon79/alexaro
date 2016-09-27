package click.tomasz.persistence.common;

import javax.persistence.EntityManager;

import click.tomasz.persistence.dao.Dao;

public class DaoFactory {

	private static DaoFactory daoFactory;

	private EntityManager entityManager;

	private DaoFactory() {
		EntityManagerFactory emf = new EntityManagerFactory();
		entityManager = emf.getEntityManager();
	}

	public static DaoFactory getDaoFactory(){
		if(daoFactory == null)
			return daoFactory = new DaoFactory();
		return daoFactory;
	}

	public Dao getDao(Class<?> clazz){
		Dao dao = null;

		try {
			dao = (Dao)clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		dao.init(entityManager);
		return dao;
	}
}

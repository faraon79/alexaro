package click.tomasz.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class Dao {

	protected EntityManager em;

	public void init(EntityManager entityManager) {
		em = entityManager;
	}

	public Object create(Object o) {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.persist(o);
		em.getTransaction().commit();
		return o;
	}

	public Object update(Object o) {
		em.getTransaction().begin();
		o = em.merge(o);
		em.getTransaction().commit();
		return o;
	}

	public void delete(Object o) {
		em.getTransaction().begin();
		o = em.merge(o);
		em.remove(o);
		em.getTransaction().commit();
	}

	protected Object findByPk(Class<?> clazz, Object pk) {
		return em.find(clazz, pk);
	}

	protected List<?> findByQuery(String queryName, Map<String, Object> map) {
		Query query = em.createNamedQuery(queryName);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
}

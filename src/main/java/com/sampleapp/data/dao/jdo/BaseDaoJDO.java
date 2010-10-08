package com.sampleapp.data.dao.jdo;

import org.springframework.transaction.annotation.Transactional;

import com.sampleapp.data.dao.interfaces.BaseDao;
import com.sampleapp.data.dataobjects.DomainObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.Query;
import org.springframework.orm.jdo.support.JdoDaoSupport;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public abstract class BaseDaoJDO<T extends DomainObject> extends JdoDaoSupport implements BaseDao<T> {

	private Class<T> domainClass;

	public BaseDaoJDO(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	@Transactional
	public void delete(T object) {
		getJdoTemplate().deletePersistent(object);
	}

	@Transactional
	public T load(long id) {
		return (T) getJdoTemplate().getObjectById(domainClass, id);
	}

	@Transactional
	public void manage(T object) {
		getJdoTemplate().makePersistent(object);
	}

	@Transactional
	public T save(T object) {
		System.out.println("+++++++++++++++++++++++++++++++++++++About to save "+object+ " With Id "+object.getId());
		if (object.getId() != null) {
			System.out.println("Object "+object+" has an ID already, not saving");
			return getJdoTemplate().makePersistent(object);
			//return object;

		}

		return getJdoTemplate().makePersistent(object);
		//return object;
	}


	protected Query newQuery()
    {
		System.out.println("NEW QUERY FOR CLASS: "+domainClass);
        return getPersistenceManager().newQuery(domainClass);
    }

	@Transactional
	public List<T> findAll() {

        Query query = newQuery();
        //query.setRange(0, 10);
		Object r = query.execute();

		System.out.println("R: "+r);
		return new ArrayList<T>( (Collection<T>) r );
	}

	@Transactional
	public int countAll() {

		return findAll().size();

	}
}

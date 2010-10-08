package com.sampleapp.data.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.sampleapp.data.dataobjects.DomainObject;
/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public interface BaseDao<T extends DomainObject>
{
	public void delete(T o);

	public T load(long id);

	public void manage(T o);

	public T save(T o);

	public List<T> findAll();

	public int countAll();

	public T copy(T o);

}


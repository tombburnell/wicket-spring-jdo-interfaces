package com.sampleapp.data.dataobjects;

import java.io.Serializable;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import org.apache.wicket.IClusterable;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 *
 * Anything needing an id should inherit from this.
 *
 *
 * we use detachable=true so that we can save objects back to the datastore
 * in subsequent requests.
 *
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="true")
public abstract class DomainObject implements IClusterable, Serializable{

	private static final long serialVersionUID = 1L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

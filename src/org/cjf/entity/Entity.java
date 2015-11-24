package org.cjf.entity;

import java.io.Serializable;

public interface Entity extends Serializable, Cloneable {
	int getId();
	void setId(int id);
}

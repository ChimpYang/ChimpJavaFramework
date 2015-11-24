package org.cjf.test;

import org.cjf.entity.User;
import org.junit.Test;

import com.sun.corba.se.impl.io.ObjectStreamClass;

public class TestSID {
	@Test
	public void test1() {
		long uid1 = ObjectStreamClass.getSerialVersionUID(User.class);
		long uid2 = ObjectStreamClass.getActualSerialVersionUID(User.class);
		
		System.out.println(uid1);
		System.out.println(uid2);
	}
}

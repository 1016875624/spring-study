package com.demo01.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo01.configuration.PlachHolderConfig;
import com.demo01.entity.User2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {PlachHolderConfig.class})
public class PlaceHolderTest {
	@Autowired
	User2 user2;
	@Test
	public void name() {
		System.out.println(user2);
	}
	
}

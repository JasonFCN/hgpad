package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.erp.hgpad.entity.TEmployee;

public interface TEmployeeDao extends JpaRepository<TEmployee, String>,JpaSpecificationExecutor<TEmployee> {
	
	@Query("select e from TEmployee e where e.status=1  and (e.account=?1 or e.mob=?1) and e.pin=?2 and e.sign=9")
	List<TEmployee> findByAccountAndPassword(String acount, String password);

	List<TEmployee> findByMobOrAccount(String fMob, String account);

}

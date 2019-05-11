/**
 * 
 */
package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TMenu;

/**
 * @author 武杰
 *
 */
public interface TMenuDao extends JpaRepository<TMenu, String>,JpaSpecificationExecutor<TMenu> {

	List<TMenu> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);

	List<TMenu> findByStatusAndStateAndCodeOrderByNoAsc(Integer status, Integer state, String code);

}

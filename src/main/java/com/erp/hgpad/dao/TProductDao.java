package com.erp.hgpad.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erp.hgpad.entity.TProduct;
import com.erp.hgpad.utilBean.SearchProductVo;

public interface TProductDao extends JpaRepository<TProduct, String> ,JpaSpecificationExecutor<TProduct>{

	List<TProduct> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);

	List<TProduct> findByStatusAndCodeOrderByNoAsc(Integer i, String fCode);
	
	List<TProduct> findByColorIn(Collection<String> colorIds);
	List<TProduct> findByColorIn(String[] colorIds);
	
	@Query( value=	" select * from f_tproduct aa where 1=1 "+
			" and if(:productPrice1!='', aa.f_price>= :productPrice1,1=1)  "+
			" and if(:productPrice2!='', aa.f_price<=:productPrice2,1=1)  "+
			" and if(:productcolors!='', find_in_set(aa.f_color,:productcolors),1=1)  "+
			" and if(:productTypeNames!='', find_in_set(aa.f_product_type_name,:productTypeNames),1=1)  "+
			" and if(:productRoomIds!='', EXISTS(SELECT * from f_tproduct_room bb WHERE bb.f_product_id=aa.f_id and find_in_set(bb.f_room_id,:productRoomIds)),1=1)  "+
			" and if(:productStyleIds!='', EXISTS(SELECT * from f_tproduct_style bb WHERE bb.f_product_id=aa.f_id and find_in_set(bb.f_style_id,:productStyleIds)),1=1)  "
	,nativeQuery=true)
	List<TProduct> search(@Param("productPrice1") BigDecimal productPrice1, @Param("productPrice2") BigDecimal productPrice2, @Param("productTypeNames") String productTypeNames, @Param("productcolors") String productcolors, @Param("productRoomIds") String productRoomIds, @Param("productStyleIds") String productStyleIds);
	
	@Query(" select aa from TProduct aa where aa.status=1 and aa.state=1 and ( "+
			 "     aa.name 				like %?1% "+
			 "  or aa.productTypeName 	like %?1% "+
			 "  or aa.styleName 		like %?1% "+
			 "  or aa.code 				like %?1% "+
			 "  or aa.spec 				like %?1% "+
			 "  or aa.gongyi 			like %?1% "+
			 "  or aa.material 			like %?1% "+
			 "  or aa.color 			like %?1% "+
			 ") order By no"
			)
	List<TProduct> containingList(String value);

	List<TProduct> findTop16ByStatusAndState(Integer status, Integer state, Sort sort);

}

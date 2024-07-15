package com.demo.repository.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import com.demo.model.BoughtItem;
import com.demo.model.MemberData;
import com.demo.repository.BoughtItemRepository;

import reactor.core.publisher.Flux;


@Component
public class RepositoryCustom{

	private final R2dbcEntityTemplate template;

	RepositoryCustom(R2dbcEntityTemplate template) {
        this.template = template;
    }
	
	public Flux<MemberData> getMembersWithPageable(Pageable pageable){
		return this.template.getDatabaseClient().sql("select * from member_data LIMIT :limit OFFSET :offset")
				.bind("limit", pageable.getPageSize())
				.bind("offset", pageable.getOffset())
				.map((row, metadata) -> {
                    MemberData memberData = new MemberData();
                    memberData.setId(row.get("id", Integer.class));
                    memberData.setUserAccount(row.get("user_account", String.class));
                    memberData.setUserName(row.get("user_name", String.class));
                    memberData.setBirthDay(row.get("birth_day",String.class));
                    return memberData;
                }).all();
	}
	
	public Flux<BoughtItem> listOrderItem(Pageable pageable,BoughtItem item){
		//用 product_id 代替 product_name 
		
		 // Start building the base SQL
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM BOUGHT_ITEM WHERE user_account = :userAccount ");

        // Dynamically add conditions based on product ID
        if (item.getProductId() != null && !item.getProductId().isEmpty()) {
            sqlBuilder.append(" AND product_id IN (SELECT product_id FROM product_item WHERE product_name LIKE :productName) ");
        }
        
		if (item.getOrderId() != null && !item.getOrderId().isEmpty()) {
			sqlBuilder.append(" AND order_id = :orderId ");
		}
		
		if (item.getBuyDate() != null) {
			sqlBuilder.append(" AND buy_date = :buyDate ");
		}

        // Add pagination
        sqlBuilder.append(" LIMIT :limit OFFSET :offset");

        // Create a SQL string
        String sql = sqlBuilder.toString();

        // Prepare the query with parameters using DatabaseClient which is entirely non-blocking
        var query = template.getDatabaseClient().sql(sql)
                .bind("userAccount", item.getUserAccount());
        
        if (item.getProductId() != null && !item.getProductId().isEmpty()) {
            query = query.bind("productName", "%" + item.getProductId() + "%");
        }
        
        if (item.getOrderId() != null && !item.getOrderId().isEmpty()) {
        	query = query.bind("orderId", item.getOrderId());
        }
        
		if (item.getBuyDate() != null) {
			query = query.bind("buyDate", item.getBuyDate());
		}

        query = query.bind("limit", pageable.getPageSize())
                     .bind("offset", pageable.getOffset());

        // Execute the query
        return query.map((row, metadata) -> {
            BoughtItem boughtItem = new BoughtItem();
            boughtItem.setId(row.get("id", Integer.class));
            boughtItem.setOrderId(row.get("order_id", String.class));
            boughtItem.setProductId(row.get("product_id", String.class));
            boughtItem.setUserAccount(row.get("user_account", String.class));
            boughtItem.setBuyDate(row.get("buy_date", LocalDate.class));
            return boughtItem;
        }).all();
		
	}
}


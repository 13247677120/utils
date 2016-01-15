package com.akuma.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.akuma.mybatis.Generater.Table;

public class Test {
	public static void main(String[] args) {
		Generater.setProjectFile("C:/Users/AiHuanGou/git/utils/mybatisGenerater");
		Generater.setJdbcConnection("jdbc:mysql://localhost:3306/ibuy?useSSL=false", "com.mysql.jdbc.Driver", "root",
				"888888");

		List<Table> tables = new ArrayList<Table>();
		tables.add(new Table("admin_account", "Account"));

		Generater.generate("com.kunter.ibuy.admin.eo", "com.kunter.ibuy.admin.xml", "com.kunter.ibuy.admin.dao",
				tables);
	}
}

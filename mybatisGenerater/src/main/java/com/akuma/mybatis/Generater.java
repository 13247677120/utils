package com.akuma.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generater {
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/ibuy?useSSL=false";
	private static String jdbcDriver = "com.mysql.jdbc.Driver";
	private static String jdbcUserName = "root";
	private static String jdbcPassword = "888888";
	/**
	 * 当前项目目录
	 */
	private static String projectFile = "F:/Users/Administrator/workspace/mybatisGenerater";

	/**
	 * 自動打包
	 * 
	 * @param modelPackage
	 *            實體類包名
	 * @param xmlPackage
	 *            xml文件所在包名
	 * @param daoPackage
	 *            dao包名
	 * @param tables
	 *            表信息
	 */
	public static void generate(String modelPackage, String xmlPackage, String daoPackage, List<Table> tables) {
		try {
			System.out.println("Start working!");
			String javaPath = projectFile + "/src/main/java";

			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			File configFile = new File(projectFile + "/src/main/resources/mybatis.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);

			// 设置参数
			Context context = config.getContext("DB2Tables");

			CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
			commentGeneratorConfiguration.setConfigurationType("com.akuma.mybatis.MyCommentGenerator");
			context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

			// 数据库连接
			setJDBCConnectionConfiguration(context, jdbcUrl, jdbcDriver, jdbcUserName, jdbcPassword);
			// 未知参数
			setJavaTypeResolverConfiguration(context);
			// 实体类配置
			setJavaModelGeneratorConfiguration(context, javaPath, modelPackage);
			// Mapper.xml配置
			setSqlMapGeneratorConfiguration(context, javaPath, xmlPackage);
			// dao类配置
			setJavaClientGeneratorConfiguration(context, javaPath, daoPackage);

			if (tables != null && tables.size() > 0) {
				for (Table table : tables) {
					TableConfiguration tc = new TableConfiguration(context);
					tc.setTableName(table.getTableName());
					tc.setDomainObjectName(table.getDomainObjectName());
					context.addTableConfiguration(tc);
				}
			}

			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println("The end!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setJdbcConnection(String url, String driver, String user, String pw) {
		jdbcUrl = url;
		jdbcDriver = driver;
		jdbcUserName = user;
		jdbcPassword = pw;
	}

	/**
	 * 设置项目所在目录
	 * 
	 * @param file
	 *            项目所在目录
	 */
	public static void setProjectFile(String file) {
		projectFile = file;
	}

	private static void setJavaTypeResolverConfiguration(Context context) {
		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
		context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
	}

	/**
	 * 设置数据库链接
	 * 
	 * @param context
	 * @param url
	 * @param driver
	 * @param user
	 * @param pw
	 */
	private static void setJDBCConnectionConfiguration(Context context, String url, String driver, String user,
			String pw) {
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(url);
		jdbcConnectionConfiguration.setDriverClass(driver);
		jdbcConnectionConfiguration.setUserId(user);
		jdbcConnectionConfiguration.setPassword(pw);
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
	}

	/**
	 * 设置实体类的放置位置
	 * 
	 * @param context
	 * @param projectFile
	 * @param packageName
	 */
	private static void setJavaModelGeneratorConfiguration(Context context, String projectFile, String packageName) {
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage(packageName);
		javaModelGeneratorConfiguration.setTargetProject(projectFile);
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
	}

	/**
	 * 设置mapper.xml的放置位置
	 * 
	 * @param context
	 * @param projectFile
	 * @param packageName
	 */
	private static void setSqlMapGeneratorConfiguration(Context context, String projectFile, String packageName) {
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage(packageName);
		sqlMapGeneratorConfiguration.setTargetProject(projectFile);
		sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
	}

	/**
	 * 设置dao的放置位置
	 * 
	 * @param context
	 * @param projectFile
	 */
	private static void setJavaClientGeneratorConfiguration(Context context, String projectFile, String packageName) {
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		javaClientGeneratorConfiguration.setTargetPackage(packageName);
		javaClientGeneratorConfiguration.setTargetProject(projectFile);
		javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
	}

	/**
	 * 表转换成java类的信息
	 * 
	 * @author AiHuanGou
	 *
	 */
	public static class Table {
		private String tableName;
		private String domainObjectName;

		public Table(String tableName, String domainObjectName) {
			super();
			this.tableName = tableName;
			this.domainObjectName = domainObjectName;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getDomainObjectName() {
			return domainObjectName;
		}

		public void setDomainObjectName(String domainObjectName) {
			this.domainObjectName = domainObjectName;
		}
	}
}

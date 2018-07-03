package net.thumbtack.airline.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Reader;

@Component
public class MyBatisUtils {
	private final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtils.class);
	@Autowired
	private static SqlSessionFactory sqlSessionFactory;

	@Value("${mybatisConfigLocation}")
	private String configPath; //= "mybatis-config.xml";

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		try (Reader reader = Resources.getResourceAsReader(configPath)) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			return sqlSessionFactory;
		} catch (Exception e) {
			LOGGER.error("Error loading mybatis-config.xml", e);
			e.printStackTrace();
			throw new Error(e.toString());
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
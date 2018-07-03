package net.thumbtack.airline;

import net.thumbtack.airline.config.StarterConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@MapperScan({"net.thumbtack.airline.mappers"})
public class AirlineApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder().
				properties(StarterConfiguration.loadConfigurations(args[0])).
				application().run();
	}
}

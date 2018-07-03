package net.thumbtack.airline.config;

import net.thumbtack.airline.errors.types.BaseError;
import net.thumbtack.airline.errors.types.runtime.BadConfigFileError;
import net.thumbtack.airline.errors.types.runtime.BadConfigurationFileError;
import net.thumbtack.airline.exception.BadConfigException;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationConverter;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

public class StarterConfiguration {
	private final static Logger logger = LoggerFactory.getLogger(StarterConfiguration.class);

	public static Properties loadConfigurations(String mainConfigPath) throws BadConfigException {
		try {
			Configurations configs = new Configurations();
			Configuration configuration = configs.properties(new File(mainConfigPath));
			Properties properties = ConfigurationConverter.getProperties(configuration);
			if(properties.size() <= 1) {
				BaseError baseError = new BadConfigFileError();
				logger.error(baseError.toString());
				throw new BadConfigException(baseError);
			}
			return properties;
		} catch (ConfigurationException e) {
			BadConfigurationFileError baseError = new BadConfigurationFileError("command line args", "bad file path");
			logger.error(baseError.toString());
			throw new BadConfigException(baseError);
		}
	}
}

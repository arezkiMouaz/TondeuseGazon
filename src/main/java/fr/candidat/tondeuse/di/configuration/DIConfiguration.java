package fr.candidat.tondeuse.di.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.candidat.tondeuse.service.FileResultWriterServiceI;
import fr.candidat.tondeuse.service.TondeuseGazonServiceI;
import fr.candidat.tondeuse.serviceImpl.FileResultWriterServiceImpl;
import fr.candidat.tondeuse.serviceImpl.TondeuseGazonServiceImpl;


@Configuration
@ComponentScan(value={"fr.candidat.tondeuse.processorImpl"})
public class DIConfiguration {

	
	@Bean
	public FileResultWriterServiceI getFileResultWriterServive() {
		return new FileResultWriterServiceImpl();
	}


	@Bean
	public TondeuseGazonServiceI getTondeuseGazonService() {
		return new TondeuseGazonServiceImpl();
	}
}

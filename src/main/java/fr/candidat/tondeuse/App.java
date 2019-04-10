package fr.candidat.tondeuse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.candidat.tondeuse.di.configuration.DIConfiguration;
import fr.candidat.tondeuse.processorImpl.ProcessTondeuseImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
    	ProcessTondeuseImpl processor = context.getBean(ProcessTondeuseImpl.class);
    	processor.Process();
    	
    }
}

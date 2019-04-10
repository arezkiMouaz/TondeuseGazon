package fr.candidat.tondeuse.serviceImpl;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.springframework.stereotype.Service;

import exceptions.TondeuseException;
import fr.candidat.tondeuse.model.TondeuseAutomatique;
import fr.candidat.tondeuse.service.FileResultWriterServiceI;
import fr.candidat.tondeuse.utils.Utilities;

@Service
public class FileResultWriterServiceImpl implements FileResultWriterServiceI {




	@Override
	public void writeResult(TondeuseAutomatique tondeuse) throws TondeuseException {
		PrintWriter out = null;
		Properties props=Utilities.getProperties();
		String chemin=props.getProperty("OUTPUT_PATH");
		try {
			 out = new PrintWriter(new FileWriter(chemin, true));
			 out.append(tondeuse.getX()+" "+tondeuse.getY()+" "+tondeuse.getDirection()+"\n");
		} catch (FileNotFoundException e) {
			throw new TondeuseException(e.getMessage());
		} catch (IOException e) {
			throw new TondeuseException(e.getMessage());
		}
		finally {
			out.close();
		}

	}




}

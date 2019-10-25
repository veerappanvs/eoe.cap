package edu.mass.doe.cap.dataservice.batch;


import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import edu.mass.doe.cap.dataservice.pojo.CapData;

/**
 * The Class DataReader.
 */
@Component
public class DataReader {
	
	/**
	 * Read.
	 *
	 * @param file the file
	 * @return the list
	 * @throws UnexpectedInputException the unexpected input exception
	 * @throws ParseException the parse exception
	 * @throws Exception the exception
	 */
	public List<CapData> read( MultipartFile file) throws UnexpectedInputException, ParseException, Exception{
		FlatFileItemReader<CapData> itemReader = new FlatFileItemReader<CapData>();
		itemReader.setResource(new InputStreamResource(file.getInputStream()));
		//DelimitedLineTokenizer defaults to comma as its delimiter
		DefaultLineMapper<CapData> lineMapper = new DefaultLineMapper<CapData>();
		
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new BatchDataMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.open(new ExecutionContext());
		
		List<CapData> records = new ArrayList<>();
		CapData capData=null;
		//skip header
		itemReader.read();
		do{
		 capData = itemReader.read();
		 if(capData!=null)
		 records.add(capData);
		}while(capData!=null);
		
			return records;
	}

}

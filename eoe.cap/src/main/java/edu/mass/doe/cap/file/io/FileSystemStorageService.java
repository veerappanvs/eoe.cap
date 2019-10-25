package edu.mass.doe.cap.file.io;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Class FileSystemStorageService.
 */
@Component
public class FileSystemStorageService implements StorageService {

    private String repoLocation;
    
   
	private Environment env;
    
	 /**
 	 * Instantiates a new file system storage service.
 	 *
 	 * @param env the env
 	 */
 	@Autowired
    public FileSystemStorageService(Environment env) {
    	this.env=env;
    	repoLocation =env.getProperty("cap.file.repository");
    }
    
    /**
     * Gets the path.
     *
     * @param cycleId the cycle id
     * @return the path
     */
    private Path getPath(Long cycleId){
    	return Paths.get(repoLocation).resolve(cycleId.toString());
    }

    /* (non-Javadoc)
     * @see edu.mass.doe.cap.file.io.StorageService#store(org.springframework.web.multipart.MultipartFile, java.lang.Long, java.lang.Long)
     */
    @Override
    public void store(MultipartFile file,Long cycleId,Long fileId) {
        try {
        	
        	Path path=getPath(cycleId);
        	
        	if (!Files.isDirectory(path))
        		createDirectory(cycleId);
        	
           InputStream inputStream = file.getInputStream() ;
                Files.copy(inputStream, getPath(cycleId).resolve(fileId.toString()),
                    StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
                
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + fileId, e);
        }
    }
/*
    @Override
    public Stream<Path> loadAll(Long cycleId) {
    	
    	Path location=getPath(cycleId);
        try {
            return Files.walk(location, 1)
                .filter(path -> !path.equals(location))
                .map(location::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }*/
    
    /* (non-Javadoc)
 * @see edu.mass.doe.cap.file.io.StorageService#repoSize(java.lang.Long)
 */
@Override 
    public Long repoSize(Long cycleId) throws IOException{
    	Long size=0L;
    	
    	Path path=getPath(cycleId);
    	
    	if (!Files.isDirectory(path))
    		return size;
    			

    	File[] files=path.toFile().listFiles();
    	
    	for(int idx=0;idx<files.length;idx++){
    		size=Files.size(files[idx].toPath())+size;
    	}
    	
    	
    	return size;
    }

    /* (non-Javadoc)
     * @see edu.mass.doe.cap.file.io.StorageService#load(java.lang.String, java.lang.Long)
     */
    @Override
    public Path load(String filename,Long cycleId) {
    	Path location=getPath(cycleId);
        return location.resolve(filename);
    }

    /* (non-Javadoc)
     * @see edu.mass.doe.cap.file.io.StorageService#loadAsResource(java.lang.String, java.lang.Long)
     */
    @Override
    public Resource loadAsResource(String filename,Long cycleId) {
        try {
            Path file = load(filename,cycleId);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    /* (non-Javadoc)
     * @see edu.mass.doe.cap.file.io.StorageService#deleteFile(java.lang.String, java.lang.Long)
     */
    @Override
    public void deleteFile(String fileName,Long cycleId) {
      getPath(cycleId).resolve(fileName).toFile().delete();
    }

    /* (non-Javadoc)
     * @see edu.mass.doe.cap.file.io.StorageService#createDirectory(java.lang.Long)
     */
    @Override
    public void createDirectory(Long cycleId) {
        try {
            Files.createDirectories(getPath(cycleId));
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}

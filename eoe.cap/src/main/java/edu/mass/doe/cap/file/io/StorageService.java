package edu.mass.doe.cap.file.io;



import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * The Interface StorageService.
 */
public interface StorageService {

    /**
     * Creates the directory.
     *
     * @param cycleId the cycle id
     */
    void createDirectory(Long cycleId);

    /**
     * Store.
     *
     * @param file the file
     * @param cycleId the cycle id
     * @param fileId the file id
     */
    void store(MultipartFile file,Long cycleId,Long fileId);

//    Stream<Path> loadAll(Long cycleId);

    /**
 * Load.
 *
 * @param filename the filename
 * @param cycleId the cycle id
 * @return the path
 */
Path load(String filename,Long cycleId);

    /**
     * Load as resource.
     *
     * @param filename the filename
     * @param cycleId the cycle id
     * @return the resource
     */
    Resource loadAsResource(String filename,Long cycleId);

    /**
     * Delete file.
     *
     * @param filename the filename
     * @param cycleId the cycle id
     */
    void deleteFile(String filename,Long cycleId);
    
    /**
     * Repo size.
     *
     * @param cycleId the cycle id
     * @return the long
     * @throws IOException Signals that an I/O exception has occurred.
     */
    Long repoSize(Long cycleId) throws IOException;

}

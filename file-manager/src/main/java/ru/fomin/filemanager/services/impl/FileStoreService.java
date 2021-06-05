package ru.fomin.filemanager.services.impl;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fomin.filemanager.entiries.FileMeta;
import ru.fomin.filemanager.repositories.FileMetaRepository;
import ru.fomin.filemanager.repositories.IFileSystemProvider;
import ru.fomin.filemanager.services.IFileStoreService;
import ru.fomin.filemanager.utils.HashHelper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;

@Component
public class FileStoreService implements IFileStoreService {

    @Autowired
    IFileSystemProvider systemProvider;

    @Autowired
    FileMetaRepository fileMetaRepository;

    @Override
    public String storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException {
        final UUID md5 = HashHelper.getMd5Hash(content);

        String filename = fileMetaRepository.checkFileExists(md5);
        if (filename == null) {
            FileMeta fileMeta = FileMeta.builder()
                    .hash(md5)
                    .fileName(fileName)
                    .subType(subFileType)
                    .size(content.length)
                    .build();
            fileMetaRepository.save(fileMeta);
            filename = systemProvider.storeFile(content, md5, fileName);
        }

        return filename;
    }

    @Override
    public byte[] getFile(UUID md5) throws IOException {
        String filename = fileMetaRepository.checkFileExists(md5);
        String ext = FilenameUtils.getExtension(filename);
        String fullFileName = md5.toString() + "." + ext;
        return systemProvider.getFile(fullFileName);
    }

    @Override
    public Collection<FileMeta> getMetaFiles(int subtype) {
        return fileMetaRepository.findAllBySubType(subtype);
    }
}

package ru.fomin.filemanager.services;

import ru.fomin.filemanager.entiries.FileMeta;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;

public interface IFileStoreService {

    String storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException;

    byte[] getFile(UUID md5) throws IOException;

    Collection<FileMeta> getMetaFiles(int subtype);

}

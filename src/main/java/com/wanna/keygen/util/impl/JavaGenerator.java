package com.wanna.keygen.util.impl;

import com.wanna.keygen.util.Generator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class JavaGenerator implements Generator {
    @Override
    public void generate(String content) throws Exception {
        File zipFile = new File(FILE_NAME);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry entry = new ZipEntry(ENTRY_NAME);
        // put crc32
        CRC32 crc32 = new CRC32();
        crc32.update(content.getBytes());
        entry.setCrc(crc32.getValue());
        entry.setSize(content.length());
        entry.setMethod(ZipEntry.STORED);
        // write content
        zipOut.putNextEntry(entry);
        zipOut.write(content.getBytes());
        // close release resource
        zipOut.closeEntry();
        zipOut.close();
    }
}

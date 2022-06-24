package com.wanna.keygen.util.impl;

import com.wanna.keygen.util.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SevenZipGenerator implements Generator {

    private static final Logger logger = LoggerFactory.getLogger(SevenZipGenerator.class);

    private static final String COMMAND = "%s a %s  %s";

    @Override
    public void generate(String keyContent) throws Exception {
        // 生成 key 文件
        File keyFile = generateKeyFile(keyContent);

        File zipFile = new File(ZIP_NAME);
        String zipPath = zipFile.getAbsolutePath();
        String keyPath = keyFile.getAbsolutePath();

        File zip7 = copy7z();
        String command = String.format(COMMAND, zip7.getAbsolutePath(), zipPath, keyPath);
        logger.info("执行的命令为: {} ", command);

        Process exec = Runtime.getRuntime().exec(command);
        exec.waitFor();
        // 重命名
        File dest = new File(FILE_NAME);
        zipFile.renameTo(dest);
        // 删除生成的相关文件
        keyFile.delete();
        zip7.delete();
        logger.info(FILE_NAME + " 文件生成的路径为: {}", dest.getAbsolutePath());
    }

    /**
     * 生成 Pro.key
     *
     * @param content content
     * @return key file
     * @throws IOException IOE
     */
    private static File generateKeyFile(String content) throws IOException {
        File keyFile = new File(ENTRY_NAME);
        FileOutputStream fis = new FileOutputStream(keyFile);
        fis.write(content.getBytes(StandardCharsets.UTF_8));
        fis.close();
        return keyFile;
    }

    /**
     * 复制 7z
     *
     * @return 7z.exe
     * @throws Exception Exception
     */
    private static File copy7z() throws Exception {
        InputStream in = SevenZipGenerator.class.getResourceAsStream("/7z/7z.exe");

        File file = new File("7z.exe");
        Files.copy(in, file.toPath());
        return file;
    }
}

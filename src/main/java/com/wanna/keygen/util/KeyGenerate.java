package com.wanna.keygen.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author wanna
 * @since 2019-01-02
 */
@SuppressWarnings("ALL")
public class KeyGenerate {

    private static final String MXTPRO_NAME = "Custom.mxtpro";

    private static final String ZIP_NAME = "Custom.zip";

    private static final String ENTRY_NAME = "Pro.key";

    private static final String COMMAND = "{{EXE}} a {{ZIP}}  {{KEY}}";


    /**
     * 生成 破解 文件
     *
     * @param keyContent key
     * @throws IOException 文件生成失败
     */
    public static void generate(String keyContent) throws Exception {
        // 生成 key 文件
        File keyFile = generateKeyFile(keyContent);


        File zipFile = new File(ZIP_NAME);
        String zipPath = zipFile.getAbsolutePath();
        String keyPath = keyFile.getAbsolutePath();

        File zip7 = copy7z();
        String command = COMMAND.replace("{{EXE}}", zip7.getAbsolutePath())
                .replace("{{ZIP}}", zipPath).replace("{{KEY}}", keyPath);
        Process exec = Runtime.getRuntime().exec(command);
        exec.waitFor();
        // 重命名
        File dest = new File(MXTPRO_NAME);
        zipFile.renameTo(dest);
        // 删除生成的相关文件
        keyFile.delete();
        zip7.delete();
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
        InputStream in = KeyGenerate.class.getResourceAsStream("/7z/7z.exe");

        File file = new File("7z.exe");
        Files.copy(in, file.toPath());
        return file;
    }

}

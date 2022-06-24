package com.wanna.keygen.util;

public interface Generator {


    String FILE_NAME = "Custom.mxtpro";

    String ENTRY_NAME = "Pro.key";

    String ZIP_NAME = "Custom.zip";

    /**
     * 生成 破解授权文件
     *
     * @param keyContent 授权内容
     * @throws Exception exception
     */
    void generate(String keyContent) throws Exception;
}

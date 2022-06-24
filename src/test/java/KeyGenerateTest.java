import com.wanna.keygen.core.License;
import com.wanna.keygen.util.EncryptUtil;
import com.wanna.keygen.util.Generator;
import com.wanna.keygen.util.VariantBase64;
import com.wanna.keygen.util.impl.JavaGenerator;
import com.wanna.keygen.util.impl.SevenZipGenerator;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class KeyGenerateTest {


    @Test
    public void testJavaGenerator() throws Exception {
        License license = new License("wanna", "11.0");
        String string = license.getLicenseKey();
        String content = VariantBase64.variantBase64Encode(
                EncryptUtil.encryptBytes(0x787, string.getBytes(StandardCharsets.UTF_8))
                        .getBytes(StandardCharsets.UTF_8)
        );
        System.out.println(content);

        JavaGenerator javaGenerator = new JavaGenerator();
        javaGenerator.generate(content);
        System.out.println(11);
    }


    @Test
    public void test7ZipGenerator() throws Exception {
        License license = new License("wanna", "11.0");
        String string = license.getLicenseKey();
        String content = VariantBase64.variantBase64Encode(
                EncryptUtil.encryptBytes(0x787, string.getBytes(StandardCharsets.UTF_8))
                        .getBytes(StandardCharsets.UTF_8)
        );
        System.out.println(content);

        Generator KeyGenerate = new SevenZipGenerator();
        KeyGenerate.generate(content);
    }

}

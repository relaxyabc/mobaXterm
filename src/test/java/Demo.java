import com.wanna.keygen.core.License;
import com.wanna.keygen.util.EncryptUtil;
import com.wanna.keygen.util.KeyGenerate;
import com.wanna.keygen.util.VariantBase64;

import java.nio.charset.StandardCharsets;

/**
 * @author wanna
 * @since 2019-01-02
 */
public class Demo {
    public static void main(String[] args) throws Exception {


        License license = new License("wanna", "11.0");
        String string = license.getLicenseKey();
        String content = VariantBase64.variantBase64Encode(
                EncryptUtil.encryptBytes(0x787, string.getBytes(StandardCharsets.UTF_8))
                        .getBytes(StandardCharsets.UTF_8)
        );
        System.out.println(content);
        KeyGenerate.generate(content);
    }

}

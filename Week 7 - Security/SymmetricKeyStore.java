package Security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class SymmetricKeyStore {
    private static KeyStore createKeyStore(String filename, String password){
        try{
            File file = new File(filename);
            KeyStore keyStore = KeyStore.getInstance(SecureData.KEY_STORE_TYPE.value);
            if(file.exists()){
                keyStore.load(new FileInputStream(file), password.toCharArray());
            }else{
                keyStore.load(null, null);
                keyStore.store(new FileOutputStream(file), password.toCharArray());
            }
            return keyStore;
        }catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex){
            System.out.println("Error");
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            KeyStore keyStore = createKeyStore(SecureData.KEY_STORE_FILE.value, SecureData.KEY_STORE_PASSWORD.value);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecretKey secretKey = keyGenerator.generateKey();
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            KeyStore.PasswordProtection keyPassword = new
                    KeyStore.PasswordProtection(SecureData.KEY_PASSWORD.value.toCharArray());
            keyStore.setEntry(SecureData.KEY_ENTRY.value, secretKeyEntry, keyPassword);
            keyStore.store(new FileOutputStream(SecureData.KEY_STORE_FILE.value),
                    SecureData.KEY_STORE_PASSWORD.value.toCharArray());
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}

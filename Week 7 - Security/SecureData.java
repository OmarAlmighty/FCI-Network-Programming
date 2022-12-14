package Security;

public enum SecureData {
    //Password for the keystore
    KEY_STORE_PASSWORD("Open ya semsem"),
    //Entry password
    KEY_PASSWORD("1234"),
    //Alias (name) of the name
    KEY_ENTRY("secret key"),
    // The name and path of the keystore file
    KEY_STORE_FILE("keystore.jks"),
    // Type of the keystore
    KEY_STORE_TYPE("JCEKS");

    public String value;

    SecureData(String value) {
        this.value = value;
    }
}

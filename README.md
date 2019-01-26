# Certificates-Public-Private-key

```
JCEKS    Java Cryptography Extension KeyStore
JKS      Java Key Store
PKCS     Public Key Cryptography Standards
PEM      Privacy Enhanced Mail
pkcs#8    (.key)private key format
pub       (.pub)public key format
X.509     (.cer) certificate form
pkcs#7    (.p7b) certificate form
JKS        JAVA KEY STORE
JCEKS      Java Cryptography Extension KeyStore
pkcs#12    Public Key Cryptography Standards (Keystore)
```

#### PKCS#12 
In cryptography, PKCS #12 defines an archive file format for storing many cryptography objects as a single file. It is commonly used to bundle a private key with its X.509 certificate or to bundle all the members of a chain of trust. A PKCS #12 file may be encrypted and signed

#### JKS
The Java KeyStore is a database that can contain keys. A Java KeyStore is represented by the KeyStore (java.security.KeyStore) class. A KeyStore can be written to disk and read again. The KeyStore as a whole can be protected with a password, and each key entry in the KeyStore can be protected with its own password. This makes the KeyStore class a useful mechanism to handle encryption keys securely.

A KeyStore can hold the following types of keys:

Private keys
Public keys + certificates
Secret keys


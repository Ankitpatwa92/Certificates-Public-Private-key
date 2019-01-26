package com.micro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class LoadKeyFromFile {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, CertificateException {
		
		loadPublicKeyBase64();	
		loadPrivateKeyBase64();
		loadPublicKeyFromCertificateFile();
	}
	
	
	/**
	 * Load private key from base 64 format file
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey loadPrivateKeyBinary() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		Path path =Paths.get("private_key_binary_format.key");
		
	 	byte[] priKeyBytes = Files.readAllBytes(path);
	 	 	 		 			 
	 	PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(priKeyBytes);
	 	
	 	KeyFactory kf = KeyFactory.getInstance("RSA");
	 	PrivateKey pvt = kf.generatePrivate(ks);
	 	return pvt;	 	
	}
	
	
	/**
	 * Load public key from binary format file
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey loadPublicKeyBinary() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		Path path =Paths.get("public_key_binary_format.pub");
	 	byte[] pubKeyBytes = Files.readAllBytes(path);
	 	
	 	X509EncodedKeySpec ks = new X509EncodedKeySpec(pubKeyBytes);
	 	KeyFactory kf = KeyFactory.getInstance("RSA");
	 	PublicKey pub = kf.generatePublic(ks);
	 	
	 	return pub;
	}

	
	/**
	 * Load private key from base 64 format file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static void loadPrivateKeyBase64() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		Path path =Paths.get("private_key_base64_format.key");		
	 	byte[] priKeyBytes = Files.readAllBytes(path);
	 	String[] linesOfFile =new String(priKeyBytes).split("\\n");	 		 	
	 	String priKeyBase64=linesOfFile[1]; //get only "key" text remove --begin--- and ---end--- statement	 	
	 	byte[] keyBytes = Base64.getDecoder().decode(priKeyBase64);	 	

	 	PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(keyBytes);
	 	KeyFactory kf = KeyFactory.getInstance("RSA");
	 	PrivateKey pvt = kf.generatePrivate(ks);	 	
	 	System.out.println("pvt======"+pvt);
	}
	
	/**
	 * Load public key from base 64 format file 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static void loadPublicKeyBase64() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		Path path =Paths.get("public_key_base64_format.pub");
	 	byte[] pubKeyBytes = Files.readAllBytes(path);	 	
	 	String[] linesOfFile =new String(pubKeyBytes).split("\\n");	 		 	
	 	String pubKeyBase64=linesOfFile[1]; //get only "key" text remove --begin--- and ---end--- statement	 	
	 	byte[] keyBytes = Base64.getDecoder().decode(pubKeyBase64);	 	
	 	X509EncodedKeySpec kspec = new X509EncodedKeySpec(keyBytes);
	 	KeyFactory kf = KeyFactory.getInstance("RSA");
	 	PublicKey pub = kf.generatePublic(kspec);	 		 	
	}
	
	
	
	/**
	 * Get public key from  Certificate
	 * @throws CertificateException
	 */
	public static void loadPublicKeyFromCertificateFile() throws CertificateException {
		
	    CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream inputStream = AppClient1.class.getClassLoader().getResourceAsStream("msa.cer");	
		X509Certificate certificate = (X509Certificate)cf.generateCertificate(inputStream);
		PublicKey pk = certificate.getPublicKey();		
	}


	/**
	 * This method will load certificate from Keystore
	 * 123456 is password of keystore
	 *    
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 */
	public static KeyPair loadCertificateFromKeyStore() throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException {

		//Key store can be any type jks (Java key Store), PKCS#12 format, JCEKS format
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(new FileInputStream("example.keystore"), "123456".toCharArray());

		Certificate cer = keyStore.getCertificate("tomcat");

		PublicKey publicKey = cer.getPublicKey();
		Key key = keyStore.getKey("aliasName", "123456".toCharArray());
		PrivateKey privateKey = null;
		if (key instanceof PrivateKey) {
			privateKey = (PrivateKey) key;
		}
		KeyPair keyPair = new KeyPair(publicKey, privateKey);
		return keyPair;
	}

}

package com.micro;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

public class CreateCer {

	
	
	public static void main(String[] args) throws GeneralSecurityException, IOException {

		generateCertificate();
	}
	
	

	
	public static void generateCertificate() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchProviderException, InvalidKeyException, SignatureException {
					     
		     String commonName = "www.test.de";
		     String organizationalUnit = "IT";
		     String organization = "test";
		     String city = "test";
		     String state = "test";
		     String country = "IN";
		     long validity = 1096; // 3 years
		     String alias = "myalias";
		     char[] keyPass = "123456".toCharArray();
		
		    //Create key store type of JAVA Key Store 
	        KeyStore keyStore = KeyStore.getInstance("JKS");
	        keyStore.load(null, null);

	        CertAndKeyGen keypair = new CertAndKeyGen("RSA", "SHA1WithRSA", null);

	        X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);

	        keypair.generate(1024);
	        
	        PrivateKey privKey = keypair.getPrivateKey();

	        X509Certificate[] chain = new X509Certificate[1];

	        chain[0] = keypair.getSelfCertificate(x500Name, new Date(), (long) validity * 24 * 60 * 60);

	        keyStore.setKeyEntry(alias, privKey, keyPass, chain);

	        keyStore.store(new FileOutputStream("example.keystore"), keyPass);		
	}
	
	
	
}

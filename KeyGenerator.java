package com.micro;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyGenerator {

	static Key pub;
	static Key pvt;
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		generateKeys();
		saveKeyInBinaryForamt();
		saveGeneratedKeyInTextForamt();
		
	}
	
	
	public static void generateKeys() throws NoSuchAlgorithmException {
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();
		pub = kp.getPublic();
		pvt = kp.getPrivate();				

	}
	
	public static void saveKeyInBinaryForamt() throws NoSuchAlgorithmException, IOException{
		
		String pubFileName="public_key_binary_format";
		FileOutputStream fosPub= new FileOutputStream(pubFileName+".pub");
		fosPub.write(pub.getEncoded());
		fosPub.close();	
		
		String privateFileName="private_key_binary_format";
		FileOutputStream fosPrivate= new FileOutputStream(privateFileName+".key");
		fosPrivate.write(pvt.getEncoded());
		fosPrivate.close();
		
		System.err.println("Private key format: " + pvt.getFormat());
		// prints "Private key format: PKCS#8" 
		 
		System.err.println("Public key format: " + pub.getFormat());
		// prints "Public key format: X.509" 

	}
	
	public static void saveGeneratedKeyInTextForamt() throws IOException {

		Base64.Encoder encoder = Base64.getEncoder();		
		String privateOutFile = "private_key_base64_format";
		Writer out = new FileWriter(privateOutFile + ".key");
		out.write("-----BEGIN RSA PRIVATE KEY-----\n");
		out.write(encoder.encodeToString(pvt.getEncoded()));
		out.write("\n-----END RSA PRIVATE KEY-----\n");
		out.close();
		
		String publicOutFile = "public_key_base64_format";
		Writer pubOut = new FileWriter(publicOutFile + ".pub");
		pubOut.write("-----BEGIN RSA PUBLIC KEY-----\n");
		pubOut.write(encoder.encodeToString(pub.getEncoded()));
		pubOut.write("\n-----BEGIN RSA PUBLIC KEY-----\n");
		pubOut.close();

		
	}
	
}
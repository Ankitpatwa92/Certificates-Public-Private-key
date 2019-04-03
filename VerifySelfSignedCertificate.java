	
  /***  
  Store all selfsigned certificate in keystore
  and while making ssl communicatin validate your certificate using your keystore
  By default it is done by java's trusted keystore which is lied on inside jre
  **/
  
  public static SSLContext getSSLContextSelfSigned() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyManagementException {
		
		KeyStore ks=KeyStore.getInstance("JKS");
		char[] passwordArray="changeit".toCharArray();
		ks.load(new FileInputStream("D://certificatetest/esKeyStore.jks"),passwordArray);
		
		TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance("SunX509");
		trustManagerFactory.init(ks);
		
		SSLContext context=SSLContext.getInstance("SSL");
		context.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
		return context;
	}


/**

Allow all type of certificate without validating
  
**/
	public static SSLContext getSSLContextValidatedAll() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
	
		@SuppressWarnings("deprecation")
		final SSLContext sslContextTrusted = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() 
		{
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {					
				
				return true;
			}
		}).build();

		return sslContextTrusted;
	}

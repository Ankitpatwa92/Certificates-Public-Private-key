
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class  CreateJWT {



   public static String getBase64EncodedKey(RSAPublicKey key) {	
		  String b64PublicKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
		  return b64PublicKey;			
		}
		
		
		private static void verifyTokenRSAAlgo(RSAPublicKey publicKey,String token) throws Exception {
		
			Algorithm rsa_algo=Algorithm.RSA256(publicKey);		
			DecodedJWT decodedJwt = JWT.decode(token);
			if(decodedJwt.getExpiresAt().getTime()<(new Date().getTime())) {
				throw new Exception("Token Expired");
			}
			rsa_algo.verify(decodedJwt);		
			
		}

		public static String getJWTUsingHSAAlgo(String secret) {
					
			 Algorithm hsa_algo=Algorithm.HMAC256(secret);		 
			 Calendar cal = Calendar.getInstance(); // creates calendar
		     cal.setTime(new Date()); // sets calendar time/date
		     cal.add(Calendar.MINUTE, 30); // adds one hour	    
		     		
			 return JWT.create()		
			.withAudience("http://abc.msa.com")
			.withClaim("domain", "msa")				
			.withExpiresAt(cal.getTime())
			.withIssuedAt(cal.getTime())
			.withKeyId("msa124")
			.withIssuer("msa.com")
			.withSubject("authorise")
			.sign(hsa_algo);			 
		}

		
		public static String getJWTUsingRSAAlgo(RSAPrivateKey privateKey) {
			
			 Algorithm rsaAlgo=Algorithm.RSA256(privateKey);		 
			 Calendar cal = Calendar.getInstance(); // creates calendar
		     cal.setTime(new Date()); // sets calendar time/date
		     cal.add(Calendar.MINUTE, 30); // adds one hour	    
		     		
			return JWT.create()		
			.withAudience("http://abc.msa.com")
			.withClaim("domain", "msa")				
			.withExpiresAt(cal.getTime())
			.withIssuedAt(cal.getTime())
			.withKeyId("msa124")
			.withIssuer("msa.com")
			.withSubject("authorise")
			.sign(rsaAlgo);			 
		}
		
		public static void verifyTokenHSAAlgo(String secret,String token) {
					
			Algorithm hsa_algo=Algorithm.HMAC256(secret);		
			DecodedJWT decodedJwt = JWT.decode(token);
			hsa_algo.verify(decodedJwt);				
		}
		

		
		public static String sign(String payload,String claim,int minutes,RSAPrivateKey privateKey) {
			
			 Algorithm rsaAlgo=Algorithm.RSA256(privateKey);		 
			 Calendar cal = Calendar.getInstance(); // creates calendar
		     cal.setTime(new Date()); // sets calendar time/date
		     cal.add(Calendar.MINUTE, minutes); // adds one hour	    
		     		
			 return JWT.create()				
			.withClaim(claim,payload)				
			.withExpiresAt(cal.getTime())
			.withIssuedAt(cal.getTime())
			.withKeyId("msa124")
			.withIssuer("msa.com")	
			.sign(rsaAlgo);		
		}

		
		public static Claim unsign(RSAPublicKey pubKey,String token,String claimName) throws Exception {

			verifyTokenRSAAlgo(pubKey,token);
			DecodedJWT decodedJwt = JWT.decode(token);
			Claim claim = decodedJwt.getClaim(claimName);
			return claim;				
		}
			
}

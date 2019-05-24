#### How to generate key using keytool command
```
keytool -genkey  -alias  serverkey -keyalg RSA -keystore keystore.jks -storepass password 
-validity 360 -keysize 2048 -ext SAN=dns:localhost,ip:127.0.0.1

OR

keytool -genkey -alias   serverkey -keyalg RSA  -keystore serverkeystore.p12 -storetype PKCS12
-validity 360 -ext SAN=dns:localhost,ip:127.0.0.1

```

#### How to delete certificate entry from keystore
```
keytool -delete  -alias compressCer  -keystore  "<keystore path>"
```


#### Show list of certificates
```
keytool -list -keystore "<Path of keystore>"
```

#### Import certificate in keystore
```
keytool -import -trustcacerts -keystore "<key store path>" -storepass changeit -alias myCert 
-import -file "<cert file path>"
```

#### Create your own CA
```
openssl req -new -x509 -keyout ca-key -out ca-cert -days 365

Output  ca-key (Private key) and ca-cert (Public Key) two file will be generated

or san

openssl req -new -x509 -keyout ca-key -out ca-cert -days 365  -extensions req_ext -config <( cat ssl.conf )


```

#### Get certifcate file from keystore
```
keytool -keystore keystore.jks -alias localhost -certreq -file cert-file
```

#### Signe Certificate with CA
```
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-file -out cert-signed -days {validity} 
-CAcreateserial -passin pass:{ca-password}
```

#### Import signed certificate in keystore
```
keytool -keystore keystore.jks -alias CARoot -import -file ca-cert
keytool -keystore keystore.jks -alias localhost -import -file cert-signed
```
#### Import CA certificate in keystore
keytool -keystore server.truststore.jks -alias CARoot -import -file ca-cert


### Useful Link
```
https://docs.confluent.io/2.0.1/kafka/ssl.html
```

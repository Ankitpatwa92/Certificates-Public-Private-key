#Generate self signed certificate using open ssl

##### 1)Generate PEM file and Certificate Request file
 ```
 openssl req -config openssl.cnf -new  -out msa.csr  -keyout msa.pem
 ```


##### 2)Convert pem file to Key(Private Key) file 
```
openssl rsa -in msa.pem -out msa.key
```

##### 3)Sign Csr file with private key and generate crt file
```
openssl x509 -in msa.csr  -out  htv.crt -req -signkey htv.key -days 3650
```



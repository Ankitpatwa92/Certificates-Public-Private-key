#Generate self signed certificate using open ssl

##### openssl req -config openssl.cnf -new  -out msa.csr  -keyout msa.pem

##### openssl x509 -in msa.csr  -out  htv.crt -req -signkey htv.key -days 3650

##### openssl rsa -in msa.pem -out msa.key

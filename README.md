# spring-cloud-eco-system


* Create the docker image and the associated roles:




    docker run --detach --name postgresdb -p 5432:5432 --env POSTGRES_PASSWORD=password postgres
    docker exec --interactive --tty postgresdb /bin/bash
        psql -h localhost -p 5432 -d postgres -U postgres
            \l
            create database prod_db;
            create database dev_db;
            create database qa_db;
            create user prod_user with encrypted password 'prod_user_password';
            create user dev_user with encrypted password 'dev_user_password';
            create user qa_user with encrypted password 'qa_user_password';
            grant all privileges on database prod_db  to prod_user;
            grant all privileges on database dev_db  to dev_user;
            grant all privileges on database qa_db  to qa_user;

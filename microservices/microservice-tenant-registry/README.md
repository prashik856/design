# Microservice Tenant Registry

## Read application.yaml file
```shell
# Follow this link to see how we can read yaml file:
https://www.baeldung.com/jackson-yaml
```

## DB - MYSQL
How to start DB locally
```shell
# Install mysql community version first
# from here https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/macos-installation-pkg.html

# Once mysql is installed, it should start automatically on startup.
# mysql is usually installed in this directory: 
# /usr/local/mysql-8.0.33-macos13-arm64/bin

# 1. Add the above path in path first
vim ~/.zshrc
# Add this line in this file
export PATH=/Users/prraut/Downloads/apache-maven-3.8.6/bin:$PATH
# Save it and run it
source ~/.zshrc

# 2. Initialize data directory first
# Follow this guide: https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/macos-installation-launchd.html
# For initializing data directory, we need to provide root credentials. REMEMBER THEM.
# By default, data directory value is this: /usr/local/mysql-8.0.33-macos13-arm64/data

# 3. Start the server by following this guide
# https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/macos-installation-launchd.html
# Run these commands to check if server is actually running
mysqladmin -u root -p version
mysqladmin variables -u root -p

# 4. Assign proper permissions for accessing data directory
sudo chown -R prraut /usr/local/mysql-8.0.33-macos13-arm64/data
sudo chgrp -R localaccounts /usr/local/mysql-8.0.33-macos13-arm64/data
sudo chgrp -R staff /usr/local/mysql-8.0.33-macos13-arm64/data
sudo chgrp -R everyone /usr/local/mysql-8.0.33-macos13-arm64/data

sudo chown -R prraut /usr/local/mysql/data
sudo chgrp -R localaccounts /usr/local/mysql/data
sudo chgrp -R staff /usr/local/mysql/data
sudo chgrp -R everyone /usr/local/mysql/data

# 5. Stop the server and start the server without root
mysqladmin -u root -p shutdown
mysqld_safe --user=mysql &
# To check logs, run this command
tail -f tail -f /usr/local/mysql-8.0.33-macos13-arm64/data/prraut-mac.err

# 6. To check if we can start the command line tool, use this command
mysql -u root -p
# Expected Output
prraut@prraut-mac ~ % mysql -u root -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 8.0.33 MySQL Community Server - GPL

Copyright (c) 2000, 2023, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> 


# 7. Add the above command as an alias for ease
vim ~/.zshrc
# Add this line
alias startdb="mysqld_safe --user=mysql &"
# Save
source ~/.zshrc
```


## Create user for Microservice-Tenant-Registry
```shell
# Login as root user
mysql -u root -p
```
```mysql
# Create User
CREATE USER 'microservice_tenant_registry'@'localhost' IDENTIFIED BY 'Oracle@123';

# Grant Permissions
GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'microservice_tenant_registry'@'localhost' WITH GRANT OPTION;

# Flush Privileges
FLUSH PRIVILEGES;

# Commit
commit;

# exit from mysql
```
```shell
# Login using newly created User
mysql -u microservice_tenant_registry -p
```

## Create Required tables for Microservice User
```mysql
# Run the statements from src/database/resources/db/migration/V1_0__Initialize_table.sql file
```

## Connect to mysql using Connector
```shell
# First we need to download the mysql connector jar file.
# The jar file is present here: https://dev.mysql.com/downloads/connector/j/
# We need the connector jar file as Platform Independent. Download the zip file.
# Unzip the zip file, and add the jar file in lib directory of project.
# Use this sample code to connect to mysql db: https://www.geeksforgeeks.org/java-database-connectivity-with-mysql/

# To use this file, we need to make some changes in our build.gradle file:
# Follow this link for more: https://www.baeldung.com/gradle-dependencies-local-jar
```

```groovy
// Include directory of jar file in repositories
repositories {
    mavenCentral()
    flatDir {
        dirs 'lib'
    }
}

// include the jar name in dependencies
dependencies {
    implementation name: 'mysql-connector-j-8.0.33'
}
```
Test and build, we should now be able to use the mysql connector driver.

Another approach to tackle this is to directly use mysql connector as a dependency.
It is much easier.
```groovy
implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.33'
```
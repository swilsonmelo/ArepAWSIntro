# ArepAWSIntro

Comandos EC2

// instalar java 1.8 y eliminar java 1.7
sudo yum install java-1.8.0
sudo yum install java-1.8.0-openjdk-devel
sudo yum remove java-1.7.0-openjdk

// instalar maven, pero este maven ejecuta en java 1.7
sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn –version

// cambiar la version de estos comandos para ejecutar java
sudo /usr/sbin/alternatives –config java
sudo /usr/sbin/alternatives –config javac

// installar Git
sudo yum install git

// Crear la carpeta y clonar el repositorio
mkdir client
git clone https://github.com/swilsonmelo/ArepAWSIntro.git

// Ejecutar el cliente con 5 hilos
mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.client.App" -Dexec.args="https://arepawsintro-swilsonmelo.herokuapp.com/index.html 10"


Ejecutar

mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.client.App" -Dexec.args="https://arepawsintro-swilsonmelo.herokuapp.com/index.html 10"

java -jar ArepAWSIntro-1.0-SNAPSHOT.jar https://arepawsintro-swilsonmelo.herokuapp.com/index.html 10
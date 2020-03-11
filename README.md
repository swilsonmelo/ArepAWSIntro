# Introduction to AWS

# Server

This application implements a web server created with java that supports multiple consecutive requests and returns the requested files, including html pages and images.

The application is deployed here: [Heroku deployment](https://arepawsintro-swilsonmelo.herokuapp.com/index.html)

The web server has these resources:

* /index.html
* /index2.html
* /java.png
* /spark.png
* /spring.png
* /eclipse.png
* /vsCode.png
* /netbeans.png


[![CircleCI](https://circleci.com/gh/swilsonmelo/ArepAWSIntro.svg?style=svg)](https://circleci.com/gh/swilsonmelo/ArepAWSIntro)

## Running locally

Clone the repository.

    git clone https://github.com/swilsonmelo/ArepAWSIntro.git

Compile first time the project after downloaded.

    mvn clean install
    mvn package

Make unit tests.

    mvn test

## Running sever locally

To run the server instance.

    mvn exec:java -D "exec.mainClass"="edu.escuelaing.arep.server.App"        

When you are running the server go to [http://localhost:4567/index.html](http://localhost:4567/index.html)


## Running the client

if you want to test the client with the local server you must first raise an instance of the server.

To run the client instance.

    mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.client.App" -Dexec.args="http://localhost:4567/<resource> <numThreads>"

Examples:

resource = index.html
numThreads = 10

    mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.client.App" -Dexec.args="http://localhost:4567/index.html 10"

if you want to test the client with the heroku server just change the url in the args.

    mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.client.App" -Dexec.args="https://arepawsintro-swilsonmelo.herokuapp.com/index.html 10"

## Generate documentation.

In order to obtain the documentation of the project, you must execute the command:

    mvn javadoc:jar

An HTML documentation will be generated in /target/site/apidocs/index.html.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management.
* [Heroku](https://www.heroku.com/) - A cloud platform that lets companies build, deliver, monitor and scale apps
* [CircleCi](https://circleci.com/) - Cloud-native continuous integration

## Author

* **Willson Sneitder Melo Merchan** - Escuela Colombiana de Ingeniería Julio Garavito.

## License

* This project is under GNU General Public License - see [LICENSE](https://github.com/swilsonmelo/ArepAWSIntro/blob/master/LICENSE) to more info.

Name         : Shreyoshi Mahato
Project      : Project 5 (Airline)

CMD usage 1  : java -jar target/airline-client.jar -README
CMD usage 2  : java -jar target/airline-client.jar -host localhost -port 8080 "Air Express" 1 PDX 10/10/2020 10:10 pm LAX 10/10/2020 11:10 pm
CMD usage 3  : java -jar target/airline-client.jar -host localhost -port 8080 -print "Air Express" 1 PDX 10/10/2020 10:10 pm LAX 10/10/2020 11:10 pm
CMD usage 4  : java -jar target/airline-client.jar -host localhost -port 8080 -search "Air Express"
CMD usage 5  : java -jar target/airline-client.jar -host localhost -port 8080 -search "Air Express" PDX LAX


Description  : This program creates an Airline and Flight using command line arguments, adds the Flight to the Airline.
               Additionally, it prints the Flight's description or the README based on command line options.

               If -README is present on the command line, then the program just prints the README for this project and exits.
               If -print option is absent, the program prints nothing, but still adds the flight to the airline.
               If -print is present, then the program prints the newly added Flight's description.
               If -search is present, then the program searches for the Flight in the Airline and prints the Flight's description.

               Below command line arguments should be provided as in the order specified :
               airline name, flight number, source, departure datetime(mm/dd/yyyy hh:mm a), destination, arrival datetime(mm/dd/yyyy hh:mm a).

GitHub User  : smah29
GitHub Email : shreyoshimahato@gmail.com
PSU Email    : smahato@pdx.edu




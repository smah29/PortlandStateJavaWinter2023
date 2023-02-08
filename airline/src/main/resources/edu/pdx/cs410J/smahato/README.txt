Name         : Shreyoshi Mahato
Project      : Project 3 (Airline)

CMD usage 1  : java -jar target/airline-2023.0.0.jar -pretty file -textFile file -print "Air Express" 1 PDX 10/10/2020 10:10 am LAX 10/10/2020 11:10 am
CMD usage 2  : java -jar target/airline-2023.0.0.jar -print -textFile file.txt "Air Express" 1 PDX 10/10/2020 10:10 am LAX 10/10/2020 11:10 am
CMD usage 3  : java -jar target/airline-2023.0.0.jar -print "Air Express" 1 PDX 10/10/2020 10:10 pm LAX 10/10/2020 11:10 pm
CMD usage 4  : java -jar target/airline-2023.0.0.jar "Air Express" 1 PDX 10/10/2020 10:10 pm LAX 10/10/2020 11:10 pm
CMD usage 5  : java -jar target/airline-2023.0.0.jar -README

Description  : This program creates an Airline and Flight using command line arguments, adds the Flight to the Airline.
               Additionally, it prints the Flight's description or the README based on command line options.

               Options like -print and -textFile can be in any order.
               If -README is present on the command line, then the program just prints the README for this project and exits.
               If both -print or -README options are absent, the program prints nothing.
               If -print is present on the command line, then the program prints the Flight's description.
               -textFile file.txt defines where the location where all flights of an airline are stored.

               Below command line arguments should be provided as in the order specified :
               airline name, flight number, source, departure datetime(mm/dd/yyyy hh:mm a), destination, arrival datetime(mm/dd/yyyy hh:mm a).

GitHub User  : smah29
GitHub Email : shreyoshimahato@gmail.com
PSU Email    : smahato@pdx.edu




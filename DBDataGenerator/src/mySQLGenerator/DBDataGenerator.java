package mySQLGenerator;


/**
 *
 * @author Vasco Craveiro Costa
 */
public class DBDataGenerator {

    public static void main(String[] args) {
        //DBInterface inter = new DBInterface();
        //inter.generateUsers(2500);
        //inter.generateSimpleUsers(10000);
        new DBInterface(1000);
    }
}

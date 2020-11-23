package pl.robocikd.prepared

import spock.lang.Specification
import spock.lang.Unroll

class MySqlConnectorTest extends Specification {

    def "should create schema "() {
        given:
        MySqlConnector mySqlConnector = new MySqlConnector()
        when:
        mySqlConnector.createDB()
        then:
        true
    }

    def "should create table "() {
        given:
        MySqlConnector mySqlConnector = new MySqlConnector()
        when:
        mySqlConnector.createTable()
        then:
        true
    }

    @Unroll
    def "should insert data "() {
        given:
        MySqlConnector mySqlConnector = new MySqlConnector()
        when:
        mySqlConnector.insertData(id, first, last, age)
        then:
        true
        where:
        id | first     | last      | age
        2  | "Damian"   | "Pasierbiewicz" | "18"
        3  | "Pasierbiewicz" | "Damian"  | "28"

    }

    def "should insert prepared statement data "() {
        given:
        MySqlConnector mySqlConnector = new MySqlConnector()
        when:
        mySqlConnector.insertPreparedStatementData(id, first, last, age)
        then:
        true
        where:
        id | first     | last      | age
        4  | "RobocikD" | "RobocikD"    | "150"
        5  | "John"   | "Barvo" | "30"

    }

    def "should get data from DB"() {
        given:
        MySqlConnector mySqlConnector = new MySqlConnector()
        when:
        List<Person> result = mySqlConnector.getDataFromDb("HelloWorld")
        then:
        for (Person e : result) {
            println e
        }
        true
    }
}

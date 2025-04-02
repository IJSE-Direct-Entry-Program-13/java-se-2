package lk.ijse.dep13.jdbc.factory;

public class FactoryMethod {

    public static void main(String[] args) {
        Statement statement = Connection.createStatement();
        statement.execute();
    }

}


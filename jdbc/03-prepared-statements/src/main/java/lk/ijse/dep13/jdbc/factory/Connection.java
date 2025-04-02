package lk.ijse.dep13.jdbc.factory;

public interface Connection {
    public static Statement createStatement() {
        return new PostgresStatementImpl2();
    }
}
interface Statement{
    void execute();
}
class PostgresStatementImpl implements Statement{
    @Override
    public void execute() {
        System.out.println("Postgres");
    }
}
class PostgresStatementImpl2 implements Statement{
    @Override
    public void execute() {
        System.out.println("Postgres:New");
    }
}

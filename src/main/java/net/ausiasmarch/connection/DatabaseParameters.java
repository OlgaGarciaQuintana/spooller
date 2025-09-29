package net.ausiasmarch.connection;

public class DatabaseParameters {
    private static final String dbUrl ="jdbc:mysql://localhost:3311/spooller?useSSL=false&serverTimezone=UTC";
    private static final String dbUser ="root";
    private static final String dbPassword ="";

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPassword() {
        return dbPassword;
    }
}

package jsm.timekeeping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;



public class TimeKeeping extends AppCompatActivity {
    Connection c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Statement stmt = null;
        setContentView(R.layout.activity_time_keeping);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Creates DB if it does not already exist and sets connection c to that db
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JSM.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //creates tables if they do not already exist
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JSM.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SignedIN " +
                    "(SIGNINID INT PRIMARY KEY     NOT NULL AUTOINCREMENT," +
                    " EMPID           INT    NOT NULL, " +
                    " TIME         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void punchIn(View view) {
        final EditText mEdit   = (EditText)findViewById(R.id.employeeID);
        int empID = Integer.parseInt(mEdit.getText().toString());
    }

    public void punchOut(View view) {
        EditText mEdit   = (EditText)findViewById(R.id.employeeID);
        /*boolean success = sql("out", mEdit.getText().toString());
        if(success)
            mEdit.setText("Success");
        else
            mEdit.setText("Error");    */}
}

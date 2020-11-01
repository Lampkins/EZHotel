package stu.lcr.eztravel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navView.setOnNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();
            if (item.isChecked())
                return false;
            navController.popBackStack();
            if (itemID == R.id.nav_home)
                navController.navigate(R.id.nav_home);
            if (itemID == R.id.nav_recommend)
                navController.navigate(R.id.nav_recommend);
            if (itemID == R.id.nav_mine)
                navController.navigate(R.id.nav_mine);
            return true;
        });
    }
}

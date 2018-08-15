package zp.olehliskovych.phoneinput;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zp.olehliskovych.phoneinput.phone_field.PhoneInputEditText;
import zp.olehliskovych.phoneinput.phone_field.adapter.BasePhoneFieldAdapter;
import zp.olehliskovych.phoneinput.phone_field.adapter.PhoneFieldAdapter;
import zp.olehliskovych.phoneinput.phone_field.phones_utils.PhonesInfoDataSource;
import zp.olehliskovych.phoneinput.phone_field.phones_utils.PhonesInfoProxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhoneInputEditText phoneInputEditText = findViewById(R.id.phone_field);
        PhonesInfoDataSource dataSource = new PhonesInfoProxy(this);
        BasePhoneFieldAdapter adapter = new PhoneFieldAdapter(this, R.layout.phone_field_dropdown_item, dataSource.getData());
        adapter.setCommunicator(phoneInputEditText);
        phoneInputEditText.setAdapter(adapter);

    }

}

package zp.olehliskovych.phoneinput;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import zp.olehliskovych.phoneinput.phone_field.phones_utils.storage.PhonesInfoStorage;
import zp.olehliskovych.phoneinput.phone_field.phones_utils.storage.PhonesInfoStorageImpl;

public class MyApplication extends Application {

    public static MyApplication getApplication(@NonNull Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public PhonesInfoStorage phonesInfoStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        phonesInfoStorage = PhonesInfoStorageImpl.getInstance();
    }
}

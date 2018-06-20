package us.powercode.design;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import us.powercode.design.phone_field.phones_utils.storage.PhonesInfoStorage;
import us.powercode.design.phone_field.phones_utils.storage.PhonesInfoStorageImpl;

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

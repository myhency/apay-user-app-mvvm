package com.autoever.apay_user_app.ui.main.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;

import androidx.annotation.Nullable;

import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ui.main.settings.modify.ModifyEasyPasswordActivity;
import com.autoever.apay_user_app.ui.main.settings.register.RegisterEasyPasswordActivity;

public class SettingsActivity extends PreferenceActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        private Preference registerEasyPassPreference;
        private Preference modifyEasyPassPreference;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
            registerEasyPassPreference = (Preference) findPreference("register_easy_pass");
            modifyEasyPassPreference = (Preference) findPreference("modify_easy_pass");

            registerEasyPassPreference.setOnPreferenceClickListener(preference -> {
                openRegisterEasyPasswordActivity();
                return false;
            });

            modifyEasyPassPreference.setOnPreferenceClickListener(preference -> {
                openModifyEasyPasswordActivity();
                return false;
            });
        }

        private void openModifyEasyPasswordActivity() {
            Log.d("debug", "openModifyEasyPasswordActivity");
            Intent intent = ModifyEasyPasswordActivity.newIntent(getContext());
            startActivity(intent);
        }

        private void openRegisterEasyPasswordActivity() {
            Log.d("debug", "openRegisterEasyPasswordActivity");
            Intent intent = RegisterEasyPasswordActivity.newIntent(getContext());
            startActivity(intent);
        }


    }
}
/*
  * This file is part of HyperCeiler.

  * HyperCeiler is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as
  * published by the Free Software Foundation, either version 3 of the
  * License.

  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.

  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <https://www.gnu.org/licenses/>.

  * Copyright (C) 2023-2024 HyperCeiler Contributions
*/
package com.sevtinge.hyperceiler.ui.fragment.home;

import static com.sevtinge.hyperceiler.utils.api.VoyagerApisKt.isPad;
import static com.sevtinge.hyperceiler.utils.devicesdk.SystemSDKKt.isMoreAndroidVersion;

import android.content.Intent;
import android.view.View;

import com.sevtinge.hyperceiler.R;
import com.sevtinge.hyperceiler.ui.SubPickerActivity;
import com.sevtinge.hyperceiler.ui.base.BaseSettingsActivity;
import com.sevtinge.hyperceiler.ui.fragment.base.SettingsPreferenceFragment;
import com.sevtinge.hyperceiler.ui.fragment.sub.AppPicker;

import moralnorm.preference.Preference;
import moralnorm.preference.SwitchPreference;

public class HomeTitleSettings extends SettingsPreferenceFragment {

    SwitchPreference mDisableMonoChrome;
    SwitchPreference mDisableMonetColor;
    SwitchPreference mDisableHideTheme;
    Preference mIconTitleCustomization;

    @Override
    public int getContentResId() {
        return R.xml.home_title;
    }

    @Override
    public View.OnClickListener addRestartListener() {
        return view -> ((BaseSettingsActivity) getActivity()).showRestartDialog(
            getResources().getString(R.string.mihome),
            "com.miui.home"
        );
    }

    @Override
    public void initPrefs() {
        mIconTitleCustomization = findPreference("prefs_key_home_title_title_icontitlecustomization");
        mDisableMonoChrome = findPreference("prefs_key_home_other_icon_mono_chrome");
        mDisableMonoChrome.setVisible(isMoreAndroidVersion(33));
        mDisableMonoChrome.setOnPreferenceChangeListener((preference, o) -> true);
        mDisableMonetColor = findPreference("prefs_key_home_other_icon_monet_color");
        mDisableMonetColor.setVisible(isMoreAndroidVersion(33));
        mDisableMonetColor.setOnPreferenceChangeListener((preference, o) -> true);
        mDisableHideTheme = findPreference("prefs_key_home_title_disable_hide_theme");
        mDisableHideTheme.setVisible(isPad());
        mIconTitleCustomization.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity(), SubPickerActivity.class);
            intent.putExtra("mode", AppPicker.INPUT_MODE);
            intent.putExtra("key", preference.getKey());
            startActivity(intent);
            return true;
        });
    }
}

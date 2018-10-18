package br.com.digitalhouse.digitalhouseapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SectionsPageAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragmentList;

	public SectionsPageAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragmentList = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}
}

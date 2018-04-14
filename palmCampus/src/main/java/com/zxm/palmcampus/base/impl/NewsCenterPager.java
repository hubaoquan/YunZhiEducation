package com.zxm.palmcampus.base.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zxm.palmcampus.activity.MainActivity;
import com.zxm.palmcampus.base.BaseMenuDetailPager;
import com.zxm.palmcampus.base.BasePager;
import com.zxm.palmcampus.base.menudetail.NewsMenuDetailPager;
import com.zxm.palmcampus.base.menudetail.PhotoMenuDetailPager;
import com.zxm.palmcampus.base.menudetail.TopicMenuDetailPager;
import com.zxm.palmcampus.domain.NewsData;
import com.zxm.palmcampus.domain.NewsData.NewsMenuData;
import com.zxm.palmcampus.fragment.LeftMenuFragment;
import com.zxm.palmcampus.global.GlobalContants;

public class NewsCenterPager extends BasePager {
	
	private NewsData mNewsData;
	private ArrayList<BaseMenuDetailPager> mPagers;
	public NewsCenterPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("初始化首页数据....");

		tvTitle.setText("课程资源");
		setSlidingMenuEnable(true);

		// String cache =

		// 加载新闻界面的侧边栏数据
		getDataFromServer();// 不管有没有缓存, 都获取最新数据, 保证数据最新
	}
	/**
	 * 从服务器获取数据
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();

		// 使用xutils发送请求
		utils.send(HttpMethod.GET, GlobalContants.CATEGORIES_URL,
				new RequestCallBack<String>() {
					// 访问成功
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo){
						String result = (String) responseInfo.result;
						System.out.println("返回结果:" + result);
						// 解析Gson数据
						parseData(result);
					}

					// 访问失败
					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
								.show();
						System.out.println("连接失败");
						error.printStackTrace();
					}
				});
	}
	/**
	 * 解析网络数据
	 * 
	 * @param result
	 */
	protected void parseData(String result){
		Gson gson = new Gson();
		mNewsData = gson.fromJson(result, NewsData.class);
		System.out.println("解析结果:" + mNewsData);
		
		//刷新侧边栏数据
		MainActivity mainUi = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
		leftMenuFragment.setMenuData(mNewsData);
		
		//准备3个菜单详情页
		mPagers = new ArrayList<BaseMenuDetailPager>();
		mPagers.add(new NewsMenuDetailPager(mActivity, mNewsData.data.get(0).children));
		mPagers.add(new PhotoMenuDetailPager(mActivity, btnPhoto));
		mPagers.add(new TopicMenuDetailPager(mActivity));
		
		
		setCurrentMenuDetailPager(0);// 设置菜单详情页-新闻为默认当前页

		
	}
	
	/**
	 * 设置当前菜单详情页
	 */
	public void setCurrentMenuDetailPager(int position){
		System.out.println("运行着了————————");
		BaseMenuDetailPager pager = mPagers.get(position);//获取当前要显示的菜单详情页
		flContent.removeAllViews();//清除之前的布局
		flContent.addView(pager.mRootView);
		
		//设置当前页的标题
		NewsMenuData menuData = mNewsData.data.get(position);
		tvTitle.setText(menuData.title);
		
		//初始化NewsMenuDetailPager页面
		pager.initDate();//初始化当前的页面数据
		
		if(pager instanceof PhotoMenuDetailPager){
			btnPhoto.setVisibility(View.VISIBLE);
		}else{
			btnPhoto.setVisibility(View.GONE);
		}
	}

}
